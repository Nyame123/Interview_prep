package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary and the two nodes say ‘a’ and ‘b’, determine whether two given nodes are cousins of each other or not.
 * Two nodes are cousins of each other if they are at same level and have different parents.
 * <p>
 * Example:
 * <p>
 * 6
 * /   \
 * 3     5
 * / \   / \
 * 7   8 1   3
 * Say two node be 7 and 1, result is TRUE.
 * Say two nodes are 3 and 5, result is FALSE.
 * Say two nodes are 7 and 5, result is FALSE.
 **/
public class CousinBinaryTree {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.left.right.right = new TreeNode<>(15);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);
        root.right.left.right = new TreeNode<>(8);

        TreeNode<Integer> Node1, Node2;
        Node1 = root.left.right.right;
        Node2 = root.left.right.left;

        if (isCousinTraversal(root, Node1, Node2))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    //level recursion
    static int level(TreeNode<Integer> root, TreeNode<Integer> node, int level) {
        if (root == null)
            return 0;

        if (root == node)
            return level;

        int l = level(root.left, node, level + 1);
        if (l != 0)
            return l;

        return level(root.right, node, level + 1);
    }

    static boolean isCousinTraversal(TreeNode<Integer> root, TreeNode<Integer> nodeA, TreeNode<Integer> nodeB) {
        if (root == null)
            return false;

        return (level(root, nodeA, 1) == level(root, nodeB, 1) &&
                !isSibling(root, nodeA, nodeB));
    }

    //is sibling
    static boolean isSibling(TreeNode<Integer> root, TreeNode<Integer> nodeA, TreeNode<Integer> nodeB) {
        if (root == null)
            return false;

        return ((root.left == nodeA && root.right == nodeB) ||
                (root.left == nodeB && root.right == nodeA) ||
                isSibling(root.left, nodeA, nodeB) ||
                isSibling(root.right, nodeA, nodeB));
    }


    private static boolean isCousin(TreeNode<Integer> root,
                                    TreeNode<Integer> node1, TreeNode<Integer> node2) {

        // Base case
        if (root == null)
            return false;

        TreeNode<Integer> dummy = new TreeNode<>(-1);
        Pair<TreeNode<Integer>, TreeNode<Integer>> pair = new Pair<>(root, dummy);
        TreeNode<Integer> parA = null;
        TreeNode<Integer> parB = null;
        Queue<Pair<TreeNode<Integer>, TreeNode<Integer>>> queue = new LinkedList<>();
        queue.add(pair);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            while (levelSize > 0) {
                Pair<TreeNode<Integer>, TreeNode<Integer>> cur = queue.poll();
                if (cur.a == node1) {
                    parA = cur.b;
                }

                if (cur.a == node2) {
                    parB = cur.b;
                }

                if (cur.a.left != null) {
                    queue.add(new Pair<>(cur.a.left, cur.a));
                }

                if (cur.a.right != null) {
                    queue.add(new Pair<>(cur.a.right, cur.a));
                }

                if (parA != null && parB != null) {
                    break;
                }

                levelSize--;
            }

            if (parA != null && parB != null)
                return parA != parB;

            if (parA != null || parB != null) {
                return false;
            }
        }

        return false;
    }

    static class Pair<A, B> {
        A a;
        B b;

        Pair(A p1, B p2) {
            a = p1;
            b = p2;
        }
    }
}
