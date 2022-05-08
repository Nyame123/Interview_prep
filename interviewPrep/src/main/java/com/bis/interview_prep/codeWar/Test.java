package com.bis.interview_prep.codeWar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

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

class ToptalTest{

    public static void main(String[] args) {

        String[] arr = {"W>I", "R>L", "T>Z", "Z>E", "S>W", "E>R", "L>A", "A>N", "N>D", "I>T"};

        String res = findWord(arr);
        System.out.println(res);
    }

    static String findWord(String[] arr){
        HashMap<String,String> map = new HashMap<>();
        int n = arr.length;
        HashSet<String> keys = new HashSet<>();
        HashSet<String> values = new HashSet<>();

        for (int i = 0; i < n; i++) {
            String rule = arr[i];
            String[] rules = rule.split(">");
            keys.add(rules[0]);
            values.add(rules[1]);
            map.put(rules[0],rules[1]);
        }

        String start  = null;
        for(String s: keys){
            if (!values.contains(s)){
                start = s;
            }
        }

        StringBuilder sb =  new StringBuilder();
        sb.append(start);
        while (map.containsKey(start)){
            sb.append(map.get(start));
            start = map.get(start);
        }


        return sb.toString();
    }
}
