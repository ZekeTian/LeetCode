package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/
 * 
 * 题目描述：给定一棵二叉树的根顶点，返回这棵树最大的深度。一棵二叉树最大的深度是指从根顶点到每个叶子节点最长路径的长度。
 * 
 */
public class _104_MaximumDepthOfBinaryTree {
    
    public static void main(String[] args) {
        // test case 1
//        TreeNode root = new TreeNode(3);
//        TreeNode node1 = new TreeNode(9);
//        TreeNode node2 = new TreeNode(20);
//        root.left = node1;
//        root.right = node2;
//
//        TreeNode node3 = new TreeNode(15);
//        TreeNode node4 = new TreeNode(7);
//        node2.left = node3;
//        node2.right = node4;
        
        
        // test case 2
        TreeNode root = null;
        
        _104Solution solution = new _104Solution();
        
        System.out.println(solution.maxDepth(root));
    }
}


class _104Solution {
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth= maxDepth(root.right);
        
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}


