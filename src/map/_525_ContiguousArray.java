package map;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/contiguous-array/
 * 
 * 题目描述：给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 * 限制条件：
 *  （1）1 <= nums.length <= 10^5
 *  （2）nums[i] 不是 0 就是 1
 *  
 * 示例：
 *  示例 1
 *      输入: nums = [0,1]
 *      输出: 2
 *      说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 *      
 *  示例 2
 *      输入: nums = [0,1,0]
 *      输出: 2
 *      说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *
 */
public class _525_ContiguousArray {

    public static void main(String[] args) {
        // test case1, output: 2
        int[] nums = { 0, 1 };
        
        // test case2, output: 2
//        int[] nums = { 0, 1, 0 };
        
//        int[] nums = { 0, 1, 0, 1};
        
        
        
        _525Solution solution = new _525Solution();
        
        
        System.out.println(solution.findMaxLength(nums));
    }
    
}

/**
 * 思路：利用 map 和前缀和的思想解决
 *      如果将 0 看作 -1，则在一个数组中，当 0 和 1 的数量相等时，该数组所有的数字之和为 0。
 *      这样，原问题转换成：寻找和为 0 的最长连续子数组，因此可以使用  map 和前缀和的思想解决
 *      0, 1, ..., i, i+1, ..., j, j+1, ..., n
 *      sum[i] 表示 nums[0...i] 之间所有数字的和，如果 sum[j] = sum[i]，则说明 nums[i+1...j] 之间所有数字的和为 0。
 *      而 nums[i+1...j] 这个子数组即为我们需要的数组（但需要注意的是，其最终不一定是最长的子数组）
 */
class _525Solution {
    
    public int findMaxLength(int[] nums) {
        // 长度不符合要求，则返回 0
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        // 计算前缀和
        int[] prefixSum = new int[nums.length + 1]; // prefixSum[i] 表示 nums[0...i-1] 之间所有数字的和
        for (int i = 1; i <= nums.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + (nums[i - 1] == 0 ? -1 : nums[i - 1]); // 如果 nums[i-1] 是 0，则将其转换成 -1
        }
        
        Map<Integer, Integer> map = new HashMap<>(); // key：表示 sum ，value：表示和为 sum 的子数组的位置
        int res = 0;
        for (int i = 0; i <= nums.length; ++i) {
            if (!map.containsKey(prefixSum[i])) {
                map.put(prefixSum[i], i); // map 中没有记录 prefixSum[i]，则需要放进 map 中。为了保证子数组长度最长，所以只记录首次出现的位置 
            }
            
            if (map.containsKey(prefixSum[i])) { // 之前有一个数组的和为 prefixSum[i] ，则中间必然有一个子数组的和为 0
                res = Math.max(res, i - map.get(prefixSum[i])); // nums[map.get(prefixSum[i]) + 1, i] 之间的数字和为 0
            }
        }
        
        return res;
    }
    
}

