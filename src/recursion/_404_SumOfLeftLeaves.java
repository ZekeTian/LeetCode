package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/sum-of-left-leaves/
 * 
 * 题目描述：计算给定二叉树的所有左叶子之和。
 * 
 * 示例：
 *        3
 *       / \
 *      9  20
 *         / \
 *        15 7
 *  输入：root = [3,9,20,null,null,15,7]
 *  输出：24
 *  解释：有两个左叶子节点，分别是 9、15，所以和是 24
 */
public class _404_SumOfLeftLeaves {

    public static void main(String[] args) {
        String data = "[3,9,20,null,null,15,7]";
        TreeNode root = TreeUtil.buildTree(data);
        
        _404Solution solution = new _404Solution();
        
        System.out.println(solution.sumOfLeftLeaves(root));
    }
}


class _404Solution {
    
    public int sumOfLeftLeaves(TreeNode root) {
        // 右子树递归结束标记
        if (null == root) {
            return 0;
        }
        
        int sum = 0;
        // 左子树递归结束标记
        if (null != root.left // 存在左节点
                && (null == root.left.left && null == root.left.right) /* 左节点为叶子节点 */ ) {
           sum += root.left.val;
        } else {
            sum += sumOfLeftLeaves(root.left); // 左子树不能结束，则继续递归
        }
        
        sum += sumOfLeftLeaves(root.right);
        
        
        return sum;
    }
}
