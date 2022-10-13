package com.bis.interview_prep.treeAndGraph.easy;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a reference to the root of a binary tree,
 * return a list containing the average value in each level of the tree.
 **/
public class AveragesOfLevel {

    public static void main(String[] args) {
        /* Let us construct a Binary Tree
     4
    / \
    2  9
   / \  \
   3  5  7 */

        Node root = null;
        root = newNode(4);
        root.left = newNode(2);
        root.right = newNode(9);
        root.left.left = newNode(3);
        root.left.right = newNode(5);
        root.right.right = newNode(7);
        System.out.println("Averages of levels : ");
        System.out.print("[");

        averageOfLevelsDFS(root);
        System.out.println("]");
    }


    static void averageOfLevelsDFS(Node root) {
        List<Double> sum = new ArrayList<>();
        List<Integer> count = new ArrayList<>();
        averageOfLevelsRec(root, 0, sum, count);
        int n = sum.size();
        for (int i = 0; i < n; i++) {
            sum.set(i, sum.get(i) / count.get(i));
        }

        System.out.print(sum);
    }

    static void averageOfLevelsRec(Node root, int level, List<Double> sum, List<Integer> count) {
        if (root == null) {
            return;
        }
        if (level < sum.size()) {
            sum.set(level, (double) root.key + sum.get(level));
            count.set(level, count.get(level) + 1);

        } else {
            sum.add((double) root.key);
            count.add(1);
        }


        averageOfLevelsRec(root.left, level + 1, sum, count);
        averageOfLevelsRec(root.right, level + 1, sum, count);

    }

    static void averageOfLevelsBFS(Node root) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int sum = 0, count = 0;
            //Temp queue
            Queue<Node> temp = new LinkedList<>();
            while (!queue.isEmpty()) {
                Node val = queue.poll();
                sum += val.key;
                count++;
                if (val.left != null) {
                    temp.add(val.left);
                }

                if (val.right != null) {
                    temp.add(val.right);
                }
            }

            queue = temp;

            System.out.print(sum * 1.0 / count + " ");
        }

    }

    static Node newNode(int data) {
        Node temp = new Node();
        temp.key = data;
        temp.left = null;
        temp.right = null;
        return temp;
    }
}
