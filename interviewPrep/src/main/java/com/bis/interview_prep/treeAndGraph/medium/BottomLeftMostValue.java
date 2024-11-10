package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

public class BottomLeftMostValue {

    static int min = 0;
    static int depth = 0;

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<Integer>(5);
        root.right.right = new TreeNode<Integer>(8);
        root.right.right.left = new TreeNode<Integer>(6);
        root.right.right.right = new TreeNode<Integer>(7);

        System.out.println(findBottomLeftValue(root));
    }

    public static int findBottomLeftValue(TreeNode<Integer> root) {
        findBottomLeftValue(root, 1);

        return min;
    }

    static void findBottomLeftValue(TreeNode<Integer> root, int h) {
        if (root == null)
            return;

        if (h > depth) {
            min = root.data;
            depth = h;
        }
        findBottomLeftValue(root.left, h + 1);
        findBottomLeftValue(root.right, h + 1);
    }
}
