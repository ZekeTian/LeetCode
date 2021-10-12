package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/symmetric-tree/
 * 
 * 题目描述：给定一个二叉树，检查它是否是镜像对称的。
 * 
 * 条件限制：
 *  （1）树中顶点数量的范围：[1, 1000]
 *  （2）-100 <= Node.val <= 100
 * 
 * 示例：
 *  示例 1
 *           1
 *         /   \
 *        2     2
 *       / \   / \
 *      3  4  4   3
 *  上面这棵二叉树即为一棵镜像对称的二叉树。
 *  
 *  示例 2
 *          1
 *        /   \
 *        2    2
 *         \    \
 *         3    3
 *  上面这棵二叉树不是镜像二叉树。
 *
 */
public class _101_SymmetricTree {

}

/**
 * 解法一：使用递归，左右子树同时分别进行遍历。
 *      当左子树处理左节点时，右子树处理右节点；当左子树处理右节点时，右子树处理左节点。
 */
class _101Solution1 {
    
    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (null == left && null == right) { // 左右子树对应的节点都为 null，则依然对称，返回 true，不需要再进行后面的操作
            return true;
        }
        
        if (null == left || null == right) { // 左右子树中有一个为 null，有一个不为 null，则不对称，直接返回
            return false;
        }
        
        return (left.val == right.val) /* 判断左右子树对应的节点是否相等 */
                && isSymmetric(left.left, right.right) /* 当左子树处理左节点时，右子树处理右节点 */
                && isSymmetric(left.right, right.left); /* 当左子树处理右节点时，右子树处理左节点 */
    }
    
    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        
        return isSymmetric(root.left, root.right);
    }
}
