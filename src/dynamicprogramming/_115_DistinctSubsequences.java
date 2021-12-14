package dynamicprogramming;

/**
 * https://leetcode.com/problems/distinct-subsequences/
 * 
 * 题目描述：给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *        字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *        题目数据保证答案符合 32 位带符号整数范围。
 * 
 * 限制条件：
 *  （1）0 <= s.length, t.length <= 1000
 *  （2）s 和 t 由英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入：s = "rabbbit", t = "rabbit"
 *      输出：3
 *      解释：如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 *          (rabb) b (it)
 *          (ra) b (bbit)
 *          (rab) b (bit)
 *  示例 2
 *      输入：s = "babgbag", t = "bag"
 *      输出：5
 *      解释：如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 *          (ba) b (g) bag
 *          (ba) bgba (g)
 *          (b) abgb (ag)
 *          ba (b) gb (ag)
 *          babg (bag)
 *
 */
public class _115_DistinctSubsequences {

    public static void main(String[] args) {
        // test case1, output: 3
//        String s = "rabbbit", t = "rabbit";
        
        // test case2, output: 5
        String s = "babgbag", t = "bag";
        
        
        _115Solution solution = new _115Solution();
        
        
        System.out.println(solution.numDistinct(s, t));
    }
}

/**
 * 动态规划（编辑距离）
 * 计算从字符串 s 得到字符串 t 的所有可能情况个数，从 s 到 t，可以对字符串 s 执行删除操作。
 * 用 memo[i][j] 记录 s[0...i-1][0...j-1] 的结果数量，状态转移过程如下：
 * （1）如果 s[i-1] = t[j-1]，则有两种可选情况：
 *      1）用 s[i-1] 得到 t[j-1]（此次发生转换），则 s 删除 s[i-1]、t 删除 t[j-1]
 *      2）不用 s[i-1] 得到 t[j-1]（此次不发生转换，后面用 s 中的其它字符得到 t[j-1]），则 s 删除 s[i-1]、t 不变（因为此时未发生转换）
 *      如在 s = s = "babgbag"、 t = "bag" 中， s[1] = t[1] = 'a'，
 *      可以用 s[1] 转换 t[1]，也可以不用 s[1] 转换，然后等到后面用 s[5] 转换。但是，无论如何， s 都要删除 s[1]
 * （2）如果 s[i-1] != t[j-1]，则 s 删除 s[i-1]、t 不变（因为未发生转换）
 * 
 * 初始状态：
 *  memo[0][i]，s 为空串，不可能从 s 得到 t，所以 memo[0][i] = 0 （可以省略不写）
 *  memo[i][0]，t 为空串，只要将 s[0...i-1] 删除干净即可得到 t，所以转换个数为 1，即 memo[i][0] = 1
 */
class _115Solution {
    
    public int numDistinct(String s, String t) {
        int[][] memo = new int[s.length() + 1][t.length() + 1]; // 用 memo[i][j] 记录 s[0...i-1][0...j-1] 的结果数量
        
        // memo[0][i]，s 为空串，不可能从 s 得到 t
        for (int i = 1; i <= t.length(); ++i) {
            memo[0][i] = 0; // 实际上，这个初始化可以省略不写，因为默认值均为 0
        }
        // memo[i][0]，t 为空串，只要将 s[0...i-1] 删除干净即可得到 t，所以转换个数为 1
        for (int i = 0; i <= s.length(); ++i) {
            memo[i][0] = 1;
        }
        
        for (int i = 1; i <= s.length(); ++i) {
            for (int j = 1; j <= t.length(); ++j) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1] + memo[i - 1][j]; // s[i-1] 转换 t[j-1] + s[i-1] 不转换 t[j-1]
                } else {
                    memo[i][j] = memo[i - 1][j]; // s[i-1] 不能转换 t[j-1]
                }
            }
        }
            
        return memo[s.length()][t.length()];
    }
}

