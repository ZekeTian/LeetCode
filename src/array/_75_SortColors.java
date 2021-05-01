package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sort-colors/
 * 题目描述：给定 n 个标记颜色的元素数组 nums（颜色有：红、白、蓝），对它们进行排序，使得相同颜色的元素相邻，颜色顺序：红、白、蓝，并且分别用 0、1、2 表示红、白、蓝。
 *
 * 条件限制：
 *  （1）n == nums.length
 *  （2）1 <= n <= 300
 *  （3）nums[i] 只可能是 0、1、2 三个中的一个
 *  
 *  进一步优化：
 *  （1）不使用编程语言提供的排序接口
 *  （2）只遍历一轮 nums，并且空间复杂度为 O(1)
 */

public class _75_SortColors {

    public static void main(String[] args) {
        //        int[] nums = { 2, 0, 2, 1, 1, 0 };
        //        int[] nums = { 2, 0, 1 };
        int[] nums = { 1 };
        _75Solution3 solution = new _75Solution3();
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}

/**
 * 解法一，直接调用 java 的排序接口，对数组 nums 排序
 */
class _75Solution1 {
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }
}

/**
 * 解法二，使用计数排序
 */
class _75Solution2 {
    public void sortColors(int[] nums) {
        int[] colorCount = new int[3]; // 各个颜色出现的次数

        // 统计各个颜色出现的次数
        for (int i : nums) {
            ++colorCount[i];
        }

        // 按照颜色的顺序，重新排放元素
        for (int i = 0; i < colorCount[0]; ++i) {
            nums[i] = 0;
        }
        for (int i = 0; i < colorCount[1]; ++i) {
            nums[colorCount[0] + i] = 1;
        }
        for (int i = 0; i < colorCount[2]; ++i) {
            nums[colorCount[0] + colorCount[1] + i] = 2;
        }
    }
}

/**
 * 解法三：使用三路快排的思路
 */
class _75Solution3 {

    public void sortColors(int[] nums) {
        int zero = -1; // [0, zero] 之间是 0
        int two = nums.length; // [two, n-1] 之间是 2

        for (int i = 0; i < two; /* empty */) {
            if (1 == nums[i]) {
                ++i;
            } else if (2 == nums[i]) {
                // 交换 nums[i]、nums[two-1]
                int tmp = nums[i];
                nums[i] = nums[two - 1];
                nums[two - 1] = tmp;
                --two;
            } else if (0 == nums[i]) {
                // 交换 nums[i]、nums[zero+1]
                int tmp = nums[i];
                nums[i] = nums[zero + 1];
                nums[zero + 1] = tmp;
                ++i;
                ++zero;
            }
        }
    }
}