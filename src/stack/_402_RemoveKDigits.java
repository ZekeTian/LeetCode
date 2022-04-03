package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/remove-k-digits/
 * 
 * 题目描述：给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
 *         请你以字符串形式返回这个最小的数字。
 *         
 * 限制条件：
 *  （1）1 <= k <= num.length <= 10^5
 *  （2）num 仅由若干位数字（0 - 9）组成
 *  （3）除了 0 本身之外，num 不含任何前导零
 *  
 * 示例：
 *  示例 1
 *      输入：num = "1432219", k = 3
 *      输出："1219"
 *      解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 *      
 *  示例 2
 *      输入：num = "10200", k = 1
 *      输出："200"
 *      解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *      
 *  示例 3
 *      输入：num = "10", k = 2
 *      输出："0"
 *      解释：从原数字移除所有的数字，剩余为空就是 0 。
 *
 */
public class _402_RemoveKDigits {

    public static void main(String[] args) {
        // test case1, output: "1219"
//        String num = "1432219";
//        int k = 3;
        
        // test case2, output: "200"
//        String num = "10200";
//        int k = 1;
        
        // test case2, output: "0"
        String num = "10";
        int k = 2;
        
        
        _402Solution solution = new _402Solution();
        
        
        System.out.println(solution.removeKdigits(num, k));
    }
}

/**
 * 遍历整数字符串
 *   判断栈是否需要弹出数字，栈弹出数字相当于是将该数字删除。
 *     栈弹出数字的条件：
 *       k > 0 （还需要删除数字）；
 *       并且栈不为空；
 *       并且当前数字比栈顶元素小（当前数字小，则更适合放进栈，从而形成一个更小的数字）
 *   利用循环从栈中不断弹出元素，直到循环结束。
 *   栈无法弹出时，判断当前数字是否能放进栈，放进栈的两个条件：当前数字不是 0 或 栈当前不为空（避免前导 0）
 * 
 * 判断是否还需要删除数字，如果需要删除数字，则继续从栈中弹出元素
 * 
 * 判断栈是否为空，如果为空则直接返回 "0"；否则，弹出栈中元素拼接成完整的整数，但是需要注意反转拼接的结果。
 * 
 */
class _402Solution {
    
    public String removeKdigits(String num, int k) {
        if (num == null || num.length() == 0 || k >= num.length()) {
            return "0";
        }
        
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); ++i) {
            while (k > 0 /* 还需要删除数字 */
                    && !stack.isEmpty() /* 栈不为空，保证能够弹出元素 */
                    && num.charAt(i) < stack.peek() /* 当前数字更小，更适合放进最终结果中，而栈顶元素不适合 */ ) {
                stack.pop(); // 从栈中弹出一个元素，相当于是从原来的 num 中删除一个数字
                --k;
            }
            
            // 避免前导 0 
            if (num.charAt(i) != '0' /* 当前数字不是 0，则可以直接该数字放进栈中 */
                    || !stack.isEmpty() /* 当前数字是 0，但是栈不为空，不会产生前导 0 */ ) {
                stack.push(num.charAt(i));
            }
        }
        
        // 判断是否还需要删除数字
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            --k;
        }
        
        if (stack.isEmpty()) {
            return "0";
        }
        
        // 拼接 stack 中数字，得到结果
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        
        return builder.reverse().toString();
    }
    
}
