package string;

/**
 * https://leetcode.com/problems/implement-strstr/
 *
 * 题目描述：实现 strStr() 函数。
 *         给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。
 *         如果不存在，则返回  -1 。
 *         说明：当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 *         对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
 * 
 * 限制条件：
 *  （1）1 <= haystack.length, needle.length <= 10^4
 *  （2）haystack 和 needle 仅由小写英文字符组成
 * 
 * 示例：
 *  示例 1
 *      输入：haystack = "hello", needle = "ll"
 *      输出：2
 *      
 *  示例 2
 *      输入：haystack = "aaaaa", needle = "bba"
 *      输出：-1
 *      
 * 
 */
public class _28_ImplementStrStr {

    public static void main(String[] args) {
        // test case1, output: 2
//        String haystack = "hello", needle = "ll";
        
        // test case2, output: -1
//        String haystack = "aaaaa", needle = "bba";
        
        // test case3, output: 4
//        String haystack = "mississippi", needle = "issip";
        
        // test case4, output: -1
        String haystack = "aaa", needle = "aaaa";
        
        
        _28Solution solution = new _28Solution();
        
        
        System.out.println(solution.strStr(haystack, needle));
    }
    
}


class _28Solution {
    
    public int strStr(String haystack, String needle) {
        // 当 needle 为空时，返回 0
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        
        // 遍历 haystack，然后将每个字符 haystack[i] 作为起始字符，之后去匹配 needle
        for (int i = 0;  i < haystack.length(); ++i) {
            int idx = i; // haystack 中匹配 needle 时的下标
            boolean flag = true; // 标记是否能够匹配完 needle
            for (int j = 0; j < needle.length(); ++j) {
                if (idx >= haystack.length() || haystack.charAt(idx) != needle.charAt(j)) {
                    flag = false;
                    break; // 从 haystack[i] 出发，去匹配 needle 时，匹配失败
                }
                ++idx;
            }
            
            if (flag) {
                return i; // 从 haystack[i] 出发，去匹配 needle 时，匹配成功
            }
        }
        
        return -1;
    }
    
}

