package map;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *  https://leetcode.com/problems/contains-duplicate-iii/
 *  题目描述：给定一个整数数组 nums 和两个整数 k、t，如果数组中存在两个不同的下标 i、j，使得 abs(nums[i] - nums[j]) <= t，并且 abs(i - j) <= k，则返回 true。
 *  
 *  条件限制：
 *  （1）0 <= nums.length <= 2 * 10^4
 *  （2）-2^31 <= nums[i] <= 2^31 - 1
 *  （3）0 <= k <= 10^4
 *  （4）0 <= t <= 2^31 - 1
 *  
 *  示例：
 *  Input: nums = [1,2,3,1], k = 3, t = 0
 *  Output: true
 *
 */
public class _220_ContainsDuplicateIII {

    public static void main(String[] args) {

        _220Solution solution = new _220Solution();
//        int[] nums = { 1, 2, 3, 1 };
//        int k = 3;
//        int t = 0;
        
        int[] nums = { 1, 2 }; // 此测试用例，需要：set.add((long) nums[i]) 在 set.remove((long) nums[i - k]) 之前进行，否则结果错误
        int k = 0;
        int t = 1;

        //        int[] nums = { 1, 0, 1, 1 };
        //        int k = 1;
        //        int t = 2;

        //        int[] nums = { 1, 5, 9, 1, 5, 9 };
        //        int k = 2;
        //        int t = 3;

        //        int[] nums = { -2147483640, -2147483641 };
        //        int k = 1;
        //        int t = 100;

        //        int[] nums = { 3, 6, 0, 4 };
        //        int k = 2;
        //        int t = 2;

        boolean reslut = solution.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(reslut);
    }
}

/**
 * 滑动窗口 + Map
 *
 */
class _220Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<Long>(); // 存储长度为 k 的区间中的元素，即存储窗口中的元素

        // |nums[i] - nums[j]| < t              
        // -t <= nums[i] - nums[j] <= t         
        // nums[i] - t <= nums[j] <= nums[i] + t
        for (int i = 0; i < nums.length; ++i) {
            Long e = set.ceiling((long) nums[i] - t);
            if (null != e && (e <= nums[i] + (long) t)) { // 满足大小要求
                return true;
            }

            set.add((long) nums[i]);
            if (set.size() >= k+1) {
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }
}