package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 * 
 * 题目描述：给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，
 *         请你构造并返回这颗 二叉树 。
 * 
 * 限制条件：
 *  （1）1 <= inorder.length <= 3000
 *  （2）postorder.length == inorder.length
 *  （3）-3000 <= inorder[i], postorder[i] <= 3000
 *  （4）inorder 和 postorder 都由 不同 的值组成
 *  （5）postorder 中每一个值都在 inorder 中
 *  （6）inorder 保证是树的中序遍历
 *  （7）postorder 保证是树的后序遍历
 *  
 * 示例：
 *  示例 1
 *              3
 *             / \
 *            9   20
 *               /  \
 *              15   7  
 *      输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 *      输出：[3,9,20,null,null,15,7]
 * 
 *  示例 2
 *      输入：inorder = [-1], postorder = [-1]
 *      输出：[-1]
 */
public class _106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    
    public static void main(String[] args) {
        // test case1, output: 3 9 20 15 7
//        int[] inorder = { 9, 3, 15, 20, 7 };
//        int[] postorder = { 9, 15, 7, 20, 3 };
    
        // test case2, output: -1
        int[] inorder = { -1 };
        int[] postorder = { -1 };
        
        _106Solution solution = new _106Solution();
        
        TreeNode root = solution.buildTree(inorder, postorder);
        
        TreeUtil.levelOrder(root);
    }

}

/**
 * 中序遍历顺序：左 根 右
 * 后序遍历顺序：左 右 根
 * 
 * 思路：
 *  因为在后序遍历中，根节点是最后一个值，所以通过后序遍历，可以确定根节点的值 rootVal；
 *  然后再在中序中，根据根节点的值找到根节点的下标 rootIdx；
 *  之后计算出中序、后序中各个左右子树的下标范围。
 *      中序：{inL ... rootIdx-1}, rootIdx, {rootIdx+1 ... inR}
 *          左子树的下标范围：[inL, rootIdx - 1]；右子树的下标范围：[rootIdx + 1, inR]
 *      后序：{postL ... x}, {x+1 ... postR-1}, postR(根节点)
 *          因为中序和后序中左右子树的节点数量一样，所以 x - postL + 1 = rootIdx - 1 - inL + 1 -> x = rootIdx - 1 - inL + postL
 *          即，左子树的下标范围：[postL, rootIdx - 1 - inL + postL]；右子树的下标范围：[rootIdx - inL + postL, postR - 1]
 *          
 *  最后，递归构建左、右子树。
 *  当左右子树的区间非法时，递归结束。
 *  
 */
class _106Solution {
    
    private int[] inorder = null;
    private int[] postorder = null;
    
    // 根据 inorder[intL ... inR]、postorder[postL ... postR] 构建二叉树
    private TreeNode build(int inL, int inR, int postL, int postR) {
        if (inL > inR) {
            // 区间非法，无法创建二叉树（实际上，此时已经构建完二叉树）
            return null;
        }
        
        // 确定根节点的值
        int rootVal = postorder[postR];
        // 在中序遍历中，根据根顶点的值，确定根顶点在中序遍历中的下标
        int rootIdx = 0;
        for (int i = inL; i <= inR; ++i) {
            if (inorder[i] == rootVal) {
                rootIdx = i;
                break;
            }
        }
        
        // 创建根顶点
        TreeNode rootNode = new TreeNode(rootVal);
        
        // 通过中序遍历中根顶点的下标 rootIdx，可以计算出左右子树的下标范围，然后去构建左右子树
        rootNode.left = build(inL, rootIdx - 1, postL, rootIdx - 1 - inL + postL); 
        rootNode.right = build(rootIdx + 1, inR, rootIdx - inL + postL, postR - 1);
        
        return rootNode;
    }
    
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        
        return build(0, inorder.length - 1, 0, postorder.length - 1);
    }
}