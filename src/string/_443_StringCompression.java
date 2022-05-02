package string;

/**
 * https://leetcode.com/problems/string-compression/
 * 
 * 题目描述：给你一个字符数组 chars ，请使用下述算法压缩：
 *         从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 *          （1）如果这一组长度为 1 ，则将字符追加到 s 中。
 *          （2）否则，需要向 s 追加字符，后跟这一组的长度。
 *         压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。
 *         需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 *         请在 修改完输入数组后 ，返回该数组的新长度。
 *         你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 *  
 * 限制条件：
 *  （1）1 <= chars.length <= 2000
 *  （2）chars[i] 可以是小写英文字母、大写英文字母、数字或符号
 *
 * 示例：
 *  示例 1
 *      输入：chars = ["a","a","b","b","c","c","c"]
 *      输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 *      解释："aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 *      
 *  示例 2
 *      输入：chars = ["a"]
 *      输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 *      解释：唯一的组是“a”，它保持未压缩，因为它是一个字符。
 *  
 *  示例 3
 *      输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 *      输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 *      解释：由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
 *      
 */
public class _443_StringCompression {

    public static void main(String[] args) {
        // test case1, output: a2b2c3
//        char[] chars = { 'a', 'a', 'b', 'b', 'c', 'c', 'c' };
        
        // test case2, output: a
//        char[] chars = { 'a' };
        
        // test case3, output: ab12
        char[] chars = { 'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b' };
        
//        _443Solution1 solution = new _443Solution1();
        
        _443Solution2 solution = new _443Solution2();
        
        int len = solution.compress(chars);
        
        for (int i = 0; i < len; ++i) {
            System.out.print(chars[i]);
        }
    }
}

/**
 * 解法一：使用 StringBuilder 存储压缩后的字符串，然后将 StringBuilder 的值复制到 chars 中。
 */
class _443Solution1 {
    
    public int compress(char[] chars) {
        if (null == chars || chars.length == 0) {
            return 0;
        }
        
        if (chars.length == 1) {
            return 1;
        }
        
        char currentChar = chars[0];
        int count = 1; // currentChar 连续出现的次数
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < chars.length; ++i) {
            if (chars[i - 1] == chars[i]) {
                ++count;
            } else {
                // 遇到一个新的字符，则需要将统计信息添加到 builder 中，并且重置 count
                builder.append(currentChar);
                if (count > 1) {
                    builder.append(count);
                }
                
                currentChar = chars[i];
                count = 1;
            }
        }
        builder.append(currentChar);
        if (count > 1) {
            builder.append(count);
        }
        
        // 将 builder 的值复制到 chars 中
        for (int i = 0; i < builder.length(); ++i) {
            chars[i] = builder.charAt(i);
        }

        return builder.length();
    }
    
}


/**
 * 解法一：不额外使用内存空间，直接在 chars 上更改
 */
class _443Solution2 {
    
    // 从 chars[i] 处开始，将字符 ch 和出现次数 count 放进 chars 中
    private int put(char[] chars, int i, char ch, int count) {
        chars[i++] = ch;
        if (count > 1) {
            String num = count + "";
            for (int j = 0; j < num.length(); ++j) {
                chars[i++] = num.charAt(j);
            }
        }
        
        return i;
    }
    
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        
        if (chars.length == 1) {
            return 1;
        }
        
        char currentChar = chars[0]; // 当前统计的字符
        int count = 1; // currentChar 字符出现的次数
        int len = 0; // 压缩后的字符串的长度，实际上也是在 chars 中的下标
        
        for (int i = 1; i < chars.length; ++i) {
            if (chars[i - 1] == chars[i]) {
                ++count; // 遇到的字符和之前的一样，则计数加 1，继续下一个字符
                continue;
            }
            // 遇到新的字符，则将之前的字符和出现的次数放进 chars 中，并且更新 currentChar 和 count
            len = put(chars, len, currentChar, count); // 将 currentChar 和 count 放进 chars 中
            currentChar = chars[i];
            count = 1;
        }
        
        // 将最后一个字符和出现的次数放进 chars 中
        len = put(chars, len, currentChar, count);
        
        return len;
    }

}