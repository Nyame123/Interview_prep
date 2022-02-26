package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

/**
 *This question is asked by Amazon. Given the root of a binary tree where every node’s value is
 * either 0 or 1 remove every subtree that does not have a 1 in it.
 *
 * Ex: Given the following binary tree…
 *
 *         1
 *       /   \
 *     1      0
 * Return the tree such that it’s been modified to look as follows…
 *         1
 *       /
 *     1
 * Ex: Given the following binary tree…
 *
 *         1
 *       /   \
 *     1      1
 * Return the tree such that it’s been modified to look as follows…
 *         1
 *       /   \
 *     1      1
 * (No modifications are necessary)
 **/
public class PruneBinaryTreeContainingNoOne {

    public TreeNode<Integer> pruneTree(TreeNode<Integer> root) {
        if(root == null){
            return null;
        }

        TreeNode<Integer> left = pruneTree(root.left);
        TreeNode<Integer> right = pruneTree(root.right);

        root.left = left;
        root.right = right;
        if(root.left == null && root.right == null && root.data == 0){
            return null;
        }
        return root;
    }

}
