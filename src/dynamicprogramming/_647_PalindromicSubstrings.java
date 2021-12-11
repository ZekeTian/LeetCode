package dynamicprogramming;

/**
 * https://leetcode.com/problems/palindromic-substrings/
 * 
 * 题目描述：给你一个字符串 s ，请你统计并返回这个字符串中回文子串的数目。回文字符串 是正着读和倒过来读一样的字符串。
 *        子字符串是字符串中的由连续字符组成的一个序列。具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 *        
 * 限制条件：
 *  （1）1 <= s.length <= 1000
 *  （2）s 由小写英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入：s = "abc"
 *      输出：3
 *      解释：三个回文子串: "a", "b", "c"
 *      
 *  示例 2
 *      输入：s = "aaa"
 *      输出：6
 *      解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class _647_PalindromicSubstrings {

    public static void main(String[] args) {
        // test case 1, output: 3
//        String s = "abc";
        
        // test case 2, output: 6
        String s = "aaa";
        
//        _647Solution1 solution = new _647Solution1();
        
        _647Solution2 solution = new _647Solution2();
        
        System.out.println(solution.countSubstrings(s));
    }
}

/**
 * 解法一：暴力解法。尝试所有可能的子串，然后判断子串是否是回文。
 */
class _647Solution1 {
    
    // 判断字符串 str 是否为回文
    private boolean isPalindromic(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
    
    public int countSubstrings(String s) {
        int num = 0;
        
        for (int i = 0; i < s.length(); ++i) {
            // 以 i 结尾，然后尝试截取长度为 j 的子串 （子串长度不可能超过 i + 1）
            for (int j = 1; j <= i + 1; ++j) {
                if (isPalindromic(s.substring(i + 1 - j, i + 1))) {
                    ++num;
                }
            }
        }
        
        return num;
    }
}

/**
 * 解法二：动态规划，具体思路如下：
 *      求解 s[i...j] 是否为回文时，先看两端是否相等，如果相等则查看 s[i+1...j-1] 是否为回文。如果 s[i+1...j-1] 是回文，则 s[i...j] 也为回文。
 *      但是需要对两种特殊情况进行处理，即
 *          （1）s[i...j] 长度等于 1，如:a
 *          （2）s[i...j] 长度等于 2，如：aa
 *      对于上述这两种情况，无需去看 s[i+1...j-1] 是否为回文（实际上，此时左右边界已经处于非法状态，也不能查看）
 *      综上，如果 s[i] = s[j]，则需要判断以下两种情况是否有一种情况成立
 *          （1）s[i...j] 长度 <= 2，即特殊情况
 *          （2）s[i+1...j-1] 是回文，即一般情况
 *      注意：以上两种情况只需要有一种情况成立即可，所以用或逻辑
 */
class _647Solution2 {
    
    public int countSubstrings(String s) {
        int num = 0;
        boolean[][] memo = new boolean[s.length()][s.length()]; // memo[i][j] 表示 s[i...j] 字符串是否为回文
        
        // 外层循环先处理 j （左边界），是为了先计算出边界小的结果，从而方便后面计算边界大的结果
        for (int j = 0; j < s.length(); ++j) {
            for (int i = 0; i <= j; ++i) {
                if (s.charAt(j) == s.charAt(i) 
                        && (j - i <= 1 || memo[i + 1][j - 1])) { // j-i <= 1 是指 s[i...j] 长度 <= 2，即 j - i + 1 <= 2
                    memo[i][j] = true;
                    ++num;
                }
            }
        }
        
        return num;
    }
}

