package queue;

import java.util.LinkedList;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/
 * 
 * 题目描述：给定一个二叉树的 root ，确定它是否是一个 完全二叉树 。
 *         在一个 完全二叉树 中，除了最后一个关卡外，所有关卡都是完全被填满的，并且最后一个关卡中的所有节点都是尽可能靠左的。
 *         它可以包含 1 到 2h 节点之间的最后一级 h 。
 *
 * 限制条件：
 *  （1）树的结点数在范围  [1, 100] 内。
 *  （2）1 <= Node.val <= 1000
 *  
 * 示例：
 *  示例 1
 *           1
 *         /   \
 *        2     3
 *       / \   /
 *      4  5  6
 *  输入：root = [1,2,3,4,5,6]
 *  输出：true
 *  解释：最后一层前的每一层都是满的（即，结点值为 {1} 和 {2,3} 的两层），且最后一层中的所有结点（{4,5,6}）都尽可能地向左。
 *  
 *  示例 2
 *           1
 *         /   \
 *        2     3
 *       / \     \
 *      4  5      7
 *  输入：root = [1,2,3,4,5,null,7]
 *  输出：false
 *  解释：值为 7 的结点没有尽可能靠向左侧。
 *  
 */
public class _958_CheckCompletenessOfABinaryTree {

    public static void main(String[] args) {
        // test case1, output: true
//        String data = "[1,2,3,4,5,6]";
        
        // test case2, output: false
        String data = "[1,2,3,4,5,null,7]";
        
        TreeNode root = TreeUtil.buildTree(data);
        _958Solution solution = new _958Solution();
        
        System.out.println(solution.isCompleteTree(root));
    }
}

/**
 * 如果一棵二叉树是完全二叉树，则 BFS 得到的顺序是连续的（即中间不会有 null）节点；
 * 反之，则 BFS 得到的顺序不连续（即中间有 null 节点）。 
 * 根据该特点，判断一棵二叉树是否是完全二叉树。
 */
class _958Solution {
    
    public boolean isCompleteTree(TreeNode root) {
        if (null == root) {
            return true;
        }
        
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean isNull = false; // 标记是否遇到了空节点
        
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (isNull && poll != null) {
                // 当前节点不为 null，但是之前已经碰到 null 节点，
                // 说明 BFS 得到的顺序不连续（即中间有 null 节点），则不是完全二叉树
                return false;
            }
            
            if (null == poll) {
                isNull = true;
            } else {
                queue.addLast(poll.left);
                queue.addLast(poll.right);
            }
        }
        
        return true;
    }
}
