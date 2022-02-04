package recursion;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/
 *
 * 题目描述：给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 *        差值是一个正数，其数值等于两值之差的绝对值。（此题与第 530 题一样）
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
public class _783_MinimumDistanceBetweenBSTNodes {

    public static void main(String[] args) {
        String data = "[4,2,6,1,3]";
        TreeNode root = TreeUtil.buildTree(data);
        
        _783Solution solution = new _783Solution();
        
        System.out.println(solution.minDiffInBST(root));
    }
}


/**
 * 中序遍历平衡二叉树，得到一个有序的列表。之后循环遍历该有序列表，计算出前后两个相邻数之差，然后将差和最小值进行比较。
 * 因为是最小的差值，那么该差值一定在相邻数之间产生。
 */
class _783Solution {
    
    private List<Integer> list = null;
    
    private void inOrder(TreeNode root) {
        if (null == root) {
            return;
        }
        
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
    
    public int minDiffInBST(TreeNode root) {

        list = new ArrayList<>();
        
        inOrder(root);
        
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); ++i) {
            min = Math.min(min, Math.abs(list.get(i) - list.get(i - 1)));
        }
        
        return min;
    }
}