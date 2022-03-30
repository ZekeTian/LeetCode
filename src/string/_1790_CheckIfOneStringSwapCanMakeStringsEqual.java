package string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
 * 
 * 题目描述：给你长度相等的两个字符串 s1 和 s2 。一次 字符串交换 操作的步骤如下：
 *         选出某个字符串中的两个下标（不必不同），并交换这两个下标所对应的字符。
 *         如果对 其中一个字符串 执行 最多一次字符串交换 就可以使两个字符串相等，返回 true ；否则，返回 false 。
 * 
 * 限制条件：
 *  （1）1 <= s1.length, s2.length <= 100
 *  （2）s1.length == s2.length
 *  （3）s1 和 s2 仅由小写英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入：s1 = "bank", s2 = "kanb"
 *      输出：true
 *      解释：例如，交换 s2 中的第一个和最后一个字符可以得到 "bank"
 *  
 *  示例 2
 *      输入：s1 = "attack", s2 = "defend"
 *      输出：false
 *      解释：一次字符串交换无法使两个字符串相等
 *      
 *  示例 3
 *      输入：s1 = "kelb", s2 = "kelb"
 *      输出：true
 *      解释：两个字符串已经相等，所以不需要进行字符串交换
 *  
 *  示例 4
 *      输入：s1 = "abcd", s2 = "dcba"
 *      输出：false
 * 
 */
public class _1790_CheckIfOneStringSwapCanMakeStringsEqual {

    public static void main(String[] args) {
        // test case1, outptu: true
//        String s1 = "bank", s2 = "kanb"; 
        
        // test case2, outptu: false
//        String s1 = "attack", s2 = "defend"; 
        
        // test case3, outptu: true
//        String s1 = "kelb", s2 = "kelb";
        
        // test case4, outptu: false
//        String s1 = "abcd", s2 = "dcba";
        
        // test case5, outptu: false
        String s1 = "abcd", s2 = "abef";
        
        _1790Solution solution = new _1790Solution();
        
        System.out.println(solution.areAlmostEqual(s1, s2));
    }
}


class _1790Solution {
    
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        
        
        List<Integer> list = new ArrayList<>(); // 存储 s1、s2 两个字符串中不相等字符的下标
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                list.add(i);
            }
        }
        
        if (list.size() == 0) {
            return true;
        }
        
        if (list.size() == 2) { 
            int idx1 = list.get(0);
            int idx2 = list.get(1);
            return (s1.charAt(idx1) == s2.charAt(idx2)) 
                    && (s1.charAt(idx2) == s2.charAt(idx1)); // 此时，交换 idx1、idx2 处的字符即可让 s1、s2 相等
        }
        
        return false;
    }
}
