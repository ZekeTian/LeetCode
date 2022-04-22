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
        int[] nums = { 3, 1, 2 };
        
        // test case2, output: 5
//        int[] nums = { 2, 2, 2, 2, 2 };
        
        
//        _673Solution1 solution = new _673Solution1();
        
        _673Solution2 solution = new _673Solution2();
        
        
        System.out.println(solution.findNumberOfLIS(nums));
    }
}

/**
 * 思路和第 300 题类似，只是需要记录个数。
 * 实现方式一，使用二维数组存储所有的中间结果。
 */
class _673Solution1 {
    
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

/**
 * 思路和第 300 题类似，只是需要记录个数。
 * 实现方式二，使用一维数组存储所有的中间结果。
 */
class _673Solution2 {
    
    public int findNumberOfLIS(int[] nums) {
        // memoNums[i] 存储以 nums[i] 结尾的最长子序列的个数
        int[] memoNums = new int[nums.length];
        // memoLength[i] 存储以 nums[i] 结尾的最长子序列的长度
        int[] memoLength = new int[nums.length];
        
        Arrays.fill(memoLength, 1);

        int longest = 1;
        for (int i = 0; i < nums.length; ++i) {
            // 计算出以 nums[i] 结尾的最长子序列的长度 memoLength[i]
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    memoLength[i] = Math.max(memoLength[i], memoLength[j] + 1);
                    longest = Math.max(longest, memoLength[i]);
                }
            }
            
            // 统计出以 nums[i] 结尾，并且长度为 memoLength[i] 的子序列的个数 memoNums[i]
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j] && (memoLength[i] == memoLength[j] + 1)) {
                    memoNums[i] += memoNums[j];
                }
            }
            
            // 当数组是递减序列，则上面循环的 if 进不去，因此此时 memoNums[i] = 0。
            // 但是实际上，nums[i] 至少可以组成一个长度为 1 的子序列（即只有它自身这一个元素），所以 memoNums[i] 至少为 1
            if (memoNums[i] == 0) {
                memoNums[i] = 1;
            }
        }
        
        // 统计出所有长度为 longest 的子序列的个数
        int count = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (memoLength[i] == longest) {
                count += memoNums[i];
            }
        }
        
        return count;
    }
}