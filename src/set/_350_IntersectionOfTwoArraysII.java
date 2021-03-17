package set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays-ii/
 * 
 * 给定两个整数数组，求出这两个数组的交集。
 * 注意：
 * （1）交集结果中每个元素出现的次数应该与所有数组中出现的次数一样
 * （2）交集结果中元素的顺序可以是任意的
 * 示例：
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 */
public class _350_IntersectionOfTwoArraysII {
    public static void main(String[] args) {
        int[] nums2 = { 4, 9, 5 };
        int[] nums1 = { 9, 4, 9, 8, 4 };
        _350Solution solution = new _350Solution();
        int[] result = solution.intersect(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}

class _350Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freqMap1 = new HashMap<Integer, Integer>(); // nums1 各个元素出现的频率 map，Key：元素值，Value：频率

        for (int i : nums1) {
            if (freqMap1.containsKey(i)) {
                freqMap1.put(i, freqMap1.get(i) + 1);
            } else {
                freqMap1.put(i, 1);
            }
        }

        List<Integer> resultArr = new ArrayList<Integer>();
        for (int i : nums2) {
            Integer freq = freqMap1.get(i);
            if (null != freq && freq > 0) { // map 存在中 i（即 num1 中存在 i），而且 i 对应的频率大于 0
                resultArr.add(i);
                freqMap1.put(i, freq - 1);
            }
        }

        int[] result = new int[resultArr.size()];
        for (int i = 0; i < resultArr.size(); ++i) {
            result[i] = resultArr.get(i);
        }
        return result;
    }
}