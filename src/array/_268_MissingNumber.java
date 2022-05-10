package array;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/missing-number/
 * 
 * 题目描述：给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 * 
 * 进阶：你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题？
 * 
 * 限制条件：
 *  （1）n == nums.length
 *  （2）1 <= n <= 10^4
 *  （3）0 <= nums[i] <= n
 *  （4）nums 中的所有数字都 独一无二
 * 
 * 示例：
 *  示例 1
 *      输入：nums = [3,0,1]
 *      输出：2
 *      解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 *      
 *  示例 2
 *      输入：nums = [0,1]
 *      输出：2
 *      解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 *      
 *  示例 3
 *      输入：nums = [9,6,4,2,3,5,7,0,1]
 *      输出：8
 *      解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 *      
 *  示例 4
 *      输入：nums = [0]
 *      输出：1
 *      解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 *      
 *
 */
public class _268_MissingNumber {

    public static void main(String[] args) {
        // test case1, output: 2
//        int[] nums = { 3, 0, 1 };
        
        // test case2, output: 2
//        int[] nums = { 0, 1 };
        
        // test case3, output: 8
//        int[] nums = { 9, 6, 4, 2, 3, 5, 7, 0, 1 };
        
        // test case4, output: 1
        int[] nums = { 0 };
        
        
//        _268Solution1 solution = new _268Solution1();
        
        _268Solution2 solution = new _268Solution2();
        
        System.out.println(solution.missingNumber(nums));
    }
    
}

/**
 * 解法一：利用 set 加快寻找
 *
 */
class _268Solution1 {
    
    public int missingNumber(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        
        for (int i = 0; i <= nums.length; ++i) {
            if (!set.contains(i)) {
                return i;
            }
        }
        
        return 0;
    }
    
}


/**
 * 解法二：原地修改 nums，从而实现空间复杂度为 O(1)，时间复杂度为 O(n) 的解法
 *        修改 nums 通常有如下两种做法：
 *          （1）nums[i] = -nums[i]，将 nums[i] 变成负数 
 *          （2）nums[i] = nums[i] + 2 * n，增大 nums[i]，使得 nums[i] 超过 [0, n] 的范围。
 *        注意：这里修改 nums[i] ，实际上为了标记是否有数字 i。如果 nums[i] 被修改过，则含有 i。
 *        在这道题中，只能使用第 2 种方法修改 nums，因为如果 nums = [2,0]，使用第 1 种做法，则 nums = [-2,0]。
 *        然后通过 nums[i] > 0 判断数字是否被修改过时，会误判最后一个 0。 
 */
class _268Solution2 {
    
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 遍历 nums，将 nums[i] 当作下标，然后去修改 nums[nums[i]] 的值，从而标记存在 nums[i] 
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int idx = (nums[i] > n ? nums[i] - 2 * n : nums[i]); // 如果 nums[i] 大于 n，则说明 nums[i] 被修改过，因此需要恢复原始值。
            if (idx < n) { // 因为 idx 可能是 n，所以需要避免下标越界
                nums[idx] = nums[idx] + 2 * n; // 修改 nums[idx]，从而标记存在 idx 
            }
        }
        
        // 重新遍历 nums，寻找没有修改过的 nums[i]
        for (int i = 0; i < n; ++i) {
            if (nums[i] <= n) { // nums[i] 没有被修改过，则说明下标 i 不存在
                return i;
            }
        }
        
        return n;
    }
    
}
