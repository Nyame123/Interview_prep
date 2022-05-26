package com.bis.interview_prep.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Given a list of words, find all pairs of unique indices such that the concatenation of the two words is a palindrome.
 * <p>
 * For example, given the list ["code", "edoc", "da", "d"], return [(0, 1), (1, 0), (2, 3)].
 **/
public class PalindromicPairs {

    public static void main(String[] args) {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        List<List<Integer>> res = palindromePairs(words);
        System.out.println(res);
    }

    /**
     * In this problem, we can use string substring formation for this.
     * 1. Find all the substrings anc check if the substring combination can form a palindrome
     * <p>
     * Time Complexity = O(N^2*K) where N = no of words, K = Len(word)
     **/
    private static List<List<Integer>> palindromicPair(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        int n = words.length;

        if (words == null || n < 2) {
            return res;
        }

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                String first = words[i] + words[j];
                if (isPalindromic(first, 0, first.length() - 1)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    res.add(temp);
                }

                String second = words[j] + words[i];
                if (isPalindromic(second, 0, second.length() - 1)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(j);
                    temp.add(i);
                    res.add(temp);
                }
            }
        }

        return res;
    }

    /**
     * Using a hashMap.
     * 1. Store the words as a key and index as a value in the hashmap.
     * 2. Traverse over the characters and check if the reverse of the
     * one of the substrings is there in the map and the other substring is
     * palindromic.
     * <p>
     * Time Complexity = O(N*K^2); N = no of words and K = Len(word)
     **/
    static List<List<Integer>> palindromePairs1(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        int n = words.length;

        if (words == null || n < 2) {
            return res;
        }

        //Load the words and index in a map
        Map<String, Integer> map = new HashMap<>(n);

        for (int i = 0; i < n; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j <= words[i].length(); j++) {
                String first = words[i].substring(0, j);
                String second = words[i].substring(j);


                if (isPalindromic(first, 0, first.length() - 1)) {
                    String rev = new StringBuilder(second).reverse().toString();
                    if (map.containsKey(rev) && map.get(rev) != i) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(map.get(rev));
                        temp.add(i);
                        res.add(temp);
                    }
                }

                if (isPalindromic(second, 0, second.length() - 1)) {
                    String rev = new StringBuilder(first).reverse().toString();
                    if (map.containsKey(rev) && map.get(rev) != i && !second.isEmpty()) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(i);
                        temp.add(map.get(rev));
                        res.add(temp);
                    }
                }
            }

        }

        return res;
    }

    static boolean isPalindromic(String s, int l, int r) {

        while (l <= r) {
            if (s.charAt(l++) != s.charAt(r--))
                return false;
        }

        return true;
    }


    static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();

        int n = words.length;

        if (words == null || n < 2) {
            return res;
        }

        HashMap<String, Integer> map = new HashMap<>(n);
        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            map.put(words[i], i);
            set.add(words[i].length());
        }

        for (int i = 0; i < n; i++) {
            String s = words[i];
            int len = s.length();
            String rev = new StringBuilder(s).reverse().toString();

            if (map.containsKey(rev) && map.get(rev) != i) {
                res.add(List.of(i, map.get(rev)));
            }

            for (int l : set) {
                if (l == len)
                    break;

                if (isPalindromic(rev, 0, len - l - 1)) {
                    String b = rev.substring(len - l);
                    if (map.containsKey(b)) {
                        res.add(List.of(i, map.get(b)));
                    }
                }

                if (isPalindromic(rev, l, len - 1)) {
                    String b = rev.substring(0, l);
                    if (map.containsKey(b)) {
                        res.add(List.of(map.get(b), i));
                    }
                }
            }

        }

        return res;
    }
}

class Trie {
    Trie arr[] = new Trie[26];
    int index = -1;
}

class PalindromicPairsTrie {
    List<List<Integer>> list = new ArrayList<>();

    public static void main(String[] args) {
        String[] words = {"code", "edoc", "da", "d"};
        List<List<Integer>> res = new PalindromicPairsTrie().palindromePairs(words);
        System.out.println(res);
    }

  /*  public List<List<Integer>> palindromePairs(String[] words) {
        Trie root = new Trie();
        int num =0;
        for(String s: words){
            Trie temp = root;
            for(char c: s.toCharArray()){
                if(temp.arr[c-'a']==null){
                    temp.arr[c-'a']= new Trie();
                }
                temp = temp.arr[c-'a'];
            }
            temp.index = num++;
        }
        for(int i=0;i<words.length;i++){
            match(words[i],i,root);
        }
        return list;
    }
    //handle empty string case
    // three cases
    //1. equal length
    //2. string in array greater
    // 3. string in trie greater
    public void dfs(Trie root,int id,StringBuilder sb,int that){
        if(root.index!=-1 && root.index!=that){
            if(palindrome(sb.toString(),0,sb.length()-1)){
                list.add(Arrays.asList(root.index,id));
            }
        }
        for(char c='a';c<='z';c++){
            if(root.arr[c-'a']!=null){
                sb.append(c);
                dfs(root.arr[c-'a'],id,sb,that);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    public void match(String s,int id, Trie root){
        boolean flag =false;
        if(root.index!=-1 && root.index!=id){
            if(palindrome(s,0,s.length()-1))list.add(Arrays.asList(root.index,id));
        }
        for(int i=s.length()-1;i>=0;i--){
            if(root.arr[s.charAt(i)-'a']!=null){
                if(root.arr[s.charAt(i)-'a'].index!=-1){
                    if(palindrome(s,0,i-1) && id!=root.arr[s.charAt(i)-'a'].index)list.add(Arrays.asList(root.arr[s.charAt(i)-'a'].index,id));
                }
                root=root.arr[s.charAt(i)-'a'];
            }
            else{
                flag = true;
                break;
            }
        }
        if(!flag)dfs(root,id,new StringBuilder(),root.index);
    }*/

    /**
     * This problem uses a Trie data structure to store all words.
     * 1. Insert the words into the trie and mark the trie with a complete word with the index of the
     * word else -1;
     * 2. Match the words one by one with the words in the trie starting the
     * word in a reverse order.
     * 3. Start matching from the reverse of word and if there is a match in the trie,
     * then we check if the rest of the substring(0,i-1) is a palindrome we save the indexes.
     * 4. Where there is no continuous character, we drill deep with dfs of the current trie node.
     **/
    public List<List<Integer>> palindromePairs(String[] words) {
        int n = words.length;
        Trie root = new Trie();
        for (int i = 0; i < n; i++) {
            insertWord(root, words[i], i);
        }

        for (int i = 0; i < n; i++) {
            matchWord(root, i, words[i]);
        }
        return list;
    }

     /**
     * Insert a word into the trie
     **/
    void insertWord(Trie root, String word, int index) {
        int n = word.length();
        Trie cur = root;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (cur.arr[c - 'a'] == null) {
                cur.arr[c - 'a'] = new Trie();
            }

            cur = cur.arr[c - 'a'];
        }

        cur.index = index;
    }

    void matchWord(Trie root, int index, String word) {
        int n = word.length();
        boolean checkHorizontally = true;
        if (root.index != -1 && root.index != index) {
            if (palindrome(word, 0, n - 1)) {
                list.add(List.of(root.index, index));
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            char c = word.charAt(i);
            if (root.arr[c - 'a'] != null) {
                if (root.arr[c - 'a'].index != -1) {
                    if (index != root.arr[c - 'a'].index && palindrome(word, 0, i - 1)) {
                        list.add(List.of(root.arr[c - 'a'].index, index));
                    }
                }
                root = root.arr[c - 'a'];
            } else {
                checkHorizontally = false;
                break;
            }
        }

        if (checkHorizontally) {
            dfs(root, new StringBuilder(), index, root.index);
        }
    }

    void dfs(Trie root, StringBuilder sb, int id, int that) {
        if (root.index != -1 && root.index != that) {
            if (palindrome(sb.toString(), 0, sb.length() - 1)) {
                list.add(List.of(root.index, id));
            }
        }

        for (int i = 0; i < 26; i++) {
            if (root.arr[i] != null) {
                sb.append((char) (i + 'a'));
                dfs(root.arr[i], sb, id, that);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public boolean palindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }
}