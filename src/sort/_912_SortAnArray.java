package sort;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/sort-an-array/
 * 
 * 题目描述：给定一个整数数组 nums，将其按照升序进行排序
 * 
 * 条件限制：
 *  （1）1 <= nums.length <= 5 * 10^4
 *  （2）-5 * 10^4 <= nums[i] <= 5 * 10^4
 */
public class _912_SortAnArray {

    public static void main(String[] args) {
        int[] nums = { 5, 2, 3, 1 };

        //        _912Solution1 solution = new _912Solution1();
        //        _912Solution2 solution = new _912Solution2();
        _912Solution6 solution = new _912Solution6();

        System.out.println(Arrays.toString(solution.sortArray(nums)));

    }
}

/**
 * 解法一：直接调用库函数
 */
class _912Solution1 {

    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }
}

/**
 * 解法二：手动实现快速排序，平均时间复杂度：O(N*logn)
 */
class _912Solution2 {

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        // 先分区，如果是升序，则小的放在参考值左边，大的放在参考值右边；如果是降序，则大的放在左边，小的放右边
        int index = ascPartition(nums, left, right);
        //        int index = descPartition(nums, left, right);

        // 然后以参考值为中心，在其左右两个区间内再进行快排
        quickSort(nums, left, index - 1);
        quickSort(nums, index + 1, right);
    }

    /**
     * 降序分区，即最终得到的顺序是降序（将大的放在参考值左边，小的放在参考值右边）
     */
    private int descPartition(int[] nums, int left, int right) {
        int l = left;
        int r = right;
        int pivot = nums[left];

        while (l < r) {
            // 左指针找第一个小于参考值的数
            while (nums[l] >= pivot && l < right) {
                ++l;
            }

            // 右指针找第一个大于参考值的数
            while (nums[r] <= pivot && r > left) {
                --r;
            }

            // 交换左右指针的数，使得左右指针对应的数处于正确的位置
            if (l < r) {
                swap(nums, l, r);
            }
        }

        // 将参考值放在正确的位置
        swap(nums, left, r);
        return r;
    }

    /**
     * 升序分区，即最终得到的顺序是升序（将小的放在参考值左边，大的放在参考值右边）
     */
    private int ascPartition(int[] nums, int left, int right) {
        int l = left; // 左指针
        int r = right; // 右指针
        int pivot = nums[left]; // 选择最左边的为参考值

        while (l < r) {
            // 左指针寻找第一个大于参考值的数
            while (nums[l] <= pivot && l < right) {
                ++l;
            }

            // 右指针寻找第一个小于参考值的数
            while (nums[r] >= pivot && r > left) {
                --r;
            }

            // 交换左右指针对应的数，从而使得左右指针两个数放在正确的位置
            if (l < r) {
                swap(nums, l, r);
            }
        }

        // 将参考值放在正确的位置
        swap(nums, left, r);
        return r;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int[] sortArray(int[] nums) {

        quickSort(nums, 0, nums.length - 1);

        return nums;
    }
}

/**
 * 解法三：手动实现归并排序
 */
class _912Solution3 {

    public int[] sortArray(int[] nums) {
        return nums;
    }
}

/**
 * 解法四：手动实现希尔排序
 */
class _912Solution4 {

    public int[] sortArray(int[] nums) {
        return nums;
    }
}

/**
 * 解法五：手动实现堆排序
 */
class _912Solution5 {

    public int[] sortArray(int[] nums) {
        return nums;
    }
}

/**
 * 解法六：手动实现冒泡排序，平均时间复杂度：O(N^2)
 */
class _912Solution6 {

    public int[] sortArray(int[] nums) {
        //        for (int i = 0; i < nums.length; ++i) {
        //            for (int j = i + 1; j < nums.length; ++j) {
        //                if (nums[i] > nums[j]) {
        //                    swap(nums, i, j);
        //                }
        //            }
        //        }

        for (int i = 0; i < nums.length - 1; ++i) { // nums.length 个数，只需要进行 nums.length - 1 趟冒泡即可（即 n 个数确定了 n - 1 个数的顺序，则最后一个数自然也就确定了）
            for (int j = 0; j < nums.length - 1 - i; ++j) {
                if (nums[j] > nums[j + 1]) { // 升序
                    //                if (nums[j] < nums[j + 1]) { // 降序
                    swap(nums, j, j + 1);
                }
            }
        }

        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

/**
 * 解法七：手动实现插入排序
 */
class _912Solution7 {

    public int[] sortArray(int[] nums) {
        return nums;
    }
}

/**
 * 解法八：手动实现选择排序
 */
class _912Solution8 {

    public int[] sortArray(int[] nums) {
        return nums;
    }
}