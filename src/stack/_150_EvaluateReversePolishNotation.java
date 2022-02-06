package stack;

import java.util.Stack;

/**
 * 题目描述：根据 逆波兰表示法，求表达式的值。
 *        有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *        注意 两个整数之间的除法只保留整数部分。
 *        可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 
 *        逆波兰表达式：
 *        逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 *          平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 *          该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 *        逆波兰表达式主要有以下两个优点：
 *          去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 *          适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
 *        
 * 限制条件：
 *  （1）1 <= tokens.length <= 10^4
 *  （2）tokens[i] 是一个算符（"+"、"-"、"*" 或 "/"），或是在范围 [-200, 200] 内的一个整数
 * 
 * 示例：
 *  示例 1
 *      输入：tokens = ["2","1","+","3","*"]
 *      输出：9
 *      解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 *  
 *  示例 2
 *      输入：tokens = ["4","13","5","/","+"]
 *      输出：6
 *      解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 
 */
public class _150_EvaluateReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = { "2","1","+","3","*" };
        
        _150Solution solution = new _150Solution();
        
        System.out.println(solution.evalRPN(tokens));
    }
}


class _150Solution {
    
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; ++i) {
            int res = 0;
            // 判断当前字符是操作符还是数字，如果是操作符则需要出栈，如果是数字则转换
            if ("+".equals(tokens[i])) {
                res = stack.pop() + stack.pop();
            } else if ("-".equals(tokens[i])) { // 两个操作数有先后顺序
                int tmp = stack.pop();
                res = stack.pop() - tmp;
            } else if ("*".equals(tokens[i])) {
                res = stack.pop() * stack.pop();
            } else if ("/".equals(tokens[i])) { // 两个操作数有先后顺序
                int tmp = stack.pop();
                res = stack.pop() / tmp;
            } else {
                res = Integer.parseInt(tokens[i]); // 不是操作符，是操作数，则直接转换成数字即可，不需要出栈
            }
        
            // 将结果压入到栈中
            stack.push(res);
        }
        
        return stack.pop();
    }
}
