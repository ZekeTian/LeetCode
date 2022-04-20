package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * 
 * 题目描述：给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *         展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 *         展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *         
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *         
 * 限制条件：
 *  （1）树中结点数在范围 [0, 2000] 内
 *  （2）-100 <= Node.val <= 100
 *
 * 示例：
 *  示例 1
 *              1               1
 *             / \               \
 *            2   5     ==>       2
 *           / \   \               \
 *          3   4   6               3
 *                                   \
 *                                    4
 *                                     \
 *                                      5
 *                                       \
 *                                        6
 *  输入：root = [1,2,5,3,4,null,6]
 *  输出：[1,null,2,null,3,null,4,null,5,null,6]
 * 
 *  示例 2
 *      输入：root = []
 *      输出：[]
 *      
 */
public class _114_FlattenBinaryTreeToLinkedList {

    public static void main(String[] args) {
        // test case1, output: 1 2 3 4 5 6 
//        String data = "[1,2,5,3,4,null,6]";
        
        // test case1, output: 
        String data = "[]";
        
        
        TreeNode root = TreeUtil.buildTree(data);
        
//        _114Solution1 solution = new _114Solution1();
        
        _114Solution2 solution = new _114Solution2();

        
        solution.flatten(root);
        
        TreeUtil.levelOrder(root);
        
    }
}

/**
 * 解法一：先序遍历（根左右），使用尾插法创建链表
 */
class _114Solution1 {
    
    private TreeNode tail = null; // 链表的尾节点
    
    public void flatten(TreeNode root) {
        if (null == root) {
            return;
        }
        
        if (tail != null) {
            // 链表不为空，则直接将 root 节点添加到链表中即可
            tail.right = root;
        }
        tail = root;
        
        // root 已经加入到链表中，则需要从二叉树中将 root 分离出来，然后再继续遍历 root 的左右子树
        TreeNode leftChild = root.left; // 在分离 root 之前，先保存左右子树，以便后面遍历
        TreeNode rightChild = root.right;
        root.left = root.right = null; // 将 root 从二叉树中分离出来
        
        flatten(leftChild);
        flatten(rightChild);
    }
}


/**
 * 解法二：按照 “右左根” 的顺序遍历，使用头插法创建链表
 *
 */
class _114Solution2 {
    
    private TreeNode head = null; // 链表头节点
    
    public void flatten(TreeNode root) {
        if (null == root) {
            return;
        }
        
        flatten(root.right);
        flatten(root.left);
        
        root.right = head;
        root.left = null; // 节点 root 加入链表后，则其原来在二叉树中的左子树需要清空掉
        head = root;
    }
}