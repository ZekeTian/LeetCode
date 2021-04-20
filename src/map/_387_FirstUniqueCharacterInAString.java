package map;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/first-unique-character-in-a-string/
 *
 * 题目描述：给定一个字符串 s，返回 s 中第一个不重复的字符的下标。如果没有，则返回 -1。字符串 s 只包含小写字母。
 * Input: s = "leetcode"
 * Output: 0
 * 
 * Input: s = "loveleetcode"
 * Output: 2
 * 
 * Input: s = "aabb"
 * Output: -1
 */
public class _387_FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        String s = "leetcode";
        _387Solution1 solution = new _387Solution1();
//        _387Solution2 solution = new _387Solution2();
        System.out.println(solution.firstUniqChar(s));
    }
}

/**
 * 使用 map 解决
 */
class _387Solution1 {
    public int firstUniqChar(String s) {
        Map<Character, Integer> count = new HashMap<Character, Integer>();
        
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            
            if (count.containsKey(ch)) {
                count.put(ch, count.get(ch) + 1);
            } else {
                count.put(ch, 1);
            }
        }
        
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (1 == count.get(ch)) {
                return i;
            }
        }

        return -1;
    }
}


/**
 * 使用数组解决
 */
class _387Solution2 {
    public int firstUniqChar(String s) {
        int[] count = new int[26];
        
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            ++count[ch - 'a'];
        }
        
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (1 == count[ch - 'a']) {
                return i;
            }
        }
        
        return -1;
    }
}
