package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * This problem was asked by Google.
 * <p>
 * Given the root of a binary search tree, and a target K, return two nodes in the tree whose sum equals K.
 * <p>
 * For example, given the following tree and K of 20
 * <p>
 * 10
 * /   \
 * 5      15
 * /  \
 * 11    15
 * Return the nodes 5 and 15
 **/
public class FindPairWithGivenSumBST {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(10);
        root.left = new TreeNode<Integer>(5);
        root.right = new TreeNode<Integer>(15);
        root.right.right = new TreeNode<Integer>(15);
        root.right.left = new TreeNode<Integer>(11);

        int target = 20;

        boolean isPairFound = findTarget(root, target);
        System.out.println(isPairFound);
    }


    /**
     * In this solution, we use Set data structure and DFS for this problem.
     * 1. We traverse through the tree and when a pair is found in the set
     * then solution is found.
     * Else no solution.
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static boolean findPair(TreeNode<Integer> root, int target) {
        Set<Integer> set = new HashSet<>();
        return isPairFound(root, target, set);
    }

    private static boolean isPairFound(TreeNode<Integer> root, int target, Set<Integer> set) {
        if (root == null) {
            return false;
        }

        if (set.contains(target - root.data)) {
            return true;
        } else {
            set.add(root.data);
        }

        return isPairFound(root.left, target, set) || isPairFound(root.right, target, set);
    }

    static boolean findTarget(TreeNode<Integer> root, int k) {
        return targetSumPair(root, k, root);
    }

    static boolean find(TreeNode<Integer> node, int data) {
        // write your code here
        if (node == null) {
            return false;
        }
        if (node.data < data) {
            return find(node.right, data);
        } else if (node.data > data) {
            return find(node.left, data);
        } else {
            return true;
        }


    }

    static boolean targetSumPair(TreeNode<Integer> node, int data, TreeNode<Integer> root) {
        if (node == null) {
            return false;
        }
        boolean ansleft = targetSumPair(node.left, data, root);
        if (ansleft) {
            return true;
        }
        int comp = data - node.data;
        if (comp > node.data) {
            boolean ans = find(root, comp);
            if (ans) {
                return true;
            }
        }
        boolean ansright = targetSumPair(node.right, data, root);
        if (ansright) {
            return true;
        }
        return false;

    }

    /**
     * In this solution, we use two stacks to mimic In Order traversals.
     * 1. One stack is for normal inorder traversal and other stack for reverse inorder
     * traversal.
     * 2. When pointer to first stack and pointer to second stack sum up to the target, we return true.
     * 3. When the sum of the two pointers is less than target ,we increase the first pointer.
     * 4. When the sum of the two pointers is greater than target, we decrease the second pointer.
     *
     * Time Complexity = O(n)
     * Space Complexity = O(logn)
     **/
    static boolean findTarget1(TreeNode<Integer> root, int target) {
        //inOrder traversal stack
        Deque<TreeNode<Integer>> stack1 = new ArrayDeque<>();
        Deque<TreeNode<Integer>> stack2 = new ArrayDeque<>();

        boolean finished1 = false, finished2 = false;
        int val1 = 0, val2 = 0;
        TreeNode<Integer> root1 = root, root2 = root;
        while (true){

            while (!finished1){
                if (root1 != null) {
                    stack1.push(root1);
                    root1 = root1.left;
                }else {
                    if (!stack1.isEmpty()) {
                        root1 = stack1.pop();
                        val1 = root1.data;
                        finished1 = true;
                        root1 = root1.right;
                    }
                }

            }


            while (!finished2){
                if (root2 != null){
                    stack2.push(root2);
                    root2 = root2.right;
                }else {
                    if (!stack2.isEmpty()) {
                        root2 = stack2.pop();
                        val2 = root2.data;
                        finished2 = true;
                        root2 = root2.left;
                    }
                }
            }

            //since BST is sorted, the left value cannot be greater than
            // the right value
            if (val1 >= val2){
                return false;
            }

            if (val1 + val2 == target){
                return true;
            }else if (val1+val2 < target){
                finished1 = false;
            }else if (val1+val2 > target){
                finished2 = false;
            }
        }
    }
}
