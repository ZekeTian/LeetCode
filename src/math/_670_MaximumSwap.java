package math;

/**
 * https://leetcode.com/problems/maximum-swap/
 * 
 * 题目描述：给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * 
 * 限制条件：给定数字的范围是 [0, 10^8]
 * 
 * 示例：
 *  示例 1
 *      输入: 2736
 *      输出: 7236
 *      解释: 交换数字2和数字7。
 *      
 *  示例 2
 *      输入: 9973
 *      输出: 9973
 *      解释: 不需要交换。
 *  
 *
 */
public class _670_MaximumSwap {

    public static void main(String[] args) {
        // test case1, output: 7236
        int num = 2736;
        
        // test case2, output: 9973
//        int num = 9973;
        
        
        _670Solution solution = new _670Solution();
        
        System.out.println(solution.maximumSwap(num));
        
    }
}

/**
 * 解法一：暴力法，将所有可能的交换都尝试一遍
 */
class _670Solution {
    
    public int maximumSwap(int num) {
        String str  = num + "";
        char[] arr = str.toCharArray();
        int maxNum = num;
        
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                swap(arr, i, j);
                int newNum = Integer.parseInt(new String(arr));
                maxNum = Math.max(maxNum, newNum);
                swap(arr, i, j);
            }
        }
        
        return maxNum;
    }

    private void swap(char[] arr, int i, int j) {
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }
    
}