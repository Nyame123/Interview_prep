package com.bis.interview_prep.treeAndGraph.hard;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Construct Tree from given Inorder and Preorder traversals
 * Difficulty Level : Hard
 * Last Updated : 13 Jan, 2022
 * Let us consider the below traversals:
 * Inorder sequence: D B E A F C
 * Preorder sequence: A B D E C F
 **/
public class ConstructTreeFromInOrderPostOrderTraversal {

    static int preIndex = 0;

    public static void main(String[] args) {
        int in[] = {9,3,15,20,7};
        int post[] = {9,15,7,20,3};
        int len = in.length;

        Map<Integer, Integer> map = buildTreeMap(in, len);
        preIndex = len - 1;
        TreeNode<Integer> root = buildTree(in, post, 0, len - 1, 0, map);
        inOrder(root);
    }

    static void inOrder(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }

        inOrder(root.getLeft());
        System.out.print(root.getData() + " ");
        inOrder(root.getRight());
    }

    static Map<Integer, Integer> buildTreeMap(int[] inOrder, int len) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(inOrder[i], i);
        }

        return map;
    }

    static TreeNode<Integer> buildTree(int[] in, int[] pre, int start, int end, int index, Map<Integer, Integer> map) {
        //invalid case
        if (start > end) {
            return null;
        }

        /*if (index >= pre.length){
            return null;
        }*/

        TreeNode<Integer> treeNode = new TreeNode<>();
        treeNode.setData(pre[preIndex--]);
        //preIndex--;

        int nextIndex = map.get(treeNode.getData());

        treeNode.setRight(buildTree(in, pre, nextIndex + 1, end, index + 1, map));
        treeNode.setLeft(buildTree(in, pre, start, nextIndex - 1, index + 1, map));

        return treeNode;
    }
}

class GFG {

    static HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();


    static int index;

    // Utility function to create a new node
    /* Helper function that allocates a new node */
    static Node newNode(int data) {
        Node node = new Node();
        node.data = data;
        node.left = node.right = null;
        return (node);
    }

    /* Recursive function to conbinary of size n
    from Inorder traversal in[] and Postorder traversal
    post[]. Initial values of inStrt and inEnd should
    be 0 and n -1. The function doesn't do any error
    checking for cases where inorder and postorder
    do not form a tree */
    static Node buildUtil(int in[], int post[],
                          int inStrt, int inEnd) {

        // Base case
        if (inStrt > inEnd)
            return null;

    /* Pick current node from Postorder traversal
    using postIndex and decrement postIndex */
        int curr = post[index--];
        Node node = newNode(curr);
        //(index)--;

        /* If this node has no children then return */
//        if (inStrt == inEnd)
//            return node;

    /* Else find the index of this node in Inorder
    traversal */
        int iIndex = mp.get(curr);

    /* Using index in Inorder traversal, con
    left and right subtress */
        node.right = buildUtil(in, post, iIndex + 1,
                inEnd);
        node.left = buildUtil(in, post, inStrt,
                iIndex - 1);
        return node;
    }

    // This function mainly creates an unordered_map, then
// calls buildTreeUtil()
    static Node buildTree(int in[], int post[], int len) {

        // Store indexes of all items so that we
        // we can quickly find later
        for (int i = 0; i < len; i++)
            mp.put(in[i], i);

        index = len - 1; // Index in postorder
        return buildUtil(in, post, 0, len - 1);
    }

    /* This function is here just to test */
    static void preOrder(Node node) {
        if (node == null)
            return;
        System.out.printf("%d ", node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    // Driver code
    public static void main(String[] args) {
        int in[] = {4, 8, 2, 5, 1, 6, 3, 7};
        int post[] = {8, 4, 5, 2, 6, 7, 3, 1};
        int n = in.length;
        Node root = buildTree(in, post, n);
        System.out.print("Preorder of the constructed tree : \n");
        preOrder(root);
    }

    /* A binary tree node has data, pointer to left
    child and a pointer to right child */
    static class Node {
        int data;
        Node left, right;
    }
}
