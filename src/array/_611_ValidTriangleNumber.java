package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/valid-triangle-number/
 * 
 * 题目描述：给定一个包含非负整数的数组 nums ，返回其中可以组成三角形三条边的三元组个数。
 * 
 * 限制条件：
 *  （1）1 <= nums.length <= 1000
 *  （2）0 <= nums[i] <= 1000
 *  
 * 示例：
 *  示例 1
 *      输入: nums = [2,2,3,4]
 *      输出: 3
 *      解释:有效的组合是: 
 *      2,3,4 (使用第一个 2)
 *      2,3,4 (使用第二个 2)
 *      2,2,3
 *
 * 示例 2
 *      输入: nums = [4,2,3,4]
 *      输出: 4
 * 
 */
public class _611_ValidTriangleNumber {

    public static void main(String[] args) {
        // test case1, output: 3
//        int[] nums = { 2, 2, 3, 4 };
        
        // test case2, output: 4
        int[] nums = { 4, 2, 3, 4 };
        
        
//        _611Solution1 soltuion = new _611Solution1();
        
        _611Solution2 soltuion = new _611Solution2();
        
        System.out.println(soltuion.triangleNumber(nums));
        
    }
}

/**
 * 解法一：排序 + 二分搜索
 *       对于三角形的三条边 a、b、c，其必须满足：
 *          a + b > c
 *          a + c > b
 *          b + c > a
 *       假设，a <= b <= c，则 a + c > b 和 b + c > a 一定成立，故现在只需要满足 a + b > c 即可。
 *       为了满足 a <= b <= c，先对 nums 进行排序，然后用 i、j 两个指针表示 a、b 的位置，然后在 [j+1, nums.length-1] 之间寻找最后一个
 *       小于 a + b 的数字 c。假设数字 c 的下标是 k ，则满足 a + b > c 的总共有 k - j 组。
 *       然后不断移动 i、j，直到找到满足所有条件的 a、b、c。
 */
class _611Solution1 {
    
    public int triangleNumber(int[] nums) {
        if (null == nums || nums.length < 3) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        int count = 0; // 有效的三元组个数
        for (int i = 0; i < nums.length - 2 /* 留两个数 b、c */ ; ++i) { // nums[i] 表示 a
            for (int j = i + 1; j < nums.length - 1 /* 留一个数 c */ ; ++j) { // nums[j] 表示 b 
                // 在 [j + 1, nums.length - 1] 之间寻找最后一个小于 nums[i] + nums[j] 的数字 c（下标为 k）
                int left = j + 1, right = nums.length - 1, k = j;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                
                count = count + k - j; // nums[j+1, k] 之间的数字都可以与 nums[i]、nums[j] 组成合法的三元组
            }
        }
        
        return count;
    }
}


/**
 * 解法二：排序 + 双指针
 *       与解法一样，先对数组进行排序。之后，固定最大的数字 c，假设 c 的下标是 i，然后在 [0, i-1] 之间寻找所有可能的 a、b。
 *       假设指针 left、right 分别代表 a、b 的位置，
 *          如果 nums[left] + nums[right] > c，则 nums[left+1] + nums[right] > c、nums[left+2] + nums[right] > c、
 *       nums[left+3] + nums[right] > c、……、nums[right-1] + nums[right] > c 都成立，即总共有 right - left 组。之后，
 *       再将 right 左移，计算下一组。
 *          如果 nums[left] + nums[right] <= c，则需要增大 nums[left]，即 left 右移。
 */
class _611Solution2 {
    
    public int triangleNumber(int[] nums) {
        if (null == nums || nums.length < 3) {
            return 0;
        }
        
        Arrays.sort(nums);
        int count = 0;
        for (int i = nums.length - 1; i >= 2; --i) { // nums[i] 表示 a、b、c 中最大的数字 c
            int left = 0, right = i - 1; // nums[left]、nums[right] 分别表示 a、b
            
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    count = count + right - left; // nums[left...right-1] 之间的数字 和 nums[right] 、nums[i] 都可以组成有效的三元组
                    --right;
                } else {
                    ++left;
                }
            }
        }
        
        return count;
    }
}