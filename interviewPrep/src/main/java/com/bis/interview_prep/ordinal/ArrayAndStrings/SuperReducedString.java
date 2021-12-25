package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class SuperReducedString {

    public static void main(String[] args) {
        System.out.println(superReducedString("aaabccddd"));
    }
    public static String superReducedString(String st) {
        // Write your code here
        int n = st.length();

        int s=0,e = 0;
        StringBuilder sb = new StringBuilder(st);
        for(int i = 1; i < sb.length();){
            char cur = sb.charAt(i);
            s = e = i;
            if(i-1 >= 0 && cur == sb.charAt(i-1)){
                s = i-1;
                int diff = e-s+1;
                sb.delete(s,e+1);
                i -= diff;
                i = Math.max(i, 0);
            }else{
                i++;
            }

            //System.out.printf("Start %d and End %d\n",s,e);

        }

        return sb.toString().length() == 0? "Empty String":sb.toString();


    }

}
