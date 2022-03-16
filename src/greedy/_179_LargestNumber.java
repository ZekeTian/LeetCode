package greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/largest-number/
 * 
 * 题目描述：给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 *         注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *         
 * 限制条件：
 *  （1）1 <= nums.length <= 100
 *  （2）0 <= nums[i] <= 10^9
 *
 * 示例：
 *  示例 1
 *      输入：nums = [10,2]
 *      输出："210"
 *  
 *  示例 2
 *      输入：nums = [3,30,34,5,9]
 *      输出："9534330"
 * 
 */
public class _179_LargestNumber {

    public static void main(String[] args) {
        // test case1, output: 210
//        int[] nums = { 10, 2 };
        
        // test case2, output: 9534330
//        int[] nums = { 3, 30, 34, 5, 9 };
        
        // test case3, output: 0
        int[] nums = { 0, 0 };
        
        
        _179Solution solution = new _179Solution();
        
        
        System.out.println(solution.largestNumber(nums));
    }
}

/**
 * 对 nums 数组进行降序排序，但是排序的比较规则略有不同。对于 nums 中两个数字 a、b，不是按照它们的自然大小进行排序，而是按照如下排序规则进行：
 * 将数字 a、b 按照出现的顺序拼接得到 ab，此外，按照相反的顺序拼接得到 ba，比较 ab、ba 两个字符串的大小。
 * 如果 ab > ba，则说明将 a 放在前面会更好，即让 a > b；反之，若 ab < ba，则说明将 b 放在前面会更好，即 b > a。
 */
class _179Solution {

    public String largestNumber(int[] nums) {
        if (null == nums || nums.length == 0) {
            return "";
        }
        
        // 将 nums 转换成字符串数组，方便排序
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            strs[i] = nums[i] + "";
        }
        
        Arrays.sort(strs, (a, b) -> {
            String ab = a + b;
            String ba = b + a;
            // ab > ba => a > b，比较 a、b 实际上相当于比较 ab、ba 两个字符串，因为要是降序，所以使用 ba.compareTo(ab)
            return ba.compareTo(ab);
        });
        
        if ("0".equals(strs[0])) {
            return "0"; // strs 中最大的是 "0"，说明 strs 中全部是 0，则不需要拼接
        }
        
        // 因为 strs 是已经按照降序排序，所以直接按照 strs 中的顺序拼接即可
        StringBuilder builder = new StringBuilder();
        for (String s : strs) {
            builder.append(s);
        }
        
        return builder.toString();
    }
}
