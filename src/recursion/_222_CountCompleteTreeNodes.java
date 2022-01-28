package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/count-complete-tree-nodes/
 * 
 * 题目描述：给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 *        完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 *        若最底层为第 h 层，则该层包含 1~ 2^h 个节点。
 *        
 * 限制条件：
 *  （1）树中节点的数目范围是[0, 5 * 10^4]
 *  （2）0 <= Node.val <= 5 * 10^4
 *  （3）题目数据保证输入的树是 完全二叉树
 * 
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
 * 
 * 示例：
 *           1
 *         /   \
 *        2     3
 *       / \   /
 *      4   5 6
 *  输入：root = [1,2,3,4,5,6]
 *  输出：6
 * 
 */
public class _222_CountCompleteTreeNodes {

    public static void main(String[] args) {
        // test case1, output: 6
//        String data = "[1,2,3,4,5,6]";
        
        // test case2, output: 0
        String data = "[]";
        
        TreeNode root = TreeUtil.buildTree(data);
        
        _222Solution1 solution = new _222Solution1();
        
        System.out.println(solution.countNodes(root));
    }
}

/**
 * 解法一：使用遍历统计
 */
class _222Solution1 {
    
    public int countNodes(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        int count = countNodes(root.left);
        count += countNodes(root.right);
        
        return count + 1;
    }
}
