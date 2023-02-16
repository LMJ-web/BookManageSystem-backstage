package com.LMJ.controller;

import com.LMJ.pojo.Code;
import com.LMJ.pojo.Result;
import com.LMJ.pojo.User;
import com.LMJ.service.UserService;
import com.LMJ.utils.JWTUtils;
import com.LMJ.utils.SHAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        Result result;
        String user_name = user.getUser_name();
        User db_user = userService.findUserByName(user_name);
        if(db_user!=null){
            String user_password = user.getUser_password();
            String sha = SHAUtil.getSHA(user_password);
            String db_user_password = db_user.getUser_password();
            if(db_user_password.equals(sha)){
                String token = JWTUtils.getToken(user_name);
                result = new Result(token,Code.LOGIN_OK, user_name);
            }else {
                result = new Result(Code.LOGIN_ERR, "账号或密码错误！");
            }
        }else {
            result = new Result(Code.LOGIN_ERR, "无该用户!");
        }
        return result;
    }
    @GetMapping
    public Result findByCondition(User user,Integer pageNum,Integer pageSize){
        List<User> users = userService.findUsersByCondition(user, pageNum, pageSize);
        Result result;
        if (users!=null){
            Integer total = userService.calculateUsersByCondition(user);
            result = new Result(users,total, Code.Query_OK, "查询成功！");
        }else {
            result = new Result(Code.Query_ERR, "查询失败！");
        }
        return result;
    }
    @DeleteMapping("/{id}")
    public Result RemoveById(@PathVariable Integer id){
        Boolean aBoolean = userService.removeUserById(id);
        Result result;
        if (aBoolean){
            result = new Result(Code.REMOVE_OK, "删除成功！");
        }else {
            result = new Result(Code.REMOVE_ERR, "删除失败！");
        }
        return result;
    }
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        Result result;
            User userName = userService.findUserByName(user.getUser_name());
            if(userName!=null){
               result = new Result(Code.ADD_ERR,"用户名已存在");
            }else {
                Boolean aBoolean = userService.addUser(user);
                if(aBoolean){
                    result = new Result(Code.ADD_OK, "添加成功！");
                }else {
                    result = new Result(Code.ADD_ERR, "添加失败！");
                }
            }
            return result;
    }
    @PutMapping
    public Result editById(@RequestBody User user){
        Result result;
        Boolean aBoolean = userService.editUserById(user);
        if(aBoolean){
            result = new Result(Code.CHANGE_OK,"修改成功！");
        }else result = new Result(Code.CHANGE_ERR,"修改失败！");
        return result;
    }
    @GetMapping("/checkToken")
    public Boolean checkToken(@RequestHeader String Authorization){
        boolean b = JWTUtils.checkToken(Authorization);
        return b;
    }
}
