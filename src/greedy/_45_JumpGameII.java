package greedy;

/**
 * https://leetcode.com/problems/jump-game-ii/
 * 
 * 题目描述：给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
 *         数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *         你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *         假设你总是可以到达数组的最后一个位置。
 *         
 * 限制条件：
 *  （1）1 <= nums.length <= 10^4
 *  （2）0 <= nums[i] <= 1000
 * 
 * 示例：
 *  示例 1
 *      输入: nums = [2,3,1,1,4]
 *      输出: 2
 *      解释: 跳到最后一个位置的最小跳跃数是 2。
 *           从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *      
 *  示例 2
 *      输入: nums = [2,3,0,1,4]
 *      输出: 2
 * 
 */
public class _45_JumpGameII {

    public static void main(String[] args) {
        // test case1, output: 2
//      int[] nums = { 2, 3, 1, 1, 4 };

        // test case1, output: 2
        int[] nums = { 2, 3, 0, 1, 4 };

//        _45Solution1 solution = new _45Solution1();
        
        _45Solution2 solution = new _45Solution2();

        System.out.println(solution.jump(nums));
    }
}

/**
 * 解法一：动态规划
 *       思路和第 300 题最长子序列类似。
 */
class _45Solution1 {

    public int jump(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int[] memo = new int[nums.length]; // memo[i] 表示从下标 0 到达下标 i 所需要的最少的步数
        for (int i = 1; i < nums.length; ++i) {
            memo[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; ++j) {
                if (j + nums[j] >= i) { // 从下标 j 可以到达下标 i，则更新 memo[i]
                    memo[i] = Math.min(memo[i], memo[j] + 1);
                }
            }
        }

        return memo[nums.length - 1];
    }
}

/**
 * 解法二：贪心算法，每次都选择跳到最远的位置
 *        假设当前位置是 i，则其可以跳到最远的地方是：i + nums[i]。这意味着 i 跳一步，可以跳到 i+1, ..., nums[i] 中的任一位置。
 *        但是为了能够使得最终跳的步数最少，应当从 i+1, ..., nums[i] 中选择跳的最远的位置。然后下一次，在新位置按照此贪心规则继续跳。
 */
class _45Solution2 {

    public int jump(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        
        int end = 0; // 本轮能够到达的最远位置
        int maxPos = 0; // 下一轮能够跳到的最远位置
        int steps = 0;
        
        for (int i = 0; i < nums.length && end < nums.length - 1 /* 还未能到达数组结尾处 */; ++i) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                // 找到了跳的最远的位置，则直接跳到该位置
                ++steps;
                end = maxPos;
            }
        }

        return steps;
    }
}
