package com.bis.interview_prep.treeAndGraph.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This problem was asked by Square.
 * <p>
 * Given a list of words, return the shortest unique prefix of each word. For example, given the list:
 * <p>
 * dog
 * cat
 * apple
 * apricot
 * fish
 * Return the list:
 * <p>
 * d
 * c
 * app
 * apr
 * f
 **/
public class ShortestUniquePrefix {

    static List<String> result = new LinkedList<>();

    public static void main(String[] args) {
        String[] words = {"zebra", "dog", "duck", "dove"};
        List<String> res = shortestPrefixSorting(words);
        System.out.println(res);
    }

    /**
     * Using sorting algorithm here
     * 1. sort the words in increasing order.
     * 2. compare the words before and after and pick the one with the greater length;
     *
     * Time Complexity = O(NlogN + N*S); S= len(word), N = len(words)
     **/
    static List<String> shortestPrefixSorting(String[] words) {
        List<String> result = new LinkedList<>();
        if (words.length == 1){
            result.add(""+words[0].charAt(0));
        }
        Arrays.sort(words);

        for (int j = 0; j < words.length; j++) {
            //before word
            String before = null;
            if (j-1 >= 0) {
                int minLen = Math.min(words[j - 1].length(), words[j].length());
                int i = 0;
                while (i < minLen) {
                    if (words[j - 1].charAt(i) != words[j].charAt(i)) {
                        before = words[j].substring(0, i + 1);
                        break;
                    }
                    i++;
                }

                if (before == null && i > minLen) {
                    before = words[j];
                }
            }

            //after word
            int n = words.length;
            String after = null;
            if (j + 1 < n) {
                int minLen = Math.min(words[j + 1].length(), words[j].length());
                int i = 0;

                while (i < minLen) {
                    if (words[j + 1].charAt(i) != words[j].charAt(i)) {
                        after = words[j].substring(0, i + 1);
                        break;
                    }
                    i++;
                }

                if (after == null && i > minLen) {
                    after = words[j];
                }
            }

            if (after != null || before != null) {
                if (after == null) {
                    result.add(before);
                    continue;
                }

                if (before == null) {
                    result.add(after);
                    continue;
                }

                if (after.length() > before.length()) {
                    result.add(after);
                } else {
                    result.add(before);
                }
            }
        }

        return result;
    }

    /**
     * This problem can be solved with Trie.
     * 1. Create a Trie Data structure from the words.
     * 2. Every Trie Node has a frequency property keeping track of the number of visit to each character.
     * 3. Perform prefix traversal and print all the trieNode with frequency of 1.
     *
     * Time Complexity = O(N)
     **/
    static List<String> shortestPrefix(String[] words) {

        //put the words in the Trie Data Structure
        TrieNode root = new TrieNode();
        for (String word : words) {
            insert(root, word);
        }

        prefixTraversal(root, "");

        return result;
    }

    static void insert(TrieNode root, String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.child[c - 'a'] == null) {
                cur.child[c - 'a'] = new TrieNode();
            }
            //increase the frequency
            cur.child[c - 'a'].frequency++;

            //update the root trie
            cur = cur.child[c - 'a'];
        }
    }

    static void prefixTraversal(TrieNode root, String ans) {
        if (root == null) {
            return;
        }

        if (root.frequency == 1) {
            result.add(ans);
            return;
        }

        //traverse the children
        for (int i = 0; i < root.child.length; i++) {
            TrieNode trieNode = root.child[i];
            if (trieNode != null) {
                prefixTraversal(trieNode, ans + (char) (i + 'a'));
            }
        }
    }


    static class TrieNode {

        //Map<Character,TrieNode> child = new HashMap<>(26);
        TrieNode[] child = new TrieNode[26];
        int frequency = 0;
    }
}
