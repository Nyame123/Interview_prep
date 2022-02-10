package com.bis.interview_prep.treeAndGraph.easy;

import static com.bis.interview_prep.treeAndGraph.easy.AveragesOfLevel.newNode;

/**
 * Given the root of a binary tree and two values low and high return the sum
 * of all values in the tree that are within low and high.
 * <p>
 * Ex: Given the following tree where low = 3 and high = 5…
 * <p>
 * 1
 * / \
 * 7   5
 * /    / \
 * 4    3   9
 * return 12 (3, 4, and 5 are the only values within low and high and they sum to 12)
 **/
public class SumWithinBounds {

    public static void main(String[] args) {
        /* Let us create following BST
         10
        / \
       5   15
      / \     \
     3   7    18  */
        Node root = null;
        root = insert(root, 10);
        insert(root, 5);
        insert(root, 15);
        insert(root, 3);
        insert(root, 7);
        insert(root, 18);

        int L = 7, R = 15;
        System.out.print(rangeSumBSTOptimized(root, L, R));
    }

    static int rangeSumBST(Node root, int l, int r) {
        if (root == null)
            return 0;

        int sum = 0;
        int leftSum = rangeSumBST(root.left, l, r);
        int rightSum = rangeSumBST(root.right, l, r);

        sum += (leftSum + rightSum);
        if (l <= root.key && root.key <= r) {
            sum += root.key;
        }
        return sum;
    }


    static int rangeSumBSTOptimized(Node root, int l, int r) {
        if (root == null)
            return 0;

        if (root.key > r)
            return rangeSumBST(root.left, l, r);
        if (root.key < l)
            return rangeSumBST(root.right, l, r);

        return root.key + rangeSumBST(root.left, l, r) + rangeSumBST(root.right, l, r);
    }

    // Function to insert a new node
// into the Binary Search Tree
    static Node insert(Node node, int data) {

        // Base Case
        if (node == null)
            return newNode(data);

        // If the data is less than the
        // value of the current node
        if (data <= node.key)

            // Recur for left subtree
            node.left = insert(node.left,
                    data);

            // Otherwise
        else

            // Recur for the right subtree
            node.right = insert(node.right,
                    data);

        // Return the node
        return node;
    }
}
