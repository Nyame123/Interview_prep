package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.hard.PrintKPathBinaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BottomMostViewValue {

    public static void main(String[] args) {
        PrintKPathBinaryTree.Node root = new PrintKPathBinaryTree.Node(20);
        root.left = new PrintKPathBinaryTree.Node(8);
        root.right = new PrintKPathBinaryTree.Node(22);
        root.left.left = new PrintKPathBinaryTree.Node(5);
        root.left.right = new PrintKPathBinaryTree.Node(3);
        root.right.left = new PrintKPathBinaryTree.Node(4);
        root.right.right = new PrintKPathBinaryTree.Node(25);
        root.left.right.left = new PrintKPathBinaryTree.Node(10);
        root.left.right.right = new PrintKPathBinaryTree.Node(14);

        System.out.println("Bottom view of the given binary tree:");
        bottomView(root);
    }

    static void bottomView(PrintKPathBinaryTree.Node root){
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        Queue<PrintKPathBinaryTree.Node> queue = new LinkedList<>();
        queue.add(root);

        Map<Integer,Integer> pos = new HashMap<>();
        pos.put(root.data,0);
        treeMap.put(0,root.data);

        while (!queue.isEmpty()){
            PrintKPathBinaryTree.Node cur = queue.poll();
            
            if (cur.left != null){
                queue.add(cur.left);
                int leftPos = pos.get(cur.data)-1;
                pos.put(cur.left.data,leftPos);
                treeMap.put(leftPos,cur.left.data);
            }

            if (cur.right != null){
                queue.add(cur.right);
                int rightPos = pos.get(cur.data)+1;
                pos.put(cur.right.data,rightPos);
                treeMap.put(rightPos,cur.right.data);
            }
        }

        for(int d: treeMap.values()){
            System.out.print(d+" ");
        }

    }

    static void buildBottomValues(PrintKPathBinaryTree.Node root,TreeMap<Integer,Integer> treeMap,int pos){

    }
}
