package com.bis.interview_prep.treeAndGraph.easy;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

/**
 *Invert a binary tree.
 *
 * For example, given the following tree:
 *
 *     a
 *    / \
 *   b   c
 *  / \  /
 * d   e f
 * should become:
 *
 *   a
 *  / \
 *  c  b
 *  \  / \
 *   f e  d
 **/
public class InvertBinaryTree {

    public static void main(String[] args) {

    }

    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if(root == null){
            return null;
        }

        TreeNode<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;

    }
}
