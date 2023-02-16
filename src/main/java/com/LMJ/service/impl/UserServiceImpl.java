package com.LMJ.service.impl;

import com.LMJ.dao.UserDao;
import com.LMJ.pojo.User;
import com.LMJ.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User findUserByName(String user_name) {
        User user = userDao.selectUserByName(user_name);
        return user;
    }

    @Override
    public List<User> findUsersByCondition(User user,Integer pageNum,Integer pageSize) {
        if (user.getUser_name()!=null){
            user.setUser_name("%"+user.getUser_name()+"%");
        }
        if (user.getUser_role()!=null){
            user.setUser_role("%"+user.getUser_role()+"%");
        }
        Integer beginNum=(pageNum-1)*pageSize;
        List<User> users = userDao.selectUsersByCondition(user, beginNum, pageSize);
        return users;
    }

    @Override
    public Integer calculateUsersByCondition(User user) {
        Integer integer = userDao.countUsersByCondition(user);
        return integer;
    }

    @Override
    public Boolean removeUserById(Integer id) {
        int i = userDao.deleteUserById(id);
        System.out.println(i);
        if (i>0){
            return true;
        }else return false;
    }

    @Override
    public Boolean addUser(User user) {
        Integer i = userDao.insertUser(user);
        if(i>0){return true;}else return false;
    }

    @Override
    public Boolean editUserById(User user) {
        Integer i = userDao.updateUserById(user);
        if(i>0){return true;}else return false;
    }
}
