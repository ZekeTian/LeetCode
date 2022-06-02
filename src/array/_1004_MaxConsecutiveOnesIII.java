package array;


/**
 * https://leetcode.com/problems/max-consecutive-ones-iii/
 * 
 * 题目描述：给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 10^5
 *  （2）nums[i] 不是 0 就是 1
 *  （3）0 <= k <= nums.length
 *  
 * 示例；
 *  示例 1
 *      输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 *      输出：6
 *      解释：[1,1,1,0,0,1,1,1,1,1,1]
 *           粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 *           
 *  示例 2
 *      输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 *      输出：10
 *      解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 *           粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 *           
 */
public class _1004_MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        // test case1, output: 6
//        int[] nums = { 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
//        int k = 2;
        
        // test case2, output: 10
        int[] nums = { 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1 };
        int k = 3;
        
        _1004Solution solution = new _1004Solution();
        
        
        System.out.println(solution.longestOnes(nums, k));
    }
}

/**
 * 滑动窗口问题，利用双指针解决。
 * 思路：题目要求，可以翻转最多 k 个 0，使得子数组中是连续的 1。则该题目实际上可以转换成，寻找一个最多只有 k 个 0 的最长子数组。
 *      因为要求是连续的子数组，所以可以使用滑动窗口的解法。
 */
class _1004Solution {
    
    public int longestOnes(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        
        int res = 0;
        int left = 0, right = -1; // [left, right] 为滑动窗口
        int count = 0; // [left, right] 窗口中 0 的个数
        
        while (left < nums.length) {
            if (count <= k && right + 1 < nums.length) {
                // 窗口内 0 的个数符合要求，则滑动 right ，增大窗口
                ++right; 
                if (nums[right] == 0) {
                    ++count;
                }
            } else {
                // 窗口内 0 的个数不符合要求，则滑动 left ，减小窗口
                if (count > k && nums[left] == 0) { 
                    --count; // 因为 nums[left] = 0，所以滑动 left 后，窗口中 0 的个数会减少
                }
                ++left;
            }
            
            if (count <= k) { // 调整窗口后，窗口内 0 的个数依然符合要求，则更新 res
                res = Math.max(res, right - left + 1);
            }
        }
        
        return res;
    }
    
}
