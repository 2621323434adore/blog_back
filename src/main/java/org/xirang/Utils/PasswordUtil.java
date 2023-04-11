package org.xirang.Utils;

import cn.hutool.crypto.SecureUtil;

public class PasswordUtil {
    public static String encode(String password){
        return SecureUtil.md5(password);
    }
    public static Boolean verify(String password,String encode){
        if(encode.equals(encode(password))){
            return true;
        }
        return false;
    }
    public static String verifyToken(String token){
        String[] split = token.split(":");
        if(split.length == 0){
            return "";
        }
        return split[split.length-1];
    }
}
