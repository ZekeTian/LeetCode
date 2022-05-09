package greedy;

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
//        int num = 2736;
        
        // test case2, output: 9973
        int num = 9973;
        
        
//        _670Solution1 solution = new _670Solution1();
        
        _670Solution2 solution = new _670Solution2();
        
        System.out.println(solution.maximumSwap(num));
        
    }
}

/**
 * 解法一：暴力法，将所有可能的交换都尝试一遍
 */
class _670Solution1 {
    
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


/**
 * 解法二：贪心法
 *        从前向后遍历，对于当前数字 nums[i] ，在 i 后面找到一个比 nums[i] 大的最大数字，然后两者交换即可。
 *        例如，对于 2736。遍历到 2 时，在 2 后面比 2 大的最大数字是 7，所以交换 2、7 即可得到 7236。
 */
class _670Solution2 {
    
    public int maximumSwap(int num) {
        String str = num + "";
        int[] last = new int[10]; // 存储 0~9 这 10 个数字在 str 中最后出现的位置
        char[] arr = str.toCharArray();
        
        for (int i = 0; i < arr.length; ++i) {
            last[arr[i] - '0'] = i;
        }
            
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 9; j > (arr[i] - '0'); --j) { // 因为要在比 arr[i] 大的数字中找到最大的数字，所以内层是 9 从开始遍历
                if (last[j] > i) { // j 比 arr[i] 大，并且 j 的位置是在 arr[i] 后面，则找到，直接交换 j 和 arr[i] 即可
                    swap(arr, last[j], i);
                    return Integer.parseInt(new String(arr));
                }
            }
        }
        
        return num;
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
}

