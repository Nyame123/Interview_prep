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

        HashMap<String,String> courseMap = convertIntoHash(prereqs_courses);
        printInOrder(courseMap,"Software Design");

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

    static void printInOrder(HashMap<String,String> coursesMap,String firstCourse){

        String key = coursesMap.get(firstCourse);
        int n = coursesMap.size();
        System.out.println(firstCourse);
        while (key != null){
            if (coursesMap.containsKey(key)) {
               System.out.println(key);
                key = coursesMap.get(key);
            }else
                break;
        }

        System.out.println(key);

    }

}
