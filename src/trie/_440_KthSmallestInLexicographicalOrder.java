package trie;

import java.util.Set;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/k-th-smallest-in-lexicographical-order/
 * 
 * 题目描述：给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
 * 
 * 限制条件：1 <= k <= n <= 10^9
 * 
 * 示例：
 *  示例 1
 *      输入: n = 13, k = 2
 *      输出: 10
 *      解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 *  
 *  示例 2
 *      输入: n = 1, k = 1
 *      输出: 1
 *      
 */
public class _440_KthSmallestInLexicographicalOrder {

    public static void main(String[] args) {
        // test case1, output: 10
//        int n = 10, k = 2;
        
        // test case2, output: 1
//        int n = 1, k = 1;
        
        // test case3, output: 2
        int n = 2, k = 2;
        
        
//        _440Solution1 solution = new _440Solution1();
        
        _440Solution2 solution = new _440Solution2();
        
        
        System.out.println(solution.findKthNumber(n, k));
    }
}


/**
 * 解法一：真正地构建字典树，然后进行遍历
 */
class _440Solution1 {
    
    // 字典树节点
    private class Node {
        boolean isEnding;
        TreeMap<Character, Node> next;
        
        public Node() {
            this(false);
        }
        
        public Node(boolean isEnding) {
            this.isEnding = isEnding;
            this.next = new TreeMap<>();
        }
    }
    
    // dfs 遍历时，已经遍历过的节点数量
    private int count = 0;
    
    // 向以 root 为根顶点的字典中添加 str
    private void add(Node root, String str) {
        Node current = root;
        for (int i = 0; i < str.length(); ++i) {
            Node child = current.next.get(str.charAt(i));
            if (child == null) {
                child = new Node();
                current.next.put(str.charAt(i), child);
            }
            current = child;
        }
        current.isEnding = true;
    }
    
    // 根据 [1,n] 之间的数字，构建字典树
    private Node buildTrie(int n) {
        Node root = new Node();
        for (int i = 1; i <= n; ++i) {
            add(root, i + "");
        }
        return root;
    }
    
    // 先序遍历字典树，找到排名第 k 的节点
    private String dfs(Node root, int k, String str) {
        if (root.isEnding) {
            ++count;
        }
        
        if (count == k) {
            return str;
        }
        
        String ret = null;
        Set<Character> keys = root.next.keySet();
        for (Character c : keys) {
            ret = dfs(root.next.get(c), k, str + c);
            if (ret != null) {
                break;
            }
        }
        
        return ret;
    }
    
    public int findKthNumber(int n, int k) {
        Node root = buildTrie(n);
        String target = dfs(root, k, "");
        
        return Integer.parseInt(target);
    }
    
}


/**
 * 解法二：字典树，但是不真正构建，只是统计数量
 *       算法流程：
 *          cur = root
 *          --k // 减掉根节点
 *          while k > 0:
 *              获取 cur 节点对应子树中小于 n 的节点数量 num
 *              if num <= k:
 *                  cur 节点对应子树中小于 n 的节点数量，则节点数量不够，需要向右移动
 *              else:
 *                  节点数量足够，向下移动
 *          
 */
class _440Solution2 {
    
    public int findKthNumber(int n, int k) {
        int cur = 1; // 字典树中当前节点（初始时，为根节点 1）
        --k; // 去掉根节点
        
        while (k > 0) {
            // 获取当前层中小于 n 的节点数量
            long num = getNodeNums(cur, n);
            if (num <= k) {
                // 当前节点对应子树中的节点数量不够 k 个，则需要向右移动
                k -= num; // 向右移动，则当前节点对应子树中的节点全部被覆盖到，故 k 要减 num（即将“当前节点对应子树中的节点数量”全部减去）
                ++cur; // 向右移动
            } else {
                // 当前节点对应子树中的节点数量大于 k（数量足够），则在当前节点对应子树中向下寻找排名为 k 的节点
                --k; // 因为只是向下移动，所以只是当前节点被覆盖到，故 k 只减 1（即减掉当前节点）
                cur *= 10; // 向下移动
            }
        }
        
        return cur;
    }
    
    // 以 root 为根节点的子树中，值小于 n 的节点数量
    private long getNodeNums(int root, int n) {
        // 为了避免值的溢出，数据类型需要使用 long （主要是 left、right 会溢出，num 不会溢出）
        long num = 0;
        long left = root; // 当前层最左边的值（即当前层的最小值）
        long right = root; // 当前层最右边的值（即当前层的最大值）
        
        while (left <= n) {
            num = num + Math.min(right, n) - left + 1; // 在当前层中，值小于 n 的节点数量
            // 向下一层
            left = left * 10;
            right = right * 10 + 9;
        }
        
        return num;
    }
}
