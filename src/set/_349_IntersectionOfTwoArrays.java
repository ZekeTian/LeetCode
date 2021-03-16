package set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/intersection-of-two-arrays/
 *
 * 给定两个整数数组，计算两个数组的交集结果。
 * 注意：
 * （1）交集结果中的每个元素必须不同
 * （2）交集结果中的元素顺序可以是任意的
 */
public class _349_IntersectionOfTwoArrays {

    public static void main(String[] args) {
        int[] nums1 = { 4, 9, 5 };
        int[] nums2 = { 9, 4, 9, 8, 4 };

        _349Solution2 solution = new _349Solution2();

        int[] result = solution.intersection(nums1, nums2);
        for (int i : result)
            System.out.println(i);
    }
}

/**
 * 求解方式一，用 Set 存储结果，从而确保最终的结果不会重复
 */
class _349Solution1 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> resultSet = new HashSet<Integer>();

        for (int i : nums1) {
            set1.add(i);
        }

        for (int i : nums2) {
            if (set1.contains(i)) {
                resultSet.add(i);
            }
        }

        int[] result = new int[resultSet.size()];
        int idx = 0;
        for (int i : resultSet) {
            result[idx++] = i;
        }
        return result;
    }
}

/**
 * 求解方式二，用 List 存储最终的结果，但是会从 Set 中删除元素，从而确保最终结果不会重复
 */
class _349Solution2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<Integer>();
        List<Integer> resultArr = new ArrayList<Integer>();

        for (int i : nums1) {
            set1.add(i);
        }

        for (int i : nums2) {
            if (set1.contains(i)) {
                resultArr.add(i);
                set1.remove(i); // 因为 i 已经添加到结果中，所以在 set1 中将 i 删除，从而可以确保最终的结果中不会有重复的元素
            }
        }

        int[] result = new int[resultArr.size()];
        for (int i = 0; i < resultArr.size(); ++i) {
            result[i] = resultArr.get(i);
        }
        return result;
    }
}
