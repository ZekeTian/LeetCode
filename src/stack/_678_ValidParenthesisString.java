package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/valid-parenthesis-string/
 * 
 * 题目描述：给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *          （1）任何左括号 ( 必须有相应的右括号 )。
 *          （2）任何右括号 ) 必须有相应的左括号 ( 。
 *          （3）左括号 ( 必须在对应的右括号之前 )。
 *          （4）* 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 *          （5）一个空字符串也被视为有效字符串。
 * 
 * 限制条件：字符串大小将在 [1，100] 范围内
 * 
 * 示例：
 *  示例 1
 *      输入: "()"
 *      输出: True
 * 
 *  示例 2
 *      输入: "(*)"
 *      输出: True
 *  
 *  示例 3
 *      输入: "(*))"
 *      输出: True
 * 
 */
public class _678_ValidParenthesisString {

    public static void main(String[] args) {
        // test case1, output: true
//        String s = "()";
        
        // test case2, output: true
//        String s = "(*)";
        
        // test case3, output: true
//        String s = "(*))";
        
        // test case3, output: true
        String s = "()(*()";
        
        
        _678Solution1 solution = new _678Solution1();
        
        
        System.out.println(solution.checkValidString(s));
    }
}

/**
 * 思路一：回溯法
 */
class _678Solution1 {

    private String s = "";
    
    private boolean check(int idx, Stack<Character> stack) {
        if (idx == s.length()) {
            return stack.isEmpty();
        }
        
        boolean res = false;
        if (s.charAt(idx) == '(') {
            stack.push('(');
            res = check(idx + 1, (Stack<Character>) stack.clone());
        } else if (s.charAt(idx) == ')') {
            if (stack.isEmpty()) {
                return false;
            }
            stack.pop();
            res = check(idx + 1, (Stack<Character>) stack.clone());
        } else {
            // s[i] == '*' 时，尝试所有可能的情况
            // 将 '*' 视作一个空字符串
            if (check(idx + 1, (Stack<Character>) stack.clone())) {
                return true;
            }
            
            // 将 '*' 视作 '('
            stack.push('(');
            if (check(idx + 1, (Stack<Character>) stack.clone())) {
                return true;
            }
            stack.pop();
            
            // 将 '*' 视作 ')'
            if (stack.isEmpty()) {
                return false;
            }
            stack.pop();
            if (check(idx + 1, (Stack<Character>) stack.clone())) {
                return true;
            }
        }
        
        return res;
    }
    
    public boolean checkValidString(String s) {
        this.s = s;
        return check(0, new Stack<>());
    }
    
}

/**
 * 解法二：使用两个栈解决。一个栈存储 '(' 的下标，另一个栈存储 '*' 的下标。
 */
class _678Solution2 {
    
    public boolean checkValidString(String s) {
        Stack<Integer> leftStack = new Stack<>(); // 存储 '(' 的下标
        Stack<Integer> starStack = new Stack<>(); // 存储 '*' 的下标
        
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                leftStack.push(i);
            } else if (s.charAt(i) == '*') {
                starStack.push(i);
            } else {
                // 如果是右括号时，优先用 '(' 匹配
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }
        
        // 判断 leftStack、starStack 剩余的字符是否有效
        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            if (leftStack.pop() > starStack.pop()) { // 形如：*(，像这种字符串无效
                return false;
            }
        }
        
        return leftStack.isEmpty();
    }
}

