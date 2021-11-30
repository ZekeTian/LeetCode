package dynamicprogramming;

import java.util.HashMap;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/house-robber-iii/
 * 
 * 题目描述：在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 
 *        除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 *        如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *        计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * 
 * 
 * 限制条件：
 *  （1）节点数量范围：[1, 10^4]
 *  （2）0 <= Node.val <= 10^4
 * 
 * 示例：
 *    示例 1
 *           3
 *         /  \
 *        2    3
 *         \    \
 *         3     1
 *    输入：[3,2,3,null,3,null,1]
 *    输出：7
 *    解释：小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *    
 *    示例 2
 *           3
 *         /  \
 *        4    5
 *       / \    \
 *      1  3     1
 *     输入：[3,4,5,1,3,null,1]
 *     输出：9
 *     解释：小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class _337_HouseRobberIII {
    
    public static void main(String[] args) {
        // test case 1, output: 7
//        TreeNode root = new TreeNode(3);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node31 = new TreeNode(3);
//        root.left = node2;
//        root.right = node31;
//        TreeNode node32 = new TreeNode(3);
//        TreeNode node1 = new TreeNode(1);
//        node2.right = node32;
//        node31.right = node1;
        
        // test case 2, output: 9
        TreeNode root = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node4;
        root.right = node5;
        
        TreeNode node11 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        node4.left = node11;
        node4.right = node3;
        
        TreeNode node12 = new TreeNode(1);
        node5.right = node12;
        
        _337Solution solution = new _337Solution();
        
        System.out.println(solution.rob(root));
    }
}

/**
 * 自底向上 + 记忆化搜索
 */
class _337Solution {
    
    private HashMap<TreeNode, Integer> memo = new HashMap<>();
    
    private int tryRob(TreeNode root) {
        if (null == root.left && null == root.right) {
            memo.put(root, root.val);
            return root.val;
        }
        
        // 先不断递归，直到叶子节点
        if (null != root.left) {
            tryRob(root.left);
        }
        if (null != root.right) {
            tryRob(root.right);
        }
        
        // 递归到叶子节点之后，在返回的过程中开始计算
        if (-1 != memo.getOrDefault(root, -1)) {
            return memo.get(root);
        }
        
        // 偷当前节点和孙子节点的房间
        int res1 = root.val;
        if (null != root.left) {
            res1 = res1 + (null == root.left.left ? 0 : memo.get(root.left.left))
                    + (null == root.left.right ? 0 : memo.get(root.left.right));
        }
        if (null != root.right) {
            res1 = res1 + (null == root.right.left ? 0 : memo.get(root.right.left))
                    + (null == root.right.right ? 0 : memo.get(root.right.right));
        }
        
        // 不偷当前节点的房间，偷子节点的房间
        int res2 = 0;
        if (null != root.left) {
            res2 = res2 + memo.get(root.left);
        }
        if (null != root.right) {
            res2 = res2 + memo.get(root.right);
        }
        
        memo.put(root, Math.max(res1, res2));
        return Math.max(res1, res2);
    }
    
    public int rob(TreeNode root) {
        return tryRob(root);
    }
}

