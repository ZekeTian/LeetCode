package map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/contains-duplicate-ii/
 * 题目描述：给定一个整数数组 nums 和一个整数 k，如果数组中存在两个不同的下标 i、j，使得 nums[i] == nums[j]，并且 abs(i - j) <= k，则返回 true。
 * 
 * 条件限制：
 * （1）1 <= nums.length <= 10^5
 * （2）-10^9 <= nums[i] <= 10^9
 * （3）0 <= k <= 10^5
 * 
 * 示例：
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 */
public class _219_ContainsDuplicateII {
    public static void main(String[] args) {
        //        _219Solution1 solution = new _219Solution1();
        //        _219Solution2 solution = new _219Solution2();
        _219Solution3 solution = new _219Solution3();

        //        int[] nums = { 1, 2, 3, 1 };
        //        int k = 3;

        //        int[] nums = { 1, 0, 1, 1 };
        //        int k = 1;

        int[] nums = { 1, 2, 3, 1, 2, 3 };
        int k = 2;

        boolean result = solution.containsNearbyDuplicate(nums, k);
        System.out.println(result);
    }
}

/**
 * 解法一：使用 Map，只存储遍历过的元素（思路同 “1 Two Sum” 问题类似）
 */
class _219Solution1 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // key: 数组中的元素，val: 元素在数组中的下标

        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i]) && Math.abs(i - map.get(nums[i])) <= k) {
                return true;
            } else {
                map.put(nums[i], i);
            }
        }

        return false;
    }
}

/**
 * 解法二：滑动窗口结合 Map 实现一（显式指定滑动窗口的左右指针）
 */
class _219Solution2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        int left = 0;
        int right = -1;
        Set<Integer> set = new HashSet<Integer>(); // set 存储 nums 中 [left, right] 之间的元素

        while (right < nums.length) {
            // 保证 right - left <= k，不会 > k
            if (right - left < k) {
                ++right; // ++right 之后，right - left 可能等于 k
                if (right == nums.length) {
                    return false;
                }

                if (set.contains(nums[right])) {
                    return true;
                }
                set.add(nums[right]);
            } else if (right - left == k) {
                set.remove(nums[left++]);
            }
        }

        return false;
    }
}

/**
 * 解法二：滑动窗口结合 Map 实现二（滑动窗口左右指针由 Set 大小计算得到）
 */
class _219Solution3 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) {
                return true;
            }

            set.add(nums[i]);
            if (set.size() > k) {
                // i - left = n - 1 --> left = i - n + 1 
                set.remove(nums[i - set.size() + 1]);
            }
        }

        return false;
    }
}
