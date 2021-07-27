package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber/
 *
 * 题目描述：你是一名职业小偷，计划抢劫街道上的房屋。每间房屋都有一定金额的现金，但是你不能同时抢劫两间相邻的房屋。
 *        因为如果两间相邻的房屋在同一天晚上被抢劫，会触发报警系统。给定一个整数数组 nums，表示每间房屋现金的
 *        金额，返回你能够在不触发警报的前提下所能抢劫到的最高的金额。
 *        
 * 限制条件：
 *  （1） 1 <= nums.length <= 100
 *  （2） 0 <= nums[i] <= 400
 */
public class _198_HouseRobber {

    public static void main(String[] args) {
        // test case1, output: 4
        //        int[] nums = { 1, 2, 3, 1 };

        // test case2, output: 12
        int[] nums = { 2, 7, 9, 3, 1 };

        //        _198Solution1 solution = new _198Solution1();

        //        _198Solution2 solution = new _198Solution2();

        _198Solution3 solution = new _198Solution3();

        System.out.println(solution.rob(nums));
    }
}

/**
 * 解法一：仅使用 递归 的方式解决
 */
class _198Solution1 {

    // 从 nums[index] 处的房间开始偷窃
    public int tryRob(int[] nums, int n, int index) {
        if (index >= n) {
            return 0;
        }

        int res = -1;
        // 偷窃 nums[index] 此处的房间，下一次从 nums[index + 2] 处开始偷窃 
        res = Math.max(res, nums[index] + tryRob(nums, n, index + 2));

        // nums[i] 不偷窃，下一次从 nums[index + 1]
        res = Math.max(res, tryRob(nums, n, index + 1));

        return res;
    }

    public int rob(int[] nums) {
        return tryRob(nums, nums.length, 0);
    }
}

/**
 * 解法二：使用 递归 + 记忆化搜索 的方式解决
 */
class _198Solution2 {

    // memo[index] 存储从 index 处开始偷窃时所能获得的最高金额
    private int[] memo;

    // 从 nums[index] 开始偷窃
    private int tryRob(int[] nums, int n, int index) {
        if (index == n - 1) {
            memo[index] = nums[index];
            return nums[index];
        }

        int res = -1;
        // nums[index] 处偷窃，下一次从 nums[index + 2] 开始偷窃
        if (index + 2 < n && memo[index + 2] == -1) {
            tryRob(nums, n, index + 2);
        }
        res = Math.max(res, nums[index] + (index + 2 < n ? memo[index + 2] : 0));

        // nums[index] 处不偷窃，下一次从 nums[index + 1] 开始偷窃
        if (index + 1 < n && memo[index + 1] == -1) {
            tryRob(nums, n, index + 1);
        }
        res = Math.max(res, (index + 1 < n ? memo[index + 1] : 0));

        memo[index] = res;
        return res;
    }

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        return tryRob(nums, nums.length, 0);
    }
}

/**
 * 解法三：使用 动态规划 的方式解决
 */
class _198Solution3 {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n]; // memo[index] 存储从 index 处开始偷窃时所能获得的最高金额

        memo[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            // nums[i] 处偷窃，下一次从 nums[i + 2] 处开始偷窃
            int res1 = nums[i] + (i + 2 < n ? memo[i + 2] : 0);

            // nums[i] 处不偷窃，下一次从 nums[i + 1] 处开始偷窃
            int res2 = (i + 1 < n ? memo[i + 1] : 0);
            memo[i] = Math.max(res1, res2);
        }

        return memo[0];
    }
}
