package array;

/**
 * https://leetcode.com/problems/minimum-size-subarray-sum/
 * 题目描述：给定一个正数数组 nums 和一个正整数 target，在 nums 中寻找一个最小长度的连续子数组 [numsl, numsl, numsl+1, ..., numsr-1, numsr]，
 *        使得该子数组的和大于等于 target。如果能找到，则返回该子数组的长度；否则，返回 0。
 * 
 * 条件限制：
 *  （1）1 <= target <= 10^9
 *  （2）1 <= nums.length <= 10^5
 *  （3）1 <= nums[i] <= 10^5
 *  
 * 示例：
 *  Input: target = 7, nums = [2,3,1,2,4,3]
 *  Output: 2
 *  解释：子数组 [4,3] 是满足条件的最短子数组。
 */
public class _209_MinimumSizeSubarraySum {

    public static void main(String[] args) {
        //        int target = 7;
        //        int[] nums = { 2, 3, 1, 2, 4, 3 };

        //        int target = 4;
        //        int[] nums = { 1, 4, 4 };

        int target = 11;
        int[] nums = { 1, 1, 1, 1, 1, 1, 1, 1 };

        _209Solution solution = new _209Solution();
        int len = solution.minSubArrayLen(target, nums);
        System.out.println(len);
    }
}

/**
 * 滑动窗口
 *
 */
class _209Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0; // 子数组左边界
        int right = -1; // 子数组右边界，子数组范围：[left, right]
        int sum = 0; // 子数组内的元素和
        int length = nums.length + 1; // 最短子数组的长度

        while (left < nums.length) {
            if (sum < target && right + 1 < nums.length) {
                sum += nums[++right]; // 将 nums[right + 1] 添加到子数组中
            } else {
                if (sum >= target /* 子数组的和满足条件 */
                        && (right - left + 1) < length /* 当前子数组的长度比之前的更小 */) {
                    length = right - left + 1;
                }

                sum -= nums[left++]; // 将 nums[left] 从子数组中移除
            }
        }

        return (length == nums.length + 1) ? 0 : length;
    }
}