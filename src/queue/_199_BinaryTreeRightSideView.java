package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-right-side-view/
 *
 * 题目描述：给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 
 * 限制条件：
 *  （1）二叉树的节点个数的范围是 [0, 100]
 *  （2）-100 <= Node.val <= 100
 *  
 *  示例：
 *          1       <--
 *         / \     
 *        2   3     <--
 *        \    \
 *         5    4   <--
 *         
 *   从右向左看上面的二叉树，得到的结果是 1、3、4
 */
public class _199_BinaryTreeRightSideView {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        root.left = node2;
        root.right = node3;
        
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node2.right = node5;
        node3.right = node4;

        _199Solution solution = new _199Solution();
        
        System.out.println(solution.rightSideView(root)); // output: [1,3,4]
    }
}

/**
 * 对二叉树进行层序遍历
 */
class _199Solution {
    
    private class Pair {
        int level;
        TreeNode node;
        
        Pair(int level, TreeNode node) {
            this.level = level;
            this.node = node;
        }
    }
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>(); // 存储最终的结果，result[i] 表示第 i 层最右边节点的值
        LinkedList<Pair> queue = new LinkedList<>();
        
        if (null == root) {
            return result;
        }
        
        queue.addLast(new Pair(0, root));
        
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int level = pair.level;
            TreeNode node = pair.node;
            
            if (level == result.size()) {
                result.add(node.val); // 是新一层的第一个节点，则直接加入该节点的值即可
            } else {
                result.set(level, node.val); // 不是新一层的节点，则直接用该节点更新对应位置的值，当遍历完一层之后， result[level] 处的值即为 level 这一层最右边的节点的值
            }
            
            if (null != node.left) {
                queue.addLast(new Pair(level + 1, node.left));
            }
            if (null != node.right) {
                queue.addLast(new Pair(level + 1, node.right));
            }
        }
        
        return result;
    }
}

