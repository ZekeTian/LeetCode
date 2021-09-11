package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/balanced-binary-tree/
 * 
 * 题目描述：给定一个二叉树，判断它是否是高度平衡的二叉树。
 *                   本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *                   
 * 条件限制：
 *  （1）树中的节点数量范围：[0, 5000]
 *  （2）-10^4 <= Node.val <= 10^4
 *
 * 示例：
 *      示例 1
 *                          3
 *                        /   \
 *                      9     20
 *                           /     \
 *                         15    7
 *      Input: root = [3,9,20,null,null,15,7]
 *      Output: true
 *      
 *      示例 2
 *                         1
 *                       /   \
 *                     2     2
 *                   /  \
 *                 3    3
 *               /  \
 *              4   4
 *     Input: root = [1,2,2,3,3,null,null,4,4]
 *     Output: false
 *     
 *     示例 3
 *     Input: root = []
 *     Output: true
 */
public class _110_BalancedBinaryTree {

    public static void main(String[] args) {
        // test case 1, output:  true
        TreeNode root = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        
        root.left = node9;
        root.right = node20;
        node20.left = node15;
        node20.right = node7;
        
        _110Solution solution = new _110Solution();
        
        System.out.println(solution.isBalanced(root));
    }
}

class _110Solution {
    
    private int getHeight(TreeNode node) {
        if (null == node) {
            return 0;
        }
        
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }
    
    
    public boolean isBalanced(TreeNode root) {
        if (null == root) {
           return true;
        }
        
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        
        return isBalanced(root.left) && isBalanced(root.right);
    }
}

