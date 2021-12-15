package com.bis.interview_prep.treeAndGraph.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class InOrderTraversal {

    public static void main(String[] args) {
        PreOrderTraversal.Node root = new PreOrderTraversal.Node(1);
        root.right = new PreOrderTraversal.Node(2);
        root.right.right = new PreOrderTraversal.Node(5);
        root.right.right.left = new PreOrderTraversal.Node(3);
        root.right.right.right = new PreOrderTraversal.Node(6);
        root.right.right.left.right = new PreOrderTraversal.Node(4);

        inOrderIterative(root);
    }

    public static void inOrderRecursive(PreOrderTraversal.Node root) {
        //base case
        if(root == null){
            return;
        }

        inOrderRecursive(root.left);
        System.out.print(root.data+" ");
        inOrderRecursive(root.right);
    }

    public static void inOrderIterative(PreOrderTraversal.Node root) {
        Deque<PreOrderTraversal.Node> stack = new ArrayDeque<>();
        //stack.push(root);
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                System.out.print(root.data+" ");
                root = root.right;
            }
        }
    }

}
