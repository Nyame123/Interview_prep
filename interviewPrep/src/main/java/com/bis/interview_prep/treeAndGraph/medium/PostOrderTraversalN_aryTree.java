package com.bis.interview_prep.treeAndGraph.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given an n-ary tree, return a list containing the post order traversal of the tree. Write your solution iteratively.
 * <p>
 * Ex: Given the following n-ary tree...
 * <p>
 * 1
 * / | \
 * 2  3  4, return [2, 3, 4, 1]
 **/
public class PostOrderTraversalN_aryTree {

    public static void main(String[] args) {
        Node root = new Node(1);

        root.children.add(new Node(3));
        root.children.add(new Node(2));
        root.children.add(new Node(4));

        root.children.get(0).children.add(new Node(5));
        root.children.get(0).children.add(new Node(6));

        List<Integer> list = postOrderNAryRec(root);
        System.out.println(list);
    }

    private static List<Integer> postOrderNAryRec(Node root){
        List<Integer> st = new ArrayList<>();
        return postorder(root,st);
    }


    static List<Integer> postorder(Node root, List<Integer> st) {
        if (root == null)
            return st;
        for(Node child: root.children) {
            postorder(child, st);
        }

        st.add(root.val);
        return st;
    }

    /**
     * Using Iterative method
     **/
    private static List<Integer> postOrderTravNAryTree(Node root) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        Node temp = root;
        List<Integer> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node cur = stack.peek();
            if (doneWithNode(cur, temp)) {
                ans.add(cur.val);
                temp = cur;
                stack.pop();
            } else {
                addToStack(cur, stack);
            }
        }

        return ans;
    }

    private static void addToStack(Node cur, Deque<Node> stack) {
        List<Node> children = cur.children;
        int n = children.size();
        for (int i = n - 1; i >= 0; i--) {
            stack.push(children.get(i));
        }
    }

    private static boolean doneWithNode(Node cur, Node temp) {
        List<Node> children = cur.children;
        int n = children.size();
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < n; i++) {
            Node node = children.get(i);
            if (node != null && node == temp) {
                return true;
            }
        }

        return false;
    }


    static class Node {
        int val;
        List<Node> children = new ArrayList<>();

        public Node() {

        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
}
