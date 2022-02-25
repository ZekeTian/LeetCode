package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * 
 * 题目描述：给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *        异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *        
 * 限制条件：
 *  （1）1 <= s.length, p.length <= 3 * 10^4
 *  （2）s 和 p 仅包含小写字母
 *  
 * 示例：
 *  示例 1
 *      输入: s = "cbaebabacd", p = "abc"
 *      输出: [0,6]
 *      解释:
 *          起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 *          起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *  
 *  示例 2
 *      输入: s = "abab", p = "ab"
 *      输出: [0,1,2]
 *      解释:
 *          起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 *          起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 *          起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 */
public class _438_FindAllAnagramsInAString {

    public static void main(String[] args) {
        // test case1, output: [0,6]
//        String s = "cbaebabacd", p = "abc";
        
        // test case1, output: [0,1,2]
        String s = "abab", p = "ab";
        
        _438Solution solution = new _438Solution();
        
        System.out.println(solution.findAnagrams(s, p));
    }
}


class _438Solution {
    
    private boolean isEqual(Map<Character, Integer> aMap, Map<Character, Integer> bMap) {
        if (aMap.size() != bMap.size()) {
            return false;
        }
        
        Set<Character> keys = bMap.keySet();
        for (Character k : keys) {
            if (!bMap.get(k).equals(aMap.get(k))) {
                return false;
            }
        }
        
        return true;
    }
    
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> resultList = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || p.length() > s.length()) {
            return resultList;
        }
        
        Map<Character, Integer> pCountMap = new HashMap<>();
        Map<Character, Integer> windowCountMap = new HashMap<>();
        int left = 0, right = 0; // [left, right) 之间形成一个 window
        for (int i = 0; i < p.length(); ++i) {
            pCountMap.put(p.charAt(i), pCountMap.getOrDefault(p.charAt(i), 0) + 1);
        }
        for (right = 0; right < p.length(); ++right) {
            windowCountMap.put(s.charAt(right), windowCountMap.getOrDefault(s.charAt(right), 0) + 1);
        }
        
        while (right <= s.length()) {
            if (isEqual(windowCountMap, pCountMap)) {
                resultList.add(left);
            }
            
            if (right == s.length()) {
                break; // 已经遍历完，window 无法向后移动
            }
            
            // window 向后移动
            // 先在 windowCountMap 中删除 s[left]，添加 s[right]
            int count = windowCountMap.get(s.charAt(left));
            if (count - 1 > 0) {
                windowCountMap.put(s.charAt(left), count - 1);
            } else {
                windowCountMap.remove(s.charAt(left));
            }
            
            windowCountMap.put(s.charAt(right), windowCountMap.getOrDefault(s.charAt(right), 0) + 1);
            
            // 之后，left、right 向后移动
            ++left;
            ++right;
        }
        
        return resultList;
    }
}