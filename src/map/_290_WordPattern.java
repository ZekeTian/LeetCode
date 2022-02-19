package map;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/word-pattern/
 * 
 * 题目描述：给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 *        这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 *        你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。  
 * 
 * 示例
 *  示例 1
 *      输入: pattern = "abba", str = "dog cat cat dog"
 *      输出: true
 *  
 *  示例 2
 *      输入:pattern = "abba", str = "dog cat cat fish"
 *      输出: false
 * 
 *  示例 3
 *      输入: pattern = "aaaa", str = "dog cat cat dog"
 *      输出: false
 *      解释： pattern 到 str 方向的映射不是一对一，而是一对多
 *  
 *  示例 4
 *      输入：pattern = "abba", str = "dog dog dog dog"
 *      输出：false
 *      解释：str 到 pattern 方向的映射不是一对一，而是一对多
 *      
 */
public class _290_WordPattern {

    public static void main(String[] args) {
        // test case1, output: true
//        String pattern = "abba", str = "dog cat cat dog";

        // test case2, output: false
//        String pattern = "abba", str = "dog cat cat fish";
        
        // test case3, output: false
//        String pattern = "aaaa", str = "dog cat cat dog";
        
        // test case3, output: false
        String pattern = "abba", str = "dog dog dog dog";
        
        _290Solution solution = new _290Solution();
        
        
        System.out.println(solution.wordPattern(pattern, str));
    }
}

class _290Solution {

    public boolean wordPattern(String pattern, String s) {
        String[] splits = s.split(" ");
        if (splits.length != pattern.length()) {
            return false;
        }

        // pattern 与 s 之间是一一对应的关系
        Map<Character, String> pMap = new HashMap<>(); // 存储 p -> s 之间的映射关系
        Map<String, Character> sMap = new HashMap<>(); // 存储 s -> p 之间的映射关系
        for (int i = 0; i < pattern.length(); ++i) {
            if (!pMap.containsKey(pattern.charAt(i))) {
                pMap.put(pattern.charAt(i), splits[i]);
            } else {
                if (!pMap.get(pattern.charAt(i)).equals(splits[i])) {
                    return false;
                }
            }
            
            if (!sMap.containsKey(splits[i])) {
                sMap.put(splits[i], pattern.charAt(i));
            } else {
                if (!sMap.get(splits[i]).equals(pattern.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }
}
