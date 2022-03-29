package com.bis.interview_prep.ndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

/**
 *Given the reference to a binary  return the length of the longest path in the that contains consecutive values.
 * Note: The path must move downward in the
 * Ex: Given the following binary
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 * return 3.
 * Ex: Given the following binary
 *
 *        1
 *       / \
 *      2   2
 *     / \ / \
 *    4  3 5  8
 *      /
 *     4
 * return 4.
 *
 **/
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
       TreeNode<Integer> root = new TreeNode<>(6);
        root.right = new TreeNode<>(9);
        root.right.left = new TreeNode<>(7);
        root.right.right = new TreeNode<>(10);
        root.right.right.right = new TreeNode<>(11);

        System.out.println(longestConsecutive(root));
    }

    static int maxLen = 1;
    private static int longestConsecutive(TreeNode<Integer> root) {
        longestCon(root,null,1);
        return maxLen;
    }


    /**
     * The idea of this solution is to check the root node and
     * traverse to the left and right part of the root.
     * In the right and left part of the root, you check if parent data is lesser by one.
     * When that condition is met, increase the counter by one else, traverse down with counter set to
     * one.
     * Time Complexity = O(N)
     * Space Complexity = O(N) and O(logN) for binary search tree
     **/
    static void longestCon(TreeNode<Integer> root,Integer parent,int data){
        maxLen = Math.max(maxLen,data);
        if (root == null){
            return;
        }

        if (parent == null){
            longestCon(root.left, root.data + 1, 1);
            longestCon(root.right, root.data + 1, 1);
        }else{
            if (parent.equals(root.data)) {
                longestCon(root.left, root.data + 1, data+1);
                longestCon(root.right, root.data + 1, data+1);
            }else{
                longestCon(root.left, root.data + 1, 1);
                longestCon(root.right, root.data + 1, 1);
            }
        }


    }
}
