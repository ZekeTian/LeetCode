package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/path-sum/
 * 
 * 题目描述：给定一棵二叉树的根顶点和一个整数 targetSum。如果二叉树中存在一条从根顶点到叶子顶点的路径，使得路径上所有整数之和等于 targetSum，则返回 true。
 *       在本题中，如果给定的是一棵空树，即使 targetSum = 0，也应返回 false。
 *       
 * 条件限制：
 *  （1）二叉树中顶点的数量范围：[0, 5000]
 *  （2）-1000 <= Node.val <= 1000
 *  （3）-1000 <= targetSum <= 1000
 */
public class _112_PathSum {

    public static void main(String[] args) {
        // test case 1
        //        TreeNode root = new TreeNode(5);
        //        TreeNode node4 = new TreeNode(4);
        //        TreeNode node8 = new TreeNode(8);
        //        TreeNode node11 = new TreeNode(11);
        //        TreeNode node13 = new TreeNode(13);
        //        TreeNode node4_2 = new TreeNode(4);
        //        TreeNode node7 = new TreeNode(7);
        //        TreeNode node2 = new TreeNode(2);
        //        TreeNode node1 = new TreeNode(1);
        //        
        //        root.left = node4;
        //        root.right = node8;
        //        node4.left = node11;
        //        node8.left = node13;
        //        node8.right = node4_2;
        //        node11.left = node7;
        //        node11.right = node2;
        //        node4_2.right = node1;

        // test case 2
        //        TreeNode root = new TreeNode(1);
        //        TreeNode node2 = new TreeNode(2);
        //        TreeNode node3 = new TreeNode(3);
        //        root.left = node2;
        //        root.right = node3;

        // test case 3
        TreeNode root = null;

        _112Solution solution = new _112Solution();
        //        System.out.println(solution.hasPathSum(root, 22)); // test case 1, true
        //        System.out.println(solution.hasPathSum(root, 0)); // test case 2, false
        System.out.println(solution.hasPathSum(root, 0)); // test case 3, false

    }
}

class _112Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return false;
        }

        if (null == root.left && null == root.right) { // 判断是否是叶子节点
            // 叶子节点，则判断叶子节点的值是否和 targetSum 相等
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
