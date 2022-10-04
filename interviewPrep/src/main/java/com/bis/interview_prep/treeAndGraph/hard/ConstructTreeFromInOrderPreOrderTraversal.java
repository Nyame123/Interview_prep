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
public class ConstructTreeFromInOrderPreOrderTraversal {

    static int preIndex = 0;

    public static void main(String[] args) {
        char in[] = new char[]{'D', 'B', 'E', 'A', 'F', 'C'};
        char pre[] = new char[]{'A', 'B', 'D', 'E', 'C', 'F'};
        int len = in.length;

        Map<Character, Integer> map = buildTreeMap(in, len);
        TreeNode<Character> root = buildTree(in, pre, 0, len - 1, 0, map);
        inOrder(root);
    }

    static void inOrder(TreeNode<Character> root) {
        if (root == null) {
            return;
        }

        inOrder(root.getLeft());
        System.out.print(root.getData() + " ");
        inOrder(root.getRight());
    }

    static Map<Character, Integer> buildTreeMap(char[] inOrder, int len) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(inOrder[i], i);
        }

        return map;
    }

    static TreeNode<Character> buildTree(char[] in, char[] pre, int start, int end, int index, Map<Character, Integer> map) {
        //invalid case
        if (start > end) {
            return null;
        }

        /*if (index >= pre.length){
            return null;
        }*/

        TreeNode<Character> treeNode = new TreeNode<>();
        treeNode.setData(pre[preIndex++]);

       /* if (start == end){
            return treeNode;
        }*/

        int nextIndex = map.get(treeNode.getData());

        treeNode.setLeft(buildTree(in, pre, start, nextIndex - 1, index + 1, map));
        treeNode.setRight(buildTree(in, pre, nextIndex + 1, end, index + 1, map));

        return treeNode;
    }
}
