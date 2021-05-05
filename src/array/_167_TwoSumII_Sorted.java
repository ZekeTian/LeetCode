package array;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * 题目描述：给定一个已经排序（升序）的整数数组 numbers，在数组中寻找两个数使得它们的和等于指定数 target。
 *        将找到的两个数的下标（从 1 开始）放进数组 answer 中，最后返回 answer。其中， 1 <= asnwer[0] < answer[1] <= numbers.length。
 *        你可以假设每组输入都只有唯一正解的解，并且你不能重复使用同一个整数。
 * 
 * 条件限制：
 *  （1）2 <= numbers.length <= 3 * 10^4
 *  （2）-1000 <= numbers[i] <= 1000
 *  （3）numbers 是按照升序排序的
 *  （4）-1000 <= target <= 1000
 *  （5）只存在唯一正确解
 *  
 * 示例：
 *  Input: numbers = [2,7,11,15], target = 9
 *  Output: [1,2]
 *  Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2
 */
public class _167_TwoSumII_Sorted {

    public static void main(String[] args) {
        //        int[] numbers = { 2, 7, 11, 15 };
        //        int target = 9;

        //        int[] numbers = { -1, 0 };
        //        int target = -1;

        int[] numbers = { 2, 4, 5, 7, 8, 12 };
        int target = 13;

        _167Solution2 solution = new _167Solution2();
        int[] answer = solution.twoSum(numbers, target);

        System.out.println(Arrays.toString(answer));
    }
}

/**
 * 解法一：使用二分搜索
 */
class _167Solution1 {
    public int[] twoSum(int[] numbers, int target) {
        int[] answer = new int[2];

        for (int i = 0; i < numbers.length; ++i) {
            int key = target - numbers[i];
            int idx2 = Arrays.binarySearch(numbers, i + 1, numbers.length, key);

            if (idx2 > 0) {
                answer[0] = i + 1;
                answer[1] = idx2 + 1;

                return answer;
            }
        }

        return answer;
    }
}

/**
 * 解法二：使用对撞指针
 */
class _167Solution2 {
    public int[] twoSum(int[] numbers, int target) {
        int[] answer = new int[2];

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum > target) {
                // sum 偏大，则减小 numbers[right] 的值，使得 sum 的值减小，从而接近 target 的值 
                --right;
            } else if (sum < target) {
                // sum 偏小，则增大 numbers[left] 的值，使得 sum 的值增大，从而接近 target 的值
                ++left;
            } else { // sum = target
                answer[0] = left + 1;
                answer[1] = right + 1;
                return answer;
            }
        }

        return answer;
    }
}