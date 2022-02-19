package com.bis.interview_prep.treeAndGraph.hard;

import java.util.ArrayList;
import java.util.Arrays;
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
        printKPath(root, k,0);

        System.out.println(kPathCounter);
    }

    static void printPath(List<Integer> v, int i) {
        for (int j = i; j < v.size(); j++)
            System.out.print(v.get(j) + " ");
        System.out.println();
    }

    static void printKPath(Node root, int k,int sum) {
        if (k == sum){
            kPathCounter++;
        }
        if (root == null) {
            return;
        }

        sum += root.data;
        printKPath(root.left, k,sum);
        printKPath(root.right, k,sum);




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

