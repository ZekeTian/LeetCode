package recursion;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 *
 * 题目描述：给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *        差值是一个正数，其数值等于两值之差的绝对值。（此题与第 783 题一样）
 *        
 * 限制条件：
 *  （1）树中节点的数目范围是 [2, 10^4]
 *  （2）0 <= Node.val <= 10^5
 * 
 * 示例：
 *          4
 *         / \
 *        2   6
 *       / \
 *      1   3
 *  输入：root = [4,2,6,1,3]
 *  输出：1
 */
public class _530_MinimumAbsoluteDifferenceInBST {

    public static void main(String[] args) {
        String data = "[4,2,6,1,3]";
        TreeNode root = TreeUtil.buildTree(data);
        
        _530Solution solution = new _530Solution();
        
        System.out.println(solution.getMinimumDifference(root));
    }
}

class _530Solution {
    
    private List<Integer> list = new ArrayList<>();
    
    private void inOrder(TreeNode root) {
        if (null == root) {
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
    
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        
        if (list.size() <= 1) {
            return 0;
        }
        
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); ++i) {
            minDiff = Math.min(minDiff, Math.abs(list.get(i) - list.get(i - 1)));
        }
        
        return minDiff;
    }
}
