package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

/**
 * Given the sequence of keys visited by a postorder traversal of a binary search tree, reconstruct the tree.
 * <p>
 * For example, given the sequence 2, 4, 3, 8, 7, 5, you should construct the following tree:
 * <p>
 * 5
 * / \
 * 3   7
 * / \   \
 * 2   4   8
 **/
public class BinaryTreeFromPostOrder {

    /**
     * In this problem, we use min and max algorithm
     * 1. the root starts from the rightmost item.
     * 2. the min = -Inf and max = Inf.
     * 3. when the root is not within the range, we return null.
     * 4. Else right subtree = (arr,root.data+1,max)
     * and left subtree = (arr, min, root.data-1)
     **/
    static int postIndex = 0;

    public static void main(String[] args) {
        int[] postOrder = {2, 4, 3, 8, 7, 5};
        postIndex = postOrder.length - 1;
        TreeNode<Integer> root = postOrderTreeConstruction(postOrder);
        inOrderTraversal(root);
    }

    static TreeNode<Integer> postOrderTreeConstruction(int[] postOrder) {
        return postOrderTreeConstruction(postOrder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static TreeNode<Integer> postOrderTreeConstruction(int[] postOrder, int min, int max) {
        if (postIndex < 0) {
            return null;
        }

        int cur = postOrder[postIndex];
        if (cur < min || cur > max) {
            return null;
        }

        TreeNode<Integer> root = new TreeNode<>();
        root.data = cur;
        postIndex--;

        root.right = postOrderTreeConstruction(postOrder, cur + 1, max);
        root.left = postOrderTreeConstruction(postOrder, min, cur - 1);
        return root;
    }

    static void inOrderTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.println(root.data + " ");
        inOrderTraversal(root.right);
    }
}
