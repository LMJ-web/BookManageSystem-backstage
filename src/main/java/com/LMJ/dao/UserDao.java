package com.LMJ.dao;

import com.LMJ.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    @Select("select * from tb_user where user_name=#{user_name}")
    User selectUserByName(String user_name);
    List<User> selectUsersByCondition(@Param("user") User user, @Param("beginNum") Integer beginNum, @Param("pageSize") Integer pageSize);
    Integer countUsersByCondition(@Param("user") User user);
    @Delete("delete from tb_user where id=#{id}")
    int deleteUserById(Integer id);
    Integer insertUser(@Param("user") User user);
    Integer updateUserById(@Param("user")User user);
}
