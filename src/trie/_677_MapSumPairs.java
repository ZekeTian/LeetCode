package trie;

import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/map-sum-pairs/
 *
 * 实现 MapSum 类，其包含如下几个方法：
 * MapSum()：初始化 MapSum 对象
 * void insert(String key, int val)：向 Map 中插入 key-val 对。如果 key 存在，用新 val 修改旧 val
 * int sum(String prefix)：返回所有以 prefix 开始的 key 对应的 value 和
 * 
 * 示例：
 * MapSum 插入 "(apple, 3)"、"(app, 2)"
 * mapSum.sum("ap") 的值为 5（ apple + app = 3 + 2 = 5）
 */
public class _677_MapSumPairs {

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("apple")); // return 3 (apple = 3)
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap")); // return 5 (apple + app = 3 + 2 = 5)
    }
}

class MapSum {

    private class Node {
        Integer val; // 当前 Node 所对应 key 的 val 值（若其不为 null，则说明当前节点可以构成一个 key）
        TreeMap<Character, Node> next; // Key：字符，Val：子节点

        public Node() {
            this(null);
        }

        public Node(Integer val) {
            this.val = val;
            next = new TreeMap<Character, Node>();
        }
    }

    private Node root; // 根顶点

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        // 从根顶点出发，逐个遍历 key 的字符，根据字符选择子节点方向
        Node current = root;
        for (int i = 0; i < key.length(); ++i) {
            char c = key.charAt(i);
            if (current.next.containsKey(c)) {
                current = current.next.get(c);
            } else {
                Node node = new Node();
                current.next.put(c, node);
                current = node;
            }
        }
        current.val = val;
    }

    /**
     * 返回所有以 prefix 开始的 key 对应的 value 和
     * @param prefix 待匹配的前缀
     * @return 能够匹配到 prefix 的 key 的 value 和
     */
    public int sum(String prefix) {
        // 先匹配 prefix
        Node current = root;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (!current.next.containsKey(c)) {
                return 0; // 如果不能匹配完 prefix，直接返回 0
            }

            current = current.next.get(c);
        }

        int result = 0;
        if (null != current.val) {
            result += current.val; // 当前节点能够构成一个 key，则也需要加上当前节点的 val（此时的 key 即为 prefix） 
        }
        
        // 能够匹配完 prefix，则遍历后面所有的子树，求得所有 val 和
        Set<Entry<Character, Node>> childNodes = current.next.entrySet();
        for (Entry<Character, Node> child : childNodes) {
            result += sum(child.getValue());
        }

        return result;
    }

    /**
     * 在以 root 为根节点的 Trie 中，求所有 val 的和
     * @param root    Trie 的根顶点
     * @return 所有 val 的和
     */
    private int sum(Node root) {
        // 当递归到叶子节点时，终止递归
        if (0 == root.next.size()) {
            return root.val;
        }

        int result = 0;
        // 判断当前节点是否有 val。如果有，则加上 val
        if (null != root.val) {
            result += root.val; // 此时，当前节点构成一个 key
        }

        // 继续遍历子树
        Set<Entry<Character, Node>> childNodes = root.next.entrySet();
        for (Entry<Character, Node> child : childNodes) {
            result += sum(child.getValue());
        }

        return result;
    }
}
