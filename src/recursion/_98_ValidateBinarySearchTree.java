package recursion;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * 题目描述：给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *        有效二叉搜索树定义如下：
 *          （1）节点的左子树只包含 小于 当前节点的数。
 *          （2）节点的右子树只包含 大于 当前节点的数。
 *          （3）所有左子树和右子树自身必须也是二叉搜索树。
 * 
 * 限制条件：
 *  （1）树中节点数目范围在[1, 10^4] 内
 *  （2）-2^31 <= Node.val <= 2^31 - 1
 * 
 * 示例：
 *      2
 *     / \
 *    1   3
 *  输入：root = [2,1,3]
 *  输出：true
 */
public class _98_ValidateBinarySearchTree {

    public static void main(String[] args) {
        // output: true
        String data = "[2,1,3]";
        
        TreeNode root = TreeUtil.buildTree(data);
        
        _98Solution solution = new _98Solution();
        
        
        System.out.println(solution.isValidBST(root));
    }
}


class _98Solution {
    
    private List<Integer> list = new ArrayList<>();
    
    private void inOrder(TreeNode root) {
        if (null == root) {
            return;
        }
        
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
    
    public boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }
        
        inOrder(root);
        
        for (int i = 1; i < list.size(); ++i) {
            if (list.get(i - 1) >= list.get(i)) {
                return false;
            }
        }
        
        return true;
    }
}

