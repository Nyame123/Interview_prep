package com.bis.interview_prep.treeAndGraph.medium;

import com.bis.interview_prep.treeAndGraph.prep.TreeNode;

public class DeepestNodeBinaryTree {

    static int maxLevel = -1, res = -1;
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);
        root.left.left = new TreeNode<>(4);
        root.right.left = new TreeNode<>(5);
        root.right.right = new TreeNode<>(6);
        root.right.left.right = new TreeNode<>(7);
        root.right.right.right = new TreeNode<>(8);
        root.right.left.right.left = new TreeNode<>(9);
        System.out.println(deepestNode(root));
    }

    private static int deepestNode(TreeNode<Integer> root) {
        deepestNodeLevel(root,0);
        return res;
    }

    static void deepestNodeLevel(TreeNode<Integer> root,int level){

        if (root != null){
            if (level > maxLevel){
                maxLevel = level;
                res = root.data;
            }
            deepestNodeLevel(root.left,level+1);
            deepestNodeLevel(root.right,level+1);

        }
    }
}
