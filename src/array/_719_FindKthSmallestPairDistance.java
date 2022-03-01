package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-k-th-smallest-pair-distance/
 * 
 * 题目描述：给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * 
 * 限制条件：
 *  （1）2 <= len(nums) <= 10000.
 *  （2）0 <= nums[i] < 1000000.
 *  （3）1 <= k <= len(nums) * (len(nums) - 1) / 2
 *  
 * 示例：
 *  输入：
 *      nums = [1,3,1]
 *      k = 1
 *  输出：0 
 *  解释：
 *      所有数对如下：
 *      (1,3) -> 2
 *      (1,1) -> 0
 *      (3,1) -> 2
 *      因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 *
 */
public class _719_FindKthSmallestPairDistance {

    public static void main(String[] args) {
        // test case1, output: 0
//        int[] nums = { 1, 3, 1 };
//        int k = 1;
        
        // test case2, output: 0
//        int[] nums = { 1, 1, 1 };
//        int k = 2;

        // test case3, output: 5
        int[] nums = { 1, 6, 1 };
        int k = 3;
        
        
        _719Solution solution = new _719Solution();
        
        System.out.println(solution.smallestDistancePair(nums, k));
    }
}

/**
 * 思路：先对 nums 进行排序，然后再对距离进行二分搜索。
 *     要想求得第 k 个最小距离，实际上相当于利用二分搜索寻找到一个距离 x，使得 nums 中小于 x 的数对的个数恰好是 k。
 *     最终，距离 x 即为所求的第 k 个最小距离。
 */
class _719Solution {
    
    public int smallestDistancePair(int[] nums, int k) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        
        // 对 nums 进行排序，方便后面对距离进行二分搜索
        Arrays.sort(nums);
        
        // 利用二分搜索，寻找一个距离阈值 x，使得 nums 中距离小于等于 x 的数对个数恰好是 k
        int low = 0; // 最小距离
        int high = nums[nums.length - 1] - nums[0]; // 最大距离
        
        while (low < high) {
            int mid = low + (high - low) / 2; // mid 即作为距离阈值 x
            
            // 统计出 nums 中距离小于 mid 的数对个数
            int count = 0;
            for (int left = 0, right = 0; right < nums.length; ++right) {
                // 统计出 nums[left...right) 中，与 nums[right] 之间的距离小于等于 mid 的数字个数
                // 如果 nums[right] - nums[left] <= mid，那么 nums[left...right) 之间所有数字与 nums[right] 之间的距离都小于等于 mid
                // 则 count 可以直接加上 right - left。
                // 如果 nums[right] - nums[left] > mid，则不能直接计算出符合条件的数字个数，需要右移 left，使得差值减小，
                // 从而让 nums[right] - nums[left] <= mid，方便直接计算出符合条件的数字个数。
                while (nums[right] - nums[left] > mid) {
                    ++left; // 距离偏大，右移 left
                }
                count = count + (right - left); // nums[right] - nums[left] <= mid，可以直接计算出符合条件的数字个数
            }
            
            if (count >= k) { // nums 中距离小于 mid 的数对个数偏多，则将 mid 作为 阈值偏大，因此缩小下次的搜索范围
                high = mid; // mid 不减 1，是因为 count 可能等于 k
            } else {
                low = mid + 1; // nums 中距离小于 mid 的数对个数偏少，扩大下次的搜索范围
            }
        }
        
        return low;
    }
}
