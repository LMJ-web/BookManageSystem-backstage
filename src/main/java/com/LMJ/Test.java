package com.LMJ;

import com.LMJ.utils.JWTUtils;


public class Test {
    public static void main(String[] args){
        String token = JWTUtils.getToken("aabb");
        System.out.println(token);
    }
}
