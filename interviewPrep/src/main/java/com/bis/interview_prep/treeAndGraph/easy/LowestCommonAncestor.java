package com.bis.interview_prep.treeAndGraph.easy;

public class LowestCommonAncestor {

    public static PreOrderTraversal.Node lcaRec(PreOrderTraversal.Node root, int v1, int v2) {
        if(root == null){
            return null;
        }

        if(root.data > v1 && root.data > v2){
            lcaRec(root.left,v1,v2);
        }else if(root.data < v1 && root.data < v2){
            lcaRec(root.right,v1,v2);
        }

        return root;

    }

    public static PreOrderTraversal.Node lca(PreOrderTraversal.Node root, int v1, int v2) {
        while (root != null){
            // If both n1 and n2 are smaller
            // than root, then LCA lies in left
            if (root.data > v1 && root.data > v2){
                root = root.left;

                // If both n1 and n2 are greater
                // than root, then LCA lies in right
            }else if (root.data < v1 && root.data < v2){
                root = root.right;
            }
            else
                break;
        }
        return root;
    }
}
