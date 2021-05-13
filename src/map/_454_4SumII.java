package map;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/4sum-ii/
 * 题目描述：给定四个整数数组 nums1、nums2、nums3、num4，它们的长度均为 n，在这四个数组中各找一个元素，组成一个四元组 (i, j, k, l)，使得它们的和为 0。
 *        请给出符合条件的所有四元组个数。
 *
 * 条件限制：
 *  （1）nums1.length = nums2.length = nums3.length = nums4.length = n
 *  （2）1 <= n <= 200
 *  （3）-2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 *  
 *  示例：
 *  Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
 *  Output: 2
 *  Explanation:
 *  1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
 *  2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class _454_4SumII {

    public static void main(String[] args) {
        //        int[] nums1 = { 1, 2 };
        //        int[] nums2 = { -2, -1 };
        //        int[] nums3 = { -1, 2 };
        //        int[] nums4 = { 0, 2 };

        int[] nums1 = { 0 };
        int[] nums2 = { 0 };
        int[] nums3 = { 0 };
        int[] nums4 = { 0 };

        _454Solution solution = new _454Solution();
        int result = solution.fourSumCount(nums1, nums2, nums3, nums4);
        System.out.println(result);
    }
}

/**
 * 解法一：用一个 map 存储两个数组的和（也可以使用两个 Map 分别各自存储两个数组的和）
 */
class _454Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int result = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        // 用 map 存储 num3、num4 的和
        for (int i = 0; i < nums3.length; ++i) {
            for (int j = 0; j < nums4.length; ++j) {
                int sum = nums3[i] + nums4[j];
                Integer val = map.getOrDefault(sum, 0);
                map.put(sum, val + 1);
            }
        }

        // 遍历 nums1、nums2 
        for (int i = 0; i < nums1.length; ++i) {
            for (int j = 0; j < nums2.length; ++j) {
                if (map.get(0 - (nums1[i] + nums2[j])) != null) {
                    result += map.get(0 - (nums1[i] + nums2[j]));
                }
            }
        }

        return result;
    }
}