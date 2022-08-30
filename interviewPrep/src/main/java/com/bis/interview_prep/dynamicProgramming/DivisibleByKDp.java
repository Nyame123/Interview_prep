package com.bis.interview_prep.dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of positive integers, nums, return the largest
 * sum we can create such that it is divisible by three.
 * <p>
 * Ex: Given the following nums…
 * <p>
 * nums = [3, 1, 5, 8, 2], return 18 (3 + 5 + 8 + 2).
 * Ex: Given the following nums…
 * <p>
 * nums = [2, 4, 9], return 15.
 **/
public class DivisibleByKDp {

    public static void main(String[] args) {
        int[] nums = {2, 4, 9, 5};
        int k = 3;
        int maxSum = maxSumDivisibleK(nums, k);
        System.out.println(maxSum);
    }

    /**
     * We try to find all possible combination that can give max sum
     * and divisible by k. We can achieve this in 2^n time complexity which is not
     * efficient. We decide to use dynamic programming strategy which decreases the
     * time complexity.
     **/
    static int maxSumDivisibleK(int[] nums, int k) {

        return maxSumDivisbleRec(0, nums, k, 0, new HashMap<>());
    }

    static int maxSumDivisbleRec(int index, int[] nums, int k, int rem, Map<String, Integer> map) {
        String key = String.format("%d,%d", rem, index);
        if (index >= nums.length) {
            return 0;
        }

        if (map.containsKey(key)) {
            return map.get(key);
        }

        int max = 0;

        //exclude the current item
        int exclude = maxSumDivisbleRec(index + 1, nums, k, rem, map);
        if ((rem + exclude) % k == 0) {
            max = exclude;
        }
        //include the current item
        int include = maxSumDivisbleRec(index + 1, nums, k, (rem + nums[index]) % k, map);
        if ((rem + nums[index] + include) % k == 0) {
            max = Math.max(max, nums[index] + include);
        }

        map.put(key, max);
        return max;
    }
}

class SolutionDiv {
    public int maxSumDivThree(int[] nums) {
        return maxSumDivK(nums, 3);
    }

    public int maxSumDivK(int[] nums, int k) {
        if (k == 0) return -1;
        int[] dp = new int[k];
        for (int num : nums) {
            int tmp[] = Arrays.copyOf(dp, k);
            for (int i = 0; i < k; i++) {
                int rem = (num + tmp[i]) % k;
                dp[rem] = Math.max(dp[rem], num + tmp[i]);
            }
        }
        return dp[0];
    }
}


class SolutionDivOnePass {

    public static void main(String[] args) {
        int[] nums = {3, 5, 8};
        int k = 3;
        int maxSum = maxSumDivThree(nums);
        System.out.println(maxSum);
    }

    /**
     * Best!!
     * How I understood it :
     * Is it the right way to think of it ??
     * <p>
     * leftOne: (if total sum originally of all the elements of nums) % 3 == 1,
     * then we need to subtract a number = leftOne to achieve sum%3 = 0,
     * leftTwo : (if total sum originally of all the elements of nums) % 3 == 2,
     * then we need to subtract a number = leftTwo to achieve sum%3 = 0,
     * <p>
     * Now, when we find a n from nums such that n % 3 == 2, updatation of
     * leftOne is obvious, but there might be case when there is no single
     * number available whose modulo gives 3, hence we also update leftOne
     * using the numbers which have modulo with 3 = 2, this is possible because,
     * two numbers having modulo 2 with 3 when added always produce a sum whose modulo with 3 will always be 1.
     * <p>
     * Same analogy for updating leftTwo everytime , we find a , n % 3 = 1.
     **/
    static int maxSumDivThree(int[] nums) {
        int res = 0, leftOne = 20000, leftTwo = 20000;
        for (int n : nums) {
            res += n;
            if (n % 3 == 1) {
                leftTwo = Math.min(leftTwo, leftOne + n);
                leftOne = Math.min(leftOne, n);
            }
            if (n % 3 == 2) {
                leftOne = Math.min(leftOne, leftTwo + n);
                leftTwo = Math.min(leftTwo, n);
            }
        }
        if (res % 3 == 0) return res;
        if (res % 3 == 1) return res - leftOne;
        return res - leftTwo;

    }
}
