package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 * 
 * 题目描述：给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 k 。
 *         返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 *         
 * 限制条件：
 *  （1）节点数在 [1, 500] 范围内
 *  （2）0 <= Node.val <= 500
 *  （3）Node.val 中所有值 不同
 *  （4）目标结点 target 是树上的结点。
 *  （5）0 <= k <= 1000
 *  
 * 
 * 示例：
 *  示例 1    
 *              3
 *            /   \
 *           5     1
 *          / \   / \
 *         6  2  0   8
 *           / \
 *          7   4   
 *      输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 *      输出：[7,4,1]
 *      解释：所求结点为与目标结点（值为 5）距离为 2 的结点，值分别为 7，4，以及 1
 *  
 *  示例 2
 *      输入: root = [1], target = 1, k = 3
 *      输出: []
 *      
 */
public class _863_AllNodesDistanceKIinBinaryTree {
    
    // 在以 root 为根顶点的树中寻找值为 val 的节点
    private static TreeNode getNodeByVal(TreeNode root, int val) {
        if (null == root) {
            return null;
        }
        
        if (root.val == val) {
            return root;
        }
        
        TreeNode left = getNodeByVal(root.left, val);
        if (left != null) {
            return left;
        }
        
        TreeNode right = getNodeByVal(root.right, val);
        if (right != null) {
            return right;
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        // test case1, output: [7,4,1]
//        String data = "[3,5,1,6,2,0,8,null,null,7,4]";
//        int targetVal = 5;
//        int k = 2;
        
        // test case2, output: []
        String data = "[1]";
        int targetVal = 1;
        int k = 3;
        
        TreeNode root = TreeUtil.buildTree(data);
        TreeNode target = getNodeByVal(root, targetVal);
        
        
        _863Solution solution = new _863Solution();
        
        System.out.println(solution.distanceK(root, target, k));
    }
    
}

/**
 * 思路：记录每个节点的父节点，从而可以向上遍历。该过程，相当于是将树转换成图，然后从 target 节点出发，走 k 步。
 *      从 target 节点出发，进行遍历时，可以分成如下两种类型的遍历方式：
 *          （1）向下遍历，即常规的深度优先遍历
 *          （2）向上遍历，需要去遍历父节点，同时在遍历父节点的时候，还需要向下去遍历。
 *              因为在遍历父节点时，还需要向下遍历，所以可能会存在重复遍历。为了避免重复遍历，需要使用一个集合 set 
 *              保存所有已经遍历过的节点。
 */
class _863Solution {
    
    private Map<TreeNode, TreeNode> parentMap = null; // key: 节点 node, value: 节点 node 对应的父节点
    private List<Integer> resultList = null; // 最终的结果
    private int k = 0; // 距离
    
    // 在 parentMap 中记录 node 的父节点 parent
    private void recordParent(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        parentMap.put(node, parent);
        
        // 继续向下遍历 
        recordParent(node.left, node);
        recordParent(node.right, node);
    }
    
    // 从 target 节点向上遍历，但是其它节点不仅可以向上遍历还可以向下遍历
    private void upward(TreeNode node, int dis, Set<TreeNode> set) {
        if (node == null || set.contains(node)) {
            return; // node 节点为空，或已经遍历过该节点，则直接结束即可
        }
        
        ++dis;
        if (dis == k) {
            resultList.add(node.val);
            return; // 距离已经满足条件，则直接结束
        }
        set.add(node); // 将 node 添加进 set 中，标记已经访问
        
        // 沿父节点遍历
        TreeNode parent = parentMap.get(node);
        upward(parent, dis, set);
        
        // 沿子节点遍历
        upward(node.left, dis, set);
        upward(node.right, dis, set);
    }
    
    // 从 node 节点向下遍历（即常规的 DFS 遍历），此时距离 target 的距离是 dis
    private void downward(TreeNode node, int dis) {
        if (node == null) {
            return;
        }
        
        ++dis; // 到达 node 节点，则距离 dis 可以加 1
        if (dis == k) {
            resultList.add(node.val);
            return;
        }
        
        // 距离 dis 小于 k，则继续向下遍历
        downward(node.left, dis);
        downward(node.right, dis);
    }
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        this.parentMap = new HashMap<>();
        this.resultList = new ArrayList<>();
        this.k = k;
        
        if (null == root || null == target) {
            return resultList;
        }
        
        if (k == 0) {
            resultList.add(target.val);
            return resultList;
        }
        
        // 记录各个节点的父节点
        recordParent(root, null);
        
        // 沿 target 的子节点向下遍历
        downward(target.left, 0);
        downward(target.right, 0);
        
        // 向 target 的父节点向上遍历
        Set<TreeNode> set = new HashSet<>(); // 用于存储已经遍历过的节点
        set.add(target); // 因为是从 target 出发，所以 target 相当于是已经访问过，故直接存储进 set 中
        upward(parentMap.get(target), 0, set);
        
        return resultList;
    }
    
}
