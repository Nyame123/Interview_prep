package com.bis.interview_prep.treeAndGraph.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Given the reference to the root of a binary search tree and a target value, return whether or
 * not two individual values within the tree can sum to the target.
 * <p>
 * Ex: Given the following tree and target…
 * <p>
 * 1
 * / \
 * 2   3, target = 4, return true.
 * Ex: Given the following tree and target…
 * <p>
 * 1
 * / \
 * 2   3, target = 7, return false.
 **/
public class TreePair {

    public static void main(String[] args) {
              /*
                15
                / \
               10 20
               / \ / \
              8 12 16 25 */

        Node root = NewNode(15);
        root.left = NewNode(10);
        root.right = NewNode(20);
        root.left.left = NewNode(8);
        root.left.right = NewNode(12);
        root.right.left = NewNode(16);
        root.right.right = NewNode(25);

        //System.out.println(inOrderTraversal(root,33));
        System.out.println(isPairPresent(root,8));
    }

    /**
     * For we to check the pair from the BST that can sum up to the target
     * We can generate an inorder traversal from the tree and check if there are
     * a pair of items that sum up to the target.
     *
     * InOrder Traversal will give a sorted array
     *Time Complexity = O(N)
     * Space Complexity = O(N)
     **/
    static boolean inOrderTraversal(Node root,int target){
        List<Integer> ans = new ArrayList<>();
        inOrderTraversal(root,ans);

        int left = 0, right = ans.size()-1;
        while (left < right){
            int l = ans.get(left);
            int r = ans.get(right);
            if (l + r == target){
                return true;
            }else if (l+r < target){
                left++;
            }else{
                right--;
            }
        }

        return false;
    }

    static void inOrderTraversal(Node root, List<Integer> list){
        if (root == null){
            return;
        }

        inOrderTraversal(root.left,list);
        list.add(root.data);
        inOrderTraversal(root.right,list);
    }

    /**
     * We can optimize the above solution to use a stack data structure
     * 1. We do a normal inOrder traversal from the root and a reverse traversal from
     * root simultaneously.
     * 2. We compare if the left and right values sum up to the target.
     *
     * Doing the inOrder and reverse traversal iteratively is important so that we
     * can get control on the flow.
     *
     * This strategy gives us
     * Time Complexity = O(N)
     * Space Complexity = O(logN)
     **/

    static boolean isPairPresent(Node root, int target){
        //inOrder traversal stack
        Deque<Node> stack1 = new ArrayDeque<>();
        Deque<Node> stack2 = new ArrayDeque<>();

        boolean finished1 = false, finished2 = false;
        int val1 = 0, val2 = 0;
        Node root1 = root, root2 = root;
        while (true){

            while (!finished1){
                if (root1 != null) {
                    stack1.push(root1);
                    root1 = root1.left;
                }else {
                    if (!stack1.isEmpty()) {
                        root1 = stack1.pop();
                        val1 = root1.data;
                        finished1 = true;
                        root1 = root1.right;
                    }
                }

            }


            while (!finished2){
                if (root2 != null){
                    stack2.push(root2);
                    root2 = root2.right;
                }else {
                    if (!stack2.isEmpty()) {
                        root2 = stack2.pop();
                        val2 = root2.data;
                        finished2 = true;
                        root2 = root2.left;
                    }
                }
            }

            //since BST is sorted, the left value cannot be greater than
            // the right value
            if (val1 >= val2){
                return false;
            }

            if (val1 + val2 == target){
                return true;
            }else if (val1+val2 < target){
                finished1 = false;
            }else if (val1+val2 > target){
                finished2 = false;
            }
        }
    }

    static Node NewNode(int val) {
        Node tmp = new Node();
        tmp.data = val;
        tmp.right = tmp.left = null;
        return tmp;
    }

    public static class Node {
        public int data;
        public Node left, right;

        public Node(){

        }

        public Node(int x) {
            data = x;
            left = right = null;
        }
    }
}
