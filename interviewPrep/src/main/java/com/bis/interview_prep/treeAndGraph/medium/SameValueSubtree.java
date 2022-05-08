package com.bis.interview_prep.treeAndGraph.medium;

/**
 * Given the root of a binary tree, return the total number of subtrees that all contain the same value.
 * <p>
 * Ex: Given the following binary tree...
 * <p>
 * 2
 * /   \
 * 5     7
 * /   \     \
 * 3     3     7, return 4 (both threes and both sevens).
 *
 *                      1
 *                    /   \
 *                   2     3
 *                 /     /   \
 *                4     5     6
 *              /     /   \     \
 *             4     5     5     7
 *
 *             returns 6;
 **/
public class SameValueSubtree {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.left.left.left = new Node(4);
        root.right.left.left = new Node(5);
        root.right.left.right = new Node(5);
        root.right.right.right = new Node(7);

        System.out.print(countSubtrees(root));
    }

    private static int countSubtrees(Node root) {
        int[] ans = new int[1];
        countSubtrees(root,ans);
        return ans[0];
    }

    /**
     * In this solution, we use dfs or recursion to achieve the solution
     * 1. We traverse the root in a postOrder traversal fashion.
     * 2. We count all the leaf nodes as a solution.
     * 3. If the root has both left and right node, then it is an answer.
     * 4. If the root has only left or right node and it is same of the root, then it is an answer
     *
     * Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    private static int countSubtrees(Node root, int[] ans) {
        if(root == null){
            return Integer.MIN_VALUE;
        }
        //leaf nodes
        if (root.left == null && root.right == null){
             ans[0]++;
             return root.data;
        }

        int left = countSubtrees(root.left, ans);
        int right = countSubtrees(root.right, ans);

        if ((left == Integer.MIN_VALUE && root.data == right) ||
                (right == Integer.MIN_VALUE && root.data == left) ||
                (left == right && root.data == left)){
            ans[0]++;
            return root.data;
        }

        return Integer.MAX_VALUE;
    }

    static class Node {
        int data;
        Node left = null, right = null;

        Node(int data) {
            this.data = data;
        }
    }

}
