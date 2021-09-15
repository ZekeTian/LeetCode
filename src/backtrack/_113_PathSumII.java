package backtrack;

import java.util.ArrayList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/path-sum-ii/
 * 
 * 题目描述：给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。叶子节点 是指没有子节点的节点。
 * 
 * 限制条件：
 *  （1）树中节点总数在范围 [0, 5000] 内
 *  （2）-1000 <= Node.val <= 1000
 *  （3）-1000 <= targetSum <= 1000
 *  
 * 示例：
 *      示例 1
 *             5
 *           /   \
 *          4     8
 *         /     / \
 *        11    13  4
 *       / \       / \
 *      7  2      5   1
 *      输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 *      输出：[[5,4,11,2],[5,8,4,5]]
 *      
 *      示例 2
 *            1
 *           / \
 *          2   3
 *      输入：root = [1,2,3], targetSum = 5
 *      输出：[]
 */
public class _113_PathSumII {

    public static void main(String[] args) {
        // test case, output: [[5,4,11,2],[5,8,4,5]]
        TreeNode root = new TreeNode(5);
        TreeNode node4_1 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        root.left = node4_1;
        root.right = node8;
        
        TreeNode node11 = new TreeNode(11);
        node4_1.left = node11;
        
        TreeNode node13 = new TreeNode(13);
        TreeNode node4_3 = new TreeNode(4);
        node8.left = node13;
        node8.right = node4_3;
        
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        node11.left = node7;
        node11.right = node2;
        
        TreeNode node5 = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        node4_3.left = node5;
        node4_3.right = node1;
        
        _113Solution solution = new _113Solution();
        List<List<Integer>> pathList = solution.pathSum(root, 22);
        
        System.out.println(pathList);
    }
}


class _113Solution {
    
    private List<List<Integer>> resultList = new ArrayList<>();
    
    private void getPath(TreeNode root, int targetSum, List<Integer> path) {
        if (null == root.left && null == root.right) {
            // 到达叶子节点
            if (targetSum == root.val) {
                // 此时得到的路径，其路径和等于 targetSum，则该路径满足条件。但是需要注意的是：要将最后一个节点加入到路径中，然后再才将整个路径添加到 resultList 中
                path.add(root.val); // 将最后的叶子节点加入到 path 中得到最终完整的路径
                resultList.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
            }
            
            return;
        }
        
        if (null != root.left) {
            path.add(root.val);
            getPath(root.left, targetSum - root.val, path);
            path.remove(path.size() - 1);
        }
        if (null != root.right) {
            path.add(root.val);
            getPath(root.right, targetSum - root.val, path);
            path.remove(path.size() - 1);
        }
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return resultList;
        }
        
        getPath(root, targetSum, new ArrayList<Integer>());
        
        return resultList;
    }
}
