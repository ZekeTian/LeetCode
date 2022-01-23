package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 * 
 * 题目描述：给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 *        高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 10^4
 *  （2）-10^4 <= nums[i] <= 10^4
 *  （3）nums 按 严格递增 顺序排列
 * 
 * 示例：
 *          0                  0  
 *         / \                / \ 
 *        -3  9   或        -10  5
 *        /   /               \   \
 *      -10  5                -3   9
 *  输入：nums = [-10,-3,0,5,9]
 *  输出：[0,-3,9,-10,null,5]
 *  解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 */
public class _108_ConvertSortedArrayToBinarySearchTree {

    public static void main(String[] args) {
        int[] nums = { -10, -3, 0, 5, 9 };
        
        _108Solution solution = new _108Solution();
        
        
        TreeNode root = solution.sortedArrayToBST(nums);
        
        TreeUtil.levelOrder(root);
    }
}

/**
 * 思路：将数组从中间一分为二，中间的数作为根顶点，左边作为左子树，右边作为右子树。
 *     因为是从中间一分为二，所以左右两边的个数最多相差 1 ，即相当于左右子树高度最大相差 1。
 */
class _108Solution {
    
    private TreeNode convertToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        
        if (left == right) {
            return new TreeNode(nums[left]);
        }
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = convertToBST(nums, left, mid - 1);
        root.right = convertToBST(nums, mid + 1, right);
        
        return root;
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return null;
        }
        
        return convertToBST(nums, 0, nums.length - 1);
    }
}


