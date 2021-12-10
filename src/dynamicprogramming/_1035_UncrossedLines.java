package dynamicprogramming;


/**
 * https://leetcode.com/problems/uncrossed-lines/
 * 
 * 题目描述：在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
 *          （1）nums1[i] == nums2[j]
 *          （2）且绘制的直线不与任何其他连线（非水平线）相交。
 *        请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。以这种方法绘制线条，并返回可以绘制的最大连线数。
 * 
 * 限制条件：
 *  （1）1 <= nums1.length, nums2.length <= 500
 *  （2）1 <= nums1[i], nums2[j] <= 2000
 * 
 * 示例：
 *  示例 1
 *          1  4  2
 *          |   \
 *          |    \
 *          1  2  4
 *      输入：nums1 = [1,4,2], nums2 = [1,2,4]
 *      输出：2
 *      解释：可以画出两条不交叉的线，如上图所示。
 *          但无法画出第三条不相交的直线，因为从 nums1[1]=4 到 nums2[2]=4 的直线将与从 nums1[2]=2 到 nums2[1]=2 的直线相交。
 *  
 *  示例 2
 *      输入：nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
 *      输出：3
 *      
 *  示例 3
 *      输入：nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
 *      输出：2
 */
public class _1035_UncrossedLines {

    public static void main(String[] args) {
        // test case 1, output: 2
//        int[] nums1 = {1, 4, 2}, nums2 = {1, 2, 4};
        
        // test case 2, output: 3
//        int[] nums1 = {2, 5, 1, 2, 5}, nums2 = {10, 5, 2, 1, 5, 2};
        
        // test case 3, output: 2
        int[] nums1 = {1, 3, 7, 1, 7, 5}, nums2 = {1, 9, 2, 5, 1};
        
        
        _1035Solution solution = new _1035Solution();
        
        
        System.out.println(solution.maxUncrossedLines(nums1, nums2));
    }
}

/**
 * 动态规划解法
 * 此题本质上和最长公共子序列一样，以下面的示例进行解释说明：
 *   nums1     1  4  2
 *             |   \
 *             |    \
 *   nums2     1  2  4
 * nums1 相当于是字符串 str1 = "142"，nums2 相当于是字符串 str2 = "124" 
 * str1 和 str2 最长公共子序列是 "14"，即对应上图中的两条线。
 * 由于公共子序列的要求，如果字符串 str1 中，4 在 1 的后面，则在 str2 中，4 必须也要在 1 的后面。正是由于这一特点，使得连线不会彼此相交，
 */
class _1035Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] memo = new int[nums1.length + 1][nums2.length + 1];
        
        for (int i = 1; i <= nums1.length; ++i) {
            for (int j = 1; j <= nums2.length; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i][j - 1], memo[i - 1][j]);
                }
            }
        }
        
        return memo[nums1.length][nums2.length];
    }
}
