package dynamicprogramming;

/**
 * https://leetcode.com/problems/interleaving-string/
 * 
 * 题目描述：给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 *         两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *            （1）s = s1 + s2 + ... + sn
 *            （2）t = t1 + t2 + ... + tm
 *            （3）|n - m| <= 1
 *            （4）交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 *         注意：a + b 意味着字符串 a 和 b 连接。
 * 
 * 限制条件：
 *  （1） 0 <= s1.length, s2.length <= 100
 *  （2）0 <= s3.length <= 200
 *  （3）s1、s2、和 s3 都由小写英文字母组成
 *  
 * 示例：
 *  示例 1
 *      输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 *      输出：true
 *      
 *  示例 2
 *      输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 *      输出：false
 *      
 *  示例 3
 *      输入：s1 = "", s2 = "", s3 = ""
 *      输出：true
 * 
 */
public class _97_InterleavingString {

    public static void main(String[] args) {
        // test case1, output: true
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
        
        // test case2, output: false
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";
        
        // test case3, output: true
        String s1 = "", s2 = "", s3 = "";
        
        
        _97Solution solution = new _97Solution();
        
        
        System.out.println(solution.isInterleave(s1, s2, s3));
    }
}

/**
 * 动态规划，类似于在一个二维数组中寻找一条路径。如果能找到一条从左上角走到右下角的路径，则返回 true；否则，返回 false。
 * 以 s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" 为例，s1、s2 组成的二维数组，如下所示：
 *    --s2-->
 * |      d  b  b  c  a  
 * s1   T F  F  F  F  F
 * ↓  a T
 *    a T T  T  T  T
 *    b F    T     T
 *    c F    T  T  T  T
 *    c F             T
 * 在上面的示例二维数组中，从左上角走到右下角有两条路径，因此，返回 true。
 * memo[i][j] 表示 s1[0...i-1]、s2[0...j-1] 是否能够交叉形成 s3[0...i+j-1]，如果能，则是 true；否则，则是 false。
 * 初始状态
 *    memo[0][0] = true
 *    memo[i][0] = (s1.charAt(i - 1) == s3.charAt(i - 1) (1 <= i <= m)
 *    memo[0][j] = (s2.charAt(j - 1) == s3.charAt(j - 1) (1 <= j <= n)
 * 中间的状态转移
 *    memo[i][j] = (memo[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) // 从 memo[i-1][j] 向下走到 memo[i][j]
 *              || (memo[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) // 从 memo[i][j-1] 向右走到 memo[i][j]
 *              
 */
class _97Solution {
    
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null && s3 == null) {
            return true; // s1、s2、s3 同时为空，则为 true
        }
        
        if (s1 == null || s2 == null || s3 == null) {
            return false; // 字符串中既有空串，又有非空串，则不能交叉形成 s3
        }
        
        if (s1.length() + s2.length() != s3.length()) {
            return false; // 长度不符合要求，则也不可能交叉形成 s3
        }
        
        int m = s1.length(), n = s2.length();
        // memo[i][j] 表示 s1[0...i-1]、s2[0...j-1] 是否能够交叉形成 s3[0...i+j-1]，如果能，则是 true；否则，则是 false
        boolean[][] memo = new boolean[m + 1][n + 1];
        
        // 初始化初始状态
        memo[0][0] = true;
        for (int i = 1; i <= m && s1.charAt(i - 1) == s3.charAt(i - 1); ++i) {
            memo[i][0] = true;
        }
        for (int j = 1; j <= n && s2.charAt(j - 1) == s3.charAt(j - 1); ++j) {
            memo[0][j] = true;
        }
        
        // 设置中间状态
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                memo[i][j] = (memo[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) // 从 memo[i-1][j] 向下走到 memo[i][j]
                          || (memo[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)); // 从 memo[i][j-1] 向右走到 memo[i][j]
            }
        }
        
        return memo[m][n];
    }
}
    
