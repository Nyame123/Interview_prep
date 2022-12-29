package com.bis.interview_prep.ordinal.twoPointer;
/**
 * You are given an integer array height of length n. There are n vertical
 * lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 *
 * Notice that you may not slant the container.
 *
 * Example 1:
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water (blue section) the container can contain is 49.
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 **/
public class ContainerWIthMostWater {

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        int res = maxArea(height);
        System.out.println(res);
    }

    //Time Complexity = O(N)
    static int maxArea(int[] height) {
        int n = height.length;
        int res = 0;
        int left = 0; int right = n-1;

        while(left < right){
            int area = (right-left)*Math.min(height[left],height[right]);
            res = Math.max(res,area);

            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }

        return res;
    }

    //Time Complexity = O(N^2)
    public int maxArea1(int[] height) {
        int n = height.length;
        int res = 0;

        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n; j++){
                int area = (j-i)*Math.min(height[i],height[j]);
                res = Math.max(res,area);
            }
        }

        return res;
    }
}
