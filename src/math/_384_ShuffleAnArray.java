package math;

import java.util.Arrays;
import java.util.Random;

/**
 * https://leetcode.com/problems/shuffle-an-array/
 * 
 * 题目描述：给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。打乱后，数组的所有排列应该是 等可能 的。
 *         实现 Solution class:
 *          （1）Solution(int[] nums) 使用整数数组 nums 初始化对象
 *          （2）int[] reset() 重设数组到它的初始状态并返回
 *          （3）int[] shuffle() 返回数组随机打乱后的结果
 *          
 * 限制条件：
 *  （1）1 <= nums.length <= 200
 *  （2）-10^6 <= nums[i] <= 10^6
 *  （3）nums 中的所有元素都是 唯一的
 *  （4）最多可以调用 5 * 10^4 次 reset 和 shuffle
 * 
 * 示例：
 *  输入：
 *      ["Solution", "shuffle", "reset", "shuffle"]
 *      [[[1, 2, 3]], [], [], []]
 *  输出：[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *  解释：
 *      Solution solution = new Solution([1, 2, 3]);
 *      solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
 *      solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
 *      solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 *      
 */
public class _384_ShuffleAnArray {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3 };
        
        
        _384Solution solution = new _384Solution(nums);
        
        System.out.println("shuffle 前；" + Arrays.toString(nums));
        System.out.println("shuffel 后：" + Arrays.toString(solution.shuffle()));
        System.out.println("reset 后：" + Arrays.toString(solution.reset()));
        System.out.println("shuffle 后：" + Arrays.toString(solution.shuffle()));
    }
}

/**
 * Knuth 洗牌算法
 * 对于数组 nums，遍历 nums，然后进行如下操作：
 *  当前位置为 i，从 [i...nums.length-1] 中随机选择一个下标 randomIdx
 *  交换 nums[i] 和 nums[randomIdx]
 */
class _384Solution {
    
    private int[] nums = null;
    private Random random = null;
    
    public _384Solution(int[] nums) {
        this.nums = nums;
        this.random = new Random();
    }
    
    public int[] reset() {
        return nums;
    }
    
    public int[] shuffle() {
        // 为了保护原始的数组 nums，需要将 nums 复制一份出来，然后再对 nums 的副本进行 shuffle
        int[] copyNums = nums.clone();
        for (int i = 0; i < copyNums.length; ++i) {
            // 从 [i ... copyNums.length-1] 中随机选择一个
            int randomIdx = i + random.nextInt(copyNums.length - i);
            
            // 交换 copyNums[i] 和 copyNums[randomIdx]
            int tmp = copyNums[i];
            copyNums[i] = copyNums[randomIdx];
            copyNums[randomIdx] = tmp;
        }
        
        return copyNums;
    }
}
