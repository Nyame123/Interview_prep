package com.bis.interview_prep.combinatoricsGameTheory.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Given an array digits that represents a non-negative integer, add one to the number and return the result as an array.
 *
 * Ex: Given the following digits...
 *
 * digits = [1, 2], return [1, 3].
 * Ex: Given the following digits...
 *
 * digits = [9, 9], return [1, 0, 0].
 *
 **/
public class AddOneToArray {

    public static void main(String[] args) {
        int[] nums = {9, 9};
        int[] ans = addOne(nums);
        System.out.println(Arrays.toString(ans));
    }

    /**
     * In this problem, we just add one to the last elements in the array.
     * 1. When after adding one and the element is no more digits, then we send the carry to
     * the next element on the left of it.
     **/
    private static int[] addOne(int[] digits) {
        List<Integer> result = new ArrayList<>();
        int n = digits.length;

        if(n == 0){
            return new int[1];
        }
        int ans = (digits[n-1]+1) % 10;
        int carry = (digits[n-1]+1) / 10;
        result.add(ans);

        for(int i = n-2; i >= 0; i--){
            int it = digits[i]+carry;
            ans = it % 10;
            carry = it / 10;
            result.add(ans);
        }

        if(carry != 0){
            result.add(carry);
        }

        int[] resArr = new int[result.size()];
        n = resArr.length;
        int index = 0;
        for(int i = n-1; i >= 0; i--){
            resArr[index++] = result.get(i);
        }

        return resArr;
    }
}
