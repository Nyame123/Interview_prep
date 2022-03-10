package com.bis.interview_prep.Random;

import java.util.Random;

public class Rand5ToRand7 {

    static Random random = new Random();


    public static void main(String[] args) {
        System.out.println(rand5());
    }

    static int rand7(){
        return random.nextInt(7) + 1;
    }

    static int rand5(){
        int s = 7*rand7() + rand7() - 7;
        if (s > 45){
            return rand5();
        }

        return s % 5 + 1;
    }
}
