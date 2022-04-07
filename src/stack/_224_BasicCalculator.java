package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/basic-calculator/
 * 
 * 题目描述：给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 *         注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 3 * 10^5
 *  （2）s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 *  （3）s 表示一个有效的表达式
 *  （4）'+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 *  （5）'-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 *  （6）输入中不存在两个连续的操作符
 *  （7）每个数字和运行的计算将适合于一个有符号的 32位 整数
 *  
 * 示例：
 *  示例 1
 *      输入：s = "1 + 1"
 *      输出：2
 *      
 *  示例 2
 *      输入：s = " 2-1 + 2 "
 *      输出：3
 *  
 *  示例 3
 *      输入：s = "(1+(4+5+2)-3)+(6+8)"
 *      输出：23
 *
 */
public class _224_BasicCalculator {

    public static void main(String[] args) {
        
        // test case1, output: 3
        String s = " 2-1 + 2 ";
        
        // test case2, output: 23
//        String s = "(1+(4+5+2)-3)+(6+8)";
        
        // test case3, output: -12
//        String s = "- (3 + (4 + 5))";
        
        _224Solution1 solution = new _224Solution1();
        
        System.out.println(solution.calculate(s));
    }
}

/**
 * 解法一：消除括号，然后直接全部累加
 *       因为每对括号前面的正负号都不一样，如：1 + (2 - (3 + 4))，外面那对括号的符号是正数，而里面那对括号的符号是负数。
 *       为此，需要用栈 signStack 单独记录每对括号的符号。
 *       可以将原始的表达式视作一个整体，被一对括号包住，因此初始时，当前正负符号 sign 设为 +1，并压入 signStack 中。
 *       然后遍历表达式字符串。
 *          若当前字符是空格，则继续遍历
 *          若当前字符是+，则当前正负符号 sign 为 singStack 栈顶的符号
 *          若当前字符是-，则当前正负符号 sign 为 singStack 栈顶的符号 * -1
 *          若当前字符是(，则遇到一对新的括号，需要将当前正负符号 sign 压入 signStack 中
 *          若当前字符是)，则一对括号匹配结束，从 signStack 中弹出栈顶对应的正负符号
 *          若当前字符是数字，则取出完整的数字，并且将对应数字乘以当前正负符号 sign，然后累加到最终结果中
 *              如示例表达式中，取出数字 3 之后，再乘以当前符号 -1，得到 -3，然后累加到最终结果中
 * 
 */
class _224Solution1 {
    
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Stack<Integer> signStack = new Stack<>(); // 用于存储每对括号对应的正负符号
        int sign = 1; // 当前正负符号。将表达式 s 视作一个整体，则 s 相当于是被一对括号包住，因此初始时，正负符号是 1
        signStack.push(sign);
        
        // 遍历 s，将括号消除，然后将数字累加
        int ret = 0; // 最终的结果
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) == ' ') {
                ++i;
            } else if (s.charAt(i) == '+') {
                sign = signStack.peek(); // 如果当前字符是 + ，则当前正负符号即为栈顶的符号，不需要进行变化
                ++i;
            } else if (s.charAt(i) == '-') {
                sign = -1 * signStack.peek(); // 如果当前字符是 -，则当前正负符号是栈顶符号的相反数
                ++i;
            } else if (s.charAt(i) == '(') {
                signStack.push(sign); // 遇到一对新括号，则将其对应的正负符号压入栈中
                ++i;
            } else if (s.charAt(i) == ')') {
                signStack.pop(); // 一对括号匹配结束，则从栈中弹出该对括号对应的正负符号
                ++i;
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                // 遇到数字，则提取出数字，并累加到最终结果 ret 中
                int num = 0;
                while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + s.charAt(i) - '0';
                    ++i;
                }
                
                ret += num * sign; // 将数字 num 乘以当前符号，相当于是消除了括号，然后将数字累加到最终结果中
            }
        }
        
        return ret;
    }
    
}


