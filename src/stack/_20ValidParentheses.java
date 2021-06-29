package stack;

import java.util.ArrayList;
import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parentheses/
 * 
 * 题目描述：给定一个只包含 "()[]{}" 的字符串，判断输入的字符串是否合法。字符串合法的要求是，字符串的左右括号必须是匹配的。
 * 
 * 条件限制：
 *  （1）1 <= s.length <= 10^4
 *  （2）s 只包含 "()[]{}"
 * 
 * 示例：
 *  Input: s = "()"
 *  Output: true
 *  
 *  Input: s = "(]"
 *  Output: false
 */
public class _20ValidParentheses {
    
    public static void main(String[] args) {
        String s = "(]";
        
        boolean result = new _20Solution().isValid(s);

        System.out.println(result);
    }
}


class _20Solution {
    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        
        // 字符集：()[]{}
        for (char c : charArray) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) {
                    return false; // 右括号的数量多于左括号
                }

                // 将栈顶元素和当前元素进行匹配
                Character top = stack.pop();
                if (top.equals('(') && c != ')') {
                    return false;
                }
                if (top.equals('[') && c != ']') {
                    return false;
                }
                if (top.equals('{') && c != '}') {
                    return false;
                }
            }
        }
        
        if (stack.isEmpty()) {
            return true; // 左右括号的数量一样，且都能匹配到
        } else {
            return false; // 左括号的数量多于右括号 
        }
    }
}
