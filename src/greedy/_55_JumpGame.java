package greedy;

/**
 * https://leetcode.com/problems/jump-game/
 * 
 * 题目描述：给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
 *         数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *         判断你是否能够到达最后一个下标。
 *         
 * 限制条件：
 *  （1）1 <= nums.length <= 3 * 10^4
 *  （2）0 <= nums[i] <= 10^5
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [2,3,1,1,4]
 *      输出：true
 *      解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *      
 *  示例 2
 *      输入：nums = [3,2,1,0,4]
 *      输出：false
 *      解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *      
 */
public class _55_JumpGame {

    public static void main(String[] args) {
        // test case1, output: true
//        int[] nums = { 2, 3, 1, 1, 4 };
        
        // test case2, output: false
        int[] nums = { 3, 2, 1, 0, 4 };
        
        _55Solution solution = new _55Solution();
        
        System.out.println(solution.canJump(nums));
    }
}

/**
 * 使用贪心算法，每次都尽量跳最远的距离。如果最终最远的距离大于 nums 的长度，则说明可以到达最后一个下标。
 * 具体做法如下：
 *  遍历 nums
 *      如果能目前最远的距离 longestDistance 小于当前下标 i，说明无法跳到当前位置，则更不可能到达最后一个下标，因此直接返回 false。
 *      否则，从当前位置出发，然后跳最远的距离（即 i + nums[i]）。之后，判断 i + nums[i] 和 longestDistance 的大小，取两者的较大值，赋值给 longestDistance。
 *  在上述循环中，若 longestDistance 比当前下标 i 大，说明可以跳到当前位置，则自然可以从当前位置向后跳。
 */
class _55Solution {
    
    public boolean canJump(int[] nums) {
        if (null == nums || nums.length == 0) {
            return true;
        }
        
        int longestDistance = 0; // 目前能够跳到的最远距离
        for (int i = 0; i < nums.length; ++i) {
            if (longestDistance < i) {
                return false; // 最远的距离都比当前的下标小，则说明无法跳到当前位置
            }
            // 更新最远距离
            longestDistance = Math.max(longestDistance, // 目前能够跳到的最远距离
                                       i + nums[i]); // 从当前位置出发，能够跳到的最远距离
        }
        
        return true;
    }
}
