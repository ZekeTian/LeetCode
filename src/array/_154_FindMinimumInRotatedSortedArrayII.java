package array;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/
 * 
 * 题目描述：已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。
 *         例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 *           若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 *           若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 *         注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *         给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。
 *         请你找出并返回数组中的 最小元素 。
 *         你必须尽可能减少整个过程的操作步骤。
 *         
 * 限制条件：
 *  （1）n == nums.length
 *  （2）1 <= n <= 5000
 *  （3）-5000 <= nums[i] <= 5000
 *  （4）nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *  
 * 示例
 *  示例 1
 *       输入：nums = [1,3,5]
 *       输出：1
 *       
 *  示例 2
 *      输入：nums = [2,2,2,0,1]
 *      输出：0
 *      
 */
public class _154_FindMinimumInRotatedSortedArrayII {

    public static void main(String[] args) {
        // test case1, output: 1
//        int[] nums = { 1, 3, 5 };
        
        // test case2, output: 0
        int[] nums = { 2, 2, 2, 0, 1 };
        
        
//        _154Solution1 solution = new _154Solution1();
        
        _154Solution2 solution = new _154Solution2();
        
        
        System.out.println(solution.findMin(nums));
    }
    
}

/**
 * 解法一：直接遍历一遍，获得最小值
 */
class _154Solution1 {
    
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int min = Integer.MAX_VALUE;
        for (int i : nums) {
            min = Math.min(i, min);
        }
        
        return min;
    }
    
}

/**
 * 解法二：二分搜索
 */
class _154Solution2 {
    
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < nums[right]) {
                right = mid; // 因为 nums[mid] 可能就是最小值，所以不能是 right = mid - 1
            } else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else { // nums[mid] == nums[right]，则将 right 左移（因为 nums[right] 和 nums[left] 相等，所以 right 左移也不会丢失 nums[right] 这个值）
                --right;
            }
        }
        
        return nums[right];
    }

}