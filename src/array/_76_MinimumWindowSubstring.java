package array;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * 
 * 题目描述：给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *        对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *        注意：子串是连续的字符串序列。
 * 
 * 限制条件：
 *  （1）1 <= s.length, t.length <= 10^5
 *  （2）s 和 t 由英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入：s = "ADOBECODEBANC", t = "ABC"
 *      输出："BANC"
 *      
 *  示例 2
 *      输入：s = "a", t = "a"
 *      输出："a"
 *  
 *  示例 3
 *      输入: s = "a", t = "aa"
 *      输出: ""
 *      解释: t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子字符串，返回空字符串。
 *
 */
public class _76_MinimumWindowSubstring {

    public static void main(String[] args) {
        // test case1, output: "BANC"
        String s = "ADOBECODEBANC", t = "ABC";
        
        // test case2, output: "a"
//        String s = "a", t = "a";
        
        // test case3, output: ""
//        String s = "a", t = "aa";
        
        _76Solution solution = new _76Solution();
        
        System.out.println(solution.minWindow(s, t));
    }
}


/**
 * 使用双指针，具体思路：
 *  初始时，left = 0, right = -1，然后 left、right 同时向右移动
 *  当 [left, right] 窗口满足条件（即包含字符串 t 中所有的字符）时：
 *      如果当前 window 的长度比之前的更小，则更新 window 的区间
 *      减小 window（因为是为了获取最小的 window），left 左移
 *  否则（不满足条件）：
 *      扩大 window（从而尝试让 window 满足条件），right 右移
 *      
 *  本来当 window 满足条件时，减小 window 有进行两种尝试，分别是：left 右移、right 左移，但是因为 right 是从左边移到右边的，所以没有必要再让 right 左移。
 *  同理，当 window 不满足条件时，扩大 window 也有两种尝试，分别是：left 左移，right 右移，但是因为 left 是从左边移到右边的，所以也没必要再让 left 左移。
 *  故，最终的移动情况简化为上面的思路所述。
 */
class _76Solution {
    
    // 判断 aMap 是否包含 bMap
    private boolean isContains(Map<Character, Integer> aMap, Map<Character, Integer> bMap) {
        if (aMap.size() < bMap.size()) {
            return false;
        }
        
        Set<Character> keys = bMap.keySet();
        for (Character k : keys) {
            if (aMap.getOrDefault(k, 0) < bMap.get(k)) {
                return false;
            }
        }
        
        return true;
    }
    
    public String minWindow(String s, String t) {
        if (s == null || 0 == s.length() || t == null || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        
        Map<Character, Integer> tCountMap = new HashMap<>();
        Map<Character, Integer> windowCountMap = new HashMap<>();
        int left = 0, right = -1; // [left, right] 形成 window
        int L = 0, R = -1;
        
        for (int i = 0; i < t.length(); ++i) {
            tCountMap.put(t.charAt(i), tCountMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        
        while (right < s.length()) {
            if (isContains(windowCountMap, tCountMap)) {
                // 比较当前窗口和之前窗口的大小
                if (R == -1 || right - left < R - L) {
                    L = left;
                    R = right;
                }
                
                // window 中包含 t 的字符，则缩小 window，即 left 向右移
                int lCount = windowCountMap.get(s.charAt(left));
                if (lCount - 1 > 0) {
                    windowCountMap.put(s.charAt(left), lCount - 1);
                } else {
                    windowCountMap.remove(s.charAt(left));
                }
                
                ++left;
            } else {
                // window 中不包含 t 的字符，则扩大 window，即 right 向右移
                if (right + 1 < s.length()) {
                    windowCountMap.put(s.charAt(right + 1), windowCountMap.getOrDefault(s.charAt(right + 1), 0) + 1);
                }
                ++right;
            }
        }
        
        return s.substring(L, R + 1);
    }
}