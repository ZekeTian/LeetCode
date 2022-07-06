package greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/split-array-largest-sum/
 * 
 * 题目描述：给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
 *         设计一个算法使得这 m 个子数组各自和的最大值最小。
 *         
 * 限制条件：
 *  （1）1 <= nums.length <= 1000
 *  （2）0 <= nums[i] <= 106
 *  （3）1 <= m <= min(50, nums.length)
 *  
 * 示例：
 *  示例 1
 *      输入：nums = [7,2,5,10,8], m = 2
 *      输出：18
 *      解释：
 *          一共有四种方法将 nums 分割为 2 个子数组。 
 *          其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 *          因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 *          
 *  示例 2
 *      输入：nums = [1,2,3,4,5], m = 2
 *      输出：9
 *      
 *  示例 3
 *      输入：nums = [1,4,4], m = 3
 *      输出：4    
 *      
 */
public class _410_SplitArrayLargestSum {

    public static void main(String[] args) {
        // test case1, output: 18
//        int[] nums = { 7, 2, 5, 10, 8 };
//        int m = 2;
        
        // test case2, output: 9
//        int[] nums = { 1, 2, 3, 4, 5 };
//        int m = 2;
        
        // test case3, output: 4
        int[] nums = { 1, 4, 4 };
        int m = 3;
        
//        _410Solution1 solution = new _410Solution1();
        
        _410Solution2 solution = new _410Solution2();
        
        
        System.out.println(solution.splitArray(nums, m));
        
    }
    
}


/**
 * 解法一：动态规划
 * 
 * 题目中，“使得这 m 个子数组各自和的最大值最小” 的含义：
 *  对于一个一个数组，将其分割成 m 个子数组可能有 n 种方式。
 *      在每种方式中，m 个子数组对应着有 m 个子数组和，然后取这 m 个子数组和的最大值 max。
 *  因为总共有 n 种方式，所以有 n 个最大值。最后，再在这 n 个最大值中取最小值作为最终结果。
 * 例如：nums = [1,2,3,4,5], m = 2
 *      nums 分割成 2 个子数组，可以有如下几种方式：
 *          [1] [2,3,4,5]，子数组和分别为：1、14，最大值为 14
 *          [1,2] [3,4,5]，子数组和分别为：3、12，最大值为 12
 *          [1,2,3] [4,5]，子数组和分别为：6、9， 最大值为 9
 *          [1,2,3,4] [5]，子数组和分别为：10、5， 最大值为 10
 *     因为有 4 种分割方式，所以有 4 个最大值：14、12、9、10，最后取这 4 个中的最小值 9 作为最终结果。
 *     
 * 动态规划思路：
 *  创建一个二维数组 memo[i][k]，其表示 nums[0...i] 分割成 k 段时的结果。
 *   memo[i][k] 可以由 memo[j][k - 1](j < i) 转移得到，即相当于 nums[0...j] 中 k - 1 段和 nums[j+1...i] 这一段，总共组成 k 段。
 * 为了快速求解 nums 中区间的和，需要先求解出 nums 的前缀和。
 * 
 */
class _410Solution1 {
    
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 求解出 nums 的前缀和，以便后面求解 nums 的区间和
        int len = nums.length;
        int[] prefixSum = new int[len + 1];
        for (int i = 1; i <= len; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        
        // 将 memo 中的值初始化为最大值
        int[][] memo = new int[len][m + 1]; // memo[i][k] 表示将 nums[0...i] 分割成 k 段时的结果
        for (int i = 0; i < len; ++i) {
            Arrays.fill(memo[i], Integer.MAX_VALUE);
        }
        
        // 设置 memo 的初始状态（即只分成一段时）
        for (int i = 0; i < len; ++i) {
            memo[i][1] = prefixSum[i + 1];
        }
        
        // 计算 memo 中各个状态的值
        for (int k = 2; k <= m; ++k) { // 将 nums 分割成 k 段
            for (int i = k - 1; i < len; ++i) { // 将 nums 分割成 k 段，则 nums 中至少有 k 个元素，所以 i 从 k - 1 开始
                for (int j = k - 2; j < i; ++j) { // memo[i][k] 是从 memo[j][k - 1] 转移过来，所以 nums[0...j] 至少有 k - 1 段（即 nums[0...j] 至少有 k - 1 个元素），故 j 从 k - 2 开始
                    memo[i][k] = Math.min(memo[i][k],
                                          Math.max(memo[j][k - 1], prefixSum[i + 1] - prefixSum[j + 1])); // memo[j][k - 1] 这种分割方式中，子数组和的最大值
                }
            }
        }
        
        return memo[len - 1][m];
    }
    
}


/**
 * 解法二：二分搜索
 * 
 * 思路：
 *  （1）遍历 nums，确定 nums 子数组和的最小值（即二分的左边界 left）、最大值（即二分的右边界 right）
 *  （2）在 left、right 之间，利用二分法寻找一个值，使得 nums 中的子数组符合要求
 */
class _410Solution2 {
    
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0; // nums 中子数组和的最小值，即二分搜索中的左边界值。
        int right = 0; // nums 中子数组和的最大值，即二分搜索中的右边界值
        
        // 遍历 nums，确定 left、right 
        for (int i : nums) {
            left = Math.max(left, i);
            right += i;
        }
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            int s = splits(nums, mid); // 按照 mid 去分割 nums，获得子数组的数量
            
            if (s > m) {
                left = mid + 1; // 增大 mid，使得后续子数组更长，从而减少子数组的数量
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    // 将 nums 分割成多个连续的子数组，每个子数组的和小于等于 target，最后返回子数组个数
    private int splits(int[] nums, int target) {
        int curSum = 0; // 当前子数组的和
        int num = 1; // 分割的子数组的数量
        
        for (int i : nums) {
            if (curSum + i > target) { // 当前数字 i 不能放进之前的子数组中，需要放进单独的一个子数组中
                curSum = 0; // 子数组的和置为 0
                ++num; // 子数组数量增加
            }
            
            curSum += i;
        }
        
        return num;
    }
    
}
