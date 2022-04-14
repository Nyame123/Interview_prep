package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

/**
 *Given a reference to a binary tree, return the width of the tree.
 *  The width of the tree is defined as the length of the longest path between any two nodes in the tree.
 * Note: The length of the longest path is measured by the number of edges between the two nodes.
 *
 * Ex: Given the following binary tree...
 *
 *          1
 *        /   \
 *      3      9
 *     / \
 *    8   7, return 3 (there are three edges between 8->3->1->9 and 7->3->1->9).
 *
 **/
public class LongestDiameterBinaryTree {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(2);
        root.left = new TreeNode<Integer>(5);
        root.left.left = new TreeNode<>(3);
        root.left.left.left = new TreeNode<>(1);
        root.left.left.right = new TreeNode<Integer>(4);

        /*TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.left = new TreeNode<Integer>(3);
        root.right = new TreeNode<Integer>(9);
        root.left.left = new TreeNode<>(8);
        root.left.right = new TreeNode<Integer>(7);
        root.right.right = new TreeNode<Integer>(8);
        root.right.right.left = new TreeNode<Integer>(6);
        root.right.right.right = new TreeNode<Integer>(7);*/

        int longestDiameter = longestDiameterCallup(root);
        System.out.println(longestDiameter);
    }

    /**
     * 1. In finding the longest diameter of a binary tree, we can use the height of the root.
     * 2. We find the longest height of the right subtree and the longest height of the left subtree for
     * each node in the binary tree.
     *
     * Time Complexity = O(N^2)
     **/
    private static int longestDiameter(TreeNode<Integer> root) {
        if (root == null){
            return 0;
        }

        //find the height of the left and right subtree of the root
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int leftDiameter = longestDiameter(root.left);
        int rightDiameter = longestDiameter(root.right);

        return Math.max(leftHeight+rightHeight+1,Math.max(leftDiameter,rightDiameter));

    }

    static int height(TreeNode<Integer> root){
        if (root == null){
            return 0;
        }

        return Math.max(height(root.left),height(root.right)) + 1;
    }

    /**
     * We can improve the above solution by computing the height within the recursive diameter computation
     *
     * Time Complexity = O(N)
     **/
    static int longestDiameterRec(TreeNode<Integer> root, Height height){
        Height lHeight = new Height();
        Height rHeight = new Height();
        if (root == null){
            height.height = 0;
            return 0;
        }

        int leftDiameter = longestDiameterRec(root.left,lHeight);
        int rightDiameter = longestDiameterRec(root.right,rHeight);

        //height of the root computation
        height.height = Math.max(lHeight.height, rHeight.height)+1;

        return Math.max(lHeight.height + rHeight.height + 1,Math.max(leftDiameter,rightDiameter));

    }

    static int longestDiameterCallup(TreeNode<Integer> root){
        return longestDiameterRec(root,new Height());
    }
    static class Height{
        int height = 0;
    }
}
