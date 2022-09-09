package com.bis.interview_prep.codeWar;

import java.util.HashMap;

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
