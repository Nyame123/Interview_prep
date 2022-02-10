package com.bis.interview_prep.treeAndGraph.easy;

import static com.bis.interview_prep.treeAndGraph.easy.AveragesOfLevel.newNode;

/**
 * Given two trees s and t return whether or not t is a subtree of s.
 * Note: For t to be a subtree of s not only must each node’s value in t match its
 * corresponding node’s value in s, but t must also exhibit the exact same structure as s.
 * You may assume both trees s and t exist.
 * <p>
 * Ex: Given the following trees s and t…
 * <p>
 * s = 1
 * / \
 * 3   8
 * t = 1
 * \
 * 8
 * return false
 * <p>
 * Ex: Given the following trees s and t…
 * <p>
 * s = 7
 * / \
 * 8   3
 * t = 7
 * / \
 * 8   3
 * return true
 * <p>
 * Ex: Given the following trees s and t…
 * <p>
 * s = 7
 * / \
 * 8   3
 * t = 7
 * / \
 * 8   3
 * /
 * 1
 * return false
 **/
public class TreeWithinTree {

    public static void main(String[] args) {
        Node root = null;
        root = newNode(4);
        root.left = newNode(2);
        root.right = newNode(9);
        root.left.left = newNode(3);
        root.left.right = newNode(5);
        root.right.right = newNode(7);

        Node sub = newNode(2);
        sub.left = newNode(3);
        sub.right = newNode(5);

        boolean res = isSubtree(root,sub);
        System.out.println(res);
    }

    static boolean isSame(Node root, Node sub) {
        if (root == null && sub == null)
            return true;
        if (root == null || sub == null)
            return false;

        if (root.key != sub.key)
            return false;

        return isSame(root.left, sub.left) && isSame(root.right, sub.right);
    }

    static boolean isSubtree(Node root, Node subRoot) {
        if (root == null)
            return false;

        if (root.key == subRoot.key) {
            boolean res = isSame(root.left, subRoot.left) && isSame(root.right, subRoot.right);
            if (res) {
                return true;
            }
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
}
