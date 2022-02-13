package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/single-number/
 * 
 * 题目描述：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 
 * 说明：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *          
 * 示例：
 *  输入: [2,2,1]
 *  输出: 1
 *  
 */
public class _136_SingleNumber {

    public static void main(String[] args) {
        int[] nums = { 2, 2, 1 };
        
//        _136Solution1 solution = new _136Solution1();

        _136Solution2 solution = new _136Solution2();
        
        System.out.println(solution.singleNumber(nums));
    }
}

/**
 * 解法一：使用 map 统计出各个数字出现的次数，然后找出只出现一次的数字
 */
class _136Solution1 {
    
    public int singleNumber(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i : nums) {
            Integer count = map.getOrDefault(i, 0);
            map.put(i, count + 1);
        }
        
        Set<Entry<Integer,Integer>> entrySet = map.entrySet();
        for (Entry<Integer, Integer> e :  entrySet) {
            if (e.getValue() == 1) {
                return e.getKey();
            }
        }
        
        return 0;
    }
}

/**
 * 解法二：使用异或运算。因为在异或运算中，相同为 0，不同为 1，所以出现两次的数字在异或运算中会被消除，最终只会留下出现一次的数字。
 */
class _136Solution2 {
    
    public int singleNumber(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        
        return res;
    }
}
