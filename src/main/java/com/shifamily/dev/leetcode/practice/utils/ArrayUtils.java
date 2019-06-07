package com.shifamily.dev.leetcode.practice.utils;

import java.util.Random;

public class ArrayUtils {


    public static int[] createRandomArray(int len){
        Random random = new Random();
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = random.nextInt();
        }
        return result;

    }
}
