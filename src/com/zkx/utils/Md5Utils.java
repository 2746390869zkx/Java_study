package com.zkx.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author zkx
 */

public class Md5Utils {
    public static String md5(String str){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md5.digest();
            return new BigInteger(1,digest).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

