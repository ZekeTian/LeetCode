package array;

/**
 * https://leetcode.com/problems/next-greater-element-iii/
 * 
 * 题目描述：给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。
 *         如果不存在这样的正整数，则返回 -1 。
 *         注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 *         
 * 限制条件：1 <= n <= 2^31 - 1
 *  
 * 示例：
 *  示例 1
 *      输入：n = 12
 *      输出：21
 *      
 *  示例 2
 *      输入：n = 21
 *      输出：-1
 * 
 */
public class _556_NextGreaterElementIII {

    public static void main(String[] args) {
        // test case1, output: 21
//        int n = 12;
        
        // test case2, output: -1
//        int n = 21;
        
        // test case3, output: 251347
        int n = 247531;
        
        
        _556Solution solution = new _556Solution();
        
        
        System.out.println(solution.nextGreaterElement(n));
    }
}


/**
 * 思路：
 *  （1）从后向前遍历，直到找到一个山峰，停止，假设山峰前的数字为 arr[i]
 *  （2）如果 i < 0，则说明数组是非单调递增的，此时，组成的数字已经是最大的，无法找到一个比它还大的数字，例如：4321，直接返回 -1。
 *  （3）如果 i > 0，则从后面向前寻找 j，找到 arr[j] > arr[i]，然后交换 arr[i]、arr[j]，从而增大数字（此时，arr[i...len-1] 依然保持单调递减）。
 *  （4）因为 arr[i...len-1] 是单调递减，其在大于 num 的数字中不是最小。因此，还需要反转 arr[i+1...len-1] 中的数字。
 *  （5）如果反转后的 arr，形成的数字比 Integer.MAX_VALUE 还要大，则返回 -1。
 * 
 * 示例：
 *  以 247531 为例，
 *  （1）从后向前遍历，找到山峰 7，然后停在数字 4 处（下标 i = 1）
 *  （2）因为 i > 0，则从后向前寻找大于 4 的数字，最后找到 5（下标 j = 3）
 *  （3）交换 4 和 5 ，得到 257431，因为 "7431" 是单调递减的，其在大于 247531 中的数字中不是最小，因此反转 "7431"，得到新的数字 251347
 *  （4）251347 > 247531，得到所需结果
 *  
 */
class _556Solution {
    
    public int nextGreaterElement(int n) {
        String str = n + "";
        char[] arr = str.toCharArray();
        if (arr.length < 2) {
            return -1;
        }
        
        // 寻找到山峰，并将 i 停在山峰前一个位置
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            --i;
        }
        if (i < 0) {
            return -1; // arr 单调递增，则 n 此时已经是最大值
        }
        
        // 从后面找到一个比 arr[i] 更大的数字 arr[j]
        int j = arr.length - 1;
        while (j > i && arr[j] <= arr[i]) {
            --j;
        }
        
        // 交换 arr[i]、arr[j]，从而增大数字
        swap(arr, i, j);
        
        // 将 arr[i+1 ... len-1] 之间的数字进行反转，变成递增，从而减小数字
        reverse(arr, i + 1, arr.length - 1);
        
        long num = Long.parseLong(new String(arr));
        
        return (num > Integer.MAX_VALUE ? -1 : (int) num);
    }

    // 反转 arr[i...j] 之间的元素
    private void reverse(char[] arr, int i, int j) {
        int l = i, r = j;
        
        while (l < r) {
            swap(arr, l, r);
            ++l;
            --r;
        }
    }

    private void swap(char[] arr, int i, int j) {
        char ch = arr[i];
        arr[i] = arr[j];
        arr[j] = ch;
    }
    
}