package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 * 
 * 题目描述：给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 * 
 * 限制条件：
 *  （1）0 <= s.length <= 3 * 10^4
 *  （2）s[i] 为 '(' 或 ')'
 * 
 * 示例：
 *  示例 1
 *      输入：s = "(()"
 *      输出：2
 *      解释：最长有效括号子串是 "()"
 *      
 *  示例 2
 *      输入：s = ")()())"
 *      输出：4
 *      解释：最长有效括号子串是 "()()"
 *      
 *  示例 3
 *      输入：s = ""
 *      输出：0
 *
 */
public class _32_LongestValidParentheses {
    
    public static void main(String[] args) {
        // test case1, output: 2
        String s = "(()";
        
        // test case2, output: 4
//        String s = ")()())";
        
        // test case2, output: 0
//        String s = "";
        
//        _32Solution1 solution = new _32Solution1();

        _32Solution2 solution = new _32Solution2();
        
        
        System.out.println(solution.longestValidParentheses(s));
    }
}

/**
 * 解法一：利用栈判断括号是否合法，然后用一个列表保存所有合法的括号的索引。之后，对列表进行升序排序，然后寻找到最长的连续上升子串。
 */
class _32Solution1 {
    
    public int longestValidParentheses(String s) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        
        // 记录可以匹配到的括号的下标
        for (int i = 0; i < s.length(); ++i) {
            if ('(' == s.charAt(i)) {
                stack.push(i);
            } else if (!stack.isEmpty()) {
                list.add(i);
                list.add(stack.pop());
            }
        }
        
        // 对 list 排序，然后计算连续子串的最长长度
        Collections.sort(list);
        int maxLen = 0, len = 1;
        for (int i = 1; i < list.size(); ++i) {
            if (list.get(i) - list.get(i - 1) == 1) {
                ++len; // 是连续匹配，长度加 1
            } else {
                len = 1; // 不是连续匹配，则从当前括号开始新的匹配
            }
            maxLen = Math.max(maxLen, len);
        }
        
        return maxLen;
    }
}

/**
 * 解法二：只利用一个栈记录数据。但是这个栈需要记录两种类型的数据，一是：记录最左边无法匹配到的右括号的下标，二是：记录左括号的下标。
 *        记录最左边无法匹配到的右括号的下标，是为了计算出子串长度。
 */
class _32Solution2 {
    
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始时，最左边无法匹配到的右括号的下标是 -1
        int maxLen = 0;
        for (int i = 0; i < s.length(); ++i) {
            if ('(' == s.charAt(i)) {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i); // 此时，s[i] 是 '('，且无法匹配到 ')'，所以用 stack 记录其位置
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        
        return maxLen;
    }
}
