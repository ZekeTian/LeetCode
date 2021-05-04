package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/merge-sorted-array/
 * 题目描述：给定两个排序（升序）的整数数组 nums1 和 nums2，将 nums1、nums2 合并成一个有序的数组。
 *        nums1、nums2 两个数组中的元素个数分别为 m、n。可以假设 nums1 的大小等于 m+n，其有足够的空间存放来自 nums2 中的元素。
 * 
 * 条件限制：
 * （1）nums1.length == m + n
 * （2）nums2.length == n
 * （3）0 <= m,n <= 200
 * （4）1 <= m + n <= 200
 * （5）-10^9 <= nums1[i], nums2[i] <= 10^9
 */
public class _88_MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2 = { 2, 5, 6 };
        _88Solution4 solution = new _88Solution4();
        solution.merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        //        int[] nums1 = { 1 };
        //        int[] nums2 = {};
        //        _88Solution4 solution = new _88Solution4();
        //        solution.merge(nums1, 1, nums2, 0);
        //        System.out.println(Arrays.toString(nums1));
    }
}

/**
 * 解法一：直接排序
 */
class _88Solution1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //        for (int i = 0; i < n; ++i) {
        //            nums1[i + m] = nums2[i];
        //        }

        // 调用 java 的数组复制接口
        System.arraycopy(nums2, 0, nums1, m, n);

        Arrays.sort(nums1);
    }
}

/**
 * 解法二：借助新数组存储结果
 */
class _88Solution2 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < m && j < n) {
            // 根据 nums1[i]、nums2[j] 的大小关系，选择较小者放进新数组中
            if (nums1[i] < nums2[j]) {
                result[k++] = nums1[i++];
            } else if (nums1[i] > nums2[j]) {
                result[k++] = nums2[j++];
            } else {
                result[k++] = nums1[i++];
            }
        }

        // 将 nums1、nums2 中剩余的元素放进新数组中
        while (i < m) {
            result[k++] = nums1[i++];
        }
        while (j < n) {
            result[k++] = nums2[j++];
        }

        for (int idx = 0; idx < result.length; idx++) {
            nums1[idx] = result[idx];
        }
    }
}

/**
 * 解法三：使用数组指针解决（即归并排序中的归并过程）
 *
 */
class _88Solution3 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 将 nums1 中原有的元素移到 nums1 的后面
        for (int i = m + n - 1; i >= n; --i)
            nums1[i] = nums1[i - n];

        int i = n; // 遍历 nums1 的指针，范围 nums1[n, n+m)，[n, i) 表示 nums1 已处理的元素
        int j = 0; // 遍历 nums2 的指针，范围 nums2[0, n)，[0, j) 表示 nums2 已处理的元素
        int k = 0; // 遍历合并后的数组的指针，范围 nums1[0, n+m)，其中 [0, k) 之间表示已经确定顺序的元素

        for (k = 0; k < m + n; ++k) {
            if (i >= m + n) { // nums1 已遍历完
                nums1[k] = nums2[j++]; // 直接将 nums2 中剩余的元素放进 nums1（合并后） 中即可
            } else if (j >= n) { // nums2 已遍历完
                nums1[k] = nums1[i++]; // 直接将 nums1 中剩余的元素放进 nums1（合并后） 中即可
            } else if (nums1[i] < nums2[j]) {
                nums1[k] = nums1[i++]; // 为保持升序，将 nums1 中的元素放进 nums1（合并后）中 
            } else { // nums1[i] >= nums2[j]
                nums1[k] = nums2[j++];
            }
        }
    }
}

class _88Solution4 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1; // 遍历 nums1（向前遍历），[0, i] 表示 nums1 未处理的元素
        int j = n - 1; // 遍历 nums2（向前遍历），[0, j] 表示 nums2 未处理的元素
        int k = m + n - 1; // 遍历合并后的数组，其中 (i, k] 之间表示未确定顺序的元素

        // 从后向前遍历 nums1、nums2 以及合并后的数组
        while (i >= 0 && j >= 0) { // nums1、nums2 均未遍历完
            // 将 nums1、nums2 中较大的元素放进合并后的数组中
            if (nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        // 如果先遍历完 nums1，而 nums2 中还有未遍历完的元素，则将 nums2 中剩余未遍历的元素直接放进合并的数组中即可
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}
