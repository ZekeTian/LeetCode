package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-continuous-increasing-subsequence/
 *
 * 题目描述：给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
 *        连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 *        那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *        
 * 限制条件：
 *  （1）1 <= nums.length <= 104
 *  （2）-109 <= nums[i] <= 109
 *  
 * 示例：
 *  示例 1
 *      输入：nums = [1,3,5,4,7]
 *      输出：3
 *      解释：最长连续递增序列是 [1,3,5], 长度为3。
 *          尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。 
 *  
 *  示例 2
 *      输入：nums = [2,2,2,2,2]
 *      输出：1
 *      解释：最长连续递增序列是 [2], 长度为1。
 */
public class _674_LongestContinuousIncreasingSubsequence {

    public static void main(String[] args) {
        // test case 1, output: 3
//        int[] nums = {1, 3, 5, 4, 7};
        
        // test case 2, output: 1
        int[] nums = {2, 2, 2, 2, 2};
        
        _674Solution solution = new _674Solution();
        
        System.out.println(solution.findLengthOfLCIS(nums));
    }
}

/**
 * 与第 300 题 LongestIncreasingSubsequence 不同的是，本题要求子序列是连续的，而第 300 题则不要求连续。
 * 如果要求连续，则当前第 i 个只需要与前一个（i - 1）进行比较即可，代码中的表现形式为：for 循环 + if 判断。
 * 如果不要求连续，则当前第 i 个需要与前面所有的元素进行比较（即前面的 i - 1 个），代码中的表现形式为：两层 for 循环（内层循环到 i - 1 即可）。
 */
class _674Solution {
    
    public int findLengthOfLCIS(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, 1);
        
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > nums[i - 1]) {
                memo[i] = memo[i - 1] + 1;
            }
        }
        
        int max = 0;
        for (int i : memo) {
            max = Math.max(i, max);
        }
        
        return max;
    }
}
