package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import datastructure.TreeNode;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * 
 * 题目描述：序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 *         同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 *         请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 *         你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *         
 *         提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 *         你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *         
 * 限制条件：
 *  （1）树中结点数在范围 [0, 10^4] 内
 *  （2）-1000 <= Node.val <= 1000
 * 
 * 示例：
 *  示例 1
 *      输入：root = [1,2,3,null,null,4,5]
 *      输出：[1,2,3,null,null,4,5]
 *      
 *  示例 2
 *      输入：root = []
 *      输出：[]
 *      
 *  示例 3
 *      输入：root = [1]
 *      输出：[1]
 *  
 *  示例 4
 *      输入：root = [1,2]
 *      输出：[1,2]
 *      
 */
public class _297_SerializeAndDeserializeBinaryTree {

    public static void main(String[] args) {
        // test case1, output: [1,2,3,null,null,4]
//        String data = "[1,2,3,null,null,4]";
        
        // test case2, output: [1,2,3,2]
        String data = "[1,2,3,2]";
        
//        _297Solution1 solution = new _297Solution1();
        
        _297Solution2 solution = new _297Solution2();
        
        TreeNode root = solution.deserialize(data);
        
        System.out.println(solution.serialize(root));
    }
}

/**
 * 实现方式一
 *  serialize 的实现方式是：遍历当前层，然后向 joiner 中添加下一层的节点
 * 使用这种实现方式，序列化出来的字符串，其后面会有多余的 null
 * 如以下的二叉树：
 *          1
 *         / \
 *        2   3
 *       /
 *      2
 * 使用方式一序列化，得到的字符串是：[1,2,3,2,null,null,null]，但是实际上最简形式应该是：[1,2,3,2]
 *
 */
class _297Solution1 {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) {
            return "[]";
        }
        
        // BFS 遍历，获得二叉树对应的层序遍历结果
        List<List<TreeNode>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int num = queue.size(); // 当前层节点的数量
            List<TreeNode> currentLevel = new ArrayList<>();
            for (int i = 0; i < num; ++i) {
                TreeNode poll = queue.poll();
                currentLevel.add(poll);
                
                if (poll.left != null) {
                    queue.addLast(poll.left);
                }
                if (poll.right != null) {
                    queue.addLast(poll.right);
                }
            }
            list.add(currentLevel);
        }
        
        // 遍历当前层，然后向 joiner 中添加下一层的节点
        StringJoiner joiner = new StringJoiner(","); // 保存每层节点（包括中间的 null 节点）
        // 先将 root 添加到 joiner 中
        joiner.add(root.val + "");
        
        for (int i = 0; i < list.size() - 1; ++i) {
            List<TreeNode> currentLevel = list.get(i);
            for (TreeNode node : currentLevel) {
                // 遍历当前层的节点，然后将下一层节点（子节点）添加到 joiner 中
                joiner.add((node.left == null ? "null" : node.left.val + ""));
                joiner.add((node.right == null ? "null" : node.right.val + ""));
            }
        }
        
        return "[" + joiner.toString() + "]";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data || data.length() == 0 || "[]".equals(data)) {
            return null;
        }
        
        String[] nodes = data.substring(1, data.length() - 1) // 去掉前后的 "[]"
                             .split(","); // 按照 ',' 分割，得到每个节点的值
        
        // 将根顶点添加到 list 中
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        List<List<TreeNode>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(root);
        
        // 遍历 nodes ，逐个创建对应的 TreeNode
        int idx = 1; // nodes 中的下标
        while (idx < nodes.length) {
            // 在创建 TreeNode 时，遍历上一层，然后创建当前层的 TreeNode
            List<TreeNode> preLevel = list.get(list.size() - 1);
            List<TreeNode> currentLevel = new ArrayList<>();
            for (TreeNode node : preLevel) {
                if (idx < nodes.length && !"null".equals(nodes[idx])) { // 左子节点
                    node.left = new TreeNode(Integer.parseInt(nodes[idx]));
                    currentLevel.add(node.left);
                }
                ++idx; // 已经处理 nodes 中的一个节点，则继续向后处理
                
                if (idx < nodes.length && !"null".equals(nodes[idx])) { // 右子节点
                    node.right = new TreeNode(Integer.parseInt(nodes[idx]));
                    currentLevel.add(node.right);
                }
                ++idx;
            }
            list.add(currentLevel);
        }
        
        return root;
    }
    
}

/**
 * 实现方式二，serialize 的实现方式是
 *  遍历当层序结果（此时的层序结果中包含 null 节点），将节点逐个加入到 joiner 中，但是对最后一层进行特殊处理，从而去掉多余的 null
 */
class _297Solution2 {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (null == root) {
            return "[]";
        }
        
        // BFS 遍历，获得二叉树对应的层序遍历结果
        List<List<TreeNode>> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        int lastNotNullIdx = 0; // 最后一个非空节点在层序中的下标
        queue.add(root);
        while (!queue.isEmpty()) {
            int num = queue.size(); // 当前层节点的数量
            List<TreeNode> currentLevel = new ArrayList<>();
            for (int i = 0; i < num; ++i) {
                TreeNode poll = queue.poll();
                currentLevel.add(poll);
                if (null != poll) {
                    queue.addLast(poll.left);
                    queue.addLast(poll.right);
                    lastNotNullIdx = i;
                }
            }
            list.add(currentLevel);
        }
        
        // 遍历当前层，然后向 joiner 中添加下一层的节点
        StringJoiner joiner = new StringJoiner(","); // 保存每层节点（包括中间的 null 节点）
        for (int i = 0; i < list.size() - 2; ++i) {
            List<TreeNode> currentLevel = list.get(i);
            for (TreeNode node : currentLevel) {
                // 遍历当前层的节点，然后将其添加到 joiner 中
                joiner.add((node == null ? "null" : node.val + ""));
            }
        }
        
        // 遍历最后一层的节点（只遍历前面的一部分，后面全部为 null 的部分不遍历）
        List<TreeNode> lastlevel = list.get(list.size() - 2);
        for (int i = 0; i <= lastNotNullIdx; ++i) {
            TreeNode node = lastlevel.get(i);
            joiner.add((node == null ? "null" : node.val + ""));
        }
        
        return "[" + joiner.toString() + "]";
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data || data.length() == 0 || "[]".equals(data)) {
            return null;
        }
        
        String[] nodes = data.substring(1, data.length() - 1) // 去掉前后的 "[]"
                             .split(","); // 按照 ',' 分割，得到每个节点的值
        
        // 将根顶点添加到 list 中
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        List<List<TreeNode>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        list.get(0).add(root);
        
        // 遍历 nodes ，逐个创建对应的 TreeNode
        int idx = 1; // nodes 中的下标
        while (idx < nodes.length) {
            // 在创建 TreeNode 时，遍历上一层，然后创建当前层的 TreeNode
            List<TreeNode> preLevel = list.get(list.size() - 1);
            List<TreeNode> currentLevel = new ArrayList<>();
            for (TreeNode node : preLevel) {
                if (idx < nodes.length && !"null".equals(nodes[idx])) { // 左子节点
                    node.left = new TreeNode(Integer.parseInt(nodes[idx]));
                    currentLevel.add(node.left);
                }
                ++idx; // 已经处理 nodes 中的一个节点，则继续向后处理
                
                if (idx < nodes.length && !"null".equals(nodes[idx])) { // 右子节点
                    node.right = new TreeNode(Integer.parseInt(nodes[idx]));
                    currentLevel.add(node.right);
                }
                ++idx;
            }
            list.add(currentLevel);
        }
        
        return root;
    }
    
}


