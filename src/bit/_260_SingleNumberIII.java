package bit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/single-number-iii/
 * 
 * 题目描述：给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *         你可以按 任意顺序 返回答案。
 * 
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * 
 * 限制条件：
 *  （1）2 <= nums.length <= 3 * 10^4
 *  （2）-2^31 <= nums[i] <= 2^31 - 1
 *  （3）除两个只出现一次的整数外，nums 中的其他数字都出现两次
 *  
 * 示例：
 *  示例 1
 *      输入：nums = [1,2,1,3,2,5]
 *      输出：[3,5]
 *      解释：[5, 3] 也是有效的答案。
 *      
 *  示例 2
 *      输入：nums = [-1,0]
 *      输出：[-1,0]
 *      
 *  示例 3
 *      输入：nums = [0,1]
 *      输出：[1,0]
 *
 */
public class _260_SingleNumberIII {

    public static void main(String[] args) {
        // test case1, output: [3, 5]
//        int[] nums = { 1, 2, 1, 3, 2, 5 };
        
        // test case2, output: [-1, 0]
//        int[] nums = { -1, 0 };
        
        // test case3, output: [0, 1]
        int[] nums = { 1, 0 };
        
        
//        _260Solution1 solution = new _260Solution1();
        
        _260Solution2 solution = new _260Solution2();
        
        
        System.out.println(Arrays.toString(solution.singleNumber(nums)));
    }
    
}

/**
 * 解法一：使用 Map 统计出各个数字的次数
 */
class _260Solution1 {
    
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i : nums) {
            Integer count = countMap.getOrDefault(i, 0);
            countMap.put(i, count + 1);
        }
        
        int[] res = new int[2];
        int i = 0; 
        Set<Integer> keys = countMap.keySet();
        for (int k : keys) {
            if (countMap.get(k) == 1) {
                res[i++] = k;
            }
        }
        
        return res;
    }
    
}


/**
 * 解法二：利用位操作解决
 * 将 nums 中两个只出现一次的数字分别记为 a、b。
 * 思路：
 *  （1）先对数组中的所有数字进行异或操作，得到异或和 xor
 *  （2）根据 xor 确定 a、b 二进制串中第一个不同的位置（假设，a、b 在 ith 位不同），得到 mask
 *  （3）遍历 nums，将这些数字分成两类，一类是在 ith 位与 a 相同，另一个是在 ith 位与 a 不同。
 *      分成两类之后，a、b 各自在一个类别中，并且在这两个类别中，都是：只有一个数字出现一次，其它数字出现两次。
 *      从而转换成第 136 题。
 *
 */
class _260Solution2 {
    
    public int[] singleNumber(int[] nums) {
        int xor = 0; // 异或和
        for (int i : nums) {
            xor ^= i;
        }
        
        // 得到两个数的分界位置，即两个数最开始不同的位置
        int mask = xor & (-xor);
        
        // 对两个数进行分类，类别一：数字和 mask 与（&）操作后为 0；类别二：数字和 mask 与（&）操作后不为 0 
        int[] res = new int[2];
        for (int i : nums) {
            if ((i & mask) == 0) {
                res[0] ^= i;
            } else {
                res[1] ^= i;
            }
        }
        
        return res;
    }
    
}
