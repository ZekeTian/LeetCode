package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/group-anagrams/
 * 
 * 题目描述：给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *        字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *        
 * 限制条件：
 *  （1）1 <= strs.length <= 10^4
 *  （2）0 <= strs[i].length <= 100
 *  （3）strs[i] 仅包含小写字母
 *  
 * 示例：
 *  示例 1
 *      输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 *      输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *  
 *  示例 2
 *      输入: strs = [""]
 *      输出: [[""]]
 *      
 *  示例 3
 *      输入: strs = ["a"]
 *      输出: [["a"]]
 *
 */
public class _49_GroupAnagrams {

    public static void main(String[] args) {
        // test case1, output: [["bat"],["nat","tan"],["ate","eat","tea"]]
//        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        // test case2, output: [[""]]
//        String[] strs = {""};
        
        // test case3, output: [["a"]]
        String[] strs = {"a"};
        
        
        _49Solution solution = new _49Solution();
        
        System.out.println(solution.groupAnagrams(strs));
    }
}


/**
 * 思路：对每个单词对应的字符数组进行排序，然后将排序后的字符数组作为 key。
 *     因为“字母异位词”排序后得到的单词是一样的，所以如果两个词是字母异位词，那么它们得到的 key 一样，将会被分到同一个组中。
 */
class _49Solution {
    
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> resultList =  new ArrayList<>();
        if (null == strs || strs.length == 0) {
            return resultList;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // 对 s 对应的字符数组进行排序，然后将排序后的字符数组作为 key（字母异位词 排序后的单词是一样的，因此 key 一样，将会被分到同一个组中）。
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(s);
            map.put(key, list);
        }
        
        Set<String> keys = map.keySet();
        for (String k : keys) {
            resultList.add(map.get(k));
        }
        
        return resultList;
    }
}