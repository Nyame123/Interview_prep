package com.bis.interview_prep.treeAndGraph.easy;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewTree {

    public static void main(String[] args) {
        QNode root = new QNode(1);
        root.left = new QNode(2);
        root.right = new QNode(3);
        root.left.right = new QNode(4);
        root.left.right.right = new QNode(5);
        root.left.right.right.right = new QNode(6);

        topView(root);
    }

    public static void topView(QNode root) {
        Queue<QNode> queue = new ArrayDeque<>();
        root.level = 0;
        queue.add(root);

        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (!queue.isEmpty()) {
            QNode item = queue.poll();

            if (!map.containsKey(item.level)) {
                map.put(item.level, item.data);
            }

            if (item.right != null) {
                item.right.level = item.level + 1;
                queue.add(item.right);
            }

            if (item.left != null) {
                item.left.level = item.level - 1;
                queue.add(item.left);
            }

        }

        for (int d : map.values()) {
            System.out.print(d + " ");
        }


    }

    static class QNode {

        int data;
        int level;
        QNode left;
        QNode right;

        QNode() {

        }

        QNode(int key) {
            this.data = key;
        }

        QNode(int key, int level) {
            this.data = key;
            this.level = level;
        }

    }
}
