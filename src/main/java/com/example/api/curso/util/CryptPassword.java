package com.example.api.curso.util;

import java.security.MessageDigest;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class CryptPassword {

    public String md5(String input) {

        String md5 = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}