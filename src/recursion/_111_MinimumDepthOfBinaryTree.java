package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * 题目描述：给定一个二叉树，找出其最小深度。
 *        最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *        说明：叶子节点是指没有子节点的节点。
 * 
 * 限制条件：
 *  （1）树中节点数的范围在 [0, 10^5] 内
 *  （2）-1000 <= Node.val <= 1000
 * 
 * 示例：
 *  示例 1
 *          3
 *         / \
 *        9  20
 *           / \
 *          15  7
 *      输入：root = [3,9,20,null,null,15,7]
 *      输出：2
 *  
 *  示例 2
 *          2
 *           \
 *            3
 *             \
 *              4
 *               \
 *                5
 *                 \
 *                  6
 *       输入：root = [2,null,3,null,4,null,5,null,6]
 *       输出：5
 *       
 */
public class _111_MinimumDepthOfBinaryTree {

    public static void main(String[] args) {
        // test case1, output: 2
//        String data = "[3,9,20,null,null,15,7]";
        
        String data = "[2,null,3,null,4,null,5,null,6]";
        
        TreeNode root = TreeUtil.buildTree(data);
        
        
        _111Solution solution = new _111Solution();
        
        
        System.out.println(solution.minDepth(root));
    }
}

class _111Solution {
    
    public int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        if (null == root.left) {
            return minDepth(root.right) + 1;
        }
        
        if (null == root.right) {
            return minDepth(root.left) + 1;
        }
        
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}

