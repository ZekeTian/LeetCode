package datastructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class TreeUtil {
    
    private static class Pair {
        TreeNode node;
        int level;
        
        Pair(TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }

    public static void levelOrder(TreeNode root) {
        if (null == root) {
            return;
        }
        
        LinkedList<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            TreeNode node = poll.node;
            int level = poll.level;
            
            System.out.print(node.val + " ");
            
            if (null != node.left) {
                queue.add(new Pair(node.left, level + 1));
            }
            if (null != node.right) {
                queue.add(new Pair(node.right, level + 1));
            }
        }
        
        System.out.println();
    }
    
    /**
     * 根据输入的字符串序列创建二叉树
     * 示例：
     *  输入：root = [1,2,3,null,null,4,5]
     *  创建如下的二叉树
     *           1
     *          / \
     *         2   3
     *            / \
     *           4   5
     */
    public static TreeNode buildTree(String data) {
        if (data == null || data.length() == 0 || "[]".equals(data)) {
            return null;
        }
        
        // 分割得到单个节点的值
        String[] values = data.substring(1, data.length() - 1) /* 去除前后的 [] */
                              .split(",");
        
        if ("null".equals(values[0])) {
            return null; // 根顶点为 null
        }
        
        // 将根顶点添加到 levelList 中
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        List<List<TreeNode>> levelList = new ArrayList<>(); // 存放树中每层节点
        levelList.add(new ArrayList<>());
        levelList.get(0).add(root);
        int proccesedNodeNum = 1; // 已经处理节点个数
        
        while (proccesedNodeNum < values.length) {
            // 创建一个 list 存放当前层节点
            List<TreeNode> curLevel = new ArrayList<>();
            // 获取上层节点（因为当前层节点还没有加进 levelList 中，所以 levelList 中的最后一层即为上一层）
            List<TreeNode> upperLevel=  levelList.get(levelList.size() - 1);
            
            for (TreeNode node : upperLevel) {
                // 取出 node 左右子树的值，并创建相应子节点
                if (proccesedNodeNum < values.length) {
                    String left = values[proccesedNodeNum++];
                    node.left = ("null".equals(left) ? null : new TreeNode(Integer.parseInt(left)));
                    if (null != node.left) {
                        curLevel.add(node.left); // 将子节点添加到当前层中
                    }
                }
                
                if (proccesedNodeNum < values.length) {
                    String right = values[proccesedNodeNum++];
                    node.right = ("null".equals(right) ? null : new TreeNode(Integer.parseInt(right)));
                    if (null != node.right) {
                        curLevel.add(node.right);
                    }
                }
            }
            
            levelList.add(curLevel); // 将当前层节点添加到 levelList 中
        }
        
        return root;
    }
}
