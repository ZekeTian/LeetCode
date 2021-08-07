package map;

import java.util.LinkedHashMap;
import java.util.TreeMap;

public class _146_LRUCache {

    public static void main(String[] args) {
        //        _146LRUCache1 lRUCache = new _146LRUCache1(2);
        _146LRUCache2 lRUCache = new _146LRUCache2(2);

        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        int res = lRUCache.get(1); // 返回 1
        System.out.println(res);

        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        res = lRUCache.get(2); // 返回 -1 (未找到)
        System.out.println(res);

        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}

        res = lRUCache.get(1); // 返回 -1 (未找到)
        System.out.println(res);

        res = lRUCache.get(3); // 返回 3
        System.out.println(res);

        res = lRUCache.get(4); // 返回 4
        System.out.println(res);

        //        int res = lRUCache.get(2); // 返回 -1 (未找到)
        //        System.out.println(res);
        //        lRUCache.put(2, 6); // 缓存是 {2=6}
        //        
        //        res = lRUCache.get(1); // 返回 -1 (未找到)
        //        System.out.println(res);
        //        
        //        lRUCache.put(1, 5); // 缓存是 {1=5, 2=6}
        //        lRUCache.put(1, 2); // 缓存是 {1=2, 2=6}
        //        
        //        res = lRUCache.get(1); // 返回 2
        //        System.out.println(res);
        //        
        //        res = lRUCache.get(2); // 返回 6
        //        System.out.println(res);

    }
}

/**
 * 解法一：使用两个哈希 map 实现
 */
class _146LRUCache1 {

    private class Operation {
        int time;
        int key;
        int val;

        Operation(int time, int key, int val) {
            this.time = time;
            this.key = key;
            this.val = val;
        }
    }

    private int capacity = 0;
    private int timeCount = 0;
    private TreeMap<Integer, Operation> opMap = null; // key: op key, val: op data，便于实现 get、put 操作 
    private TreeMap<Integer, Integer> timeMap = null; // key: time, val: op key，便于查找最近最久未使用的命令

    public _146LRUCache1(int capacity) {
        this.capacity = capacity;
        opMap = new TreeMap<>();
        timeMap = new TreeMap<>();
    }

    public int get(int key) {
        ++timeCount;

        Operation res = opMap.get(key);
        if (null != res) {
            // 更新操作的时间
            updateOperationTime(key, res);
        }

        return (null == res) ? -1 : res.val;
    }

    public void put(int key, int value) {
        ++timeCount;
        // put 时，已经存在 key，则只更新值，不删除操作
        Operation res = opMap.get(key);
        if (null != res) {
            // 更新 key 操作对应的值
            res.val = value;
            // 更新 key 对应的时间
            updateOperationTime(key, res);
            return;
        }

        // put 时，不存在 key，则判断容量是否足够
        if (opMap.size() >= capacity) {
            // 取出第一个最久未使用的命令，并将其删除
            int longTime = timeMap.firstKey();
            int longUnusedOp = timeMap.get(longTime);
            opMap.remove(longUnusedOp);
            timeMap.remove(longTime);
        }

        Operation op = new Operation(timeCount, key, value);
        opMap.put(key, op);
        timeMap.put(timeCount, key);
    }

    private void updateOperationTime(int key, Operation res) {
        timeMap.remove(res.time);
        timeMap.put(timeCount, key);
        res.time = timeCount;
        opMap.put(key, res);
    }
}

/**
 * 解法二：使用 LinkedHashMap 实现（本质上还是双向链表）
 */
class _146LRUCache2 {

    private LinkedHashMap<Integer, Integer> opMap = null;
    private int capacity = 0;

    public _146LRUCache2(int capacity) {
        this.capacity = capacity;
        opMap = new LinkedHashMap<Integer, Integer>(capacity);
    }

    public int get(int key) {
        Integer res = opMap.get(key);

        if (res != null) {
            opMap.remove(key);
            opMap.put(key, res); // 删除 key 操作，然后再放入 key 操作，相当于是把 key 操作放到最后（即把 key 操作设置成最新操作）
        }

        return (null == res) ? -1 : res;
    }

    public void put(int key, int value) {
        Integer res = opMap.get(key);
        if (null != res) {
            opMap.remove(key);
            opMap.put(key, value);
            return;
        }

        if (opMap.size() >= capacity) {
            // 删除时间最久的操作，即 map 中的第一个操作
            opMap.remove(opMap.entrySet().iterator().next().getKey());
        }
        opMap.put(key, value);
    }
}
