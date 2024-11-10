package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;


/**
 * You’re a thief trying to rob a binary tree. As a thief, you are trying to steal as much money as possible.
 * The amount of money you steal is equivalent to the sum of all the node’s values that you decide to rob.
 * If two adjacent nodes are robbed, the authorities are automatically alerted. Return the maximum
 * loot that you can steal without alerting the authorities.
 *
 * Ex: Given the following binary tree...
 *
 *         2
 *       /   \
 *      5     7, return 12 (5 + 7 = 12).
 * Ex: Given the following binary tree...
 *
 *         4
 *       /   \
 *      3     2
 *       \     \
 *        9     7, return 20 (4 + 9 + 7 = 20).
 * */
public class HouseRobber111 {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(4);
        root.left = new TreeNode<>(3);
        root.right = new TreeNode<>(2);
        root.left.right = new TreeNode<>(9);
        root.right.right = new TreeNode<>(7);
        int maxRob = robHouse(root);
        System.out.println(maxRob);
    }

    static int robHouse(TreeNode<Integer> root){
        int[] nums = dfs(root);
        return Math.max(nums[0],nums[1]);
    }

    static int[] dfs(TreeNode<Integer> root) {
        if (root == null){
            return new int[2];
        }

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int[] res = new int[2];
        res[0] = left[1] + right[1] + root.data;
        res[1] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        return res;
    }
}
