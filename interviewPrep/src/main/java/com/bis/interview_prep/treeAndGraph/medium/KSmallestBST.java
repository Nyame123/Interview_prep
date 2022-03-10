package com.bis.interview_prep.treeAndGraph.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class KSmallestBST {

    public static void main(String[] args) {
        Node root = null;
        int[] keys = {20, 8, 22, 4, 12, 10, 14};

        for (int x : keys)
            root = insert(root, x);

        int k = 3;
       kSmallestRec(root, k);
       System.out.println(res);
    }



    /**
     * In this solution, we will use an in-order traversal to achieve the
     * result.
     * 1. In BST, the invariant property follows Left-Root-Right and this is like
     * In-Order traversal.
     * 2. We consider the left which is smaller first then the Root and then Right.
     * 3. The second part is printing the Kth smallest item.
     * 4. We would use recursion since it makes the work neat.
     *
     *
     * Another Solution is using Morris Traversal -LOOK IT UP
     * =================================================
     **/

    static List<Integer> arr = new ArrayList<>();
    private static void printKthSmallestUsingInOrderTraversal(Node root, int k) {
       printKthSmallestUsingInOrderTraversal(root);
       int n = arr.size();
       System.out.println(arr.get(k-1));
    }



    private static void printKthSmallestUsingInOrderTraversal(Node root) {
        //base case
        if(root == null){
            return;
        }

        printKthSmallestUsingInOrderTraversal(root.left);
        arr.add(root.data);
        printKthSmallestUsingInOrderTraversal(root.right);
    }

    /**
     * This step uses recursion and very similar to the first one
     * but here, we break the recursion once the solution is found.
     * No need to generate every item in ascending order
     **/
    static int res = 0,index = 0;
    private static void kSmallestRec(Node root, int k) {
        if (root == null){
            return;
        }

        kSmallestRec(root.left,k);
        index++;
        if (index == k){
            res = root.data;
            return;
        }

        kSmallestRec(root.right,k);
    }

    /**
     * One can use Iteration to solve this problem and it gives more control
     * as when the kth item is found then you can break out of the iteration
     * which is less costly in time.
     **/
    static int kSmallestBst(Node root,int k){
        Deque<Node> stack = new LinkedList<>();

        while (!stack.isEmpty() || root != null){

            if (root != null) {
                stack.push(root);
                root = root.left;
            }else{
                root = stack.pop();
                if (--k == 0){
                    return root.data;
                }
                root = root.right;
            }
        }

        return -1;
    }

    // Recursive function to insert an key into BST
    public static Node insert(Node root, int x) {
        if (root == null)
            return new Node(x);
        if (x < root.data)
            root.left = insert(root.left, x);
        else if (x > root.data)
            root.right = insert(root.right, x);
        return root;
    }

    // A BST node
    static class Node {
        int data;
        Node left, right;

        Node(int x) {
            data = x;
            left = right = null;
        }
    }
}
