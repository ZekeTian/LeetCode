package bit;

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
        int[] nums = { 1, 2, 1, 3, 2, 5 };
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
