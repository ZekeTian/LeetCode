package string;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/fraction-to-recurring-decimal/
 * 
 * 题目描述：给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
 *         如果小数部分为循环小数，则将循环的部分括在括号内。
 *         如果存在多个答案，只需返回 任意一个 。
 *         对于所有给定的输入，保证 答案字符串的长度小于 10^4 。
 *         
 * 限制条件：
 *  （1）-2^31 <= numerator, denominator <= 2^31 - 1
 *  （2）denominator != 0
 *  
 * 示例
 *  示例 1
 *      输入：numerator = 1, denominator = 2
 *      输出："0.5"
 *  
 *  示例 2
 *      输入：numerator = 2, denominator = 1
 *      输出："2"
 *  
 *  示例 3
 *      输入：numerator = 4, denominator = 333
 *      输出："0.(012)"
 *
 */
public class _166_FractionToRecurringDecimal {

    public static void main(String[] args) {
        // test case1, output: 0.5
//        int numerator = 1, denominator = 2;
        
        // test case2, output: 2
//        int numerator = 2, denominator = 1;
        
        // test case3, output: 0.(012)
        int numerator = 4, denominator = 333;
        
        
        _166Solution solution = new _166Solution();
        
        
        System.out.println(solution.fractionToDecimal(numerator, denominator));
    }
    
}

/**
 * 模拟竖式相除，唯一需要特别注意的是，判断是否是循环小数。
 * 思路：
 *  （1）判断是否能整除，如果能整除，则直接返回即可
 *  （2）不能整除，则判断正负，如果是负数，则添加 “-”
 *  （3）将 numerator、denominator 全部变为正数，并且计算出整数部分
 *  （4）循环计算小数部分
 *      1）将 numerator 放进 map 中
 *      2）对 numerator 进行补位，即扩大 10 倍，从而方便下一次计算
 *      3）计算出当前的小数位，numerator / denominator
 *      4）计算出余数部分，numerator % denominator，赋值给 numerator，从而方便后面的计算
 *      5）判断 map 中是否存在 numerator，如果存在，则说明陷入循环，直接按相应的格式返回即可；否则，继续下一次循环
 *      
 */
class _166Solution {
    
    public String fractionToDecimal(int numerator, int denominator) {
        long a = numerator, b = denominator; // 将 numerator、denominator 转换成 long 类型，避免整数溢出
        
        // 判断是否能整除
        if (a % b == 0) {
            return (a / b) + "";
        }
        
        StringBuilder builder = new StringBuilder();
        
        // 判断正负性
        if (a * b < 0) {
            builder.append('-'); // 如果是负数，则先添加上负号
        }
        a = Math.abs(a);
        b = Math.abs(b);
        
        // 添加整数部分
        builder.append(a / b).append('.');
        a %= b;
        
        // 计算小数部分
        Map<Long, Integer> map = new HashMap<>(); // key：余数，value：余数在 builder 中的位置
        while (a != 0) {
            map.put(a, builder.length());
            a *= 10; // 对 a 进行补位，从而方便后面计算小数部分
            builder.append(a / b); // 计算出小数
            a %= b; // 更新 a ，方便后面的计算
            
            // 判断是否陷入循环
            if (map.containsKey(a)) {
                Integer idx = map.get(a); // 获得循环的起始位置
                return String.format("%s(%s)", builder.substring(0, idx), builder.substring(idx));
            }
        }
        
        return builder.toString();
    }
    
}
