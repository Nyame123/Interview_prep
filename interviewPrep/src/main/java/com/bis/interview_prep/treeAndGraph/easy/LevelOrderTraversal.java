package com.bis.interview_prep.treeAndGraph.easy;

import java.util.ArrayDeque;
import java.util.Queue;

public class LevelOrderTraversal {

    static class QNode{
        PreOrderTraversal.Node node;
        int level;
        QNode(){

        }

        QNode(int l,PreOrderTraversal.Node n){
            this.level = l;
            this.node = n;
        }
    }

    public static void levelOrder(PreOrderTraversal.Node root) {
        //base case
        if(root == null){
            return;
        }

        Queue<QNode> queue = new ArrayDeque<>();
        queue.add(new QNode(0,root));
        while(!queue.isEmpty()){
            QNode node = queue.poll();
            System.out.print(node.node.data+" ");
            //visit the neighbour
            if(node.node.left != null){
                queue.add(new QNode(node.level+1,node.node.left));
            }

            if(node.node.right != null){
                queue.add(new QNode(node.level+1,node.node.right));
            }
        }

    }
}
