package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 * 
 * 题目描述：给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *         整数除法仅保留整数部分。
 *         你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
 *         注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 3 * 10^5
 *  （2）s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 *  （3）s 表示一个 有效表达式
 *  （4）表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
 *  （5）题目数据保证答案是一个 32-bit 整数
 * 
 * 示例：
 *  示例 1
 *      输入：s = "3+2*2"
 *      输出：7
 *  
 *  示例 2
 *      输入：s = " 3/2 "
 *      输出：1
 *  
 *  示例 3
 *      输入：s = " 3+5 / 2 "
 *      输出：5
 *
 */
public class _227_BasicCalculatorII {

    public static void main(String[] args) {
        // test case1, output: 7
//        String s = "3+2*2";
        
        // test case2, output: 1
//        String s = "3/2";
        
        // test case3, output: 5
//        String s = " 3+5 / 2 ";
        
        // test case4, output: 2
        String s = "1 - 1 + 2";
        
        
        _227Solution solution = new _227Solution();
        
        
        System.out.println(solution.calculate(s));
    }
}

/**
 *  利用辅助栈求解，一个辅助栈 numStack 存储操作数，另一个辅助栈 opStack 存储操作符。
 *  遍历表达式字符串，跳过空格，提取出数字和操作符。
 *  提取的数字直接放进 numStack 中。
 *  提取的操作符，判断该操作符和栈顶操作符的优先级。
 *      如果 opStack 栈顶的操作符大于该操作符，则可以从栈中弹出元素，进行计算。重复该过程，直到 opStack 栈顶的操作符小于该操作符。
 *      如果 opStack 栈顶的操作符小于该操作符，则将该操作符压入 opStack 中。
 *  当到达字符串结尾时，循环弹出栈中元素，执行计算操作。
 */
class _227Solution {
    
    private boolean isDigital(char ch) {
        return (ch >= '0' && ch <= '9');
    }
    
    // 从 s[i] 处开始跳过一段空格。即从 s[i] 处开始遍历，直到遇到非空格时停止。
    private int skipBlank(String s, int i) {
        while (i < s.length() && s.charAt(i) == ' ') {
            ++i;
        }
        
        return i;
    }
    
    // 从栈中弹出元素，计算出结果，然后再将结果压入栈中
    private void compute(Stack<Integer> numStack, Stack<Character> opStack) {
        char op = opStack.pop();
        int a = numStack.pop();
        int b = numStack.pop();
        
        if (op == '+') {
            numStack.push(b + a);
        } else if (op == '-') {
            numStack.push(b - a);
        } else if (op == '*') {
            numStack.push(b * a);
        } else { // '/'
            numStack.push(b / a); 
        }
    }
    
    
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // 设置操作符优先级
        Map<Character, Integer> priorityMap = new HashMap<>();
        priorityMap.put('+', 1);
        priorityMap.put('-', 1);
        priorityMap.put('*', 2);
        priorityMap.put('/', 2);
        
        Stack<Integer> numStack = new Stack<>();; // 存储操作数
        Stack<Character> opStack = new Stack<>(); // 存储操作符
        for (int i = 0; i < s.length(); ++i) {
            // 提取数字
            i = skipBlank(s, i); // 提取数字前，先跳过空格
            int num = 0;
            while (i < s.length() && isDigital(s.charAt(i))) {
                num = num * 10 + s.charAt(i++) - '0';
            }
            numStack.push(num);
            
            // 提取操作符
            i = skipBlank(s, i); // 提取操作符前，先跳过空格
            while (!opStack.isEmpty() // 还有操作符需要计算
                    && (i == s.length() // 已经遍历完，则可以计算
                        || priorityMap.get(s.charAt(i)) <= priorityMap.get(opStack.peek())) /* 当前操作符的优先级小于栈顶操作符 */ ) {
                compute(numStack, opStack); // 满足计算的情况，即：已经遍历完字符串 或 当前操作符的优先级小于栈顶操作符
            }
            
            // 将当前的操作符存压入 opStack 中
            if (i < s.length()) {
                opStack.push(s.charAt(i)); // 还未到达字符串结尾处，则当前位置依然是有效的操作符，需要压入 opStack 中
            }
        }
        
        return numStack.pop();
    }
}