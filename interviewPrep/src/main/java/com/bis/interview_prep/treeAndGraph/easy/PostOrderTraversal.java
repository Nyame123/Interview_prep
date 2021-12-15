package com.bis.interview_prep.treeAndGraph.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class PostOrderTraversal {

    public static void postOrderRecursive(PreOrderTraversal.Node root) {
        //base case
        if (root == null) {
            return;
        }

        postOrderRecursive(root.left);
        postOrderRecursive(root.right);
        System.out.print(root.data + " ");
    }

    public static void postOrderIterative(PreOrderTraversal.Node root) {
        PreOrderTraversal.Node t = root;
        Deque<PreOrderTraversal.Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.peek();
            if ((root.left == null && root.right == null) ||
                    (root.left == t || root.right == t)) {
                System.out.print(root.data + " ");
                t = root;
                stack.pop();
            } else {
                if (root.right != null) {
                    stack.push(root.right);
                }

                if (root.left != null) {
                    stack.push(root.left);
                }
            }
        }
    }
}
