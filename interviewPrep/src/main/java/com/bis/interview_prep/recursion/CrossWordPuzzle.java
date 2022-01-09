package com.bis.interview_prep.recursion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrossWordPuzzle {
    public static void main(String[] args) throws IOException{

        CrossWordPuzzle sut = new CrossWordPuzzle();
        CrossWordGraph graph = sut.new CrossWordGraph();
        List<String> lines = new ArrayList<>();

        File desktop = new File(System.getProperty("user.home"), "/Desktop/testCases/crossword_puzzle_test_case.txt");
        try (final Scanner s = new Scanner(new InputStreamReader(new FileInputStream(desktop)));) {

            int counter = 11;

            while (--counter >= 0 && s.hasNextLine()) {
                String line = s.nextLine();
                if (line.length() == 0)
                    break;
                lines.add(line);
            }
        }

        if (lines.isEmpty()) {
            return;
        }

        String places = lines.remove(lines.size() - 1);

        Set<String> allWords = Stream.of(places.split(";")).collect(Collectors.toSet());

        CrossWordGrid grid = sut.new CrossWordGrid();

        for (int row = 0; row < lines.size(); row++) {
            String line = lines.get(row);
            for (int charAt = 0; charAt < line.length(); charAt++) {
                char c = line.charAt(charAt);
                if (c == '-') {
                    grid.grid[row][charAt] = c;
                }
            }
        }

        grid.produceGraph(graph);

        sut.recursive(graph, new HashMap<>(), allWords);
        grid.print(graph);
    }

    private void recursive(final CrossWordGraph grid, final Map<CellNode, Character> crossCheck, final Set<String> remainingWords) {

        for (final String w : remainingWords) {
            for (int index = 0; index < grid.nodes.size(); index++) {
                if (grid.excludeIndexes.contains(index)) {
                    continue;
                }

                final CellNode cellNode = grid.nodes.get(index);
                final HashMap<CellNode, Character> crossLetterCheck = new HashMap<>(crossCheck);
                if (cellNode.accept(w, crossLetterCheck)) {

                    final HashSet<String> newRemaining = new HashSet<>(remainingWords);
                    // remove this word and pass remaining
                    newRemaining.remove(w);
                    grid.excludeIndexes.add(index);

                    if (!grid.isComplete && newRemaining.isEmpty()) {
                        grid.isComplete = true;
                        return;
                    }

                    recursive(grid, crossLetterCheck, newRemaining);

                    if (grid.isComplete) {
                        return;
                    }
                    grid.excludeIndexes.remove(index);
                }
            }
        }
    }

    // Represents crossword puzzle matrix
    // this one is used to print
    private class CrossWordGrid {

        private char[][] grid = new char[10][10];
        // initialize with pluses '+'

        public CrossWordGrid() {
            for (int r = 0; r < grid.length; r++) {
                Arrays.fill(grid[r], '+');
            }
        }

        public void print(final CrossWordGraph from) {
            from.nodes.forEach(n -> n.visit((vn) -> grid[vn.row][vn.column] = vn.letter));
            for (int r = 0; r < grid.length; r++) {
                System.out.println(grid[r]);
            }
        }

        public void produceGraph(final CrossWordGraph graph) {
            List<CellNode> nodes = new ArrayList<>();
            for (int r = 0; r < grid.length; r++) {
                buildGraphRowsRecursively(null, r, nodes, 0, grid[r]);
            }
            for (int c = 0; c < 10; c++) {
                buildGraphColumnsRecursively(0, c, nodes, null);
            }

            graph.nodes = nodes;
        }

        private void buildGraphColumnsRecursively(int r, int c, List<CellNode> acc, CellNode current) {
            if (r < 10) {
                if (grid[r][c] == '-' && isAboveOrBelowMinus(r, c)) {
                    if (current == null) {
                        current = new CellNode(r, c, null);
                        acc.add(current);
                        current.isCrossLetter = isLeftOrRightMinus(r, c);
                    } else {
                        current.link = new CellNode(r, c, null);
                        current.link.isCrossLetter = isLeftOrRightMinus(r, c);
                    }
                    buildGraphColumnsRecursively(r + 1, c, acc, current.hasNextLink() ? current.link : current);
                } else {
                    buildGraphColumnsRecursively(r + 1, c, acc, null);
                }
            }
        }

        private void buildGraphRowsRecursively(CellNode current, int r, List<CellNode> acc, int column, char[] row) {
            if (column < row.length) {
                // can't be one '-'
                if (row[column] == '-' && isLeftOrRightMinus(r, column)) {
                    if (current == null) {
                        current = new CellNode(r, column, null);
                        acc.add(current);
                        current.isCrossLetter = isAboveOrBelowMinus(r, column);
                    } else {
                        current.link = new CellNode(r, column, null);
                        current.link.isCrossLetter = isAboveOrBelowMinus(r, column);
                    }
                    buildGraphRowsRecursively(current.hasNextLink() ? current.link : current, r, acc, column + 1, row);
                } else {
                    buildGraphRowsRecursively(null, r, acc, column + 1, row);
                }
            }
        }

        private boolean isAboveOrBelowMinus(int r, int c) {
            boolean result = false;
            if ((r - 1) >= 0) {
                // look up
                result |= grid[r - 1][c] == '-';
            }
            if ((r + 1) < grid.length) {
                result |= grid[r + 1][c] == '-';
            }
            return result;
        }

        private boolean isLeftOrRightMinus(int r, int c) {
            boolean result = false;
            if ((c - 1) >= 0) {
                // look up
                result |= grid[r][c - 1] == '-';
            }
            if ((c + 1) < grid.length) {
                result |= grid[r][c + 1] == '-';
            }
            return result;
        }
    }

    // Represents all place holder sequencies as nodes
    private class CrossWordGraph {
        public boolean isComplete = false;
        private List<CellNode> nodes = new ArrayList<>();
        private Set<Integer> excludeIndexes = new HashSet<>();
    }

    private static class CellNode {
        /* row index where this placeholder is on grid */
        private int row = -1;
        /* column index where this placeholder should be on grid */
        private int column = -1;
        /* node's value per se */
        private char letter = '-';

        public CellNode(final int row, final int column, final CellNode link) {
            this.row = row;
            this.column = column;
            this.link = link;
        }

        public boolean accept(final String w, final Map<CellNode, Character> crossLetterCheck) {

            if (w.length() > 0) {
                letter = w.charAt(0);

                if (isCrossLetter) {
                    crossLetterCheck.putIfAbsent(this, letter);
                    if (crossLetterCheck.get(this).charValue() != letter) {
                        return false;
                    }
                }

                // if not return false
                if (w.length() == 1) {
                    return !hasNextLink();
                }
                return hasNextLink() && link.accept(w.substring(1), crossLetterCheck);
            }

            return false;
        }

        /*
         * denotes whether intersecting letter or cell that shared by multiple
         * words
         */
        private boolean isCrossLetter;
        /* link to next cell in this sequence */
        private CellNode link = null;

        public boolean hasNextLink() {
            return link != null;
        }

        public void visit(Consumer<CellNode> consumer) {
            consumer.accept(this);
            if (hasNextLink()) {
                consumer.accept(link);
                link.visit(consumer);
            }
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + column;
            result = prime * result + row;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            CellNode other = (CellNode) obj;

            if (column != other.column)
                return false;
            if (row != other.row)
                return false;
            return true;
        }
    }

}


class Result {

    /*
     * Complete the 'crosswordPuzzle' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY crossword
     *  2. STRING words
     */

    public static void crosswordPuzzle(char[][] crossword, String words) {
        // Write your code here
        //get the words from the string
        StringBuilder s = new StringBuilder();
        List<String> wordList = new ArrayList<>();
        int n = words.length();
        for (int i = 0; i < n; i++) {
            if (words.charAt(i) == ';') {
                wordList.add(s.toString());
                s = new StringBuilder();
            } else {
                s.append(words.charAt(i));
            }
        }

        wordList.add(s.toString());
        call(0, crossword, wordList);

    }

    static void call(int index, char[][] crossword, List<String> words) {
        //if the words size is equal to index
        int n = words.size();
        if (index == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < crossword.length; i++) {
                sb.append(crossword[i]).append("\n");
            }
            System.out.println(sb.toString());
        } else {

            int i, j, p, q, k;
            int row = crossword.length;
            int col = crossword[0].length;
            for (i = 0; i < row; i++) {
                for (j = 0; j < col; j++) {

                    p = i;
                    q = j;
                    //check if the grid col i can accomodate the word at index
                    for (k = 0; k < words.get(index).length() && p + k < col; k++) {
                        char cur = crossword[p + k][q];
                        if (cur != '-' && cur != words.get(index).charAt(k)) {
                            break;
                        }
                    }

                    //check if the word can fit vertically or the column
                    if (k == words.get(index).length()) {
                        char[][] temp = duplicate(crossword);
                        for (k = 0; k < words.get(index).length() && p + k < col; k++) {
                            temp[p + k][q] = words.get(index).charAt(k);
                        }

                       /* StringBuilder sb = new StringBuilder();
                        for (int l = 0; l < temp.length; l++) {
                            sb.append(temp[l]).append("\n");
                        }
                        System.out.println(sb.toString());*/

                        call(index + 1, temp, words);
                    }

                    //check if the grid row i can accomodate the word at index
                    for (k = 0; k < words.get(index).length() && q + k < row; k++) {
                        char cur = crossword[p][q + k];
                        if (cur != '-' && cur != words.get(index).charAt(k)) {
                            break;
                        }
                    }

                    //check if the word can fit vertically or the column
                    if (k == words.get(index).length()) {
                        char[][] temp = duplicate(crossword);
                        for (k = 0; k < words.get(index).length() && q + k < row; k++) {
                            temp[p][q + k] = words.get(index).charAt(k);
                        }

                       /* StringBuilder sb = new StringBuilder();
                        for (int l = 0; l < temp.length; l++) {
                            sb.append(temp[l]).append("\n");
                        }
                        System.out.println(sb.toString());*/

                        call(index + 1, temp, words);
                    }
                }
            }

            //call(index+1,crossword,words);

        }
    }

    static char[][] duplicate(char[][] cross){
        char[][] grid = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = cross[i][j];
            }
        }

        return grid;
    }

}

class Solution {
    public static void main(String[] args) throws IOException {
        File desktop = new File(System.getProperty("user.home"), "/Desktop/testCases/crossword_puzzle_test_case.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(desktop)));


        char[][] grid = new char[10][10];
        for (int i = 0; i < 10; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < line.length(); j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        String words = bufferedReader.readLine();

        Result.crosswordPuzzle(grid, words);


        bufferedReader.close();
    }
}

class CrossWordSol {
    static int emptyx, emptyy;

    public static void main(String args[])throws IOException {
        File desktop = new File(System.getProperty("user.home"), "/Desktop/testCases/crossword_puzzle_test_case.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(desktop)));
        char arr[][] = new char[10][10];
        int i, j, k, l;
        for (i = 0; i < 10; i++) {
            String s = bufferedReader.readLine();
            for (j = 0; j < s.length(); j++) {
                arr[i][j] = s.charAt(j);
            }
        }
        String temp = bufferedReader.readLine();
        String[] str = temp.split(";");
        boolean done[] = new boolean[str.length];
        if (solveshit(arr, str, done)) {
            for (i = 0; i < 10; i++) {
                for (j = 0; j < 10; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static boolean solveshit(char arr[][], String str[], boolean done[]) {
        if (isFull(arr)) {
            return true;
        } else {
            //part 1
            //System.out.println("fuck the police");
            int i, j, k, l, m, n, flag;
            i = emptyx;
            j = emptyy;
            j--;
            while (ifSafe(j) && arr[i][j] != '+') {
                j--;
            }
            j++;
            k = j;
            int length = 0;
            while (ifSafe(k) && arr[i][k] != '+') {
                length++;
                k++;
            }
            k--;
            for (l = 0; l < str.length; l++) {
                if (str[l].length() == length && done[l] == false) {
                    char tempstr[] = str[l].toCharArray();
                    char tempchar[] = new char[length];
                    for (m = 0; m < length; m++)
                        tempchar[m] = arr[i][j + m];
                    flag = 0;
                    for (m = 0; m < length; m++) {
                        if (arr[i][j + m] == tempstr[m] || arr[i][j + m] == '-') {

                        } else
                            flag = 1;
                    }
                    if (flag == 0) {
                        done[l] = true;
                        for (m = 0; m < length; m++) {
                            arr[i][j + m] = tempstr[m];
                        }
                        if (solveshit(arr, str, done))
                            return true;
                        done[l] = false;
                        for (m = 0; m < length; m++) {
                            arr[i][j + m] = tempchar[m];
                        }

                    }
                }
            }
            //part 2
            //
            //System.out.println("fuck the damm police");
            i = emptyx;
            j = emptyy;
            i--;
            while (ifSafe(i) && arr[i][j] != '+') {
                i--;
            }
            i++;
            k = i;
            length = 0;
            while (ifSafe(k) && arr[k][j] != '+') {
                length++;
                k++;
            }
            //System.out.println("i = "+i);
            //System.out.println("j = "+j);
            //System.out.println(length);
            k--;
            for (l = 0; l < str.length; l++) {
                //System.out.println(str[l].length());
                if (str[l].length() == length && done[l] == false) {
                    char tempstr[] = str[l].toCharArray();
                    char tempchar[] = new char[length];
                    for (m = 0; m < length; m++)
                        tempchar[m] = arr[i + m][j];
                    flag = 0;
                    for (m = 0; m < length; m++) {
                        if (arr[i + m][j] == tempstr[m] || arr[i + m][j] == '-') {

                        } else
                            flag = 1;
                    }
                    if (flag == 0) {
                        done[l] = true;
                        for (m = 0; m < length; m++) {
                            arr[i + m][j] = tempstr[m];
                        }
                        if (solveshit(arr, str, done))
                            return true;
                        done[l] = false;
                        for (m = 0; m < length; m++) {
                            arr[i + m][j] = tempchar[m];
                        }

                    }
                }
            }
            return false;
        }
    }

    public static boolean ifSafe(int x) {
        if (x >= 0 && x < 10)
            return true;
        else
            return false;
    }


    public static boolean isFull(char arr[][]) {
        int i, j, k;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                if (arr[i][j] == '-') {
                    emptyx = i;
                    emptyy = j;
                    return false;
                }
            }
        }
        return true;
    }
}
