package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 * 
 * 题目描述：给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *         假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *         你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *         
 * 进阶：
 *  （1）如何证明 nums 中至少存在一个重复的数字?
 *  （2）你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 * 
 * 限制条件：
 *  （1）1 <= n <= 10^5
 *  （2）nums.length == n + 1
 *  （3）1 <= nums[i] <= n
 *  （4）nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *  
 * 示例：
 *  示例 1
 *       输入：nums = [1,3,4,2,2]
 *       输出：2
 *       
 *  示例 2
 *      输入：nums = [3,1,3,4,2]
 *      输出：3
 *      
 */
public class _287_FindTheDuplicateNumber {

    public static void main(String[] args) {
        // test case1, output: 2
//        int[] nums = { 1, 3, 4, 2, 2 };
        
        // test case2, output: 3
        int[] nums = { 3, 1, 3, 4, 2 };
        
//        _287Solution1 solution = new _287Solution1();
        
//        _287Solution2 solution = new _287Solution2();
        
//        _287Solution3 solution = new _287Solution3();
        
        _287Solution4 solution = new _287Solution4();
        
        
        System.out.println(solution.findDuplicate(nums));
    }
}

/**
 * 解法一：使用 HashSet （或 HashMap）
 */
class _287Solution1 {
    
    public int findDuplicate(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (set.contains(i)) {
                return i;
            }
            
            set.add(i);
        }
        
        return 0;
    }
    
}


/**
 * 解法二：排序
 */
class _287Solution2 {
    
    public int findDuplicate(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        
        return 0;
    }
    
}


/**
 * 解法三：使用二分搜索
 *        数组中存储的数字范围是 [1, n]，重复的数字也必定是 [1, n] 之内。
 *        原问题是找到这个重复的数字，即相当于是在 [1, n] 中找一个数字。因为 [1, n] 之间的数字是升序的，所以可以考虑使用二分查找。
 *        将中间位置的数字记为 mid，如果重复的数字不在 mid 左侧，则小于等于 mid 的数字的个数最多是 mid。
 *        因为一旦大于 mid，则必定 mid 的左侧有重复的。
 *        若 mid 左侧的数字在数组中不存在重复时，有如下两种情况：
 *          当 [1, mid] 内的数字在数组中都存在，并且没有重复的时候，小于等于 mid 的数字的个数是 mid
 *          当 [1, mid] 内的数字在数组中部分存在，并且没有重复的时候，小于等于 mid 的数字的个数小于 mid
 *        所以如果 mid 左侧的数字在数组中不存在重复时，小于等于 mid 的数字的个数最多是 mid。如果大于 mid，则存在重复。
 *        例如：[1,3,4,2,2]，数字范围是 [1, 4]，mid = 2。nums 中小于等于 mid 的有 3 个，大于 mid，因此 mid 左侧（即 [1, 2]）
 *        的数字在 nums 中有重复的。
 */
class _287Solution3 {
    
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 在 [1, n] 之前寻找重复的数字，n 为 nums.length - 1
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 统计出 nums 中小于等于 mid 的数字个数 
            int count = 0;
            for (int i : nums) {
                if (i <= mid) {
                    ++count;
                }
            }
            
            if (count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        
        return right;
    }
    
}


/**
 * 解法四：将数组表示成链表。若数组中有重复的数字，则必定会形成循环链表，然后使用快慢指针找到环的入口。
 *        如：nums = [1,3,4,2,2]
 *          对应下标： 0,1,2,3,4
 *        因为 nums 中存储的数字，其范围是 [0, n]，所以可以将 nums 中的数字视为下标，这样即可形成如下的链表：
 *                     ---
 *                    /   \
 *        0 - 1 - 3 - 2 - 4 
 *        刚开始，0 指向 nums 中的第一个元素，即 1，然后 1 是下一个元素的下标，则后面连接到 nums[1]（即 3）；
 *        然后 3 将作为下一个元素的下标，则后面又连接到 nums[3]（即 2）；然后 2 将作为下一个元素的下标，则后面
 *        又连接到 nums[2] （即 4）；然后 4 作为下一个元素的下标，则连接到 nums[4]（即 2）。此时，形成一个带环
 *        的链表，在该链表中用快慢指针找到环的入口，即可找到重复的数字。
 *        即对于 nums[i]，其是 nums 中的下标，将 nums[i] 视作当前位置 i 的下一个位置，则形成如下链表形状
 *          i -> nums[i] -> nums[nums[i]]-> ...
 */
class _287Solution4 {
    
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums.length;
        }
        
        // 因为 nums 中数字的范围是 [1, n]，没有超过 nums 的下标范围，所以可以将 nums 中的值视作下标。
        // 即对于 nums[i]，其是 nums 中的下标。如果将 nums[i] 视作当前位置 i 的下一个位置，则可以形成
        // 链表形状（即 i -> nums[i] -> nums[nums[i]]-> ...）。因为 nums 中存在重复的值，因此最终
        // 会形成一个带环的链表。则可以使用快慢指针找到链表中环的入口处，该入口处即为重复的数字。
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        
        // 寻找环的入口处
        fast = 0;
        while (slow != fast) {
            fast = nums[fast];
            slow = nums[slow];
        }
        
        return fast;
    }
    
}
