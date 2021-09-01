package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * 
 * 题目描述：二叉树中的路径，被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中至多出现一次。
 *        该路径至少包含一个节点，且不一定经过根节点。“路径和”是路径中各节点值的总和。
 *        给你一个二叉树的根节点 root，返回其最大路径和。
 * 
 * 条件限制：
 *  （1）树中节点数目范围是 [1, 3 * 10^4]
 *  （2）-1000 <= Node.val <= 1000
 */
public class _124_BinaryTreeMaximumPathSum {

    public static void main(String[] args) {
        // test case 1, output: 6
//        TreeNode root = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        root.left = node2;
//        root.right = node3;
        
        // test case 2, output: 42
        TreeNode root = new TreeNode(-10);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        root.left = node9;
        root.right = node20;
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node20.left = node15;
        node20.right = node7;
        
        _124Solution solution = new _124Solution();
        
        System.out.println(solution.maxPathSum(root));
    }
}

/**
 * 使用递归求解。在求解的时候，需要注意：sum(root) 求解的是 root 二叉树中单侧路径的最大值。但是在求解路径最大和时，需要考虑双侧路径。
 * 单侧路径是下面两种情形的路径：
 *        root      root
 *        /            \
 *      左子树           右子树
 * 双侧路径指如下的路径：
 *           root 
 *           /  \
 *       左子树  右子树
 */
class _124Solution {

    private int maxSum = Integer.MIN_VALUE;

    // 获取 root 单侧路径中的最大和（即只能包含左子树或右子树一边的节点）
    private int sum(TreeNode root) {
        if (null == root) {
            return 0;
        }

        int leftSum = sum(root.left);
        leftSum = Math.max(0, leftSum); // 因为是求最大和，所以如果所得 sum 比 0 还小，则没有必要考虑左子树的 sum
        int rightSum = sum(root.right);
        rightSum = Math.max(0, rightSum);

        // 在求最大 sum 时，需要考虑双侧路径，既要考虑左侧路径又要考虑右侧路径
        // leftSum + rightSum + root.val 实际上包含如下三种情况：
        //  （1）leftSum = 0，即不考虑左侧，只考虑根节点和右侧
        //  （2）rightSum = 0，即不考虑右侧，只考虑根节点和左侧
        //  （3）leftSum = 0 & rightSum = 0，左右子树都不考虑，只考虑 root 节点（root 节点必须考虑，如果不考虑，则无法向上连通其它节点）
        maxSum = Math.max(maxSum, leftSum + rightSum + root.val);

        return root.val + Math.max(leftSum, rightSum); // 求解 root 的单侧路径时，只考虑单侧路径，所以只取左右路径中的最大值
    }

    public int maxPathSum(TreeNode root) {
        sum(root);

        return maxSum;
    }
}