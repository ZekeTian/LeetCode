package queue;

import java.util.LinkedList;

/**
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 * 
 * 题目描述：给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。
 *         如果不存在这样的 子数组 ，返回 -1 。
 *         子数组 是数组中 连续 的一部分。
 *
 * 限制条件：
 *  （1）1 <= nums.length <= 10^5
 *  （2）-10^5 <= nums[i] <= 10^5
 *  （3）1 <= k <= 10^9
 *  
 * 示例：
 *   示例 1
 *      输入：nums = [1], k = 1
 *      输出：1
 *      
 *   示例 2
 *      输入：nums = [1,2], k = 4
 *      输出：-1
 *      
 *   示例 3
 *      输入：nums = [2,-1,2], k = 3
 *      输出：3
 * 
 */
public class _862_ShortestSubarrayWithSumAtLeastK {

    public static void main(String[] args) {
        // test case1, output: 1
//        int[] nums = { 1 };
//        int k = 1;
        
        // test case2, output: -1
//        int[] nums = { 1, 2 };
//        int k = 4;
        
        // test case3, output: 3
//        int[] nums = { 2, -1, 2 };
//        int k = 3;
        
        // test case4, output: 2
        int[] nums = { 2, -1, 1, 1, 2 };
        int k = 3;
        
        
        _862Solution solution = new _862Solution();
        
        System.out.println(solution.shortestSubarray(nums, k));
    }
    
}

/**
 * 前缀和 + 单调栈
 * 
 * 思路：
 *  （1）遍历原始数组 nums，求得前缀和数组 presum
 *  （2）遍历前缀和数组 presum
 *      利用 while 循环，保证双端队列 queue 是单调递增（保证单调递增，是为了让长度最短）
 *      利用 while 循环，计算出符合条件的数组的长度
 *      将当前 presum 的下标添加到 queue 中
 *  
 * 示例：nums = [2, -1, 1, 1, 2], k = 3
 *      nums 对应的 presum = [0, 2, 1, 2, 3, 5]
 *      初始时，queue = []
 *      遍历到 presum[0] 时，queue = [0]
 *      遍历到 presum[1] 时，queue = [0, 2]（实际存储元素对应下标 [0, 1]）
 *      遍历到 presum[2] 时，queue = [0, 1]（实际存储元素对应下标 [0, 2]）
 *                         此时，为了保持单调递增，需要在队列中将 2 移除，然后再加入 1
 *      遍历到 presum[3] 时，queue = [0, 1, 2]（实际存储元素对应下标 [0, 2, 3]）
 *      遍历到 presum[4] 时，queue = [1, 2, 3]（实际存储元素对应下标 [2, 3, 4]）
 *                         因为， 3 - 0 >= 3，所以会移除队头元素 0，然后再加入 3，并且计算的 res = 4 - 0 = 4。
 *      遍历到 presum[5] 时，queue = [3, 5]（实际存储元素对应下标 [4, 5]）
 *                         第二个 while 第一次循环时，因为， 5 - 1 >= 3，所以会移除队头元素 1，得到 queue = [2, 3]（实际存储元素对应下标 [3, 4]），
 *                               并且计算的 res = 5 - 2 = 3
 *                         第二个 while 第一次循环时，因为，5 - 2 >= 3，所以会移除队头元素 2，然后再加入 5，得到 queue = [3, 5]（实际存储元素对应下标 [4, 5]）
 *                               并且计算的 res = 5 - 3 = 2（实际上即 nums 中最后两个元素 1、2）
 *      遍历完 presum 后，res 的最小值为 2，所以和大于 3 的子数组，其最短长度为 2，即 nums 中最后两个元素 1、2。
 *      
 *      如果不保证 queue 单调递增，是过程如下：
 *      遍历到 presum[2] 时，将 presum[2] 添加到 queue 中，则 queue = [0, 2, 1] （实际存储元素对应下标 [0, 1, 2]）
 *      但是，当后面遍历到 presum[i] 时，presum[i] - presum[2] = presum[i] - 1，presum[i] - presum[1] = presum[i] - 2
 *                              所以，presum[i] - presum[2] > presum[i] - presum[1]
 *                              因此，保留 presum[1] 无意义，即无需在 queue 中保留 2。
 *      结合图形描述，该思想相当于：
 *          给定两条曲线，曲线 a 是单调递增、曲线 b 是非单调递增，当曲线 a 与曲线 b 的高度差一样时，
 *      则曲线 a 的宽度更窄（即相当于此题中，子数组长度更短），
 *      
 */
class _862Solution {
    
    public int shortestSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        // 计算前缀和
        long[] presum = new long[nums.length + 1];
        for (int i = 1; i <= nums.length; ++i) {
            if (nums[i - 1] >= k) {
                return 1; // 如果 nums[i - 1] 大于 k，则只需要 nums[k - 1] 即可
            }
            presum[i] = presum[i - 1] + nums[i - 1];
        }
        
        int res = Integer.MAX_VALUE;
        LinkedList<Integer> queue = new LinkedList<>(); // 队列保持单调递增，从而保证数组长度是最短
        
        // 遍历 presum，更新 res、queue
        for (int i = 0; i < presum.length; ++i) {
            // 保证 queue 是单调递增
            while (!queue.isEmpty() && presum[i] <= presum[queue.peekLast()]) {
                queue.pollLast(); 
            }
            
            // 保证子数组 nums[queue.peekFirst()+1 ... i] 的和是大于等于 k
            while (!queue.isEmpty() && (presum[i] - presum[queue.peekFirst()] >= k)) {
                res = Math.min(res, i - queue.pollFirst()); // 将符合条件的新子数组的长度和 res 比较，取较小者
            }
            
            queue.add(i);
        }
        
        return (res == Integer.MAX_VALUE ? -1 : res);
    }
    
}
