package com.bis.interview_prep.treeAndGraph.hard;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

/**
 * This problem was asked by Google.
 * <p>
 * Given a binary tree of integers,
 * find the maximum path sum between two TreeNode<></>s. The path must
 * go through at least one TreeNode<></>, and does not need to go through the.
 * <p>
 * Example:
 * <p>
 * Input: of below tree
 * 1
 * / \
 * 2   3
 * Output: 6
 * <p>
 * See below diagram for another example.
 * 1+2+3
 **/
public class MaximumPathSum {

    public static void main(String[] args) {
        TreeNode<Integer> tree = new TreeNode<>(50);
        tree = new TreeNode<>(10);
        tree.left = new TreeNode<>(2);
        tree.right = new TreeNode<>(10);
        tree.left.left = new TreeNode<>(20);
        tree.left.right = new TreeNode<>(1);
        tree.right.right = new TreeNode<>(-25);
        tree.right.right.left = new TreeNode<>(3);
        tree.right.right.right = new TreeNode<>(4);

        int res = maxPathSum(tree);
        System.out.println(res);

    }

    /**
     * This problem can be solved in Time Complexity of O(N)
     * We can make use of Kadane Algorithm in this approach.
     * <p>
     * 1. We do traversal from the root left and right node
     * 2. We will build a ADT class Result where the max will be stored
     * 3. We compare max with the root node.
     * 4. We again compare the max with the left Node max
     * 5. We compare the max with the right Node max
     * 5. We compare the max with the root + left + right
     * <p>
     * With this in mind all the possible max value will be obtained
     **/
    static int res;
    private static int maxPathSum(TreeNode<Integer> tree) {
        res = Integer.MIN_VALUE;
        return maxPathSumDown(tree);
    }

    private static int maxPathSumDown(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int leftMax = Math.max(0,maxPathSum(root.left));
        int rightMax = Math.max(0,maxPathSum(root.right));
        res = Math.max(leftMax + rightMax + root.data, res);
        return Math.max(leftMax,rightMax) + root.data;
    }

}
