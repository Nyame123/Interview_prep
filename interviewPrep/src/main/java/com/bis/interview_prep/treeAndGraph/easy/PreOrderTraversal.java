package com.bis.interview_prep.treeAndGraph.easy;

import java.util.Stack;

public class PreOrderTraversal {

    public static void main(String[] args) {

    }
    //Using recursive mode
    public static void preOrderRecursive(Node root) {
        //base case
        if(root == null){
            return;
        }

        System.out.print(root.data+" ");
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);

    }

    public static void preOrderIterative(Node root){
        if(root == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            System.out.print(node.data+" ");

            if(node.right != null){
                stack.push(node.right);
                //System.out.printf("Right %d-",node.right.data);
            }

            if(node.left != null){
                stack.push(node.left);
                //System.out.printf("Left %d-",node.left.data);
            }

        }
    }

   static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
}


