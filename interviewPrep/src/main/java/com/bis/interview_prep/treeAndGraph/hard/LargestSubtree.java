package com.bis.interview_prep.treeAndGraph.hard;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

/**
 * Given a tree, find the largest tree/subtree that is a BST.
 * Given a tree, return the size of the largest tree/subtree that is a BST.
 * <p>
 * Input:
 * 5
 * /  \
 * 2    4
 * /  \
 * 1    3
 * <p>
 * Output: 3
 * The following subtree is the maximum size BST subtree
 * 2
 * /  \
 * 1    3
 * <p>
 * <p>
 * Input:
 * 50
 * /    \
 * 30       60
 * /  \     /  \
 * 5   20   45    70
 * /  \
 * 65    80
 * Output: 5
 * The following subtree is the maximum size BST subtree
 * 60
 * /  \
 * 45    70
 * /  \
 * 65    80
 **/
public class LargestSubtree {

    static int count = 0;
    /**
     * We can use a very efficient method where we do a bottom up traversal with a
     * recursion.
     * We will build an ADT that holds all our states, {size, min,max,isBST}
     * Time Complexity = O(N)
     **/

    static int MIN = Integer.MIN_VALUE, MAX = Integer.MAX_VALUE;

    public static void main(String[] args) {

        TreeNode<Integer> tree = new TreeNode<>(50);
        tree.left = new TreeNode<>(10);
        tree.right = new TreeNode<>(60);
        tree.left.left = new TreeNode<>(5);
        tree.left.right = new TreeNode<>(20);
        tree.right.left = new TreeNode<>(55);
        tree.right.left.left = new TreeNode<>(45);
        tree.right.right = new TreeNode<>(70);
        tree.right.right.left = new TreeNode<>(65);
        tree.right.right.right = new TreeNode<>(80);

       // System.out.println(largestBST(tree));
        NodeState result = largestBSTWithState(tree);
        System.out.println(result.size);
    }

    static NodeState largestBSTWithState(TreeNode<Integer> root) {
        if (root == null) {
            //we map the min to Integer.MAXVALUE;
            //we map the max to Integer.MINVALUE;
            //bcos of the invariant property
            return new NodeState(0, MIN, MAX, true);
        }

        if (root.left == null && root.right == null) {
            return new NodeState(1, root.data, root.data, true);
        }

        NodeState leftState = largestBSTWithState(root.left);
        NodeState rightState = largestBSTWithState(root.right);

        NodeState state = new NodeState();
        if (leftState.isBST && rightState.isBST && leftState.max < root.data && rightState.min > root.data) {
            int min = Math.min(Math.min(leftState.min, rightState.min), root.data);
            int max = Math.max(Math.max(leftState.max, rightState.max), root.data);

            int size = leftState.size + rightState.size + 1;
            count = Math.max(count, size);
            state.max = max;
            state.min = min;
            state.size = size;
            state.isBST = true;
            return state;
        }

        int size = Math.max(leftState.size, rightState.size);
        count = Math.max(count, size);
        state.size = size;
        state.isBST = false;
        return state;
    }

    /**
     * We can take every one of the nodes in the tree and check if they are BST tree
     * then we return the size of the tree
     * <p>
     * Every Node will run n times because the isBST has a runtime of O(N) and the size has
     * a runtime of O(N)
     * <p>
     * Time Complexity = O(n^2)
     **/
    static int largestBST(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        if (isBST(root)) {
            return size(root);
        }

        return Math.max(largestBST(root.left),
                largestBST(root.right));
    }

    //Time Complexity = O(n)
    static int size(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        return size(root.left) + size(root.right) + 1;
    }

    static boolean isBST(TreeNode<Integer> root) {
        return isBST(null, root, null);
    }

    //check if it is BST
    //Time Complexity = O(n)
    static boolean isBST(Integer min, TreeNode<Integer> root, Integer max) {
        if (root == null) {
            return true;
        }

        if ((min != null && min >= root.data) || (max != null && max <= root.data)) {
            return false;
        }

        if (!isBST(min, root.left, root.data) || !isBST(root.data, root.right, max))
            return false;

        return true;
    }

    static class NodeState {

        int size; // Size of subtree
        int max; // Min value in subtree
        int min; // Max value in subtree
        boolean isBST; // If subtree is BST


        NodeState(int size, int max, int min, boolean isBST) {
            this.size = size;
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }


        public NodeState() {
        }

        /*public NodeState(int size, int min, int max, boolean isBST) {
            this.size = size;
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }*/
    }
}

