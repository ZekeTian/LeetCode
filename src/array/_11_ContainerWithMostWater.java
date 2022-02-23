package array;

/**
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * 题目描述：给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *        找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。返回容器可以储存的最大水量。
 *        说明：你不能倾斜容器。
 *        
 * 限制条件：
 *  （1）n == height.length
 *  （2）2 <= n <= 10^5
 *  （3）0 <= height[i] <= 10^4
 * 
 * 示例：
 *  输入：[1,8,6,2,5,4,8,3,7]
 *  输出：49 
 *  解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *  
 */
public class _11_ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        
//        _11Solution1 solution = new _11Solution1();

        _11Solution2 solution = new _11Solution2();
        
        
        System.out.println(solution.maxArea(height));
    }
}

/**
 * 解法一：使用双重循环，暴力破解
 */
class _11Solution1 {
    
    public int maxArea(int[] height) {
        if (null == height || height.length == 0) {
            return 0;
        }
        
        int max = 0;
        for (int i = 0; i < height.length; ++i) {
            for (int j = i + 1; j < height.length; ++j) {
                int area = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(max, area);
            }
        }
        
        return max;
    }
}

/**
 * 解法二：使用双指针，思路如下：
 *      （1）初始化两个指针 left、right，分别指向最左边和最右边
 *      （2）开始下面步骤的循环，左右指针不断向中间靠拢，直到相遇时结束循环
 *      （3）计算出 left、right 围出来的面积，并与最大面积进行比较
 *      （4）移动 left、right 中较短的一个短板
 *      
 *      在上述思路中，关键的是第 4 步，为什么该步骤可以确保最终的结果正确？
 *      假设 height[left] < height[right]，进行如下分析：
 *      在移动 left、right 时，底部的高度一定会变小，现在要想办法移动 left、right 时，尽量让面积变大。
 *      （1）如果移动 left：
 *          当 height[left + 1] > height[left] 时，左右两条边中的短边变长，面积可能会变大
 *          当 height[left + 1] <= height[left] 时，左右两条边中的短边变短或不变，面积一定会变小
 *      （2）如果移动 right：
 *          当 height[right - 1] > height[right] 时，左右两条边中的短边保持不变，面积一定变小
 *          当 height[left] < height[right - 1] < height[right] 时，左右两条边中的短边保持不变，面积一定变小
 *          当 height[right - 1] <= height[left] 时，左右两条边中的短边保持不变或变短，面积一定变小
 *      从上面的两种情况可知，移动 right，面积一定会变小，但是移动 left 可能使得面积变大。因此，为了获取最大的面积，我们应当移动 left。
 *      实际上，也就是移动较短的那条边。即我们为了让面积最大，我们应当让长边保持不变，然后移动短边，从而尽可能使得面积增大。
 *      简单来说，移动较短的那条边，就是将一些不可能的情况过滤掉。
 */
class _11Solution2 {
    
    public int maxArea(int[] height) {
        if (null == height || 0 == height.length) {
            return 0;
        }
        
        int max = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Math.min(height[left], height[right]);
            max = Math.max(max, area);
            if (height[left] < height[right]) {
                ++left;
            } else {
                --right;
            }
        }
        
        return max;
    }
}
