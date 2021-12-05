package com.bis.interview_prep.ordinal.ArrayAndStrings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * There is a given list of strings where each string contains only lowercase letters from , inclusive.
 * The set of strings is said to be a GOOD SET if no string is a prefix of another string.
 * In this case, print GOOD SET. Otherwise, print BAD SET on the first line followed by the string being checked.
 * Note If two strings are identical, they are prefixes of each other.
 **/
public class NoPrefixSet {

    public static void main(String[] args) throws IOException {

        runTestCases();
    }

    static void runTestCases() throws IOException {
        File file = new File(System.getProperty("user.home"), "/Desktop/no_prefix_set.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        int n = Integer.parseInt(reader.readLine().trim());
        List<String> words = IntStream.range(0, n).mapToObj(i -> {
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());

        noPrefix(words);

        reader.close();
    }

    public static void noPrefix1(List<String> words) {
        // Write your code here
        //organize the starting character of each word
        HashMap<Character, List<String>> map = new HashMap<>();
        int n = words.size();

        for (String s : words) {
            //check if word is not null
            if (s != null && s.length() > 0) {
                char key = s.charAt(0);
                map.putIfAbsent(key, new ArrayList<>());
                List<String> list = map.get(key);
                list.add(s);

            }

        }

        System.out.println(map);

        //check for the prefix in the contained list of words
        boolean isBadCheck = false;
        String badWord = "", other = "";

        for (String s : words) {
            char key = s.charAt(0);
            if (map.containsKey(key)) {
                List<String> wordList = map.get(key);
                for (int i = 0; i < wordList.size(); i++) {
                    String w = wordList.get(i);
                    if (w.indexOf(s) != -1 && !w.equals(s)) {
                        isBadCheck = true;
                        badWord = w;
                        other = s;
                    }
                }
            }
        }

        if (isBadCheck) {
            System.out.println("BAD SET");
            System.out.println(badWord);
            //System.out.println(other);
        } else {
            System.out.println("GOOD SET");
        }

    }

    static void prefix4(List<String> words){
        HashMap<Character, List<String>> map = new HashMap<>();
        int n = words.size();
        for (int i = 0; i < n; i++) {
            String s = words.get(i);
            char key = s.charAt(0);
            if (map.containsKey(key)) {
                List<String> wordList = map.get(key);
                for (int j = 0; j < wordList.size(); j++) {
                    String w = wordList.get(j);
                    if (w.startsWith(s) || s.startsWith(w)) {
                        System.out.println("BAD SET");
                        //System.out.println(w);
                        System.out.println(s);
                        return;
                    }
                }
            }

            map.putIfAbsent(key, new ArrayList<>());
            List<String> list = map.get(key);
            list.add(s);

        }

        System.out.println("GOOD SET");
    }

    static void prefix3(List<String> words){

        int n = words.size();
        for (int i = 0; i < n; i++) {

            for (int j = i-1; j >= 0; j--) {
                String s = words.get(i);
                String w = words.get(j);
                if (w.startsWith(s) || s.startsWith(w)) {
                    System.out.println("BAD SET");
                    //System.out.println(w);
                    System.out.println(s);
                    return;
                }
            }
        }

        System.out.println("GOOD SET");
    }

    /**
     * this solution uses a TreeSet --this is all that is needed.
     * A TreeSet holds items in sorted order; so as I process items from
     * the original input list, I do the following; find the least element
     * in this set greater than or equal to the given element, or null if
     * there is no such element. find the greatest element in this set
     * less than or equal to the given element, or null if there is no such element.
     * check if the current word being processed is (or has) a prefix to (for) an
     * already processed word in the set. if #3 is false, then add current word to
     * the set of processed words. [this must be done last before the loop continues,
     * so that we can catch & return the duplicate -since the duplicate will be a valid answer]
     **/
    public static void noPrefix(List<String> words) {
        // Write your code here
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String next = treeSet.ceiling(word);
            String prev = treeSet.floor(word);

            if ((next != null && next.startsWith(word)) || (prev != null && word.startsWith(prev))) {
                System.out.println("BAD SET");
                System.out.println(word);
                System.out.println(next);
                System.out.println(prev);
                return;
            }

            treeSet.add(word);
        }

        System.out.println("GOOD SET");

    }
}
