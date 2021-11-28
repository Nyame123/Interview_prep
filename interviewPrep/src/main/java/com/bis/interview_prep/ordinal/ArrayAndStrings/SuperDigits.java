package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class SuperDigits {

    public static void main(String[] args) {

        String n = "9875";
        int k = 4;
        int res = superDigit(n,k);
        System.out.println(res);
    }

    public static int superDigitIterative(String n, int k) {
        // Write your code here
        //edge case
        if(n.length() == 1){
            return Integer.parseInt(n);
        }

        long sum = 0;
        for(int i = 0; i < n.length(); i++){
            sum += Integer.parseInt(n.charAt(i)+"");
        }


        sum *= k;

        if(sum > 10){
            long temp = sum;
            while(temp > 10){
                sum = temp;
                temp = 0;
                while(sum != 0){
                    temp += sum%10;
                    sum /= 10;
                }

            }

            sum = temp;
        }
        return (int)sum;

    }

    public static int superDigit(String n, int k) {
        // Write your code here
        String test = n;
        long sum = 0;

        //Base case - if it's one digit, return itself
        if (test.length() == 1) {
            return Integer.parseInt(test);
        }

        // if multiple digits, add the digits together and call superDigit() on new result
        else {
            //iterate through each digit
            for (int i = 0; i < test.length(); i++) {

                sum += Character.getNumericValue(test.charAt(i));
            }
            //multiply sum by k -- hit runtime error if you try to concatenate beforehand
            sum = sum * k;

            //recursive call of new sum, with k of 1
            return superDigit(String.valueOf(sum), 1);
        }
    }


}
