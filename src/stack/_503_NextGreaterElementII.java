package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/next-greater-element-ii/
 * 
 * 题目描述：给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 *         数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
 *         如果不存在，则输出 -1 。
 *         
 * 限制条件：
 *  （1）1 <= nums.length <= 10^4
 *  （2）-10^9 <= nums[i] <= 10^9
 *  
 * 示例：
 *  示例 1
 *      输入: nums = [1,2,1]
 *      输出: [2,-1,2]
 *      解释: 第一个 1 的下一个更大的数是 2；
 *           数字 2 找不到下一个更大的数； 
 *           第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 *           
 *  示例 2
 *      输入: nums = [1,2,3,4,3]
 *      输出: [2,3,4,-1,4]
 *
 */
public class _503_NextGreaterElementII {

    public static void main(String[] args) {
        // test case1, output: [2, -1, 2]
//        int[] nums = { 1, 2, 1 };
        
        // test case2, output: [2, 3, 4, -1, 4]
        int[] nums = { 1, 2, 3, 4, 3 };
        
        _503Solution solution = new _503Solution();
        
        
        System.out.println(Arrays.toString(solution.nextGreaterElements(nums)));
    }
}


/**
 * 思路和第 739 题类似，利用单调栈
 * 对于给定数组 nums，如果题目中要求离 nums[i] 最近并且比 nums[i] 大（或小）的数，则考虑使用单调栈。
 * 与第 739 题稍有不同的地方是，本题需要遍历原数组两遍，并且还得是循环遍历。
 * 遍历两遍的原因是，确保数组中每个元素都能被处理到。
 * 例如，对于数组 [2,3,1]，其单调栈 stack（单调非递增）的变化过程如下：
 *   1）初始时，stack 为空
 *   2）遍历到 2 时，stack = [2
 *   3）遍历到 3 时，弹出 2 ，将 3 压入栈中，stack = [3。此时，2 的结果已经确定。 
 *   4）遍历到 1 时，将 1 压入栈中，stack = [3, 1。
 * 如果只遍历一遍，最终栈中还剩余 3、1 ，1 的结果未确定。
 */
class _503Solution {
    
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }
        
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        for (int i = 0; i < 2 * n; ++i) {
            int idx = i % n; // 通过取模实现循环遍历
            while (!stack.isEmpty() && nums[idx] > nums[stack.peek()]) {
                res[stack.pop()] = nums[idx]; // nums[idx] 比 stack 中栈顶元素大，则 nums[idx] 即为 stack 栈顶元素需要找的值
            }
            stack.push(idx);
        }
        
        return res;
    }
}
