package dynamicprogramming;

/**
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/
 *
 * 题目描述：给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * 
 * 限制条件：
 *  （1）1 <= len(A), len(B) <= 1000
 *  （2）0 <= A[i], B[i] < 100
 * 
 * 示例：
 *  输入：
 *      A: [1,2,3,2,1]
 *      B: [3,2,1,4,7]
 *  输出：3
 *  解释：长度最长的公共子数组是 [3, 2, 1] 。
 */
public class _718_MaximumLengthOfRepeatedSubarray {

    public static void main(String[] args) {
        // test case 1, output: 3
//        int[] nums1 = {1, 2, 3, 2, 1};
//        int[] nums2 = {3, 2, 1, 4, 7};
        
        // test case 2, output: 3
        int[] nums1 = {1, 0, 0, 0, 1};
        int[] nums2 = {1, 0, 0, 1, 1};
        
        _718Solution solution = new _718Solution();
        
        System.out.println(solution.findLength(nums1, nums2));
    }
}

/**
 * 此题与第 1143 道题 LongestCommonSubsequence 相比，本题要求连续。
 * 因为要求连续，所以如果中间不连续，则前面的结果不能传递到后面，需要从头开始计算，对应代码中的形式即为：循环中的 if 不需要加 else（即使要加 else，else 中也只是重置当前位置的值）。
 * 如果不要求连续，则无论中间是否连续，前面的结果都可以传递到后面，对应代码中的形式即为：循环中的 if 要加 else ，在 else 中将之前的结果传递到当前位置。
 */
class _718Solution {
    
    public int findLength(int[] nums1, int[] nums2) {
        int[][] memo = new int[nums1.length + 1][nums2.length + 1]; // memo[i][j] 表示：子数组以 nums1[i - 1]、nums2[j - 1] 结尾 
        int max = 0; // 记录最长子数组的长度。因为 memo[nums1.length][nums2.length] 中记录的不一定是最长子数组的长度。
        for (int i = 1; i <= nums1.length; ++i) {
            for (int j = 1; j <= nums2.length; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                    max = Math.max(max, memo[i][j]);
                }
            }
        }

        return max;
    }
}