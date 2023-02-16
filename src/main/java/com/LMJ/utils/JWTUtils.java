package com.LMJ.utils;

import io.jsonwebtoken.*;

import java.util.UUID;

public class JWTUtils {
    private static String signature = "SecretKeyIsLMJ";

    public static String getToken(String user_name){
        JwtBuilder jwtBuilder = Jwts.builder();
        String token = jwtBuilder
                .setHeaderParam("type", "JWT")
                .setHeaderParam("alg","HS256")
                .claim("username",user_name)
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return token;
    }
    public static boolean checkToken(String token){
        if(token == null){
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(signature).parseClaimsJws(token);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
