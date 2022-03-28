package map;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 * 
 * 题目描述：给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
 *
 * 限制条件：
 *  （1）1 <= nums.length <= 2 * 10^4
 *  （2）-1000 <= nums[i] <= 1000
 *  （3）-10^7 <= k <= 10^7
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [1,1,1], k = 2
 *      输出：2
 *      
 *  示例 2
 *      输入：nums = [1,2,3], k = 3
 *      输出：2
 *
 */
public class _560_SubarraySumEqualsK {

    public static void main(String[] args) {
        // test case1, output: 2
//        int[] nums = { 1, 1, 1 };
//        int k = 2;
        
        // test case2, output: 2
        int[] nums = { 1, 2, 3 };
        int k = 3;
        
//        _560Solution1 solution = new _560Solution1();
        
        _560Solution2 solution = new _560Solution2();
        
        System.out.println(solution.subarraySum(nums, k));
    }
}

/**
 * 解法一：暴力求解
 *        两重循环，枚举出所有可能的 [i, j] 区间，然后再统计出和为 k 的区间数量。
 */
class _560Solution1 {
    
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        
        for (int i = 0; i < nums.length; ++i) {
            // 枚举出所有的 [i,j] 区间
            int sum = 0;
            for (int j = i; j < nums.length; ++j) {
                sum += nums[j]; // 计算出 [i, j] 之间的和
                if (sum == k) {
                    ++count;
                }
            }
        }
        
        return count;
    }
}

/**
 * 解法二：使用 Map 存储中间结果
 * 
 * 思路：[0,j] 区间的和 = [0, i] 区间的和 + [i, j] 区间的和
 *      （1）计算出 [0, j] 之间的和 sum
 *      （2）使用 map 取出 sum - k 的次数（即 [0, i] 区间的和的次数），该次数和 k 的次数（即 [i, j] 区间的和的次数）一样，
 *          因此将该次数累加到 count 中
 *      （3）使用一个 map 存储 sum 的次数。
 *       
 */
class _560Solution2 {
    
    public int subarraySum(int[] nums, int k) {
        int sum = 0; // 存储 [0, i] 之间的和
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();  // key: [0, i] 区间的 sum ，value:  sum 对的次数
        map.put(0, 1); // sum 为 0 的次数为 1
        
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k); // 存在和为 sum - k 的区间，则也存在和为 k 的区间，且次数一样
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return count;
    }
}
