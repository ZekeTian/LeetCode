package queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * 题目描述：给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 *        请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *        
 * 限制条件：
 *  （1）1 <= k <= nums.length <= 10^4
 *  （2）-10^4 <= nums[i] <= 10^4
 *  
 * 示例：
 *  Input: [3,2,1,5,6,4] , k = 2
 *  Output: 5
 * 
 */
public class _215_KthLargestElementInAnArray {

    public static void main(String[] args) {
        // test case 1, output: 5
        int[] nums = { 3, 2, 1, 5, 6, 4 };
        int k = 2;

        // test case 2, output: 4
        //        int[] nums = { 3, 2, 3, 1, 2, 4, 5, 5, 6 };
        //        int k = 4;

        //        _215Solution1 solution = new _215Solution1();

        _215Solution2 solution = new _215Solution2();

        System.out.println(solution.findKthLargest(nums, k));

    }
}

/**
 * 解法一：直接排序
 */
class _215Solution1 {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        
        return nums[nums.length - k];
    }
}

/**
 * 解法二：利用最小堆
 */
class _215Solution2 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i : nums) {
            if (queue.size() < k) {
                queue.add(i);
            } else {
                if (i > queue.peek()) {
                    queue.poll();
                    queue.add(i);
                }
            }
        }

        return queue.peek();
    }
}
