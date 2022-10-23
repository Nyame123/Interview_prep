package com.bis.interview_prep.recursion.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Boggle is a game played on a 4 x 4 grid of letters. The goal is to find as many words as
 * possible that can be formed by a sequence of adjacent letters in the grid, using each cell at most once.
 * Given a game board and a dictionary of valid words, implement a Boggle solver.
 * Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
 * boggle[][]   = {{'G', 'I', 'Z'},
 * {'U', 'E', 'K'},
 * {'Q', 'S', 'E'}};
 * isWord(str): returns true if str is present in dictionary
 * else false.
 * <p>
 * Output:  Following words of dictionary are present
 * GEEKS
 * QUIZ
 **/
public class BoggleFindWords {


    //U,L,R,D,UL,UR,DL,DR
    static int[] x = {0, -1, 1, 0, -1, 1, -1, 1};
    static int[] y = {-1, 0, 0, 1, -1, -1, 1, 1};
    static char VISITED = '-';

    public static void main(String[] args) {
        String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GO"};
        char[][] boggle = {
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'},
                {'F', 'E', 'G'},
                {'R', 'I', 'E'}
        };

        List<String> wordsInGrid = findBoggleWords(dictionary, boggle);
        System.out.println(wordsInGrid);
    }

    /**
     * We can use a backtracking algorithm here
     * 1. We traverse over the words in the dictionary together with the
     * characters in the grid in all the 8 directions.
     * <p>
     * Time Complexity = O(N^2*M^2)
     **/
    static List<String> findBoggleWords(String[] dictionary, char[][] boggle) {
        int len = dictionary.length, n = boggle.length, m = boggle[0].length;

        List<String> ans = new ArrayList<>();

        next:
        for (int i = 0; i < len; i++) {
            String word = dictionary[i];
            char[] wordChar = word.toCharArray();
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (wordChar[0] == boggle[j][k]) {
                        if (isWordPresent(boggle, word.toCharArray(), j, k, 0)) {
                            ans.add(word);
                            continue next;
                        }
                    }
                }
            }
        }

        return ans;
    }

    static boolean isWordPresent(char[][] boggle, char[] words, int i, int j, int index) {
        //base case
        if (index == words.length) {
            return true;
        }

        if (!isValid(boggle, i, j)) {
            return false;
        }

        if (words[index] != boggle[i][j]) {
            return false;
        }

        char temp = boggle[i][j];
        //delete data
        boggle[i][j] = VISITED;
        for (int dirx = 0; dirx < x.length; dirx++) {
            if (isWordPresent(boggle, words, i + y[dirx], j + x[dirx], index + 1)) {
                //restore data
                boggle[i][j] = temp;
                return true;
            }
        }

        //restore data
        boggle[i][j] = temp;
        return false;
    }

    static boolean isValid(char[][] boggle, int i, int j) {
        int n = boggle.length, m = boggle[0].length;
        return i >= 0 && i < n && j >= 0 && j < m && boggle[i][j] != VISITED;
    }
}

/**
 * Using a Trie Data Structure
 **/
class FindWordUsingTrie {

    //U,L,R,D,UL,UR,DL,DR
    static int[] x = {0, -1, 1, 0, -1, 1, -1, 1};
    static int[] y = {-1, 0, 0, 1, -1, -1, 1, 1};
    static char VISITED = '-';

    public static void main(String[] args) {
        String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GO"};
        char[][] boggle = {
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'},
                {'F', 'E', 'G'},
                {'R', 'O', 'E'}
        };

        List<String> wordsInGrid = findBoggleWords(dictionary, boggle);
        System.out.println(wordsInGrid);
    }

    static List<String> findBoggleWords(String[] dictionary, char[][] boggle) {
        TrieNode root = new TrieNode();
        List<String> ans = new ArrayList<>();
        buildTrie(root, dictionary);
        int n = boggle.length, m = boggle[0].length;
        next:
        for (int node = 0; node < 26; node++) {
            if (root.child[node] != null) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {

                        if (boggle[i][j] == root.child[node].c) {
                            searchAllDirections(root.child[node], boggle, i, j, ans);
                            continue next;
                        }
                    }
                }
            }
        }

        return ans;
    }

    static boolean searchAllDirections(TrieNode trieNode, char[][] boggle, int i, int j, List<String> ans) {
        //base case
        if (!isValid(boggle, i, j)) {
            return false;
        }

        if (boggle[i][j] != trieNode.c) {
            return false;
        }

        if (trieNode.isEnd) {
            ans.add(trieNode.word);
        }

        char temp = boggle[i][j];
        //delete data
        boggle[i][j] = VISITED;

        for (int child = 0; child < 26; child++) {
            if (trieNode.child[child] != null) {
                for (int dirx = 0; dirx < x.length; dirx++) {
                    searchAllDirections(trieNode.child[child], boggle, i + y[dirx], j + x[dirx], ans);
                }
            }
        }

        //restore data
        boggle[i][j] = temp;
        return false;
    }


    static void buildTrie(TrieNode root, String[] dictionary) {
        for (int i = 0; i < dictionary.length; i++) {
            String word = dictionary[i];
            char[] wordChar = dictionary[i].toCharArray();
            insertWord(root, word, wordChar);
        }
    }

    static boolean isValid(char[][] boggle, int i, int j) {
        int n = boggle.length, m = boggle[0].length;
        return i >= 0 && i < n && j >= 0 && j < m && boggle[i][j] != VISITED;
    }

    static void insertWord(TrieNode current, String word, char[] wordChar) {
        int len = 0;
        while (len < wordChar.length) {
            int c = wordChar[len] - 'A';
            if (current.child[c] == null) {
                TrieNode node = new TrieNode();
                node.c = wordChar[len];
                current.child[c] = node;
            }

            current = current.child[c];
            len++;
        }
        current.word = word;
        current.isEnd = true;
    }

    static class TrieNode {
        char c;
        boolean isEnd = false;
        TrieNode[] child = new TrieNode[26];
        String word;
    }
}

/**
 * Using a HashMap Data Structure
 **/
class FindWordUsingHashMap {

    public static void main(String[] args) {
        String[] dictionary = {"GEEKS", "FOR", "QUIZ", "GO"};
        char[][] boggle = {
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'},
                {'F', 'E', 'G'},
                {'R', 'I', 'E'}
        };

        List<String> wordsInGrid = findBoggleWords(dictionary, boggle);
        System.out.println(wordsInGrid);
    }

    //Time Complexity = O(N*M) N=row, M = col, S = len(dictionary)
    static List<String> findBoggleWords(String[] dictionary, char[][] boggle) {
        List<String> ans = new ArrayList<>();
        int n = boggle.length, m = boggle[0].length;
        boolean[][] visited = new boolean[n][m];
        LinkedList<Cell>[] flatGrid = new LinkedList[26];
        for (int i = 0; i < 26; i++) {
            flatGrid[i] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = boggle[i][j];
                int index = c - 'A';
                Cell cell = new Cell();
                cell.row = i;
                cell.col = j;
                cell.c = c;
                flatGrid[index].add(cell);
            }
        }

        int len = dictionary.length;
        for (int i = 0; i < len; i++) {
            char[] word = dictionary[i].toCharArray();
            int j = 0;
            LinkedList<Cell> cells = flatGrid[word[j] - 'A'];
            for (Cell cell : cells) {
                if (!visited[cell.row][cell.col]) {
                    if (dfs(cell, flatGrid, visited, word, 1)) {
                        ans.add(dictionary[i]);
                        break;
                    }
                }
            }

        }

        return ans;
    }

    static boolean dfs(Cell current, LinkedList<Cell>[] flatGrid, boolean[][] visited, char[] word, int index) {
        //base case
        if (index == word.length) {
            visited[current.row][current.col] = false;
            return true;
        }

        //find the next character in the word to match the grid
        LinkedList<Cell> cells = flatGrid[word[index] - 'A'];
        visited[current.row][current.col] = true;
        //find the neighbouring cell
        for (Cell cell : cells) {
            //checking if the 'cell' is adjacent to the 'current' cell and not visited
            if (Math.max(
                    Math.abs(cell.col - current.col),
                    Math.abs(cell.row - current.row)) <= 1 && !visited[cell.row][cell.col]) {
                if (dfs(cell, flatGrid, visited, word, index + 1)) {
                    visited[current.row][current.col] = false;
                    return true;
                }
            }
        }

        visited[current.row][current.col] = false;
        return false;
    }

    static class Cell {
        int row, col;
        char c;
    }
}