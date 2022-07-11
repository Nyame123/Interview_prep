package com.bis.interview_prep.treeAndGraph.easy;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

/**
 * Given a complete binary tree, count the number of nodes in faster than O(n) time. Recall that a
 * complete binary tree has every level filled except the last,
 * and the nodes in the last level are filled starting from the left.
 **/
public class CountCompleteBinaryTreeNodes {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(2);
        root.left = new TreeNode<>(1);
        root.right = new TreeNode<>(3);
        root.left.right = new TreeNode<>(9);
        root.right.right = new TreeNode<>(7);

        int countCompleteTreeNodes = countCompleteNodes(root);
        System.out.println(countCompleteTreeNodes);
    }

    /**
     * In a complete binary tree, the nodes can be counted by counting the right and left subtree.
     * With a complete binary tree, the total nodes = (2^h - 1).
     * 1. Count the left subtree and right subtree and if they are equal, then totalCount = (2^h - 1).
     * 2. If the left and right subtree counts are not equal, then we count the right and left subtree
     *
     * Time Complexity = O(Log(N)^2)
     * Space Complexity = O(Log(N))
     **/
    static int countCompleteNodes(TreeNode<Integer> root) {
        if (root == null){
            return 0;
        }

        int leftCount = leftSubtreeCount(root);
        int rightCount = rightSubtreeCount(root);

        if (leftCount == rightCount){
            return (1 << leftCount - 1);
        }

        return 1 + countCompleteNodes(root.left) +
                countCompleteNodes(root.right);

    }

    static int leftSubtreeCount(TreeNode<Integer> root) {

        TreeNode<Integer> node = root;
        int leftCount = 0;
        while (node != null){
            leftCount++;
            node = node.left;
        }

        return leftCount;
    }

    static int rightSubtreeCount(TreeNode<Integer> root) {

        TreeNode<Integer> node = root;
        int rightCount = 0;
        while (node != null){
            rightCount++;
            node = node.right;
        }

        return rightCount;
    }
}
