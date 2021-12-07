package dynamicprogramming;

/**
 * https://leetcode.com/problems/longest-common-subsequence/
 * 
 * 题目描述：给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 *        一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *        例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 *        两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *        
 * 限制条件：
 *  （1）1 <= text1.length, text2.length <= 1000
 *  （2）text1 和 text2 仅由小写英文字符组成。
 *  
 *  示例：
 *   示例 1
 *      输入：text1 = "abcde", text2 = "ace"
 *      输出：3
 *      解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *      
 *   示例 2
 *      输入：text1 = "abc", text2 = "abc"
 *      输出：3
 *      解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *   
 *   示例 3
 *      输入：text1 = "abc", text2 = "def"
 *      输出：0
 *      解释：两个字符串没有公共子序列，返回 0 。
 */
public class _1143_LongestCommonSubsequence {

    public static void main(String[] args) {
        // test case 1, output: 3
        String text1 = "abcde", text2 = "ace";
        
        // test case 2, output: 3
//        String text1 = "abc", text2 = "abc";
        
        // test case 3, output: 0
//        String text1 = "abc", text2 = "def";
        
//        _1143Solution1 solution = new _1143Solution1();

        _1143Solution2 solution = new _1143Solution2();
        
        System.out.println(solution.longestCommonSubsequence(text1, text2));
    }
}

/**
 * 解法一：自底向上动态规划 实现方式一（包含右边界）
 */
class _1143Solution1 {
    public int longestCommonSubsequence(String text1, String text2) {
        // memo[i][j] 表示字符串 text1[0...i]、text2[0...j] 的最长公共子序列长度（包含 i、j）
        int[][] memo = new int[text1.length()][text2.length()];
        memo[0][0] = (text1.charAt(0) == text2.charAt(0) ? 1 : 0);
        for (int i = 1; i < text2.length(); ++i) {
            // 如果 text2 中当前字符与 text1 首字符一样，则为 1；否则，其结果和前面结果一样（前面只要有一个是 1，则后面全部是 1）
            memo[0][i] = (text1.charAt(0) == text2.charAt(i) ? 1 : memo[0][i - 1]);
        }
        
        for (int i = 1; i < text1.length(); ++i) {
            memo[i][0] = (text1.charAt(i) == text2.charAt(0) ? 1 : memo[i - 1][0]); // 与上面的 for 循环中一样
            for (int j = 1; j < text2.length(); ++j) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    // 当前 text1、text2 中两个字符相等，则 text1、text2 均退回一个字符（退到状态 memo[i - 1][j - 1]），然后在原来状态的结果上加上当前这一个字符（即 +1）
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    // 当前 text1、text2 中两个字符不相等，则 text1 或 text2 退回一个字符（退到状态 memo[i - 1][j] 或 memo[i][j - 1]）
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        
        return memo[text1.length() - 1][text2.length() - 1];
    }
}


/**
 * 解法二：自底向上动态规划 实现方式二（不包含右边界）
 */
class _1143Solution2 {
    public int longestCommonSubsequence(String text1, String text2) {
        // memo[i][j] 表示 text1[0...i-1]、text2[0...j-1] 两个字符串的最长公共子序列的长度
        int[][] memo = new int[text1.length() + 1][text2.length() + 1];
        
        for (int i = 1; i <= text1.length(); ++i) {
            for (int j = 1; j <= text2.length(); ++j) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
                }
            }
        }
        
        return memo[text1.length()][text2.length()];
    }
}