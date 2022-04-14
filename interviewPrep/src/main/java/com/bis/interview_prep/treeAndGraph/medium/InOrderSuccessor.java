package com.bis.interview_prep.ndGraph.medium;

/**
 * Given a node in a binary search  return the next bigger element, also known as the inorder successor.
 * For example, the inorder successor of 22 is 30.
 * <p>
 * 10
 * /  \
 * 5    30
 * /  \
 * 22    35
 * You can assume each node has a parent pointer.
 **/
public class InOrderSuccessor {

    public static void main(String[] args) {
        Node root = null, temp = null, suc = null, min = null;
        root = insert(root, 20);
        root = insert(root, 8);
        root = insert(root, 22);
        root = insert(root, 4);
        root = insert(root, 12);
        root = insert(root, 10);
        root = insert(root, 14);
        temp = root.left.right.right;
        suc = inOrderSuccessorWithNoParent(root, temp);
        if (suc != null) {
            System.out.println(
                    "Inorder successor of "
                            + temp.data + " is " + suc.data);
        }
        else {
            System.out.println(
                    "Inorder successor does not exist");
        }
    }


    /**
     * 1. InOrder Successor is the next bigger element of a particular element.
     * 2. In BST tree, the items are already sorted so to get the next bigger element,
     * we search the right subtree is the node has right subtree.
     * 3. If there is no right subtree, we travel up the parent to get the node whose left is the searched node.
     *
     * Time Complexity = O(h); h = depth of the BST.
     **/
    private static Node inOrderSuccessor(Node root, Node temp) {
        if (temp == null){
            return null;
        }

        if (temp.right != null){
            return minValueFromRight(temp);
        }

        Node p = temp.parent;
        while (p != null && p.right == temp){
            temp = p;
            p = temp.parent;
        }

        return p;
    }

    /**
     * Assuming we do not have a link to the parent
     * then we can get this by using the root of the node.
     *
     **/
    private static Node inOrderSuccessorWithNoParent(Node root, Node temp) {
        if (temp == null){
            return null;
        }

        if (temp.right != null){
            return minValueFromRight(temp);
        }

        Node succ = null;
        Node cur = root;
        while (cur != null){
            if (temp.data < cur.data){
                succ = cur;
                cur = cur.left;
            }else  if (temp.data > cur.data){
                cur = cur.right;
            }else {
                break;
            }
        }

        return succ;
    }

    private static Node minValueFromRight(Node temp) {
        Node cur =  temp;
        while (cur.left != null){
            cur = cur.left;
        }

        return cur;
    }

    static Node insert(Node node, int data) {

        /* 1. If the is empty, return a new,
         single node */
        if (node == null) {
            return (new Node(data));
        } else {

            Node temp = null;

            /* 2. Otherwise, recur down the */
            if (data <= node.data) {
                temp = insert(node.left, data);
                node.left = temp;
                temp.parent = node;
            } else {
                temp = insert(node.right, data);
                node.right = temp;
                temp.parent = node;
            }

            /* return the (unchanged) node pointer */
            return node;
        }
    }


    static class Node {

        int data;
        Node left, right, parent;

        Node(int d) {
            data = d;
            left = right = parent = null;
        }
    }
}
