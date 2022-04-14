package com.bis.interview_prep.treeAndGraph.easy;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You are given the reference to the root of a binary tree and are asked to trim the tree
 * of “dead” nodes. A dead node is a node whose value is listed in the provided dead array.
 * Once the tree has been trimmed of all dead nodes, return a list containing references to
 * the roots of all the remaining segments of the tree.
 * <p>
 * Ex: Given the following binary tree and array dead…
 * <p>
 * 3
 * /   \
 * 1     7
 * /  \   /  \
 * 2    8 4    6,
 * dead = [7, 8], return a list containing a reference to the following nodes [3, 4, 6].
 **/
public class TrimDadNodesTree {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<Integer>(3);
        root.left = new TreeNode<>(1);
        root.right = new TreeNode<>(7);
        root.left.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(8);
        root.right.right = new TreeNode<>(6);
        root.right.left = new TreeNode<>(4);

        int[] dead = {7, 1, 8};
        List<Integer> res = nonDeadSegments(root, dead);
        System.out.println(res);
    }

    /**
     * In this problem, we are returning the list of non-dead segments from the tree.
     * 1. We check in if the root is not in the dead list, then we write the root as part of the result.
     * 2. We use BFS algorithm to traverse all the nodes to see if there is a node to trim.
     * <p>
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static List<Integer> nonDeadSegments(TreeNode<Integer> root, int[] dead) {
        if (root == null) {
            return new ArrayList<>();
        }

        HashSet<Integer> set = new HashSet<>(dead.length);
        for (int d : dead) {
            set.add(d);
        }
        List<Integer> ans = new ArrayList<>();

        if (!set.contains(root.data)) {
            ans.add(root.getData());
        }

        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> cur = queue.poll();
            boolean isDead = set.contains(cur.data);

            if (cur.left != null) {
                if (isDead && !set.contains(cur.left.data)) {
                    ans.add(cur.left.data);
                }
                queue.add(cur.left);
            }

            if (cur.left != null) {
                if (isDead && !set.contains(cur.right.data)) {
                    ans.add(cur.right.data);
                }
                queue.add(cur.right);
            }

        }

        return ans;
    }
}
