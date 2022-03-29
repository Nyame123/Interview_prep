package com.bis.interview_prep.treeAndGraph.easy;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        levelOrderTraversal(root);
    }

    /**
     * We can do a level traversal with BFS algorithm
     * 1. We can use two queues to do the level traversal.
     * 2. We use one queue to display the level nodes while the other
     * queue is used to hold the neighbouring nodes.
     * <p>
     * In this process, we will use a different strategy by using one queue.
     * We count the items in a level and starting displaying them while
     * we still keep the neighbouring items in the same queue.
     * <p>
     * Time Complexity = O(N)
     **/
    static void levelOrderTraversal(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (true) {

            int size = queue.size();
            if (size == 0) {
                break;
            }

            while (size > 0) {
                Node cur = queue.poll();
                System.out.print(cur.key + " ");
                if (cur.left != null)
                    queue.add(cur.left);
                if (cur.right != null)
                    queue.add(cur.right);
                size--;
            }

            System.out.println();
        }
    }

    public static void levelOrder(PreOrderTraversal.Node root) {
        //base case
        if (root == null) {
            return;
        }

        Queue<QNode> queue = new ArrayDeque<>();
        queue.add(new QNode(0, root));
        while (!queue.isEmpty()) {
            QNode node = queue.poll();
            System.out.print(node.node.data + " ");
            //visit the neighbour
            if (node.node.left != null) {
                queue.add(new QNode(node.level + 1, node.node.left));
            }

            if (node.node.right != null) {
                queue.add(new QNode(node.level + 1, node.node.right));
            }
        }

    }

    static class QNode {
        PreOrderTraversal.Node node;
        int level;

        QNode() {

        }

        QNode(int l, PreOrderTraversal.Node n) {
            this.level = l;
            this.node = n;
        }
    }
}
