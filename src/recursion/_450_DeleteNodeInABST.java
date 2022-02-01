package recursion;

import datastructure.TreeNode;
import datastructure.TreeUtil;

/**
 * https://leetcode.com/problems/delete-node-in-a-bst/
 * 
 * 题目描述:给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *       一般来说，删除节点可分为两个步骤：
 *          (1)首先找到需要删除的节点；
 *          (2)如果找到了，删除它。
 * 
 * 限制条件:
 *  (1)节点数的范围 [0, 10^4].
 *  (2)-10^5 <= Node.val <= 10^5
 *  (3)节点值唯一
 *  (4)root 是合法的二叉搜索树
 *  (5)-10^5 <= key <= 10^5
 *
 */
public class _450_DeleteNodeInABST {

    public static void main(String[] args) {
       // ouput: 5 4 6 2 7
       String data = "[5,3,6,2,4,null,7]";
       TreeNode root = TreeUtil.buildTree(data);
       int key = 3;
       
       
       _450Solution solution = new _450Solution();
       
       
       root = solution.deleteNode(root, key);
       
       TreeUtil.levelOrder(root);
    }
}


class _450Solution {
    
    // 在以 root 为根节点的二叉树中获取最小节点
    private TreeNode getMin(TreeNode root) {
        if (null == root.left) {
            return root;
        }
        
        return getMin(root.left);
    }
    
    // 在以 root 为根节点的二叉树中删除最小节点
    private TreeNode deleteMin(TreeNode root) {
        if (null == root.left) {
            TreeNode ret = root.right;
            root.right = null;
            return ret;
        }
        
        root.left = deleteMin(root.left);
        
        return root;
    }
    
    // 获取值为 val 的节点
    private TreeNode get(TreeNode root, int val) {
        if (root.val == val) {
            return root;
        }
        
        if (root.val > val && null != root.left) {
            return get(root.left , val);
        }
        
        if (root.val < val && null != root.right) {
            return get(root.right, val);
        }
        
        return null;
    }
    
    // 在以 root 为根节点的二叉树中，删除值为 key 的节点
    private TreeNode delete(TreeNode root, int key) {
        if (null == root) {
            return null;
        }
        
        // 找到值为 key 的节点，准备删除该节点
        if (root.val == key) {
            if (null == root.left) { // root 的左节点为空，直接返回右节点即可
                TreeNode res = root.right;
                root.right = null;
                return res;
            }
            if (null == root.right) { // root 的右节点为空，直接返回左节点
                TreeNode res = root.left;
                root.left = null;
                return res;
            }
            
            // root 的左右节点均不为空，则从右节点中取出最小的节点，然后用最小节点代替 root 节点的位置
            TreeNode min = getMin(root.right);
            min.right = deleteMin(root.right);
            min.left = root.left;
            root.left = root.right = null;
            
            return min;
        }
        
        if (root.val > key) {
            root.left = delete(root.left, key); // 去左子树中寻找 key 节点
        } else {
            root.right = delete(root.right, key); // 去右子树中寻找 key 节点
        }
        
        return root;
    }
    
    public TreeNode deleteNode(TreeNode root, int key) {
        if (null == root) {
            return null;
        }
        
        // 判断待删除的节点是否存在，如果不存在，则直接返回
        TreeNode node = get(root, key);
        if (null == node) {
            return root;
        }
        
        // 删除指定节点
        return delete(root, key);
    }
}
