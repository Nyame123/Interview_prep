package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given the reference to a binary search tree, “level-up”
 * the tree. Leveling-up the tree consists of modifying every node
 * in the tree such that every node’s value increases by the sum of all the node’s
 * values that are larger than it.
 * Note: The tree will only contain unique values and you may assume
 * that it is a valid binary search tree.
 * <p>
 * Ex: Given a reference to the following binary search tree...
 * <p>
 * 0
 * \
 * 3, modify the tree such that it becomes...
 * 3
 * \
 * 3
 * Ex: Given a reference to the following binary search tree...
 * <p>
 *   2
 * /   \
 * 1     3, modify the tree such that it becomes...
 *   5
 * /   \
 * 6     3
 **/
public class LevelUpTree {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(2);
        root.left = new TreeNode<>(1);
        root.right = new TreeNode<>(3);
        root.left.right = new TreeNode<>(9);
        root.right.right = new TreeNode<>(7);
        TreeNode<Integer> res = levelUp(root);
        printInOrder(res);
    }

    /**
     * 1. Flatten the bst tree into a linear data structure.
     * 2. Add up from the right side of the linear data structure
     * 3. Now assign the new data to the original tree using the position for the assignment.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static TreeNode<Integer> levelUpIter(TreeNode<Integer> root){
        List<Integer> list = new ArrayList<>();
        flattenTree(root,list);

        int n = list.size();
        for (int i = n-2; i >= 0; i--) {
            list.set(i,list.get(i+1)+list.get(i));
        }

        updateTree(root,list);
        return root;
    }
    static int counter = 0;

    static void flattenTree(TreeNode<Integer> root, List<Integer> list){
        if (root == null){
            return;
        }

        flattenTree(root.left,list);
        list.add(root.data);
        flattenTree(root.right,list);
    }

    static void updateTree(TreeNode<Integer> root, List<Integer> list){
        if (root == null){
            return;
        }

        updateTree(root.left,list);
        root.data = list.get(counter++);
        updateTree(root.right,list);
    }

    /**
     * In this problem, we use an approach similar to post order traversal to update the
     * node with a data of greater node value.
     *
     * This solution is neat but does not handle a scenario where there is a right node of a left subtree.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static TreeNode<Integer> levelUp(TreeNode<Integer> root) {
        //base case
        if (root == null) {
            return null;
        }

        int temp = root.data;
        TreeNode<Integer> right = levelUp(root.right);
        TreeNode<Integer> left = levelUp(root.left);

        if (right != null) {
            root.data += right.data;
        }

        if (left != null) {
            left.data += root.data;
        }


        return root;
    }


    static void printInOrder(TreeNode<Integer> root) {
        if (root == null)
            return;

        printInOrder(root.left);
        System.out.print(root.data+" ");
        printInOrder(root.right);
    }
}
