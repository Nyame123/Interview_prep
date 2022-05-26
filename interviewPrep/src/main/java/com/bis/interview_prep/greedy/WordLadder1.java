package com.bis.interview_prep.greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is
 * a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 * <p>
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words
 * in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 * <p>
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 **/
public class WordLadder1 {

    public static void main(String[] args) {
        String[] dictionary = {"hot", "dot", "dog", "lot", "log", "cog"};
        String start = "hit", end = "cog";

        int minTransformation = minTransformation(dictionary, start, end);
        System.out.println(minTransformation);
    }

    /**
     * In this problem, we can use BFS to get the minimum transformation to do
     * to reach the end word from the start word with one letter transformation at a time.
     * 1. We use set to keep the dictionary and to verify if the end word is in the dictionary.
     * 2. We use queue to hold the each word for the transformation until we reach the end word and return the depth
     * <p>
     * Time Complexity = O(N^2*M)
     **/
    static int minTransformation(String[] dictionary, String start, String end) {

        Set<String> wordSet = new HashSet<>();
        int n = dictionary.length;
        for (int i = 0; i < n; i++) {
            wordSet.add(dictionary[i]);
        }

        if (!wordSet.contains(end)) {
            return 0;
        }

        int level = 0;
        Deque<String> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {

            level++;
            int queueSize = queue.size();
            for (int j = 0; j < queueSize; j++) {

                String word = queue.poll();
                int len = word.length();
                char[] wordChar = word.toCharArray();
                for (int i = 0; i < len; i++) {

                    char[] temp = wordChar.clone();
                    for (char c = 'a'; c <= 'z'; c++) {
                        temp[i] = c;
                        String tempString = String.valueOf(temp);
                        if (tempString.equals(word)) {
                            continue;
                        }

                        if (!wordSet.contains(tempString)) {
                            continue;
                        }

                        if (tempString.equals(end)) {
                            return level+1;
                        }

                        queue.add(tempString);
                        wordSet.remove(tempString);

                    }

                }
            }
        }

        return 0;
    }
}
