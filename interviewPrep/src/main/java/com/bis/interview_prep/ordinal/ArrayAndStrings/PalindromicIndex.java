package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class PalindromicIndex {

    public static void main(String[] args) {
        String s = "hgygsvlfcwnswtuhmyaljkqlqjjqlqkjlaymhutwsnwcwflvsgygh";
        int index = palindromicIndex(s);
        System.out.println(index);
    }

    //Time Complexity = O(n^2)
    public static int palindromicIndex(String s){
        //check if the string is palindromic
        int n = s.length();
        for (int i = 0,len = n-1; i < n/2; i++,len--) {
            if (s.charAt(i) != s.charAt(len)){
                return isPalindromic(s,i+1,len)? i:len;
            }
        }

        return -1;
    }

    //Time Complexity = O(n)
    public static boolean isPalindromic(String s,int left, int right){

        while (left <= right){
            if (s.charAt(left) == s.charAt(right)){
                left++;
                right--;
            }else{
                return false;
            }
        }

        return true;
    }

}
