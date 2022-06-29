package map;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/design-hashmap/
 * 
 * 题目描述：不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *         实现 MyHashMap 类：
 *            （1）MyHashMap() 用空映射初始化对象
 *            （2）void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。
 *                如果 key 已经存在于映射中，则更新其对应的值 value 。
 *            （3）int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 *            （4）void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 *         
 * 限制条件：
 *  （1）0 <= key, value <= 10^6
 *  （2）最多调用 10^4 次 put、get 和 remove 方法
 * 
 * 示例：
 *  输入：
 *      ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 *      [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 *  输出：
 *      [null, null, null, 1, -1, null, 1, null, -1]
 *  解释：
 *      MyHashMap myHashMap = new MyHashMap();
 *      myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 *      myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 *      myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 *      myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 *      myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 *      myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 *      myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 *      myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 *      
 */
public class _706_DesignHashMap {

    public static void main(String[] args) {

        MyHashMap myHashMap = new MyHashMap();
        myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
        myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
        System.out.println(myHashMap.get(1)); // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
        System.out.println(myHashMap.get(3)); // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
        myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
        System.out.println(myHashMap.get(2)); // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
        myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
        System.out.println(myHashMap.get(2)); // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
        
    }
    
}

/**
 * 链地址法实现，即数组 + 链表
 */
class MyHashMap {
    
    private class Node {
        int key;
        int val;
        
        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private LinkedList<Node>[] arr = null;
    private static final int LENGTH = 1009; // 数组的长度
    
    
    public MyHashMap() {
        this.arr = new LinkedList[LENGTH];
        for (int i = 0; i < LENGTH; ++i) {
            this.arr[i] = new LinkedList<>();
        }
    }
    
    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) {
            node.val = value;
            return;
        }
        
        // 不存在 key 对应的节点，则需要添加新的节点
        node = new Node(key, value);
        LinkedList<Node> list = arr[key % LENGTH];
        list.add(node);
    }
    
    public int get(int key) {
        Node node = getNode(key);
        if (node == null) {
            return -1;
        }
        
        return node.val;
    }
    
    public void remove(int key) {
        Node node = getNode(key);
        if (node == null) {
            return; // 不存在 key 对应的节点，无需删除
        }
        
        // 存在 key 对应的 node 节点，则获取对应的链表，然后将 node 从链表中删除
        LinkedList<Node> list = arr[key % LENGTH];
        list.remove(node);
    }
    
    // 获取 key 对应的节点。如果获取到，则返回对应节点；否则，返回 null
    private Node getNode(int key) {
        LinkedList<Node> list = arr[key % LENGTH];
        for (Node node : list) {
            if (node.key == key) {
                return node;
            }
        }
        
        return null;
    }
    
}

