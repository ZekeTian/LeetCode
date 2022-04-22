package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/
 * 
 * 题目描述：给定一个未排序的整数数组 nums ， 返回最长递增子序列的个数 。
 *         注意 这个数列必须是 严格 递增的。
 *         
 * 限制条件：
 *  （1）1 <= nums.length <= 2000
 *  （2）-10^6 <= nums[i] <= 10^6
 *  
 * 示例；
 *   示例 1
 *      输入: [1,3,5,4,7]
 *      输出: 2
 *      解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 *   
 *   示例 2
 *      输入: [2,2,2,2,2]
 *      输出: 5
 *      解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 *
 */
public class _673_NumberOfLongestIncreasingSubsequence {

    public static void main(String[] args) {
        // test case1, output: 2
//        int[] nums = { 1, 3, 5, 4, 7 };
        
        // test case2, output: 5
        int[] nums = { 2, 2, 2, 2, 2 };
        
        
        _673Solution solution = new _673Solution();
        
        
        System.out.println(solution.findNumberOfLIS(nums));
    }
}

/**
 * 思路和第 300 题类似，只是需要记录个数。
 */
class _673Solution {
    
    public int findNumberOfLIS(int[] nums) {
        // memoNums[i][j] 表示以 nums[i] 结尾并且长度为 j 的子序列的个数 
        int[][] memoNums = new int[nums.length][nums.length + 1]; 
        // memoLength[i] 表示以 nums[i] 结尾的最长子序列的长度
        int[] memoLength = new int[nums.length]; 
        
        // 初始化 memoNums、memoLength
        for (int i = 0; i < memoNums.length; ++i) {
            memoNums[i][1] = 1; // 长度为 1 的子序列只可能是自身，所以个数是 1
        }
        Arrays.fill(memoLength, 1);
        
        int longest = 1; // 最长子序列的长度
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    memoLength[i] = Math.max(memoLength[i], memoLength[j] + 1);
                    longest = Math.max(longest, memoLength[i]);
                    // 因为 nums[i] 是接在 nums[j] 之后，所以 memoNums[j][memoLength[j]] 有多少个，memoNums[i][memoLength[j] + 1] 就得增加多少
                    memoNums[i][memoLength[j] + 1] += memoNums[j][memoLength[j]]; 
                }
            }
        }
        
        int count = 0; // 长度为 longest 的子序列个数
        for (int i = 0; i < memoNums.length; ++i) {
            count += memoNums[i][longest];
        }
        
        return count;
    }
}
