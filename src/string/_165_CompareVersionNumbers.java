package string;

/**
 * https://leetcode.com/problems/compare-version-numbers/
 * 
 * 题目描述：给你两个版本号 version1 和 version2 ，请你比较它们。
 *        版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
 *        比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 *        返回规则如下：
 *          如果 version1 > version2 返回 1，
 *          如果 version1 < version2 返回 -1，
 *          除此之外返回 0。
 * 
 * 限制条件：
 *  （1）1 <= version1.length, version2.length <= 500
 *  （2）version1 和 version2 仅包含数字和 '.'
 *  （3）version1 和 version2 都是 有效版本号
 *  （4）version1 和 version2 的所有修订号都可以存储在 32 位整数 中
 * 
 * 示例：
 *  输入：version1 = "1.01", version2 = "1.001"
 *  输出：0
 *  解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
 * 
 */
public class _165_CompareVersionNumbers {
    
    public static void main(String[] args) {
        // test case1, output: -1
        String version1 = "0.1";
        String version2 = "1.1";
        
        // test case2, output: 0
//        String version1 = "1";
//        String version2 = "1.0";
        
        // test case3, output: 1
//        String version1 = "1.0.1";
//        String version2 = "1.0";
        
//        _165Solution1 solution = new _165Solution1();

        _165Solution2 solution = new _165Solution2();

        System.out.println(solution.compareVersion(version1, version2));
    }
}

/**
 * 解法一：双指针实现方式一（使用 split 分割，然后再比较）
 */
class _165Solution1 {
    public int compareVersion(String version1, String version2) {
        String[] nums1Str = version1.split("\\.");
        String[] nums2Str = version2.split("\\.");

        int i = 0, j = 0;
        while (i < nums1Str.length || j < nums2Str.length) {
            int num1 = 0, num2 = 0;

            if (i < nums1Str.length) {
                num1 = Integer.parseInt(nums1Str[i++]);
            }
            if (j < nums2Str.length) {
                num2 = Integer.parseInt(nums2Str[j++]);
            }
            
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }

        return 0;
    }
}


/**
 * 解法二：双指针实现方式二（不使用 split 分割，而是手动分割，然后比较）
 */
class _165Solution2 {
    
    public int compareVersion(String version1, String version2) {
        if (null == version1 || null == version2 
                || "".equals(version1) || "".equals(version2)) {
            return 0;
        }

        int i = 0, j = 0;
        while (i < version1.length() || j < version2.length()) {
            // 找到 version1 中的小数点，然后停止，从而计算出两个小数点之间的整数
            int num1 = 0;
            while (i < version1.length() && version1.charAt(i) != '.') {
                num1 = num1 * 10 + version1.charAt(i++) - '0';
            }
            ++i;
            // 计算 version2 中两个小数点之间的整数
            int num2 = 0;
            while (j < version2.length() && version2.charAt(j) != '.') {
                num2 = num2 * 10 + version2.charAt(j++) - '0';
            }
            ++j;
            
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }
        
        return 0;
    }
}
