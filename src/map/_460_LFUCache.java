package map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/lfu-cache/
 * 
 * 题目描述：请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *         实现 LFUCache 类：
 *           （1）LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 *           （2）int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 *           （3）void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。
 *               当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。
 *               在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 *           
 *         为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 *         当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *         函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *         
 * 限制条件：
 *  （1）0 <= capacity <= 10^4
 *  （2）0 <= key <= 10^5
 *  （3）0 <= value <= 10^9
 *  （4）最多调用 2 * 105 次 get 和 put 方法
 *  
 *  
 * 示例：
 *  输入：
 *      ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 *      [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 *  输出：
 *      [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *  
 *  解释：
 *     // cnt(x) = 键 x 的使用计数
 *     // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 *     LFUCache lfu = new LFUCache(2);
 *     lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 *     lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 *     lfu.get(1);      // 返回 1
 *                      // cache=[1,2], cnt(2)=1, cnt(1)=2
 *     lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 *                      // cache=[3,1], cnt(3)=1, cnt(1)=2
 *     lfu.get(2);      // 返回 -1（未找到）
 *     lfu.get(3);      // 返回 3
 *                      // cache=[3,1], cnt(3)=2, cnt(1)=2
 *     lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 *                      // cache=[4,3], cnt(4)=1, cnt(3)=2
 *     lfu.get(1);      // 返回 -1（未找到）
 *     lfu.get(3);      // 返回 3
 *                      // cache=[3,4], cnt(4)=1, cnt(3)=3
 *     lfu.get(4);      // 返回 4
 *                      // cache=[3,4], cnt(4)=2, cnt(3)=3
 *
 */
public class _460_LFUCache {
    
    public static void main(String[] args) {
        
        LinkedHashSet<Integer> set = new LinkedHashSet<Integer>();
        
        
        // test case1
//        LFUCache lfu = new LFUCache(2);
//        
//        lfu.put(1, 1);
//        lfu.put(2, 2);
//        
//        System.out.println(lfu.get(1)); // 1
//        
//        lfu.put(3, 3);
//        
//        System.out.println(lfu.get(2)); // -1
//        System.out.println(lfu.get(3)); // 3
//        
//        lfu.put(4,  4);
//        
//        System.out.println(lfu.get(1)); // -1
//        System.out.println(lfu.get(3)); // 3
//        System.out.println(lfu.get(4)); // 4
        
        
        // test case2 
        LFUCache lfu = new LFUCache(2);
        
        lfu.put(3, 1);
        lfu.put(2, 1);
        lfu.put(2, 2);
        lfu.put(4, 4);
        
        System.out.println(lfu.get(2)); // 2
        
    }

}

/**
 * 结合 HashMap、TreeMap、LinkedHashMap（LinkedHashSet） 三种不同的 Map，解决该问题。
 * 使用 HashMap 可以保证 get 操作是 O(1)
 * 在 put 操作时，因为可能超过缓存容量，所以需要删除。而在删除时，需要满足如下条件：
 *  （1）删除使用频率最低的。为了方便获取频率最低的，所以使用 TreeMap（将频率作为 key，则第一个元素即为频率最低的）。
 *  （2）如果频率一样，则删除时间最久的（使用 LinkedHashSet 存储频率相同的，在最前面的则是时间最久的，在最后面的则是时间最短的）。
 *  
 *  LinkedHashMap 本质上是 双向链表 + HashMap 实现，所以其不仅可以保证元素的存储顺序和元素出现的先后顺序是一样，
 *  同时还可以保证 remove、get 操作都是 O(1)。
 */
class LFUCache {
    
    private class Pair {
        int val; // 缓存的值
        int freq; // 缓存使用频率
        
        Pair(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }
    }
    
    private int capacity = 0;
    private HashMap<Integer, Pair> keyMap = null; // key：缓存的 key，value：缓存的值和使用频率
    private TreeMap<Integer, LinkedHashSet<Integer>> freqMap = null; // key：缓存使用频率，value：相同频率的缓存的 key 集合

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.keyMap = new HashMap<>();
        this.freqMap = new TreeMap<>();
    }
    private void update(int key, int val) {
        // 从 freqMap 中删除 key （旧频率）
        Pair pair = keyMap.get(key);
        pair.val = val;
        
        LinkedHashSet<Integer> set = freqMap.get(pair.freq);
        set.remove(key);
        if (set.isEmpty()) { // 对应 set 已经删空，则 freqMap 中对应频率也已删除
            freqMap.remove(pair.freq);
        }
        
        // key 对应缓存的频率增加
        ++pair.freq;
        
        // 将 key 添加到 freqMap 中（新频率）
        set = freqMap.getOrDefault(pair.freq, new LinkedHashSet<>());
        set.add(key);
        
        freqMap.put(pair.freq, set);
    }
    
    public int get(int key) {
        Pair pair = keyMap.get(key);
        if (pair == null) {
            return -1;
        }
        
        // 通过 update 操作，更新 key 对应的频率
        update(key, pair.val);
        
        return pair.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        
        Pair pair = keyMap.get(key);
        if (pair != null) {
            update(key, value); // keyMap 中存在该 key，则直接更新即可
            return;
        }
        
        // keyMap 不存在该 key，则需要判断缓存容量是否还足够
        if (keyMap.size() >= capacity) { // 缓存容量不够，则需要删除使用次数最少的缓存
            // 最小的频率即为 freqMap 中第一个 key
            Integer minFreq = freqMap.keySet().iterator().next();
            
            // 在最小频率的缓存集合中，删除最长时间未使用的缓存（即 LinkedHashSet 中的第一个元素）
            LinkedHashSet<Integer> set = freqMap.get(minFreq);
            Integer delKey = set.iterator().next(); // 最长时间未使用的元素即为集合中第一个元素
            set.remove(delKey);
            if (set.isEmpty()) {
                freqMap.remove(minFreq); // 如果 set 被删空，则可以从 freqMap 中将 minFreq 对应的元素删除
            }
            
            keyMap.remove(delKey);
        }
        
        // 将新元素放进缓存中
        pair = new Pair(value, 1);
        keyMap.put(key, pair);
        
        LinkedHashSet<Integer> set = freqMap.getOrDefault(pair.freq, new LinkedHashSet<>());
        set.add(key);
        freqMap.put(pair.freq, set);
    }
}
