package com.ithiema.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * \* Created with IntelliJ IDEA.
 * \* @Author: 吴将
 * \* Time: 2020/7/7 11:58
 * \* Description:
 * \
 */
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
     public  static String encodingPassword(String password){
         return bCryptPasswordEncoder.encode(password);
     }

    public static void main(String[] args) {
        String password="123";
        String pwd=encodingPassword(password);
        //$2a$10$WBLOrvy4pqmO3n6ZXi9DT.u12VF09jeIeVaVhsKJ6QqGRop7yAFbS

        System.out.println(pwd);
    }
}
