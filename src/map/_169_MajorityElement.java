package map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/majority-element/
 * 
 * 题目描述：给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *        你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *        
 * 限制条件：
 *  （1）n == nums.length
 *  （2）1 <= n <= 5 * 10^4
 *  （3）-10^9 <= nums[i] <= 10^9
 */
public class _169_MajorityElement {

    public static void main(String[] args) {
//        int[] nums = { 3, 2, 3 };
        int[] nums = { 3, 2, 3, 2, 2 };
        
//        _169Solution1 solution = new _169Solution1();
//        _169Solution2 solution = new _169Solution2();
        _169Solution3 solution = new _169Solution3();
        
        
        System.out.println(solution.majorityElement(nums));
    }
}

/**
 * 解法一：使用 Map
 */
class _169Solution1 {
    
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        Set<Integer> keys = map.keySet();
        for (int k : keys) {
            if (map.get(k) > nums.length / 2) {
                return k;
            }
        }
        
        return 0;
    }
}

/**
 * 解法二：使用排序，中间的数字即为所求结果（当前前提是 nums 中一定存在结果，如果题目所给数据不一定存在结果时，则需要进行检验）。
 */
class _169Solution2 {
    
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        
        return nums[nums.length / 2];
    }
}

/**
 * 解法三：摩尔投票
 */
class _169Solution3 {
    
    public int majorityElement(int[] nums) {
        int res = 0; // 票最多的数字
        int votes = 0; // votes 记录着 res 的票数
        
        for (int i : nums) {
            if (votes == 0) {
                res = i; // 票数为 0，则从新选择一个数字。（票数多的，一定有机会进入这个 if，因为票数少的数字已经全部被消除）
            }
            
            if (i == res) {
                ++votes; // 如果当前数字是 res，则票数加 1
            } else {
                --votes; // 如果当前数字不是 res，则用 res 的票数抵消掉当前数字的票
            }
        }
        
        return res;
    }
}

