package com.bis.interview_prep.treeAndGraph.easy;

import static com.bis.interview_prep.treeAndGraph.easy.AveragesOfLevel.newNode;

/**
 * Given an N-ary tree, return its maximum depth.
 * Note: An N-ary tree is a tree in which any node may have at most N children.
 *
 * Ex: Given the following tree…
 *
 *             4
 *           / | \
 *          3  9  2
 *         /       \
 *        7         2
 * return 3.
 **/
public class DepthN_Arry {

    public static void main(String[] args) {
        /* Let us create below tree
         *             A
         *         / / \ \
         *     B F D E
         *     / \ | /|\
         *     K J G C H I
         *     /\         \
         * N M         L
         */

        Node root = newNode('A');
        (root.child).add(newNode('B'));
        (root.child).add(newNode('F'));
        (root.child).add(newNode('D'));
        (root.child).add(newNode('E'));
        (root.child.get(0).child).add(newNode('K'));
        (root.child.get(0).child).add(newNode('J'));
        (root.child.get(2).child).add(newNode('G'));
        (root.child.get(3).child).add(newNode('C'));
        (root.child.get(3).child).add(newNode('H'));
        (root.child.get(3).child).add(newNode('I'));
        (root.child.get(0).child.get(0).child).add(newNode('N'));
        (root.child.get(0).child.get(0).child).add(newNode('M'));
        (root.child.get(3).child.get(2).child).add(newNode('L'));

        System.out.print(depthOfTree(root) + "\n");
    }

    static int depthOfTree(Node root) {
        //base case
        if (root == null){
            return 0;
        }
        int maxDepth = 0;
        for (int i = 0; i < root.child.size(); i++) {
            maxDepth = Math.max(maxDepth,depthOfTree(root.child.get(i)));
        }

        return maxDepth+1;
    }
}
