package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/
 * 
 * 题目描述：给定一个非空的数组 nums，并且 nums 只含有正整数。在 nums 中能否找到两个不相交的子集，使得这两个子集的和相等。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 200
 *  （2）1 <= nums[i] <= 100
 * 
 * 示例：
 *  Input: nums = [1,5,11,5]
 *  Output: true
 *  解释：nums 可以拆分成 [1, 5, 5] 和 [11] 两个不相交的子集，而这两个子集的和相等。
 * 
 */
public class _416_PartitionEqualSubsetSum {

    public static void main(String[] args) {
        // test case1, output: true
        //        int[] nums = { 1, 5, 11, 5 };

        // test case2, output: false
        //                      int[] nums = { 1, 2, 3, 5 };

        // test case3, output: false
        int[] nums = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 99, 97 };

        //                _416Solution1 solution = new _416Solution1();

        //                _416Solution2 solution = new _416Solution2();

        _416Solution3 solution = new _416Solution3();

        System.out.println(solution.canPartition(nums));

    }
}

/**
 * 解法一：仅使用 递归 的方式解决
 */
class _416Solution1 {

    private int[] nums;

    public boolean partition(int i, int sum) {
        if (i == nums.length || sum < 0) {
            return false;
        }

        if (sum == 0) {
            return true;
        }

        if (partition(i + 1, sum - nums[i]) /* 放 nums[i] */
                || partition(i + 1, sum) /* 不放 nums[i] */) {
            return true;
        }

        return false;
    }

    public boolean canPartition(int[] nums) {
        this.nums = nums;

        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 != 0) {
            return false; // 有小数，则直接返回 false
        }

        return partition(0, sum / 2);
    }
}

/**
 * 解法二：使用 递归 + 记忆化搜索 的方式解决
 */
class _416Solution2 {

    private int[] nums;
    // memo[i][j] 存储第 i 号元素、sum 为 j 时的状态，如果值为 -1 表示为计算，值为 0 表示不能分成两个子集，值为 1 表示能分成两个子集
    private int[][] memo;

    private boolean partition(int i, int sum) {
        if (i == nums.length || sum < 0) {
            return false;
        }

        if (sum == 0) {
            memo[i][sum] = 1;
            return true;
        }

        // 判断当前状态是否计算过，如果计算过，则直接返回
        if (memo[i][sum] != -1) {
            return memo[i][sum] == 1;
        }

        // 没有计算，则计算并保存结果
        if (partition(i + 1, sum - nums[i]) /* 放 nums[i] */
                || partition(i + 1, sum) /* 不放 nums[i] */ ) {
            memo[i][sum] = 1;
            return true;
        }

        memo[i][sum] = 0;
        return false;
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 != 0) {
            return false;
        }

        this.nums = nums;
        this.memo = new int[nums.length][sum / 2 + 1];

        for (int i = 0; i < nums.length; ++i) {
            Arrays.fill(this.memo[i], -1);
        }

        return partition(0, sum / 2);
    }
}

/**
 * 解法三：使用 动态规划 的方式解决
 */
class _416Solution3 {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }

        if (sum % 2 != 0) {
            return false;
        }

        int targetSum = sum / 2; // 目标 sum，相当于是背包问题的背包容量
        boolean[] memo = new boolean[targetSum + 1]; // memo[i] 表示容量为 i 的背包能否由 nums 中的数字累加得到，如果 nums 中的数字能累加得到 i，则为 true；否则，为 false
        Arrays.fill(memo, false);

        // 由 nums[0] 初始化
        for (int i = 0; i <= targetSum; ++i) {
            memo[i] = (nums[0] == i ? true : false);
        }

        for (int i = 1; i < nums.length; ++i) {
            for (int j = targetSum; j >= nums[i]; --j) {
                memo[j] = (memo[j] || memo[j - nums[i]]); // 不放 nums[i] 和 放 nums[i]，两个只要有一个为 true 即可
            }
        }

        return memo[targetSum];
    }
}
