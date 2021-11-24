package array;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * 题目描述：
 * 
 * 
 * 条件限制：
 *  （1）nums1.length == m, nums2.length == n
 *  （2）0 <= m <= 1000, 0 <= n <= 1000
 *  （3）1 <= m + n <= 2000
 *  （4）-10^6 <= nums1[i], nums2[i] <= 10^6
 * 
 * 示例：
 *  示例 1
 *      输入：nums1 = [1,3], nums2 = [2]
 *      输出：2.00000
 *      解释：合并数组 = [1,2,3] ，中位数 2
 *  
 *  示例 2
 *      输入：nums1 = [1,2], nums2 = [3,4]
 *      输出：2.50000
 *      解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *  
 */
public final class _4_MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        // test case 1, output: 2.00000
//        int[] nums1 = { 1, 3 }, nums2 = { 2 };

        // test case 2, output: 2.50000
        int[] nums1 = { 1, 2 }, nums2 = { 3, 4 };

        //        _4Solution1 solution = new _4Solution1();

        _4Solution2 solution = new _4Solution2();

        System.out.println(solution.findMedianSortedArrays(nums1, nums2));

    }
}

/**
 * 解法一：暴力法。
 *      先将两个有序数组合并成一个有序数组 nums，然后再根据数组 nums 长度的奇偶性取中位数。
 */
class _4Solution1 {

    // 将 nums1，nums2 数组按照升序合并到 nums 数组中
    private void merge(int[] nums1, int i, int[] nums2, int j, int[] nums, int k) {
        if (i < nums1.length && j >= nums2.length) {
            nums[k] = nums1[i];
            merge(nums1, i + 1, nums2, j, nums, k + 1);
            return;
        }

        if (i >= nums1.length && j < nums2.length) {
            nums[k] = nums2[j];
            merge(nums1, i, nums2, j + 1, nums, k + 1);
            return;
        }

        if (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                nums[k] = nums1[i];
                merge(nums1, i + 1, nums2, j, nums, k + 1);
            } else {
                nums[k] = nums2[j];
                merge(nums1, i, nums2, j + 1, nums, k + 1);
            }
            return;
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        if (0 == nums.length) {
            return 0.0;
        }

        merge(nums1, 0, nums2, 0, nums, 0);
        int mid = nums.length / 2;
        if (0 == nums.length % 2) {
            return (nums[mid - 1] + nums[mid]) / 2.0;
        } else {
            return nums[mid];
        }
    }
}

/**
 * 解法二：使用二分搜索
 *      相当于在 nums1、nums2 合并的数组中寻找第 mid+1 小的数
 */
class _4Solution2 {

    // 在 nums1[left1, right1]、nums2[left2, right2] 合并后的新数组中找到第 k 小的数
    private int getKth(int[] nums1, int left1, int right1, 
                       int[] nums2, int left2, int right2, 
                       int k) {
        // 计算两个数组的有效长度
        int len1 = right1 - left1 + 1;
        int len2 = right2 - left2 + 1;

        // 判断数组是否有效
        if (0 == len1) {
            return nums2[left2 + k - 1]; // nums1 数组已经处理完，则直接在 nums2 数组中取出第 k 小的数即可
        }
        if (0 == len2) {
            return nums1[left1 + k - 1];
        }
        if (1 == k) { // 取 nums1、nums2 中第 1 小的数（即取最小的数），直接取 nums1 首元素、nums2 首元素中的最小值即可
            return Math.min(nums1[left1], nums2[left2]); // 此时，nums1、nums2 的下标不是从 0 开始，而是从 left1、left2 开始
        }

        // 计算出 nums1、nums2 中第 k/2 处的位置。因为数组可能比较短，元素个数少于 k/2，所以用 Math.min(len1, k / 2) 取两者中的较小者
        int i = left1 + Math.min(len1, k / 2) - 1;
        int j = left2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] < nums2[j]) { 
            // 因为 nums1[i] < nums2[j]，则 nums1 中 nums1[left1, i] 区间的元素不可能含有第 k 小的元素，直接丢弃 nums1[left1, i] 区间
            return getKth(nums1, i + 1, right1, nums2, left2, right2, k - (i - left1 + 1)); // 因为丢弃了 nums1[left1, i] 区间的元素，所以 k 相应减少
        } else {
            // 丢弃 nums2[left2, j] 区间
            return getKth(nums1, left1, right1, nums2, j + 1, right2, k - (j - left2 + 1));
        }
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mid = (nums1.length + nums2.length) / 2;
        if (0 == (nums1.length + nums2.length) % 2) { // nums1、nums2 的总长度为偶数，则取两个数的平均（第 mid 小和第 mid+1 小这两个数的平均值）
            return (getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, mid)
                    + getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, mid + 1)) / 2.0;
        } else { // nums1、nums2 的总长度为奇数时，mid 为中位数的下标，也就是要找第 mid+1 小的数
            return getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, mid + 1);
        }
    }
}
