package string;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 * 
 * 题目描述：编写一个函数来查找字符串数组中的最长公共前缀。
 *         如果不存在公共前缀，返回空字符串 ""。
 * 
 * 限制条件：
 *  （1）1 <= strs.length <= 200
 *  （2）0 <= strs[i].length <= 200
 *  （3）strs[i] 仅由小写英文字母组成
 * 
 * 示例：
 *  示例 1
 *       输入：strs = ["flower","flow","flight"]
 *       输出："fl"
 *
 *  示例 2
 *      输入：strs = ["dog","racecar","car"]
 *      输出：""
 *      解释：输入不存在公共前缀。
 *      
 */
public class _14_LongestCommonPrefix {

    public static void main(String[] args) {
        // test case1, output: "fl"
//        String[] strs = { "flower", "flow", "flight" };
        
        // test case2, output: ""
//        String[] strs = { "dog", "racecar", "car" };
        
        // test case3, output: "ab"
        String[] strs = { "ab" };
        
        
        _14Solution solution = new _14Solution();
        
        
        System.out.println(solution.longestCommonPrefix(strs));
    }
}

/**
 * 首先逐个遍历 strs[0] 字符串中的字符 ch，然后判断字符 ch 是否也在其它字符串的相应位置出现。
 * 如果出现，则继续遍历；否则，停止遍历；
 *
 */
class _14Solution {
    
    public String longestCommonPrefix(String[] strs) {
        if (null == strs || strs.length == 0) {
            return "";
        }
        
        for (int i = 0; i < strs[0].length(); ++i) {
            int ch = strs[0].charAt(i);
            // 判断 ch 是否在其它字符串（ strs[1...len) 中各个字符串）的相应位置（下标 i 处）出现
            for (int j = 1; j < strs.length; ++j) {
                if (i >= strs[j].length() /* strs[j] 已经比较完，则无法继续比较 */
                        || ch != strs[j].charAt(i) /* ch 不是公共字符 */ ) {
                    return strs[0].substring(0, i);
                }
            }
        }
        
        return strs[0]; // strs[0] 已经遍历完，则说明 strs[0] 是所有字符串的公共前缀
    }
}
