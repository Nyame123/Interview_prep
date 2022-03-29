package com.bis.interview_prep.treeAndGraph.easy;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * This problem was asked by Facebook.
 * <p>
 * Given a binary  return all paths from the root to leaves.
 * <p>
 * For example, given the
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * Return [[1, 2], [1, 3, 4], [1, 3, 5]]
 **/
public class RootLeafTraversal {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(10);
        root.left = new TreeNode<>(8);
        root.right = new TreeNode<>(2);
        root.left.left = new TreeNode<>(3);
        root.left.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(2);

        /* Let us test the built by printing Inorder traversal */
        List<List<Integer>> res = binaryTreePaths(root);
        System.out.println(res);
    }

    static List<List<Integer>> binaryTreePaths(TreeNode<Integer> root) {
        List<List<Integer>> ans = new ArrayList<>();
        recurse(root, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * Using recursion to achieve this state.
     * Recurse from the root to the leaf and when we gets to the leaf state
     * then we add the result to the list.
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     * */
    static void recurse(TreeNode<Integer> root, List<Integer> sb, List<List<Integer>> list) {
        if (root == null) return;
        sb.add(root.data);
        if (root.left == null && root.right == null) {
            list.add(new ArrayList<>(sb));
        }
        recurse(root.left, sb, list);
        recurse(root.right, sb, list);
        sb.remove(sb.size() - 1);
    }
}
