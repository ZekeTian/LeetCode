package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/diameter-of-binary-tree/
 * 
 * 题目描述：给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *       注意：两结点之间的路径长度是以它们之间边的数目表示。
 * 
 * 限制条件：
 *  （1）树中结点数量的范围： [1, 10^4]
 *  （2）-100 <= Node.val <= 100
 *
 * 示例：
 *        1
 *       / \
 *      2   3
 *     / \
 *    4   5
 *  输入：root = [1,2,3,4,5]
 *  输出：3
 *  解释：最长的路径，[4,2,1,3] 或者 [5,2,1,3]。
 *
 */
public class _543_DiameterOfBinaryTree {

    public static void main(String[] args) {
        String data = "[1,2,3,4,5]";
        
        TreeNode root = TreeUtil.buildTree(data);
        
        _543Solution solution = new _543Solution();
        
        System.out.println(solution.diameterOfBinaryTree(root));
    }
}


class _543Solution {
    
    // 计算以 root 为根顶点的二叉树的高度
    private int height(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    
    
    public int diameterOfBinaryTree(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        // 路径经过 root 节点
        int d1 = height(root.left) + height(root.right);
        
        // 路径不经过 root 节点，经过左子树或右子树
        int d2 = Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right));
        
        return Math.max(d1, d2);
    }
}


