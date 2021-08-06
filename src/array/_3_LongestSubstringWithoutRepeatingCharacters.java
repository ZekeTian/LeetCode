package array;

import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 题目描述：给定一个字符串 s，找到一个最长的不含重复字符的子串（连续）。如果能找到，则返回该子串的长度；否则，返回 0。
 * 
 * 条件限制：
 * （1）0 <= s.length <= 5 * 10^4
 * （2）s 包含英文字母、数字、字符、空白符
 * 
 * 示例：
 *  Input: s = "abcabcbb"
 *  Output: 3
 *  解释：最长的不含重复字符的子串是 "abc"，其长度为 3
 *  
 *  Input: s = "bbbbb"
 *  Output: 1
 *  
 *  Input: s = "pwwkew"
 *  Output: 3
 *  解释：最长的不含重复字符的子串是 "wke"，其长度为 3。
 *  注意：一定要是子串（连续），不能是子序列（不连续）。例如 "pwke" ，长度是 4，但是其不连续。
 *  
 *  Input: s = ""
 *  Output: 0
 */
public class _3_LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        //        String s = "abcabcbb";
        //                String s = "bbbbb";
        //        String s = "pwwkew";
        String s = "";

        _3Solution1 solution = new _3Solution1();

        int len = solution.lengthOfLongestSubstring(s);

        System.out.println(len);
    }
}

/**
 * 解法一：使用一个数组标记是否存在重复的字符
 */
class _3Solution1 {
    public int lengthOfLongestSubstring(String s) {
        int length = 0; // 满足条件的最长子串的长度
        int left = 0; // 左指针，子串的左边界
        int right = -1; // 右指针，子串的右边界，子串的范围：[left, right]
        int[] counts = new int[256];

        while (left < s.length()) {
            // 变换子串的范围（滑动窗口）
            if (right + 1 < s.length() && counts[s.charAt(right + 1)] < 1) {
                // 子串不存在重复字符，则 right 右移，扩大子串
                ++right;
                ++counts[s.charAt(right)];
            } else {
                // 子串中存在重复字符，left 右移，缩短子串，直到子串不存在重复字符为止
                --counts[s.charAt(left)];
                ++left;
            }

            if (counts[s.charAt(right)] <= 1 && (right - left + 1) > length) {
                length = (right - left + 1); // 变换子串的范围后，子串长度变大，则更新长度
            }
        }

        return length;
    }
}

/**
 * 解法二：使用 Map 标记是否存在重复的字符
 */
class _3Solution2 {
    
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>(); // 使用 map 存储不重复的字符
        int maxLen = -1; // 最长的不重复的子串的长度
        int left = 0; // 最长的不重复的子串中第一个字符在 s 中的下标
        
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            
            if (null != map.get(c)) {
                // 当发现有重复的，则更新 maxLen、map、 left
                maxLen = Math.max(map.size(), maxLen);

                // 删除 map 中存储的元素，即 s[left, idx] 之间的元素，从而便于存放 s[i]
                int idx = map.get(c);
                for (int j = left; j <= idx; ++j) {
                    map.remove(s.charAt(j));
                }
                
                left = idx + 1;
            }

            map.put(c, i);
        }

        maxLen = Math.max(map.size(), maxLen);

        return maxLen;
    }
}


