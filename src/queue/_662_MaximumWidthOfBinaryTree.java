package queue;

import java.util.LinkedList;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/maximum-width-of-binary-tree/
 * 
 * 题目描述：给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
 *         这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *         每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *         
 * 限制条件：答案在32位有符号整数的表示范围内。
 * 
 * 
 * 示例：  
 *  示例 1
 *              1
 *            /   \
 *           3     2
 *          / \     \  
 *         5   3     9 
 *      输出: 4
 *      解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 *      
 *  示例 2
 *             1
 *            /   
 *           3    
 *          / \     
 *         5   3   
 *      输出: 2
 *      解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 *      
 *  示例 3
 *              1
 *            /   \
 *           3     2
 *          /  
 *         5 
 *      输出: 2
 *      解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 *      
 */
public class _662_MaximumWidthOfBinaryTree {

    
    public static void main(String[] args) {
        // test case1, output: 4
//        String data = "[1,3,2,5,3,null,9]";
        
        // test case2, output: 2
//        String data = "[1,3,null,5,3]";
        
        // test case3, output: 2
        String data = "[1,3,2,5]";
        
        
        TreeNode root = TreeUtil.buildTree(data);
        
        _662Solution solution = new _662Solution();
        
        
        System.out.println(solution.widthOfBinaryTree(root));
    }
    
}

/**
 * 广度优先遍历二叉树，并且计算出每个节点在对应完全二叉树中的下标。
 * 然后利用每层第一个节点和最后一个节点的下标计算出当前层的宽度。
 */
class _662Solution {
    
    private class Pair {
        TreeNode node;
        int index;
        
        Pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
    
    public int widthOfBinaryTree(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 1));
        int maxWidth = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size(); // 当前层含有的节点个数
            int start = queue.peekFirst().index; // 当前层第一个节点的下标
            int end = queue.peekLast().index; // 当前层最后一个节点的下标
            maxWidth = Math.max(maxWidth, end - start + 1); // 更新最大宽度
            
            // 将当前层所有的节点全部出队，然后将下一层的节点加入队列中
            for (int i = 0; i < size; ++i) {
                Pair pair = queue.poll();
                TreeNode node = pair.node;
                int index = pair.index;
                
                if (node.left != null) {
                    queue.add(new Pair(node.left, 2 * index)); // 计算出左子节点的下标，并加入队列中
                }
                if (node.right != null) {
                    queue.add(new Pair(node.right, 2 * index + 1)); // 计算出右子节点的下标，并加入队列中
                }
            }
        }
        
        return maxWidth;
    }
}
