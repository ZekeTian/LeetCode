package dynamicprogramming;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/decode-ways/
 * 
 * 题目描述：
 *      一条包含字母 A-Z 的消息通过以下映射进行了编码：
 *          'A' -> 1
 *          'B' -> 2
 *          ...
 *          'Z' -> 26
 *      要解码已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 *          "AAJF" ，将消息分组为 (1 1 10 6)
 *          "KJF" ，将消息分组为 (11 10 6)
 *      注意，消息不能分组为 (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 *      给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 *      题目数据保证答案肯定是一个 32 位 的整数。
 *      
 * 限制条件：
 *  （1）1 <= s.length() <= 100
 *  （2）s 只包含数字，并且可能包含前导零
 * 
 * 示例：
 *  示例 1
 *      输入：s = "12"
 *      输出：2
 *      解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 *      
 *  示例 2
 *      输入：s = "226"
 *      输出：3
 *      解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *      
 *  示例 3
 *      输入：s = "06"
 *      输出：0
 *      解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
 */
public class _91_DecodeWays {

    public static void main(String[] args) {
        // test case 1, output: 2
        String s = "12";
        
        // test case 2, output: 3
//        String s = "226";

        // test case 3, output: 0
//        String s = "06";
        
//        _91Solution1 solution = new _91Solution1();

//        _91Solution2 solution = new _91Solution2();

        _91Solution3 solution = new _91Solution3();
        
        
        System.out.println(solution.numDecodings(s));
    }
}


/**
 * 解法一：递归
 */
class _91Solution1 {
    
    private int num = 0; // 记录解码数量
    private String s = null;
    
    private void getNumDecodings(int index) {
        if (index >= s.length()) {
            ++num; // 到达字符串结尾处，则说明已经找到一种有效解码
            return;
        }
        
        if ('0' == s.charAt(index)) {
            return; // 含有前导 0 ，则该次解码失效
        }
        
        getNumDecodings(index + 1); // 一次解码一个字符 
        
        if (index <= s.length() - 2 
                && Integer.parseInt(s.substring(index, index + 2)) <= 26) { // 一次解码两个字符
            getNumDecodings(index + 2);
        }
    }
    
    public int numDecodings(String s) {
        this.s = s;
        getNumDecodings(0);
        return num;
    }
}

/**
 * 解法二：递归 + 记忆化搜索
 */
class _91Solution2 {
    private int[] memo = null; // memo[i] 记录 s[i..len] 字符串的解码次数
    private String s = null;
    
    private int getNumDecodings(int index) {
        if (index >= s.length()) {
            return 1; // 已经处理完字符串，则说明找到了一种有效解码
        }
        
        if (-1 != memo[index]) {
            return memo[index]; // 已经计算出结果，则直接返回
        }
        
        if ('0' == s.charAt(index)) {
            memo[index] = 0; // 含有前导 0 ，则当前解码无效
            return 0;
        }
        
        int res = getNumDecodings(index + 1); // 一次只解码一个字符 
        if (index <= s.length() - 2 
                && Integer.parseInt(s.substring(index, index + 2)) <= 26) {
            res += getNumDecodings(index + 2); // 一次解码两个字符
        }
        
        memo[index] = res; // 保存结果
        
        return res;
    }
    
    public int numDecodings(String s) {
        this.s = s;
        this.memo = new int[s.length()];
        Arrays.fill(memo, -1);
        
        getNumDecodings(0);
        
        return memo[0];
    }
}


/**
 * 解法三：自底向上动态规划
 */
class _91Solution3 {

    public int numDecodings(String s) {
        int[] memo = new int[s.length() + 1]; // memo[i] 表示 s[i...len] 之间的解码方式个数
        memo[s.length()] = 1; // 解码到字符串结尾处，则可以算一种有效解码
        memo[s.length() - 1] = ('0' == s.charAt(s.length() - 1) ? 0 : 1);
        
        for (int i = s.length() - 2; i >= 0; --i) {
            if ('0' != s.charAt(i)) {
                if (Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                    memo[i] = memo[i + 1] + memo[i + 2]; // 一次可以解码两位或一位
                } else {
                    memo[i] = memo[i + 1]; // 一次只能解码一位
                }
            }
        }
        
        return memo[0];
    }
}
