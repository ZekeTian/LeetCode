package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/subtree-of-another-tree/
 * 
 * 题目描述：给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。
 *         如果存在，返回 true ；否则，返回 false 。
 *         二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 *         
 * 限制条件：
 *  （1）root 树上的节点数量范围是 [1, 2000]
 *  （2）subRoot 树上的节点数量范围是 [1, 1000]
 *  （3）-10^4 <= root.val <= 10^4
 *  （4）-10^4 <= subRoot.val <= 10^4
 * 
 * 示例：
 *  示例 1
 *          3
 *         / \
 *        4   5         4
 *       / \           / \
 *      1   2         1   2
 *      输入：root = [3,4,5,1,2], subRoot = [4,1,2]
 *      输出：true
 *      解释：左边的二叉树中含有一棵与右边二叉树一样的子树
 *  
 *  示例 2
 *         3
 *        / \
 *       4   5         4
 *      / \           / \
 *     1   2         1   2
 *        /
 *       0
 *     输入：root = [3,4,5,1,2,null,null,null,null,0], subRoot = [4,1,2]
 *     输出：false
 *     解释：左边的二叉树中不含有与右边二叉树一样的子树
 */
public class _572_SubtreeOfAnotherTree {
    
    public static void main(String[] args) {
        // test case1, output: true
        String tree = "[3,4,5,1,2]";
        String subStree = "[4,1,2]";
        
        // test case2, output: false
//        String tree = "[3,4,5,1,2,null,null,null,null,0]";
//        String subStree = "[4,1,2]";
        
        TreeNode root = TreeUtil.buildTree(tree);
        TreeNode subRoot = TreeUtil.buildTree(subStree);
        
        _572Solution solution = new _572Solution();
        
        System.out.println(solution.isSubtree(root, subRoot));
    }
}

class _572Solution {
    
    // 判断 root 对应的二叉树和 subRoot 对应的子树是否一样
    private boolean isSame(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        
        if (root == null || subRoot == null) {
            return false;
        }
        
        if (root.val != subRoot.val) {
            return false;
        }
        
        return isSame(root.left, subRoot.left) && isSame(root.right, subRoot.right);
    }
    
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        
        if (root == null) {
            return false; // root 为 null，subRoot 不为 null，则 root 对应的二叉树中一定不可能含有 subRoot 对应的子树
        }
        
        // 判断 root 节点对应的二叉树和 subRoot 对应的子树是否一样
        if (isSame(root, subRoot)) {
            return true; // 如果一样，则找到子树，返回 true
        }
        
        // root 对应的二叉树和 subRoot 对应的子树不一样，则去 root 的左右子树中寻找，看是否含有 subRoot 对应的子树
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
}
