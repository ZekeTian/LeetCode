package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * https://leetcode.com/problems/insert-delete-getrandom-o1/
 * 
 * 题目描述：实现RandomizedSet 类：
 *            （1）RandomizedSet() 初始化 RandomizedSet 对象
 *            （2）bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 *            （3）bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 *            （4）int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。
 *                每个元素应该有 相同的概率 被返回。
 *         你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 *
 * 限制条件：
 *  （1）-2^31 <= val <= 2^31 - 1
 *  （2）最多调用 insert、remove 和 getRandom 函数 2 * 10^5 次
 *  （3）在调用 getRandom 方法时，数据结构中 至少存在一个 元素。
 *  
 * 示例：
 *  输入：
 *      ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 *      [[], [1], [2], [2], [], [1], [2], []]
 *  输出：
 *      [null, true, false, true, 2, true, false, 2]
 *  解释：
 *      RandomizedSet randomizedSet = new RandomizedSet();
 *      randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 *      randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 *      randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 *      randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 *      randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 *      randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 *      randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 *      
 */
public class _380_InsertDeleteGetRandomO1 {

    public static void main(String[] args) {
        
//        RandomizedSet1 randomizedSet = new RandomizedSet1();
        
        RandomizedSet2 randomizedSet = new RandomizedSet2();
        
        System.out.println(randomizedSet.insert(1)); // true
        System.out.println(randomizedSet.remove(2)); // false
        System.out.println(randomizedSet.insert(2)); // true
        System.out.println(randomizedSet.getRandom()); // 1 或 2
        System.out.println(randomizedSet.remove(1)); // true
        System.out.println(randomizedSet.insert(2)); // false
        System.out.println(randomizedSet.getRandom()); // 2
        
    }
    
}

/**
 * 解法一：直接使用一个 Set 实现
 */
class RandomizedSet1 {
    
    private Set<Integer> set = null;
    private Random random = null;

    public RandomizedSet1() {
        this.set = new HashSet<>();
        this.random = new Random();
    }
    
    public boolean insert(int val) {
        return set.add(val);
    }
    
    public boolean remove(int val) {
        return set.remove(val);
    }
    
    public int getRandom() {
        int idx = random.nextInt(set.size()); // 从 set 中随机选择一个数，idx 是选中数字在 set 中的下标
        int count = 0;
        
        for (int i : set) {
            if (count++ == idx) {
                return i;
            }
        }
        
        return 0;
    }
}

/**
 * 解法二：使用 List + Map 实现
 */
class RandomizedSet2 {
    
    private List<Integer> list = null; // 存储 set 中的元素
    private Map<Integer, Integer> map = null; // key：存储 set 中的元素值，value: 元素值在 list 中对应的下标
    private Random random = null;

    public RandomizedSet2() {
        this.list = new ArrayList<>();
        this.map = new HashMap<>();
        this.random = new Random();
    }
    
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        
        // 不存在 val，则向集合中插入该元素
        list.add(val);
        map.put(val, list.size() - 1);
        
        return true;
    }
    
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        
        // 存在 val 时，用 list 中最后一个元素覆盖 val，从而相当于是删除 val，然后再删除 list 中最后一个元素。
        // 采用这种删除方式，可以避免移动大量元素，降低删除操作的时间复杂度。
        int idx = map.get(val);
        int last = list.get(list.size() - 1);
        list.set(idx, last); // 用最后一个元素 last 覆盖 val，从而删除 val
        map.put(last, idx); // 更新 map 中 last 对应的下标值
        list.remove(list.size() - 1); // 删除 list 中最后一个元素
        map.remove(val); // 删除 map 中的 val
        
        return true;
    }
    
    public int getRandom() {
        int idx = random.nextInt(list.size()); // 从 list 中随机选择一个元素
        return list.get(idx);
    }
    
}

