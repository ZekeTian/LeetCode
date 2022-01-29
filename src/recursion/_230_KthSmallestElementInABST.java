package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * 
 * 题目描述：给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * 
 * 限制条件：
 *  （1）树中的节点数为 n 。
 *  （2）1 <= k <= n <= 10^4
 *  （3）0 <= Node.val <= 10^4
 *
 * 示例：
 *          3
 *         / \
 *        1   4
 *         \
 *          2
 *  输入：root = [3,1,4,null,2], k = 1
 *  输出：1
 */
public class _230_KthSmallestElementInABST {

    public static void main(String[] args) {
        String data = "[3,1,4,null,2]";
        
        TreeNode root = TreeUtil.buildTree(data);
//        int k = 1; // output: 1
        int k = 3; // output: 3
        
        _230Solution solution = new _230Solution();
        
        System.out.println(solution.kthSmallest(root, k));
    }
}

/**
 * 中序遍历，当遍历次数达到 k 时终止，即可得到第 k 个最小元素。
 */
class _230Solution {
    
    private int count = 0;
    private int res = 0;
    private int k = 0;
    
    private void getKthSmallest(TreeNode root) {
        if (null == root) {
            return;
        }
        
        getKthSmallest(root.left);
        
        if (k == (++count)) {
            res =  root.val;
            return;
        }
        
        getKthSmallest(root.right);
    }
    
    public int kthSmallest(TreeNode root, int k) {
        if (null == root) {
            return 0;
        }
        this.k = k;
        
        getKthSmallest(root);
        
        return res;
    }
}
