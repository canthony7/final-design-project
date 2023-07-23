package com.crud.utils;

import java.util.Random;

public class RandomNumberUtils {

    public static String generateRandomNumber(){
        Random randomGenerator = new Random();
        StringBuilder sb = new StringBuilder();
        int randomNum = randomGenerator.nextInt(100000000);
        sb.append(String.format("%08d", randomNum));
        String randomString = sb.toString();
        return randomString;
    }

}
