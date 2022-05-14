package dynamicprogramming;

/**
 * 
 * https://leetcode.com/problems/wildcard-matching/
 * 
 * 题目描述：给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *             '?' 可以匹配任何单个字符。
 *             '*' 可以匹配任意字符串（包括空字符串）。
 *         两个字符串完全匹配才算匹配成功。
 * 
 * 说明：
 *   s 可能为空，且只包含从 a-z 的小写字母。
 *   p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *   
 * 示例：
 *  示例 1
 *      输入:
 *          s = "aa"
 *          p = "a"
 *      输出: false
 *      解释: "a" 无法匹配 "aa" 整个字符串。
 *      
 *  示例 2
 *      输入:
 *          s = "aa"
 *          p = "*"
 *      输出: true
 *      解释: '*' 可以匹配任意字符串。
 *      
 *  示例 3
 *      输入:
 *          s = "cb"
 *          p = "?a"
 *      输出: false
 *      解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *      
 *  示例 4
 *      输入:
 *          s = "adceb"
 *          p = "*a*b"
 *      输出: true
 *      解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 *      
 *  示例 5
 *      输入:
 *          s = "acdcb"
 *          p = "a*c?b"
 *      输出: false
 *
 */
public class _44_WildcardMatching {

    public static void main(String[] args) {
        // test case1, outptu: false
//        String s = "aa", p = "a";
        
        // test case2, outptu: true
//        String s = "aa", p = "*";
                
        // test case3, outptu: false
//        String s = "cb", p = "?a";
        
        // test case4, outptu: true
//        String s = "adceb", p = "*a*b";
        
        // test case5, outptu: false
        String s = "acdcb", p = "a*c?b";
        
        
        _44Solution solution = new _44Solution();
        
        
        System.out.println(solution.isMatch(s, p));
    }
}

/**
 * 解题思路和第 10 题类似，但是需要注意的是：
 *  在本题中，“*” 表示可以匹配任意字符串；而在第 10 题中，“*” 表示前面一个字符可以出现零次或多次（控制次数）
 */
class _44Solution {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return true;
        }
        
        // memo[i][j] 表示 p[0...j-1] 是否能够匹配到 s[0...i-1]，如果能，则为 true；否则，则为 false
        boolean[][] memo = new boolean[s.length() + 1][p.length() + 1];
        
        // 初始化
        memo[0][0] = true; // s、p 都为空时，则能够匹配到
        for (int j = 1; j <= p.length(); ++j) {
            if (p.charAt(j - 1) != '*') {
                memo[0][j] = false; //如果一旦发现 p 中含有其它字符，则无法匹配到空串 s
                break;
            }
            memo[0][j] = true; // 如果 p[j] 是 “*”，则能够匹配到空串
        }
        
        // 计算中间状态
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 1; j <= p.length(); ++j) {
                if (p.charAt(j - 1) == '*') {
                    memo[i][j] = memo[i - 1][j] // “*” 匹配 s[i-1]
                              || memo[i][j - 1]; // “*” 不匹配 s[i-1]
                    continue;
                }
                
                // 非 “*”，则判断 s[i-1] 与 p[j-1] 是否能够匹配上
                if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1];
                } else {
                    memo[i][j] = false;
                }
            }
        }
        
        
        return memo[s.length()][p.length()];
    }
}