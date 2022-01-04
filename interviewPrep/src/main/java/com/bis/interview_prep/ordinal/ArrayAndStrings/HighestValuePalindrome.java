package com.bis.interview_prep.ordinal.ArrayAndStrings;

public class HighestValuePalindrome {

    public static void main(String[] args) {
        String s = "3943";
        int n = s.length();
        int k = 1;
        String res = highestValuePalindrome(s, n, k);
        System.out.println(res);
    }

    public static String highestValuePalindrome(String s, int n, int k) {
        char[] ans = new char[n];
        int[] mark = new int[n];
        int left = 0, right = n - 1;
        while (left <= right) {
            //base case
            if (left == right) {
                ans[left] = s.charAt(left);
                break;
            }
            if (s.charAt(left) == s.charAt(right)) {
                ans[right] = s.charAt(right);
                ans[left] = s.charAt(left);
            } else {
                if (s.charAt(left) > s.charAt(right)) {
                    ans[right] = s.charAt(left);
                    ans[left] = s.charAt(left);
                    k--;
                    mark[right] = 1;
                } else if (s.charAt(right) > s.charAt(left)) {
                    ans[left] = s.charAt(right);
                    ans[right] = s.charAt(right);
                    k--;
                    mark[left] = 1;
                }
            }

            left++;
            right--;
        }

        if (k < 0) {
            return "-1";
        }

        //maximizing the value of the string s
        left = 0;
        right = n - 1;
        while (left <= right) {
            //base case
            if (left == right && k >= 1) {
                ans[left] = '9';
                break;
            }

            if (ans[left] < '9') {
                if (mark[left] == 0 && mark[right] == 0 && k >= 2) {
                    ans[right] = '9';
                    ans[left] = '9';
                    k -= 2;
                } else if (mark[left] == 1 || mark[right] == 1 && k >= 1) {
                    ans[left] = '9';
                    ans[right] = '9';
                    k--;
                }
            }


            left++;
            right--;
        }


        return String.valueOf(ans);
    }

    public static String highestValuePalindrome1(String s, int n, int k) {
        // Write your code here

        //split the string into halves left and right
        int differences = 0;
        int mid = n / 2;
        char[] left = new char[mid];
        char[] right = new char[mid];
        //boolean isEven = (n % 2 == 0);
        for (int i = 0; i < mid; i++) {
            //System.out.printf("Mid %s\n",i);
            left[i] = s.charAt(i);
            right[i] = s.charAt(n - i - 1);
        }

        //we can check the number of different changes that we need to make
        for (int i = 0; i < mid; i++) {
            //System.out.printf("Left %s and Right %s\n",left[i],right[i]);
            if (left[i] != right[i]) {
                differences++;
            }
        }

        //System.out.println(differences);

        int remainingCount = k;
        for (int i = 0; i < mid; i++) {
            if (left[i] == right[i]) { //if the digits at left and right is the same
                if (left[i] != '9') {
                    if (remainingCount - 2 >= differences) {
                        left[i] = '9';
                        right[i] = '9';
                        remainingCount -= 2;
                    }
                }
            } else { //if the digits at the left and right are different
                if (left[i] == '9') {
                    if (remainingCount - 1 >= (differences - 1)) {
                        right[i] = '9';
                        remainingCount -= 1;
                        differences -= 1;
                    } else {
                        return "-1";
                    }
                } else if (right[i] == '9') {
                    if (remainingCount - 1 >= (differences - 1)) {
                        left[i] = '9';
                        remainingCount -= 1;
                        differences -= 1;
                    } else {
                        return "-1";
                    }
                } else if (remainingCount - 2 >= (differences - 1)) {
                    left[i] = '9';
                    right[i] = '9';
                    remainingCount -= 2;
                    differences -= 1;
                } else if (left[i] > right[i]) {
                    //System.out.printf("Left G %s and Right %s\n",left[i],right[i]);
                    if (remainingCount - 1 >= (differences - 1)) {
                        right[i] = left[i];
                        remainingCount--;
                        differences--;
                    } else {
                        return "-1";
                    }
                    //System.out.printf("Left G %s and Right %s\n",left[i],right[i]);
                } else {
                    if (remainingCount - 1 >= (differences - 1)) {
                        left[i] = right[i];
                        remainingCount--;
                        differences--;
                    } else {
                        return "-1";
                    }
                    //System.out.printf("Left %s and Right G %s\n",left[i],right[i]);
                }
            }
        }

        //reverse the right part
        int rlen = right.length;
        for (int i = 0; i < rlen / 2; i++) {
            char temp = right[i];
            int high = rlen - i - 1;
            right[i] = right[high];
            right[high] = temp;
        }

        if (n % 2 == 0) {
            StringBuilder sb = new StringBuilder(n);
            sb.append(left);
            sb.append(right);
            return sb.toString();
        } else {
            StringBuilder sb = new StringBuilder(n);
            char midChar = s.charAt(n / 2);
            sb.append(left);
            if (remainingCount > 0 && midChar != '9') {
                sb.append('9');
            } else {
                sb.append(midChar);
            }
            sb.append(right);
            return sb.toString();
        }
    }
}
