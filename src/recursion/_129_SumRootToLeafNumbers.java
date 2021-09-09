package recursion;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * 
 * 题目描述：给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。每条从根节点到叶节点的路径都代表一个数字：
 *                 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。计算从根节点到叶节点生成的 所有数字之和 。叶节点 是指没有子节点的节点。
 *
 */
public class _129_SumRootToLeafNumbers {

    public static void main(String[] args) {
        // test case, output: 25
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        
        root.left = node2;
        root.right = node3;
        
//        _129Solution1 solution = new _129Solution1();
        _129Solution2 solution = new _129Solution2();
        
        System.out.println(solution.sumNumbers(root));
        
    }
}

/**
 * 实现方式一：拼接成字符串，然后利用 Integer.parseInt() 转换成整数
 */
class _129Solution1 {
    
    private int totalSum = 0;
    
    private void sumNode(TreeNode root, String numStr) {
        if (null == root.left && null == root.right) {
            // 到达叶子节点，则将该节点的值拼接到 numStr 中，然后转换成整数累加到 totalSum 上
            totalSum += Integer.parseInt(numStr + root.val);
            return;
        }
        
        if (null != root.left) {
            sumNode(root.left, numStr + root.val);
        }
        
        if (null != root.right) {
            sumNode(root.right, numStr + root.val);
        }
    }
    
    public int sumNumbers(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        sumNode(root, "");
        
        return totalSum;
    }
}

class _129Solution2 {
    
    private int totalSum = 0;
    
    private void sumNode(TreeNode root, int num) {
        num = num * 10 + root.val;
        
        if (null == root.left && null == root.right) {
            totalSum += num;
            return;
        }
        
        if (null != root.left) {
            sumNode(root.left, num);
        }
        
        if (null != root.right) {
            sumNode(root.right, num);
        }
    }
    
    public int sumNumbers(TreeNode root) {
        if (null == root) {
            return 0;
        }
        
        sumNode(root, 0);
        
        return totalSum;
    }
}


