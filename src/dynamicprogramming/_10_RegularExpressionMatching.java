package dynamicprogramming;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 * 
 * 题目描述：给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *         （1）'.' 匹配任意单个字符
 *         （2）'*' 匹配零个或多个前面的那一个元素
 *         所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *         
 * 限制条件：
 *  （1）1 <= s.length <= 20
 *  （2）1 <= p.length <= 30
 *  （3）s 只包含从 a-z 的小写字母。
 *  （4）p 只包含从 a-z 的小写字母，以及字符 . 和 *。
 *  （5）保证每次出现字符 * 时，前面都匹配到有效的字符
 * 
 * 示例：
 *  示例 1
 *      输入：s = "aa", p = "a"
 *      输出：false
 *      解释："a" 无法匹配 "aa" 整个字符串。
 *      
 *  示例 2
 *      输入：s = "aa", p = "a*"
 *      输出：true
 *      解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *      
 */
public class _10_RegularExpressionMatching {

    public static void main(String[] args) {
        // test case1, output: false
//        String s = "aa", p = "a";
        
        // test case2, output: true
        String s = "aa", p = "a*";
        
        _10Solution solution = new _10Solution();
        
        System.out.println(solution.isMatch(s, p));
    }
}

/**
 * 解法与剑指 Offer 的 19 题一样
 */
class _10Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] memo = new boolean[m + 1][n + 1]; // memo[i][j] 存储了 s[i - 1] 和 p[j - 1] 的匹配结果，如果为 true，则表示 s[i - 1] 和 p[j - 1] 能够匹配成功 
        
        // 初始化基本情况（s 为空串）
        memo[0][0] = true; // s、p 两个都为空串
        for (int j = 2; j <= n; j += 2) {
            if (p.charAt(j - 1) == '*') {
                // p[j - 1] = '*'，则可以匹配 0 次，从而尽量匹配空串
                memo[0][j] = memo[0][j - 2]; 
            }
        }
        
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                // 判断长度为 j 的字符规律 p，其最后一个字符 p[j - 1] 是否为 '*'
                if (p.charAt(j - 1) == '*') {
                    // 如果 p[j - 1] = '*'，则需要再向前看一位字符（因为 * 需要与前一个字符搭配使用）
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
                        memo[i][j] = memo[i - 1][j] || memo[i][j - 2]; // p[j - 2] 能够与 s[i - 1] 匹配上，则 s[i - 1] 可以选择与 p[j - 2] 匹配，也可以选择不匹配
                    } else {
                        memo[i][j] = memo[i][j - 2]; // p[j - 2] 不能与 s[i - 1] 匹配
                    }
                    continue;
                }
                
                // p[j - 1] 不是 '*'，则判断 p[j - 1] 和 s[i - 1] 是否能够匹配上
                if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
                    memo[i][j] = memo[i - 1][j - 1]; // p[j - 1] 和 s[i - 1] 能够匹配上
                } else {
                    memo[i][j] = false; // p[j - 1] 和 s[i - 1] 不能匹配
                }
            }
        }
        
        return memo[m][n];
    }
}
