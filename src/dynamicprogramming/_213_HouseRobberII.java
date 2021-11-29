package dynamicprogramming;

/**
 * https://leetcode.com/problems/house-robber-ii/
 * 
 * 题目描述：你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
 *        同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *        给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 *        
 * 限制条件：
 *  （1）1 <= nums.length <= 100
 *  （2）0 <= nums[i] <= 1000
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [2,3,2]
 *      输出：3
 *      解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 *      
 *  示例 2
 *      输入：nums = [1,2,3,1]
 *      输出：4
 *      解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。偷窃到的最高金额 = 1 + 3 = 4 。
 *
 */
public class _213_HouseRobberII {

    public static void main(String[] args) {
        // test case 1, output: 3
        int[] nums = {2, 3, 2};
        
        // test case 2, output: 4
//        int[] nums = {1, 2, 3, 1};
        
        _213Solution solution = new _213Solution();
        
        
        System.out.println(solution.rob(nums));
    }
}

/**
 * 动态规划解决
 */
class _213Solution {
    
    private int[] nums = null;
    
    // nums[start, end] 范围内的房子进行偷窃
    private int robRange(int start, int end) {
        int[] memo = new int[nums.length];
        memo[end] = nums[end];
        
        for (int i = end - 1; i >= start; --i) {
            int res = nums[i] + (i + 2 <= end ? memo[i + 2] : 0);
            memo[i] = Math.max(memo[i + 1], res);
        }
        
        return memo[start];
    }
    
    public int rob(int[] nums) {
        this.nums = nums;
        int len = nums.length;
        if (1 == len) {
            return nums[0];
        }
        
        // 在下面两种情况中取最大的一种
        //  nums[0, len - 2]：偷第一间房子，不偷最后一间；nums[1, len - 1]：不偷第一间房子，偷最后一间房子
        return Math.max(robRange(0, len - 2), robRange(1, len - 1));
    }
}
