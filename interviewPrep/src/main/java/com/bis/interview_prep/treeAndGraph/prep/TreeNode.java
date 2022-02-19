package com.bis.interview_prep.treeAndGraph.prep;

public class TreeNode<T> {

    public TreeNode<T> left;
    public TreeNode<T> right;
    public TreeNode<T> parent;
    public int level;
    public int leftSize;
    public int rank;
    public T data;

    public TreeNode() {

    }

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
