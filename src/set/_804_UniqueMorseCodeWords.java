package set;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/unique-morse-code-words/
 *
 * Morse Code 是一种标签的编码方式，其 26 个字母对应的字母表如下：
 *  [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * 给定一组字符串，输出这组字符串所有不同 Morse Code 的个数。
 * 
 * Example:
 *   Input: words = ["gin", "zen", "gig", "msg"]
 *   Output: 2
 *   Explanation: 
 *   The transformation of each word is:
 *   "gin" -> "--...-."
 *   "zen" -> "--...-."
 *   "gig" -> "--...--."
 *   "msg" -> "--...--."
 * 有两个不同的 Morse Code
 * 
 * 
 */
public class _804_UniqueMorseCodeWords {

    public int uniqueMorseRepresentations(String[] words) {
        String[] alphabet = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
                "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
        
        Set<String> set = new HashSet<String>();
        // 逐个将字符串转换成 Morse Code
        for (String w : words) {
            int len = w.length();
            StringBuilder builder = new StringBuilder();
            // 逐个字符转换
            for (int i = 0; i < len; i++) {
                // 获取字母对应的 Morse Code
                int idx = w.charAt(i) - 'a';
                builder.append(alphabet[idx]);
            }
            set.add(builder.toString());
        }
        
        return set.size();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        _804_UniqueMorseCodeWords solution = new _804_UniqueMorseCodeWords();
        System.out.println(solution.uniqueMorseRepresentations(words));
    }

}
