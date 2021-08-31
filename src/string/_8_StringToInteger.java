package string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 * 
 * 题目描述：请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 *        函数 myAtoi(string s) 的算法如下：
 *          （1）读入字符串并丢弃无用的前导空格
 *          （2）检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 *          （3）读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 *          （4）将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）
 *          （5）如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −23^1 的整数应该被固定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 2^31 − 1 。
 *          （6）返回整数作为最终结果。
 *        
 *        注意：
 *          （1）本题中的空白字符只包括空格字符 ' '
 *          （2）除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *          
 * 限制条件：
 *  （1）0 <= s.length <= 200
 *  （2）s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 *          
 * 示例：
 *  示例 1
 *      Input: s = "42"
 *      Output: 42
 *      解释：第 1 步："42"（当前没有读入字符，因为没有前导空格）
 *          第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          第 3 步："42"（读入 "42"）
 *          解析得到整数 42 。
 *          由于 "42" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 42 。
 *
 *  示例 2
 *      Input: s = "   -42"
 *      Output: -42
 *      解释：第 1 步："   -42"（读入前导空格，但忽视掉）
 *          第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
 *          第 3 步："   -42"（读入 "42"）
 *  
 *  示例 3
 *      Input: s = "4193 with words"
 *      Output: 4193
 *      解释：第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 *          第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 *          
 *  示例 4
 *      Input: s = "words and 987"
 *      Output: 0
 *      解释：第 1 步："words and 987"（当前没有读入字符，因为没有前导空格）
 *          第 2 步："words and 987"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          第 3 步："words and 987"（由于当前字符 'w' 不是一个数字，所以读入停止）
 *   
 *  示例 5
 *      Input:  s = "-91283472332"
 *      Output: -2147483648
 *      解释：第 1 步："-91283472332"（当前没有读入字符，因为没有前导空格）
 *          第 2 步："-91283472332"（读入 '-' 字符，所以结果应该是负数）
 *          第 3 步："-91283472332"（读入 "91283472332"）
 *          
 *          解析得到整数 -91283472332 。
 *          由于 -91283472332 小于范围 [-2^31, 2^31 - 1]  的下界，最终结果被截断为 -231 = -2147483648 。
 */
public class _8_StringToInteger {

    public static void main(String[] args) {
        // test case 1, output: 42
        //        String s = "42";

        // test case 2, output: -42
        //        String s = "   -42";

        // test case 3, output: 4193
        //        String s = "4193 with words";

        // test case 4, output: 0
        //        String s = "words and 987";

        // test case 5, output: -2147483648 
        //        String s = "-91283472332";

        // test case 6, output: 2147483647 
        //        String s = "3147483648";

        // test case 7, output: 0
        //        String s = "    0000000000000   ";

        // test case 8, output: 2147483647
        //        String s = "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000522545459";

        // test case 9, output: 0
        String s = " +-11";

        //        _8Solution1 solution = new _8Solution1();
        _8Solution2 solution = new _8Solution2();

        System.out.println(solution.myAtoi(s));

    }
}

/**
 * 解法一：使用循环提取出整数
 */
class _8Solution1 {
    public int myAtoi(String s) {
        StringBuilder builder = new StringBuilder();
        // 提取出整数（含正负符号和前导0）
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            // 判断是否已经提取出整数部分
            if (builder.length() == 0) {
                // 还未提取出整数部分，则当前字符可以是 +、-、数字、空格，其它的字符则不行
                if (ch != '+' && ch != '-' /* 不是正负符号 */
                        && ch != ' ' /* 不是空格 */
                        && (ch < '0' || ch > '9') /* 不是数字 */) {
                    break;
                }

                if (ch == ' ') {
                    continue; // 前导空格，无需处理
                }
                builder.append(ch);
            } else {
                // 已经提提取出整数的一部分，则当前字符必须是数字，否则结束提取
                if (ch < '0' || ch > '9') {
                    break; // 不是数字，则
                }
                builder.append(ch);
            }
        }

        if (builder.length() == 0) {
            return 0;
        }

        String numStr = builder.toString();
        // 提取出符号位
        boolean flag = true; // 标记正负，true 表示正，false 表示负
        if (numStr.charAt(0) == '-') {
            flag = false;
            numStr = numStr.substring(1);
        } else if (numStr.charAt(0) == '+') {
            flag = true;
            numStr = numStr.substring(1);
        }

        // 删除前导 0
        int noZeroIndex = 0; // 第一个非 0 数的下标
        for (noZeroIndex = 0; noZeroIndex < numStr.length(); ++noZeroIndex) {
            if (numStr.charAt(noZeroIndex) != '0') {
                break;
            }
        }
        numStr = numStr.substring(noZeroIndex);

        // 判断最终整数部分的长度，如果大于 10 位数，则超过 Int 的范围，直接返回最值即可，无需转换成整数
        if (numStr.length() > 10) {
            int num = (flag ? Integer.MAX_VALUE : Integer.MIN_VALUE);
            return num;
        }

        // 将 numStr 转换成整数
        long num = 0; // 注意使用 long 类型，因为当 numStr 的长度为 10 时，是有可能超过 int 的范围
        long a = 1;
        for (int i = numStr.length() - 1; i >= 0; --i) {
            num = num + (numStr.charAt(i) - '0') * a;
            a *= 10;
        }

        if (flag) {
            num = (num > Integer.MAX_VALUE ? Integer.MAX_VALUE : num); // 如果是正数，则判断是否超过最大值
        } else {
            num = (-num < Integer.MIN_VALUE ? Integer.MIN_VALUE : -num); // 如果是负数，则判断是否超过最小值
        }

        return (int) num;
    }
}

/**
 * 解法二：使用正则表达式撮取出整数
 */
class _8Solution2 {
    public int myAtoi(String s) {
        Pattern pattern = Pattern.compile("(^[ ]*[-+]?\\d+)");

        Matcher matcher = pattern.matcher(s);

        if (!matcher.find()) {
            return 0;
        }

        // 提取出匹配的部分
        String numStr = matcher.group(0);

        // 删除前导空格
        numStr = numStr.replaceAll(" ", "");

        if (numStr.length() == 0) {
            return 0;
        }

        // 提取出符号位
        boolean flag = true; // true 表示正数，false 表示负数
        if (numStr.charAt(0) == '+') {
            flag = true;
            numStr = numStr.substring(1);
        } else if (numStr.charAt(0) == '-') {
            flag = false;
            numStr = numStr.substring(1);
        }

        // 删除前导 0，提取出最终的有效部分整数
        int noZeroIndex = 0;
        for (noZeroIndex = 0; noZeroIndex < numStr.length(); ++noZeroIndex) {
            if (numStr.charAt(noZeroIndex) != '0') {
                break;
            }
        }
        numStr = numStr.substring(noZeroIndex);

        // 判断有效部分整数长度
        if (numStr.length() > 10) { // 超出 int 范围
            int num = (flag ? Integer.MAX_VALUE : Integer.MIN_VALUE);
            return num;
        }

        // 转换成整数
        long num = 0;
        long a = 1;
        for (int i = numStr.length() - 1; i >= 0; --i) {
            num = num + (numStr.charAt(i) - '0') * a;
            a *= 10;
        }

        if (flag) {
            num = (num > Integer.MAX_VALUE ? Integer.MAX_VALUE : num);
        } else {
            num = (-num < Integer.MIN_VALUE ? Integer.MIN_VALUE : -num);
        }

        return (int) num;
    }
}
