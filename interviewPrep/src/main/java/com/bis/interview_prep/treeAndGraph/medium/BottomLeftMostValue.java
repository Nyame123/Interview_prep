package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

public class BottomLeftMostValue {

    public static void main(String[] args) {

    }

    int min = 0;
    int depth = 0;
    public int findBottomLeftValue(TreeNode<Integer> root) {
        findBottomLeftValue(root,1);

        return min;
    }

    void findBottomLeftValue(TreeNode<Integer> root, int h){
        if(root == null)
            return;

        if(h > depth){
            min = root.data;
            depth = h;
        }
        findBottomLeftValue(root.left,h+1);
        findBottomLeftValue(root.right,h+1);
    }
}
