package com.bis.interview_prep.treeAndGraph.easy;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * <p>
 * Return the smallest level x such that the sum of all the values of TreeNode<Integers at level x is maximal.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 * Example 2:
 * <p>
 * Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
 * Output: 2
 **/
public class LevelWIthMaximumSumBinaryTree {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<Integer>(5);
        root.right.right = new TreeNode<Integer>(8);
        root.right.right.left = new TreeNode<Integer>(6);
        root.right.right.right = new TreeNode<Integer>(7);
    /*   Constructed Binary tree is:
                 1
               /   \
             2      3
           /  \      \
          4    5      8
                    /   \
                   6     7    */


        /*TreeNode<Integer> root = new TreeNode<Integer>(-100);
        root.left = new TreeNode<>(-200);
        root.right = new TreeNode<>(-300);
        root.left.left = new TreeNode<>(-20);
        root.left.right = new TreeNode<>(-5);
        root.right.left = new TreeNode<>(-10);*/
        int maxLevel = maxLevelSum(root);
        System.out.println(maxLevel);

    }

    /**
     * We will use the idea of level order generation to do this
     * 1. For every level, we will get the maximum sum of the levels of a tree.
     * 2. return the level for the maximum sum.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static int maxLevelSum(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);

        int ans = Integer.MIN_VALUE;
        int level = 0;
        int maxLevel = 1;
        while (!queue.isEmpty()) {

            int size = queue.size();
            int max = 0;
            level++;
            while (size > 0) {
                TreeNode<Integer> node = queue.poll();
                max += node.data;
                size--;
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }


            if (max > ans) {
                ans = max;
                maxLevel = level;
            }
        }

        return maxLevel;
    }

}
