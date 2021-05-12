package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * https://leetcode.com/problems/two-sum/
 * 题目描述：给定一个整数数组 nums 和一个整数 target，寻找两个数，使得它们的和等于 target，并返回它们的下标。
 *        你可以假设每组输入只有唯一正确的解，并且不能重复使用元素。
 * 
 * 条件限制：
 *  （1）2 <= nums.length <= 10^3
 *  （2）-10^9 <= nums[i] <= 10^9
 *  （3）-10^9 <= target <= 10^9
 *  （4）只有唯一解
 * 
 * 示例：
 *  Input: nums = [2,7,11,15], target = 9
 *  Output: [0,1]
 *  解释：因为 nums[0] + nums[1] = 9，因此返回 0、1
 */
public class _1_TwoSum {

    public static void main(String[] args) {
        //        _1Solution1 solution = new _1Solution1();

        _1Solution2 solution = new _1Solution2();
//        int[] nums = { 2, 7, 11, 15 };
//        int target = 9;
        int[] nums = { 4, 7, 4, 15 };
        int target = 8;

        //        int[] nums = { 3, 2, 4 };
        //        int target = 6;

        int[] res = solution.twoSum(nums, target);
        System.out.println(Arrays.toString(res));
    }
}

/**
 * 解法一：对数组排序，然后使用 167 号问题的解法
 *
 */
class _1Solution1 {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        List<Entry<Integer, Integer>> list = new ArrayList<>(); // 二元组，key：原始数组中的值，value：原始数组中的下标

        // 存储各个元素
        for (int i = 0; i < nums.length; ++i) {
            list.add(Map.entry(nums[i], i));
        }

        Collections.sort(list, new Comparator<Entry<Integer, Integer>>() {

            @Override
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });

        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            Entry<Integer, Integer> leftE = list.get(left);
            Entry<Integer, Integer> rightE = list.get(right);

            if (leftE.getKey() + rightE.getKey() == target) {
                res[0] = leftE.getValue();
                res[1] = rightE.getValue();
                return res;
            } else if (leftE.getKey() + rightE.getKey() < target) {
                ++left;
            } else { // nums[left] + nums[right] > target
                --right;
            }
        }

        return res;
    }
}

/**
 * 解法二：使用 Map，存储所有元素
 *
 */
class _1Solution2 {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>(); // key：nums 中的元素值，value：元素值在 nums 中的下标

        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                map.put(nums[i], list);
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            int num2 = target - nums[i];
            if (map.containsKey(num2)) {
                if (num2 != nums[i]) {
                    res[0] = i;
                    res[1] = map.get(num2).get(0);
                    return res;
                } else if (num2 == nums[i] && map.get(num2).size() > 1) {
                    res[0] = map.get(num2).get(0);
                    res[1] = map.get(num2).get(1);
                    return res;
                }
            }
        }

        return res;
    }
}

/**
 * 解法三：使用 Map，只存储遍历过的元素
 *
 */
class _1Solution3 {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // 只存储遍历过的元素，key：元素值，value：元素在 nums 中的下标
        
        for (int i = 0; i < nums.length; ++i) {
            int num2 = target - nums[i];
            
            if (map.containsKey(num2)) {
                res[0] = map.get(num2);
                res[1] = i;
                return res;
            }
            
            map.put(nums[i], i);
        }
        
        return res;
    }
}