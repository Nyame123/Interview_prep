package com.bis.interview_prep.greedy;
/**
 * Lexicographical order is often known as alphabetical order when dealing with strings.
 * A string is greater than another string if it comes later in a lexicographically sorted list.
 *
 * Given a word, create a new word by swapping some or all of its characters. This new word must meet two criteria:
 *
 * It must be greater than the original word
 * It must be the smallest word that meets the first condition
 *
 * Example; s = abcd; Output = abdc
 **/
public class GreaterIsBigger {


    public static void main(String[] args) {
        String s = "dkhc";
        String output = biggerIsGreaterWorking(s);
        System.out.println(output);
    }

    public static String biggerIsGreaterWorking(String str) {
        // Write your code here

        char[] chars = str.toCharArray();
        int i= chars.length-1;
        while(i>0){
            if(chars[i-1]>=chars[i]){
                i--;
            }else{
                int j=i;
                while(j<chars.length&&chars[j]>chars[i-1]){
                    j++;
                }
                char temp = chars[i-1];
                chars[i-1]=chars[j-1];
                chars[j-1]=temp;

                char[] newChar = new char[chars.length];
                for(int k=0;k<i;k++){
                    newChar[k]=chars[k];
                }
                int end = chars.length-1;
                for(int k=i;k<chars.length;k++){
                    newChar[k]=chars[end--];
                }
                return new String(newChar);
            }
        }
        return "no answer";
    }

    public static String biggerIsGreater(String w) {
        // Write your code here

        int n = w.length();
        char[] sChars = w.toCharArray();

        for (int i = n-1; i > 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                if(sChars[i] > sChars[j]){
                    StringBuilder sb = new StringBuilder();
                    sb.append(sChars,0,j);
                    sb.append(sChars,i,n-i);
                    sb.append(sChars,j,i-j);

                    return sb.toString();
                }
            }
        }

        return "no answer";
    }
}
