package dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/word-break/
 * 
 * 题目描述：给你一个字符串 s 和一个字符串列表 wordDict 作为字典，判定 s 是否可以由空格拆分为一个或多个在字典中出现的单词。
 *        说明：拆分时可以重复使用字典中的单词。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 300
 *  （2）1 <= wordDict.length <= 1000
 *  （3）1 <= wordDict[i].length <= 20
 *  （4）s 和 wordDict[i] 仅有小写英文字母组成
 *  （5）wordDict 中的所有字符串 互不相同
 * 
 * 示例：
 *  示例 1
 *      输入: s = "leetcode", wordDict = ["leet", "code"]
 *      输出: true
 *      解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 *  
 *  示例 2
 *      输入：s = "applepenapple", wordDict = ["apple", "pen"]
 *      输出: true
 *      解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。注意你可以重复使用字典中的单词。
 *  
 *  示例 3
 *      输入：s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 *      输出: false
 */
public class _139_WordBreak {

    public static void main(String[] args) {
        // test case 1, output: true
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        
        // test case 2, output: true
//        String s = "applepenapple";
//        List<String> wordDict = Arrays.asList("apple", "pen");
        
        // test case 3, output: false
//        String s = "catsandog";
//        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        
//        _139Solution1 solution = new _139Solution1();
        
        _139Solution2 solution = new _139Solution2();
        
        System.out.println(solution.wordBreak(s, wordDict));
    }
}

/**
 * 解法一：递归 + 记忆化
 */
class _139Solution1 {
    
    /**
     * memo[i] 表示 s[0...i-1] 字符串能否拆分的计算结果，具体计算结果及含义如下：
     *  memo[i] = 0：未计算，memo[i] = 1：可以拆分；memo[i] = -1，不可以拆分
     */
    private int[] memo = null;
    private String s = "";
    private List<String> wordDict = null;
    
    private boolean tryWordBreak(int index) {
        if (index < 0 ) {
            return false;
        }
        if (0 == index) {
            memo[0] = 1;
            return true; // 单词刚好被拆分完，返回 true
        }
        if (0 != memo[index]) {
            return (1 == memo[index]);
        }
        
        // 逐个单词去尝试拆分，如果有一个单词能拆分成功，则表明 s[0...index-1] 字符串可以拆分
        boolean res = false;
        for (String word : wordDict) {
            if (index >= word.length() 
                    && word.equals(s.substring(index - word.length(), index))) {
                res = res || tryWordBreak(index - word.length()); // 只要有一个为 true ，则可以拆分，因此用 || 运算
            }
        }
        
        memo[index] = (res ? 1 : -1);
        return res;
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        this.memo = new int[s.length() + 1];
        this.s = s;
        this.wordDict = wordDict;
        
        return tryWordBreak(s.length());
    }
}

/**
 * 解法二：自底向上动态规划
 */
class _139Solution2 {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] memo = new boolean[s.length() + 1]; // memo[i] 表示 s[0...i-1] 字符串能否被拆分
        memo[0] = true;
        
        for (int i = 1; i <= s.length(); ++i) {
            // 逐个单词尝试拆分
            for (String word : wordDict) {
                if (i >= word.length()
                        && word.equals(s.substring(i - word.length(), i))) {
                    memo[i] = memo[i] || memo[i - word.length()];
                }
            }
        }
        
        return memo[s.length()];
    }
}

