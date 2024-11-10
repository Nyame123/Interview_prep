package com.bis.interview_prep.codeWar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        String[][] prereqs_courses = new String[][]{
                {"Foundations of Computer Science", "Operating Systems"},
                {"Data Structures", "Algorithms"},
                {"Computer Networks", "Computer Architecture"},
                {"Algorithms", "Foundations of Computer Science"},
                {"Computer Architecture", "Data Structures"},
                {"Software Design", "Computer Networks"}
        };

        HashMap<String, String> courseMap = convertIntoHash(prereqs_courses);
        printInOrder(courseMap, "Software Design");

    }

    static HashMap<String, String> convertIntoHash(String[][] courses) {
        int length = courses.length;
        HashMap<String, String> courseMap = new HashMap<>(length);

        for (int i = 0; i < length; i++) {
            String[] course = courses[i];
            courseMap.put(course[0], course[1]);
        }
        return courseMap;
    }

    static void printInOrder(HashMap<String, String> coursesMap, String firstCourse) {

        String key = coursesMap.get(firstCourse);
        int n = coursesMap.size();
        System.out.println(firstCourse);
        while (key != null) {
            if (coursesMap.containsKey(key)) {
                System.out.println(key);
                key = coursesMap.get(key);
            } else
                break;
        }

        System.out.println(key);

    }
}

class PalindromicSolution{
    public static void main(String[] args) {
        String s = "aaaabbaa";
        String res = longestPalindromeManacher(s);

        System.out.println(res);
    }

    static String longestPalindromeManacher(String s){
        // code here
        String modified = modifyWord(s);
        int n = modified.length();
        int[] lps = new int[n];
        int maxLen = 1;
        int startIndex = 0;
        int leftPos = 0;
        int rightPos = 0;
        int pivot = 0;

        for(int i = 1; i < n; i++){
            int mirrorIndex = 2 * pivot - i;

            //get the LPS[i]
            if (i < rightPos) {
                lps[i] = Math.min(rightPos - i, lps[mirrorIndex]);
            }

            //check palindrome length from ith position
            int right = i + (lps[i] + 1);
            int left = i - (lps[i] + 1);

            while (right < n && left >= 0 && modified.charAt(right) == modified.charAt(left)) {
                lps[i]++;
                right++;
                left--;
            }

            //check if the (i+LPS[i])th > right boundary, then recalculate the center and right boundary
            if (lps[i] + i > rightPos) {
                pivot = i;
                rightPos = i + lps[i];
            }

            //check for the max length so far
            if (maxLen < lps[i]) {
                maxLen = lps[i];
                startIndex = i;
            }
        }


        StringBuilder res = new StringBuilder();
        int end = maxLen+startIndex;
        startIndex -= maxLen;
        startIndex++;
        while(startIndex < end){
            if(modified.charAt(startIndex) != '|'){
                res.append(modified.charAt(startIndex));
            }
            startIndex++;
        }

        return res.toString();
    }


    static String modifyWord(String s){
        StringBuilder sb = new StringBuilder();
        sb.append('|');

        for(char c: s.toCharArray()){
            sb.append(c);
            sb.append('|');
        }
        return sb.toString();
    }

    public static String longestPalindromeCenter(String s) {
        //length of the string
        int len = s.length();
        int startIndex = 0;
        int maxLen = 1;
        // center and go round
        for(int i = 0; i < len; i++){
            // odd position
            int left = i-1, right = i+1;
            int max = centerAndRound(left, right, s)+1;


            if(maxLen < max){
                maxLen = max;
                startIndex = i-max/2;
            }

            // even position
            left = i; right = i+1;
            max = centerAndRound(left, right, s);
            if(maxLen < max){
                maxLen = max;
                startIndex = i-(max/2-1);
            }
        }

        System.out.println("max Length ="+ maxLen);
        String ans = s.substring(startIndex, startIndex+maxLen);
        return ans;
    }

    private static int centerAndRound(int l, int r, String s){
        int left = l, right = r;
        int len = 0;
        while(left >= 0 && right < s.length()){
            if(s.charAt(left) == s.charAt(right)){
                left--;
                right++;
                len += 2;
            }else{
                break;
            }
        }
        return len;
    }

    static public String longestPalindrome(String s) {
        //length of the string
        int len = s.length();
        boolean [][] dp = new boolean[len][len];

        // All the single characters are palindromic
        for(int i = 0; i < len; i++){
            dp[i][i] = true;
        }

        //slice all the words in length wise and check the max palindromic length
        int maxLength = 1;
        int index = 0;

        for(int i = 0; i < len-1; i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                if(maxLength < 2) {
                    maxLength = 2;
                    index = i;
                }
            }
        }

        for(int i = 3; i <= len; i++){
            for(int j = 0; j <= len - i; j++){
                int high = j+i-1;
                if(s.charAt(j) == s.charAt(high) && dp[j+1][high-1] ){
                    dp[j][high] = true;
                    if(i > maxLength){
                        maxLength = i;
                        index = j;
                    }
                }
            }
        }

        System.out.println("Max length ="+ maxLength);

        for(int i = 0; i < len; i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        // Grab the longest palindromic substring
        String ans = s.substring(index, index+maxLength);
        return ans;

    }
}


class CodeSignalSolution{

    public static void main(String[] args) {
        String s = "codesignal";
        String result = solution(s);
        System.out.println(result);
    }

    /**
     * A sawtooth sequence is a sequence of numbers that
     * alternate between increasing and decreasing. In other words,
     * each element is either strictly greater than its neighbouring
     * elements or strictly less than its neighbouring elements.
     **/

    long solution(int[] arr) {
        int n = arr.length;
        String increasing = "0", decreasing = "1";
        long count = 0;
        for(int i  = 0; i < n; i++){
            String state = null;
            for(int j = i+1; j < n; j++){
                if(state == null){
                    if(arr[j] > arr[j-1]){
                        state = increasing;
                        count++;
                    }else if(arr[j] < arr[j-1]){
                        state = decreasing;
                        count++;
                    }else{
                        break;
                    }
                }else{
                    if(arr[j] == arr[j-1]){
                        break;
                    }

                    if(arr[j] > arr[j-1]){
                        if(state == decreasing){
                            count++;
                            state = increasing;
                        }else{
                            break;
                        }
                    }else{
                        if(state == increasing){
                            count++;
                            state = decreasing;
                        }else{
                            break;
                        }
                    }
                }
            }
        }

        return count;
    }

    static String solution(String s) {
        while(true){
            StringBuilder sb = new StringBuilder();
            String longestPalindrome = "";
            for(char c: s.toCharArray()){
                sb.append(c);

                String newString = sb.toString();
                //System.out.println(newString);
                if(isPalindromic(newString) && newString.length() > longestPalindrome.length()){
                    longestPalindrome = newString;
                }

            }

            //System.out.println(longestPalindrome);
            if(longestPalindrome.length() > 1){
                s = s.substring(longestPalindrome.length());
            }else{
                return s;
            }
        }
    }

   static boolean isPalindromic(String s){
        int left = 0;
        int right = s.length() - 1;

        while(left < right){
            if(s.charAt(left) == s.charAt(right)){
                left++;
                right--;
            }else{
                return false;
            }
        }

        return true;
    }
}
















/**
 *
 *
 **/



class ToptalTest {

    public static void main(String[] args) {
        int a = 555, b = 555;
        solution();
    }

    private static void solution(){
        numberOfCarryOperations(123, 456);
        numberOfCarryOperations(555, 555);
        numberOfCarryOperations(900, 11);
        numberOfCarryOperations(145, 55);
        numberOfCarryOperations(0, 0);
        numberOfCarryOperations(1, 99999);
        numberOfCarryOperations(999045, 1055);
        numberOfCarryOperations(101, 809);
        numberOfCarryOperations(189, 209);
    }
    private static void numberOfCarryOperations(int a, int b) {
        int totalCarry = 0;
        int carry = 0;

        char[] num1 = new StringBuilder(""+a).reverse().toString().toCharArray();
        char[] num2 = new StringBuilder(""+b).reverse().toString().toCharArray();

        int len = Math.max(num1.length,num2.length);

        for (int i = 0; i < len; i++) {
            int first = (i >= num1.length)? 0: num1[i]-'0';
            int se = (i >= num2.length)? 0 : num2[i]-'0';

            int sum = first + se + carry;
            int rem = sum / 10;
            //int ans = sum % 10;

            if (rem > 0){
                totalCarry++;
                carry = rem;
            }
        }

        if (carry > 1)
            totalCarry++;
        System.out.println(totalCarry);
        //return totalCarry;
    }


}

class dd{

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 9}; int k = 6;
        int result = solution(arr, k);
        System.out.println(result);
    }
    static int solution(int[] a, int k) {
        int n = a.length;
        int dividend = 1;

        while(true){
            int maxLength = 0;
            for(int i = 0;  i < n; i++){
                maxLength += (a[i] / dividend);
            }

            if (maxLength < k){
                dividend--;
                break;
            }
            dividend++;
        }

        return dividend;
    }
}

class PerimeterOfLand{

    public static void main(String[] args) {
        int[][] grid = {
                new int[]{1},
                new int[]{1},
                new int[]{1},
        };

        int res = islandPerimeter(grid);
        System.out.println(res);
    }
    public static int islandPerimeter(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Set<Integer> visited = new HashSet<>();

        int ans = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int index = (i*m) + j;
                if(!visited.contains(index) && grid[i][j] == 1){
                    ans += dfs(i,j,n,m,grid,visited);
                }
            }
        }

        return ans;
    }

    public static boolean isValid(int row, int col, int n, int m){
        return (row >= 0 && row < n && col >= 0 && col < m);
    }

    public static int dfs(int row, int col, int n, int m, int[][] grid, Set<Integer> visited){
        if(!isValid(row, col, n, m) || grid[row][col] == 0){
            return 1;
        }

        int[] x = {-1,1,0,0};
        int[] y = {0, 0, -1,1};
        int count = 0;

        int index = (row*m) + col;
        visited.add(index);

        for(int i = 0; i < 4; i++){
            int r = row+y[i];
            int c = col+x[i];
            int childIndex = (r*m) + c;
            if(!visited.contains(childIndex) || !isValid(r,c,n,m)){
                count += dfs(r,c,n,m,grid,visited);
            }
        }
        return count;
    }
}