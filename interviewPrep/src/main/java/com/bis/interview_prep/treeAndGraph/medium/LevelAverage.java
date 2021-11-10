package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Finding the level average of a non-empty binary tree
 **/
public class LevelAverage {

    public static void main(String[] args) {
      /*  TreeNode<Integer> root = new TreeNode<>(1);
        root.setLeft(new TreeNode<>(2));
        root.setRight(new TreeNode<>(3));
        root.getLeft().setLeft(new TreeNode<>(4));
        root.getLeft().setRight(new TreeNode<>(5));
        root.getRight().setLeft(new TreeNode<>(6));
        root.getRight().setRight(new TreeNode<>(7));*/

        TreeNode<Integer> root = new TreeNode<>(3);
        root.setLeft(new TreeNode<>(9));
        root.setRight(new TreeNode<>(20));
        root.getRight().setLeft(new TreeNode<>(15));
        root.getRight().setRight(new TreeNode<>(7));

        List<Double> averages = findLevelAverage(root);
        System.out.println(averages);
    }

    static List<Double> findLevelAverage(TreeNode<Integer> root) {

        List<Double> averages = new ArrayList<>();
        Queue<TreeNode<Integer>> queue = new ArrayDeque<>();
        //initialize level of the root
        root.level = 0;
        queue.add(root);

        int level = 0;
        int levelCount = 0, levelSum = 0;
        while (!queue.isEmpty()){
            TreeNode<Integer> current = queue.poll();

            //check if it is the same level
            if (level == current.level){
                levelCount++;
                levelSum += current.getData();
            }else{
                //add the average to the result
                double avg = (double) levelSum / levelCount;
                averages.add(avg);
                //different level, then reset counter and sum
                level = current.level;
                levelCount = 1;
                levelSum = current.getData();
            }

            //add the left of the current to the queue
            if (current.getLeft() != null){
                current.getLeft().level = current.level + 1;
                queue.add(current.getLeft());
            }

            //add the left of the current to the queue
            if (current.getRight() != null){
                current.getRight().level = current.level + 1;
                queue.add(current.getRight());
            }
        }

        //add the average to the result
        double avg = (double) levelSum / levelCount;
        averages.add(avg);

        return averages;
    }
}
