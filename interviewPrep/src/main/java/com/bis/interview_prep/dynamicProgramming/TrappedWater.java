package com.bis.interview_prep.dynamicProgramming;

public class TrappedWater {

    public static void main(String[] args) {
       int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
       int res = trap(height);
       System.out.println(res);
    }

    static int trap(int[] height){
        //using pointers
        int n = height.length;
        if(n == 0){
            return 0;
        }

        int leftMax = height[0];
        int rightMax = height[n-1];
        int left = 0; int right = n-1;
        int res = 0;

        while(left < right){
            if(leftMax < rightMax){
                left++;
                res += Math.max(0,leftMax - height[left]);
                leftMax = Math.max(leftMax,height[left]);

            }else{
                right--;
                res += Math.max(0,rightMax - height[right]);
                rightMax = Math.max(rightMax,height[right]);
            }
        }

        return res;
    }



    public int trapOld(int[] height) {
        int n = height.length;
        if(n == 0){
            return 0;
        }

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        //fill the leftMax
        leftMax[0] = height[0];
        for(int i = 1; i < n; i++){
            leftMax[i] = Math.max(leftMax[i-1],height[i]);
        }

        rightMax[n-1] = height[n-1];
        for(int i = n-2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i+1],height[i]);
        }

        int res = 0;
        for(int i = 0; i < n; i++){
            int min = Math.min(leftMax[i],rightMax[i]);
            int trappedWater = min - height[i];
            if(trappedWater > 0){
                res += trappedWater;
            }
        }

        return res;
    }
}
