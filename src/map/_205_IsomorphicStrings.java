package map;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/isomorphic-strings/
 * 
 * 题目描述：给定两个字符串 s 和 t ，判断它们是否是同构的。
 *        如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 *        每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 *        
 * 限制条件：
 *  （1）1 <= s.length <= 5 * 10^4
 *  （2）t.length == s.length
 *  （3）s 和 t 由任意有效的 ASCII 字符组成
 * 
 * 示例：
 *  示例 1：
 *      输入：s = "egg", t = "add"
 *      输出：true
 *      
 *  示例 2
 *      输入：s = "foo", t = "bar"
 *      输出：false
 *
 */
public class _205_IsomorphicStrings {

    public static void main(String[] args) {
        // test case1, output: true
//        String s = "egg", t = "add";
        
        // test case2, output: false
        String s = "foo", t = "bar";
        
        _205Solution solution = new _205Solution();
        
        
        System.out.println(solution.isIsomorphic(s, t));
    }
}


/**
 * 解题思路同第 290 题，需要使用两个 map 记录两个方向的映射  
 */
class _205Solution {
    
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        for (int i = 0; i < s.length(); ++i) {
            if (!sMap.containsKey(s.charAt(i))) {
                sMap.put(s.charAt(i), t.charAt(i));
            } else {
                if (!sMap.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
            }
            
            if (!tMap.containsKey(t.charAt(i))) {
                tMap.put(t.charAt(i), s.charAt(i));
            } else {
                if (!tMap.get(t.charAt(i)).equals(s.charAt(i))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
