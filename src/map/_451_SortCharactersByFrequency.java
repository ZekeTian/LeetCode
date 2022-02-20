package map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/sort-characters-by-frequency/
 * 
 * 题目描述：给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 5 * 10^5
 *  （2）s 只包含大小写字母和数字
 * 
 * 示例：
 *  示例 1
 *      输入: "tree"
 *      输出: "eert"
 *      解释: 'e'出现两次，'r'和't'都只出现一次。因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 *  
 *  示例 2
 *      输入: "cccaaa"
 *      输出: "cccaaa"
 *      解释: 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 *      
 */
public class _451_SortCharactersByFrequency {

    public static void main(String[] args) {
        // test case1, output: "eert"
//        String s = "tree";
        
        // test case1, output: "cccaaa" 或 "aaaccc"
        String s = "cccaaa";
        
        _451Solution solution = new _451Solution();
        
        
        System.out.println(solution.frequencySort(s));
    }
}

class _451Solution {
    
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); ++i) {
            Integer count = map.getOrDefault(s.charAt(i), 0);
            map.put(s.charAt(i), count + 1);
        }
        
        // 获取到 key 集合，然后转换成列表
        List<Character> list = new ArrayList<>(map.keySet());
        // 按照 key 对应 value 对 list 进行降序排序
        Collections.sort(list, (c1, c2) -> (map.get(c2) - map.get(c1)));
        
        StringBuilder builder = new StringBuilder();
        for (Character c : list) {
            for (int i = 0; i < map.get(c); ++i) {
                builder.append(c);
            }
        }
                
        return builder.toString();
    }
}
