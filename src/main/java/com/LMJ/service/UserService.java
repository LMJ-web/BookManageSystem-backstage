package com.LMJ.service;

import com.LMJ.pojo.User;

import java.util.List;

public interface UserService {
    User findUserByName(String user_name);
    List<User> findUsersByCondition(User user,Integer pageNum,Integer pageSize);
    Integer calculateUsersByCondition(User user);
    Boolean removeUserById(Integer id);
    Boolean addUser(User user);
    Boolean editUserById(User user);
}
