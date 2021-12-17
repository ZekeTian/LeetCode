package backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 * 
 * 题目描述：给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *        回文串 是正着读和反着读都一样的字符串。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 16
 *  （2）s 仅由小写英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入：s = "aab"
 *      输出：[["a","a","b"],["aa","b"]]
 *  
 *  示例 2
 *      输入：s = "a"
 *      输出：[["a"]]
 *      
 */
public class _131_PalindromePartitioning {

    public static void main(String[] args) {
        // test case1, output: [[a, a, b], [aa, b]]
//        String s = "aab";
        
        // test case2, output: [[a]] 
        String s = "a";
        
        _131Solution solution = new _131Solution();
        
        
        System.out.println(solution.partition(s));
    }
}

/**
 * 利用回溯法枚举出所有的结果，主要逻辑是：每次回溯都对字符串尝试所有可能的截取方式。
 */
class _131Solution {
    
    private List<List<String>> resultList = null;
    
    private boolean isPalindrome(String s) {
        return s.equals(new StringBuffer(s).reverse().toString());
    }
    
    private void tryPartition(String s, List<String> list) {
        if (0 == s.length()) {
            resultList.add(new ArrayList<>(list)); // 字符串已经处理完毕，得到一种结果
            return;
        }
        
        // 在字符串 s 中分别尝试去截取长度为 1、2...s.length() 的子串
        for (int i = 1; i <= s.length(); ++i) {
            String str1 = s.substring(0, i);
            String str2 = s.substring(i, s.length());
            if (!isPalindrome(str1)) { // 如果 str1 不满足回文要求，则当前截取方式无效，继续下一种截取
                continue;
            }
            list.add(str1);
            tryPartition(str2, list); // 当前 str1 有效，则继续对剩余部分字符串 str2 进行处理
            list.remove(list.size() - 1);
        }
    }
    
    public List<List<String>> partition(String s) {
        resultList = new ArrayList<>();
        
        tryPartition(s, new ArrayList<>());
        
        return resultList;
    }
}
