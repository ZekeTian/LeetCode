package stack;

import java.util.Stack;

/**
 * https://leetcode.com/problems/decode-string/
 *
 * 题目描述：给定一个经过编码的字符串，返回它解码后的字符串。
 *         编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *         你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *         此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *         
 * 限制条件：
 *  （1）1 <= s.length <= 30
 *  （2）s 由小写英文字母、数字和方括号 '[]' 组成
 *  （3）s 保证是一个 有效 的输入。
 *  （4）s 中所有整数的取值范围为 [1, 300]
 * 
 * 示例：
 *  示例 1
 *      输入：s = "3[a]2[bc]"
 *      输出："aaabcbc"
 *      
 *  示例 2
 *      输入：s = "3[a2[c]]"
 *      输出："accaccacc"
 *      
 *  示例 3
 *      输入：s = "2[abc]3[cd]ef"
 *      输出："abcabccdcdcdef"
 *      
 *  示例 4
 *      输入：s = "abc3[cd]xyz"
 *      输出："abccdcdcdxyz"
 *      
 */
public class _394_DecodeString {

    public static void main(String[] args) {
        // test case1, output: "aaabcbc"
//        String s = "3[a]2[bc]";
//        String ans = "aaabcbc";
        
        // test case2, output: "accaccacc"
//        String s = "3[a2[c]]";
//        String ans = "accaccacc";
        
        // test case3, output: "abcabccdcdcdef"
//        String s = "2[abc]3[cd]ef";
//        String ans = "abcabccdcdcdef";
        
        // test case4, output: "abccdcdcdxyz"
        String s = "abc3[cd]xyz";
        String ans = "abccdcdcdxyz";
        
        
        _394Solution solution = new _394Solution();
        String res = solution.decodeString(s);
        
        System.out.println(res + ", " + ans.equals(res));
    }
}

/**
 * 解法一：使用栈。
 *       创建两个辅助栈，一个辅助栈 repeatStack，存储重复的次数；
 *                    另一个辅助栈 builderStack，存储方括号内部的 StringBuilder（用于拼接方括号内部的字符）。
 *       
 *       遍历 s 中的字符，进行如下判断 ：
 *          （1）如果是 [，则记录当前方括号内需要重复的次数 repeatNum，并且重置 repeatNum，
 *              然后并且创建一个新的 StringBuilder（用于存储当前方括号内的字符）；
 *          （2）如果是 ]，则从 builderStack 中弹出上一层方括号的 builder，然后重复 repeatNum 次，
 *              将当前方括号内的字符串添加到上一层的 builder 中；
 *          （3）如果是数字，则用于计算 repeatNum；
 *          （4）其它字符（即当前方括号内的字母），用当前方括号内的 builder 记录。
 *          
 */
class _394Solution {
    
    public String decodeString(String s) {
        Stack<Integer> numStack = new Stack<>(); // 存储各层方括号内重复的次数
        Stack<StringBuilder> builderStack = new Stack<>(); // 存储方括号内部的 StringBuilder（用于拼接方括号内部的字符）
        
        int repeatNum = 0; // 记录当前层方括号的重复次数
        StringBuilder builder = new StringBuilder(); // 存储当前层方括号内的字符
        builderStack.push(builder); // 初始时的 builder 存储所有的字符，相当于是在原始字符外面加上一层方括号，即：1[原始字符]
        
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == '[') { // 进入新一层的方括号内部
                numStack.push(repeatNum); // 重复的次数已经计算出来，所以存储进去
                builderStack.push(builder); // 在正式进入新一层的方括号内部时，先将当前层方括号的 builder 存储进去
                
                repeatNum = 0; // 置零，方便下一次计算
                builder = new StringBuilder(); // 创建新的 builder，用于存储新一层的方括号内部的字符
            } else if (ch == ']') { // 当前层的方括号结束
                String repeatStr = builder.toString(); // 当前层方括号内部的字符串
                builder = builderStack.pop(); // 弹出上一层方括号内部的 builder，从而将当前层重复的字符串添加进去
                int curRepeatNum = numStack.pop(); // 当前层方括号需要重复的次数
                
                for (int j = 0; j < curRepeatNum; ++j) {
                    builder.append(repeatStr);
                }
            } else if (Character.isDigit(ch)) { // 数字字符
                repeatNum = repeatNum * 10 + ch - '0';
            } else { // 普通字母字符，则用当前层的 builder 记录下来
                builder.append(ch);
            }
        }
        
        return builder.toString();
    }
}
