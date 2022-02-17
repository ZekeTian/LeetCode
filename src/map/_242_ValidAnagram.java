package map;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/valid-anagram/
 * 
 * 题目描述：给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *        注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 *
 * 进阶：如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 * 
 * 限制条件：
 *  （1）1 <= s.length, t.length <= 5 * 104
 *  （2）s 和 t 仅包含小写字母
 *  
 * 示例：
 *  示例 1
 *      输入: s = "anagram", t = "nagaram"
 *      输出: true
 *      
 *  示例 2
 *      输入: s = "rat", t = "car"
 *      输出: false
 *
 */
public class _242_ValidAnagram {

    public static void main(String[] args) throws FileNotFoundException {
        String s = "anagram", t = "nagaram";
        
//        _242Solution1 solution = new _242Solution1();

        _242Solution2 solution = new _242Solution2();
        
        
        System.out.println(solution.isAnagram(s, t));
    }
}

/**
 * 解法一：使用 Map
 */
class _242Solution1 {
    
    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        
        for (int i = 0; i < s.length(); ++i) {
            Integer count = sMap.getOrDefault(s.charAt(i), 0);
            sMap.put(s.charAt(i), count + 1);
        }
        
        for (int i = 0; i < t.length(); ++i) {
            Integer count = tMap.getOrDefault(t.charAt(i), 0);
            tMap.put(t.charAt(i), count + 1);
        }
        
        Set<Map.Entry<Character, Integer>> entrySet = sMap.entrySet();
        for (Map.Entry<Character, Integer> e : entrySet) {
            if (!tMap.getOrDefault(e.getKey(), 0).equals(e.getValue())) {
                return false;
            }
        }
        
        return sMap.size() == tMap.size();
    }
}


/**
 * 解法二：使用数组
 */
class _242Solution2 {
    
    public boolean isAnagram(String s, String t) {
        int[] countS = new int[26];
        int[] countT = new int[26];
        
        for (int i = 0; i < s.length(); ++i) {
            ++countS[s.charAt(i) - 'a'];
        }
        
        for (int i = 0; i < t.length(); ++i) {
            ++countT[t.charAt(i) - 'a'];
        }
        
        for (int i = 0; i < countS.length; ++i) {
            if (countS[i] != countT[i]) {
                return false;
            }
        }
        
        return true;
    }
}