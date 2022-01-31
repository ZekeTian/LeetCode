package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode-cn.com/problems/path-sum-iii/
 * 
 * 题目描述：给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的路径的数目。
 *        路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 
 * 限制条件：
 *  （1）二叉树的节点个数的范围是 [0,1000]
 *  （2）-10^9 <= Node.val <= 10^9 
 *  （3）-1000 <= targetSum <= 1000
 * 
 * 示例：
 *                   10
 *                 /    \
 *                5     -3
 *              /   \     \
 *             3     2    11
 *            / \     \
 *           3  -2     1
 *  
 *  输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 *  输出：3
 *  解释：和等于 8 的路径有 3 条。
 */
public class _437_PathSumIII {

    public static void main(String[] args) {
        String data = "[10,5,-3,3,2,null,11,3,-2,null,1]";
        
        TreeNode root = TreeUtil.buildTree(data);
        int targetSum = 8;
        
        _437Solution solution = new _437Solution();
        
        System.out.println(solution.pathSum(root, targetSum));
    }
}


class _437Solution {
    
    // 统计以 root 为根顶点，并且路径和为 targetSum 的路径数量
    private int rootSum(TreeNode root, int targetSum) {
        if (null == root) {
            return 0;
        }
        
        int count = 0;
        if (targetSum == root.val) {
            ++count;
        }
        
        count += rootSum(root.left, targetSum - root.val); // 以 root.left 为根顶点
        count += rootSum(root.right, targetSum - root.val); // 以 root.right 为根顶点
        
        return count;
    }
    
    public int pathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return 0;
        }
        
        int count = rootSum(root, targetSum); // 以 root 为根顶点的路径数量
        count += pathSum(root.left, targetSum); // 不以 root 为根顶点，root 左子树中满足要求的路径数量
        count += pathSum(root.right, targetSum); // 不以 root 为根顶点，root 右子树中满足要求的路径数量 
        
        return count;
    }
}