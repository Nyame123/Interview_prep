package com.bis.interview_prep.treeAndGraph.easy;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeMap;

public class TopViewTree {

    public static void main(String[] args) {

    }

    static class QNode{
        PreOrderTraversal.Node node;
        int level;
        QNode(){

        }

        QNode(PreOrderTraversal.Node n,int l){
            this.node = n;
            this.level = l;
        }
    }

    public static void topView(PreOrderTraversal.Node root) {
        Queue<QNode> queue = new ArrayDeque<>();
        queue.add(new QNode(root,0));

        TreeMap<Integer,Integer> map = new TreeMap<>();
        while(!queue.isEmpty()){
            QNode item = queue.poll();

            if(!map.containsKey(item.level)){
                map.put(item.level,item.node.data);
            }

            if(item.node.right != null){
                queue.add(new QNode(item.node.right,item.level+1));
            }

            if(item.node.left != null){
                queue.add(new QNode(item.node.left,item.level-1));
            }

        }

        for(int d: map.values()){
            System.out.print(d+" ");
        }


    }
}
