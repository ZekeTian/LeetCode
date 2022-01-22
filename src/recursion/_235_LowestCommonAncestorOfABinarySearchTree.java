package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * 
 * 题目描述：给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。百度百科中最近公共祖先的定义为：
 *       “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 
 * 限制条件：
 *  （1）所有节点的值都是唯一的。
 *  （2）p、q 为不同节点且均存在于给定的二叉搜索树中。
 * 
 * 示例：
 *          6
 *        /   \
 *       2     8
 *      / \   / \
 *     0   4 7   9
 *        / \
 *       3   5
 *       
 *  输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 *  输出: 6 
 *  解释: 节点 2 和节点 8 的最近公共祖先是 6。
 *  
 */
public class _235_LowestCommonAncestorOfABinarySearchTree {

    public static void main(String[] args) {
        String data = "[6,2,8,0,4,7,9,null,null,3,5]";
        
        TreeNode root = TreeUtil.buildTree(data);
        TreeNode p = root.left;
        TreeNode q = root.right;
        
//        _235Solution1 solution = new _235Solution1();

        _235Solution2 solution = new _235Solution2();
        
        TreeNode res = solution.lowestCommonAncestor(root, p, q);
        
        System.out.println(res.val);
    }
}

/**
 * 解法一：利用二分搜索树的性质进行搜索
 */
class _235Solution1 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || null == p || null == q) {
            return null;
        }
        
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        
        return root;
    }
}

/**
 * 解法二：使用第 236 题的思路（普通二叉树的通用解法）
 */
class _235Solution2 {
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (null == root || null == p || null == q) {
            return null;
        }
        
        if (root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (null == root.left) {
            return right;
        }
        if (null == root.right) {
            return left;
        }
        
        return root;
    }
}
