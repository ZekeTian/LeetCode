package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * 题目描述：给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *         测试用例的答案是一个 32-位 整数。
 *         子数组 是数组的连续子序列。
 *         
 * 限制条件：
 *  （1）1 <= nums.length <= 2 * 10^4
 *  （2）-10 <= nums[i] <= 10
 *  （3）nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 * 
 * 示例：
 *  示例 1
 *      输入: nums = [2,3,-2,4]
 *      输出: 6
 *      解释: 子数组 [2,3] 有最大乘积 6。
 *      
 *  示例 2
 *      输入: nums = [-2,0,-1]
 *      输出: 0
 *      解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * 
 */
public class _152_MaximumProductSubarray {

    public static void main(String[] args) {
        // test case1, output: 6
//        int[] nums = { 2, 3, -2, 4 };
        
        // test case2, output: 0
//        int[] nums = { -2, 0, -1 };
        
        // test case3, output: -2
//        int[] nums = { -2 };
        
        // test case4, output: 24
        int[] nums = { -2, 3, -4 };
        
        
        _152Solution solution = new _152Solution();
        
        System.out.println(solution.maxProduct(nums));
    }
}

/**
 * 因为数组中有正数和负数，所以最终求得的结果可能是 正数 * 最大数，也可能是 负数 * 最小数。
 * 根据数字的正负，需要使用最大数或最小数，所以最大数和最小数都要记录，故需要使用两个数组记录状态。
 */
class _152Solution {
    
    public int maxProduct(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        
        int[] maxMemo = Arrays.copyOf(nums, nums.length); // maxMemo[i] 存储以 nums[i] 结尾的连续子数组的最大乘积
        int[] minMemo = Arrays.copyOf(nums, nums.length); // maxMemo[i] 存储以 nums[i] 结尾的连续子数组的最小乘积
        int max = nums[0]; // 最大乘积
        
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > 0) {
                // 正数时，要想值尽量大，应该乘以最大值；要想值尽量小，应该乘以最小值
                maxMemo[i] = Math.max(maxMemo[i - 1] * nums[i], nums[i]);
                minMemo[i] = Math.min(minMemo[i - 1] * nums[i], nums[i]);
            } else if (nums[i] < 0) {
                // 负数时，要想值尽量大，应该乘以最小值；要想值尽量小，应该乘以最大值
                maxMemo[i] = Math.max(minMemo[i - 1] * nums[i], nums[i]);
                minMemo[i] = Math.min(maxMemo[i - 1] * nums[i], nums[i]);
            }
            
            max = Math.max(max, maxMemo[i]);
        }
        
        return max;
    }
}
