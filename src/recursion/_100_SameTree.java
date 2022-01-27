package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/same-tree/
 * 
 * 题目描述：给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *        如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 
 * 限制条件：
 *  （1）两棵树上的节点数目都在范围 [0, 100] 内
 *  （2）-10^4 <= Node.val <= 10^4
 * 
 * 示例：
 *  示例 1
 *          1         1
 *         / \       / \
 *        2   3     2   3
 *      输入：p = [1,2,3], q = [1,2,3]
 *      输出：true
 *      
 *  示例 2
 *         1         1
 *        /           \
 *       2             2
 *      输入：p = [1,2], q = [1,null,2]
 *      输出：false
 */
public class _100_SameTree {

    public static void main(String[] args) {
        // test case1, output: true
//        String dataP = "[1,2,3]";
//        String dataQ = "[1,2,3]";
        
        // test case2, output: false
        String dataP = "[1,2]";
        String dataQ = "[1,null,2]";
        
        
        TreeNode p = TreeUtil.buildTree(dataP);
        TreeNode q = TreeUtil.buildTree(dataQ);
        
        _100Solution solution = new _100Solution();
        
        System.out.println(solution.isSameTree(p, q));
    }
}

class _100Solution {
    
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (null == p && null == q) {
            return true;
        }
        
        if (null == p || null == q) {
            return false;
        }
        
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
