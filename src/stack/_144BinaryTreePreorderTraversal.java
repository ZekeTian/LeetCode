package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 * 题目描述：给定一棵二叉树的根顶点，返回这棵二叉树的先序遍历结果
 * 
 * 条件限制：
 *  （1）二叉树中节点的数量的范围：[0, 100]
 *  （2）-100 <= Node.val <= 100
 * 
 */
public class _144BinaryTreePreorderTraversal {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);

        root.right = node2;
        node2.left = node3;

        //        TreeNode root = null;

        //        _144Solution1 solution = new _144Solution1();
        _144Solution2 solution = new _144Solution2();

        System.out.println(solution.preorderTraversal(root));
    }
}

/**
 * 解法一：使用递归
 */
class _144Solution1 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        preorderTraversal(root, result);

        return result;
    }

    private void preorderTraversal(TreeNode root, List<Integer> result) {
        if (null == root) {
            return;
        }

        // 根顶点
        result.add(root.val);

        // 左子节点
        preorderTraversal(root.left, result);

        // 右子节点
        preorderTraversal(root.right, result);
    }
}

/**
 * 使用非递归方式实现。此方法不是常规的经典非递归实现，而是采用模拟系统栈的方式实现，思路更加通用，可用于先、中、后三种任意遍历方式。
 */
class _144Solution2 {
    /**
     * 执行命令，用于模拟系统栈
     */
    private class Command {
        String operation; // 命令对应的操作，在遍历二叉树中操作中，只含有两种：（1）go：按照先、中、后相应顺序处理节点  （2）：print：访问节点，打印节点或保存节点数据
        TreeNode data; // 数据

        public Command(String operation, TreeNode data) {
            this.operation = operation;
            this.data = data;
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();

        if (null == root) {
            return result;
        }

        Stack<Command> stack = new Stack<Command>(); // 用于模拟系统栈
        Command command = new Command("go", root); // 最初
        stack.push(command);

        while (!stack.isEmpty()) {
            Command popCommand = stack.pop();
            if ("go".equals(popCommand.operation)) { // 对节点进行处理
                // 因为是先序遍历，顺序为：根、左、右，但是因为栈是先进后出，所以命令的入栈顺序刚好相反，应该为：右、左、根
                if (null != popCommand.data.right) {
                    stack.push(new Command("go", popCommand.data.right));
                }
                if (null != popCommand.data.left) {
                    stack.push(new Command("go", popCommand.data.left));
                }
                stack.push(new Command("print", popCommand.data)); // 此时，再对根节点进行的操作应当是访问
            } else {
                if (!"print".equals(popCommand.operation)) {
                    throw new IllegalArgumentException("Illegal Operation!");
                }

                // 对节点进行访问
                result.add(popCommand.data.val);
            }
        }

        return result;
    }
}
