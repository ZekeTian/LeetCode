package string;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string-iii/
 * 
 * 题目描述：给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 
 * 限制条件：
 *  （1）1 <= s.length <= 5 * 10^4
 *  （2）s 包含可打印的 ASCII 字符。
 *  （3）s 不包含任何开头或结尾空格。
 *  （4）s 里 至少 有一个词。
 *  （5）s 中的所有单词都用一个空格隔开。
 *  
 * 示例：
 *  示例 1
 *      输入：s = "Let's take LeetCode contest"
 *      输出："s'teL ekat edoCteeL tsetnoc"
 *      
 *  示例 2
 *      输入： s = "God Ding"
 *      输出："doG gniD"
 * 
 */
public class _557_ReverseWordsInAStringIII {

    public static void main(String[] args) {
        // test case1, output: "s'teL ekat edoCteeL tsetnoc"
//        String s = "Let's take LeetCode contest";
//        String res = "s'teL ekat edoCteeL tsetnoc";
        
        // test case2, output: "doG gniD"
        String s = "God Ding";
        String res = "doG gniD";
        
        
//        _557Solution1 solution = new _557Solution1();
        
        _557Solution2 solution = new _557Solution2();
        
        System.out.println(res.equals(solution.reverseWords(s)));
    }
    
}

/**
 * 解法一：利用 JDK 自带的 API 解决
 */
class _557Solution1 {
    
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        // 利用空格分割字符串，得到单词数组
        String[] words = s.split(" ");
        
        // 从后面向前遍历 words，将单词逐个添加到 builder 中（因为后面会 reverse，所以需要从后向前遍历）
        StringBuilder builder = new StringBuilder();
        for (int i = words.length - 1; i > 0; --i) {
            builder.append(words[i]).append(" ");
        }
        builder.append(words[0]);
        
        return builder.reverse().toString(); 
    }
    
}

/**
 * 解法二：使用双指针。
 *        使用 left、right 两个指针确定一个单词的区间，然后再将该单词逆序添加到 builder 中即可。
 */
class _557Solution2 {
    
    public String reverseWords(String s) {
        if (null == s || s.length() == 0) {
            return "";
        }
        
        int left = 0, right = -1; // [left, right] 之间表示一个单词的区间
        StringBuilder builder = new StringBuilder();
        
        while (left < s.length()) {
            // 寻找到单词的区间 [left, right]
            while (right + 1 < s.length() && s.charAt(right + 1) != ' ') {
                ++right;
            }

            // 逆序遍历 [left, right]，然后添加到 builder 中
            for (int i = right; i >= left; --i) {
                builder.append(s.charAt(i));
            }
            
            // 判断是否需要添加空格。如果是最后一个单词，则不需要添加
            if (right < s.length() - 1) {
                builder.append(' '); // 不是最后一个，则需要添加空格分隔
            }
            
            // left 从 right + 1处开始向移动，直到找到不为 ' ' 的字符 （可以解决中间含有多个空格）
            // 当然，因为本题限制中间只有一个空格分割，所以实际上可以利用如下两行代码解决：
//             ++right;
//             left = right + 1;
            
            // 跳过中间的空格
            left = right + 1;
            while (left < s.length() && s.charAt(left) == ' ') {
                ++left;
            }
            right = left - 1;
        }
        
        
        return builder.toString(); 
    }
    
}

