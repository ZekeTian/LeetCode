package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode.com/problems/daily-temperatures/
 * 
 * 题目描述：给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 *         其中 answer[i] 是指在第 i 天之后，才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *         
 * 限制条件：
 *  （1）1 <= temperatures.length <= 10^5
 *  （2）30 <= temperatures[i] <= 100
 * 
 * 示例：
 *  示例 1
 *      输入: temperatures = [73,74,75,71,69,72,76,73]
 *      输出: [1,1,4,2,1,1,0,0]
 *      
 *  示例 2
 *      输入: temperatures = [30,40,50,60]
 *      输出: [1,1,1,0]
 *  
 *  示例 3
 *      输入: temperatures = [30,60,90]
 *      输出: [1,1,0]
 *
 */
public class _739_DailyTemperatures {

    public static void main(String[] args) {
        // test case1, outptu: [1,1,4,2,1,1,0,0]
        int[] temperatures = { 73, 74, 75, 71, 69, 72, 76, 73 };
        
        // test case2, outptu: [1,1,1,0]
//        int[] temperatures = { 30, 40, 50, 60 };
        
        // test case3, outptu: [1,1,0]
//        int[] temperatures = { 30, 60, 90 };

        
        _739Solution solution = new _739Solution();
        int[] ret = solution.dailyTemperatures(temperatures);
        
        
        System.out.println(Arrays.toString(ret));
    }
}

/**
 * 对于当前天，要想知道多少天后升温，实际上相当于是找后面第一个大于当前天温度的，然后两天日期相减即可得到结果。
 * 寻找第一个大于（或小于）当前元素的元素，此类问题可以使用单调栈解决。此题中，stack 中的元素的按照非递增的顺序存储。
 * 
 * 遍历 nums 中剩余的元素
 *     循环判断当前日期的温度是否大于栈顶日期的温度
 *     如果当前日期的温度大于栈顶日期的温度，则相当于是找到特定日期，直接用当前日期减去栈顶日期即可，然后保存日期的差值。
 *     如果栈为空或当前日期的温度小于等于栈顶日期的温度，则直接将当前日期压入栈中（保持 stack 的单调非递增）
 *
 */
class _739Solution {
    
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[] {};
        }
        
        int[] ret = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>(); // 温度的非递增栈，但是为了能够快速计算出日期差，实际存储温度的下标（相当于是日期）
        
        for (int i = 0; i < temperatures.length; ++i) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()] /* 当前温度比栈顶元素的温度高 */) {
                // 当前温度比栈顶日期的温度高，则找到升温的日期，记录日期差
                int idx = stack.pop();
                ret[idx] = i - idx; // 因为是找到比栈顶日期更高的温度，所以结果存储到栈顶日期对应的位置中
            }
            
            stack.push(i); // 栈为空，或当前日期温度比栈顶日期的温度要低或一样时，则用栈记录当前日期
        }
        
        return ret;
    }
}
