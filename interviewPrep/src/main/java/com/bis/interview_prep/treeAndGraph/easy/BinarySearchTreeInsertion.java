package com.bis.interview_prep.treeAndGraph.easy;

public class BinarySearchTreeInsertion {

    public static void main(String[] args) {
        PreOrderTraversal.Node root = new PreOrderTraversal.Node(4);
        root.right = new PreOrderTraversal.Node(7);
        root.left = new PreOrderTraversal.Node(2);
        root.left.left = new PreOrderTraversal.Node(1);
        root.left.left.right = new PreOrderTraversal.Node(3);

        PreOrderTraversal.Node result = insert1(root,6);
        preOrder(result);
    }

    public static void preOrder(PreOrderTraversal.Node root ) {

        if( root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

    public static PreOrderTraversal.Node insert(PreOrderTraversal.Node root,int data) {

        if(root == null){
            return new PreOrderTraversal.Node(data);
        }
        if(data <= root.data){
            root.left = insert(root.left,data);
        }else{
            root.right = insert(root.right,data);
        }

        return root;

    }

    public static PreOrderTraversal.Node insert1(PreOrderTraversal.Node root,int data) {

        if(data <= root.data){
             if(root.left == null){
                 root.left = new PreOrderTraversal.Node(data);
                 return root;
             }
              insert1(root.left,data);
        }else{
             if(root.right == null){
                 root.right = new PreOrderTraversal.Node(data);
                 return root;
             }

             insert1(root.right,data);
        }

        return root;

    }
}
