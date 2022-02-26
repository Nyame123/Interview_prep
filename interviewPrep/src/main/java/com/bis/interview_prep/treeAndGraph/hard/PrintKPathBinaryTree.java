package com.bis.interview_prep.treeAndGraph.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * Given the reference to the root of a binary tree and a value k, return the number of paths in
 * the tree such that the sum of the nodes along the path equals k.
 * Note: The path does not need to start at the root of the tree, but must move downward.
 * <p>
 * Ex: Given the following binary tree and value k…
 * <p>
 * 3
 * / \
 * 1   8
 * k = 11, return 1 (3 -> 8).
 * Ex: Given the following binary tree and value k…
 * <p>
 * 2
 * / \
 * -4   9
 * /
 * 2
 * k = 2, return 3 (2, 2 -> -4, -4 -> 2).
 **/
public class PrintKPathBinaryTree {


    static int kPathCounter = 0;
    static List<Integer> paths = new ArrayList<>();

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(3);
        root.left.left = new Node(2);
        root.left.right = new Node(1);
        root.left.right.left = new Node(1);
        root.right = new Node(-1);
        root.right.left = new Node(4);
        root.right.left.left = new Node(1);
        root.right.left.right = new Node(2);
        root.right.right = new Node(5);
        root.right.right.right = new Node(2);

        int k = 5;
        kPathCounter = printKPathSmart(root, k,0);
//        printKPathList(root, k);

        System.out.println(kPathCounter);
    }

    static void printPath(List<Integer> v, int i) {
        for (int j = i; j < v.size(); j++)
            System.out.print(v.get(j) + " ");
        System.out.println();
    }

    //Time Complexity = O(n)
    static int printKPathSmart(Node root, int k,int sum) {
        return printKPathSmart(root,k,sum,new HashMap<>());
    }

    static int printKPathSmart(Node root, int k,int cur, HashMap<Integer,Integer> memo){
        if (root == null){
            return 0;
        }
        cur += root.data;
        int sum = 0;
        if (cur == k){
            sum++;
        }

        sum += memo.getOrDefault(cur-k,0);

        //memorize the running sum
        memorize(memo,cur,1);
        sum += printKPathSmart(root.left,k,cur,memo);
        sum += printKPathSmart(root.right,k,cur,memo);
        memorize(memo,cur,-1);

        return sum;
    }

    static void memorize(HashMap<Integer,Integer> map,int key,int it){
        int newValue = map.getOrDefault(key,0)+it;
        if (newValue == 0){
            map.remove(key);
        }else{
            map.put(key,newValue);
        }
    }

    //Time Complexity = O(nlogn or n^2)
    static int printKPath(Node root, int k,int sum) {
        if (root == null) {
            return 0;
        }
        int count = pathKSum(root,k,0);
        count += printKPath(root.left, k,sum);
        count += printKPath(root.right, k,sum);
        return count;
    }

    static int pathKSum(Node root,int k,int sum){
        if (root == null)
            return 0;

        sum += root.data;

        int count = 0;
        if (sum == k){
            count++;
        }
        int leftCount = pathKSum(root.left,k,sum);
        int rightCount = pathKSum(root.right,k,sum);

        return leftCount+rightCount+count;
    }

    //Time Complexity = O(n*h*h)
    //Space Complexity = O(h)
    static void printKPathList(Node root, int k) {
        if (root == null) {
            return;
        }


        paths.add(root.data);
        printKPathList(root.left, k);
        printKPathList(root.right, k);

        int sum = 0;
        for (int i = paths.size()-1; i >= 0; i--) {
            sum += paths.get(i);
            if (sum == k)
                printPath(paths,i);
        }

        paths.remove(paths.size()-1);

    }

    public static class Node {
        public int data;
        public Node left, right;

       public Node(int x) {
            data = x;
            left = right = null;
        }
    }
}

