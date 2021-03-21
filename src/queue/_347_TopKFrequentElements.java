package queue;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * 题目描述：给定一个整数数组 nums 和一个整数 k，返回数组中出现频率前 k 高的元素。返回结果中的元素顺序可以是任意的。
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * 
 */
public class _347_TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 3 };
        //        int[] nums = { 1 };
        int k = 2;
        _347Solution solution = new _347Solution();
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k)));
    }
}

/**
 * 频率类
 */
class FreqItem implements Comparable<FreqItem> {
    int value; // 实际值
    int freq; // value 出现的频率

    public FreqItem(int value, int freq) {
        this.value = value;
        this.freq = freq;
    }

    @Override
    public int compareTo(FreqItem o) {
        return this.freq - o.freq;
    }
}

/**
 * 利用优先队列实现
 */
class _347Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new TreeMap<Integer, Integer>(); // Key：数，Value：数在 nums 中出现的频率
        for (int i : nums) {
            if (!freqMap.containsKey(i)) {
                freqMap.put(i, 1);
            } else {
                freqMap.put(i, freqMap.get(i) + 1);
            }
        }

        PriorityQueue<FreqItem> queue = new PriorityQueue<FreqItem>(); // 存放 K 个最大的元素，但是按照 最小堆组织元素
        Set<Entry<Integer, Integer>> entries = freqMap.entrySet();
        for (Entry<Integer, Integer> e : entries) {
            if (queue.size() < k) {
                queue.add(new FreqItem(e.getKey(), e.getValue()));
            } else {
                // 判断当前元素出现的频率是否大于优先队列中的队首元素（目前 topK 元素中频率最小的那个）
                if (e.getValue() > queue.peek().freq) {
                    queue.poll();
                    queue.add(new FreqItem(e.getKey(), e.getValue()));
                }
            }
        }

        int[] result = new int[queue.size()];
        int i = 0;
        for (FreqItem item : queue) {
            result[i++] = item.value;
        }
        return result;
    }
}
