package string;

/**
 * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/
 * 
 * 题目描述：给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 10^4
 *  （2）s 仅由小写英文字母组成
 *  （3）1 <= k <= 10^5
 *  
 * 示例：
 *  示例 1
 *      输入：s = "aaabb", k = 3
 *      输出：3
 *      解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 *      
 *  示例 2
 *      输入：s = "ababbc", k = 2
 *      输出：5
 *      解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *
 */
public class _395_LongestSubstringWithAtLeastKRepeatingCharacters {

    
    public static void main(String[] args) {
        // test case1, output: 3
//        String s = "aaabb";
//        int k = 3;
        
        // test case2, output: 5
        String s = "ababbc";
        int k = 2;
        
        
        _395Solution1 solution = new _395Solution1();
        System.out.println(solution.longestSubstring(s, k));
    }
    
}


/**
 * 解法一：分治法
 * 思路如下：
 *  （1）在字符串 s 中，寻找到频率小于 k 的字符 ch
 *      因为 ch 的频率小于 k，所以只要含有字符 ch 的字符串，则该字符串必定不符合要求，即不能含有字符 ch。
 *      由于此限制，所以可以将 ch 作为分割符，将原始的字符串分割成多个子串，然后再对子串进行同样的处理。
 *  （2）以 ch 为分割符，将原始字符串分割成多个子串
 *  （3）然后对每个子串进行同样的处理
 */
class _395Solution1 {
    
    // 对 s[start...end] 进行处理，寻找题目中要求的最长子串
    private int longestSubstring(String s, int start, int end, int k) {
        if (start > end) {
            return 0;
        }
        
        // 统计出 s[start...end] 之间各个字符出现的频率
        int[] count = new int[26];
        for (int i = start; i <= end; ++i) {
            ++count[s.charAt(i) - 'a'];
        }
        
        // 在 count 中寻找频率小于 k 的字符 split，并将该字符作为分割符
        char split = 0;
        for (int i = 0; i < count.length; ++i) {
            if (count[i] > 0 && count[i] < k) {
                split = (char) ('a' + i);
                break;
            }
        }
        
        if (split == 0) {
            return (end - start + 1); // s[start...end] 中的字符均符合要求，则长度即为字符串 s[start...end] 的长度
        }
        
        // 以 split 为分割符，将 s 分割成多个子串，然后对每个子串执行同样的操作
        int left = start, right = start; // 表示子串 s[left...right] 的左右边界
        int len = 0;
        while (left <= end) {
            // 确定子串的左边界
            while (left <= end && s.charAt(left) == split) {
                ++left;
            }
            // 确定子串的右边界
            right = left;
            while (right <= end && s.charAt(right) != split) {
                ++right;
            }
            // 对子串 s[left...right] 进行同样的处理
            len = Math.max(len, longestSubstring(s, left, right - 1, k));
            
            // 移动 left，方便对下一个子串进行处理
            left = right;
        }
        
        return len;
    }
    
    public int longestSubstring(String s, int k) {
        return longestSubstring(s, 0, s.length() - 1, k);
    }
    
}
