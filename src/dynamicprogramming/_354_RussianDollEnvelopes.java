package dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.com/problems/russian-doll-envelopes/
 * 
 * 题目描述：给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 *        当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *        请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。注意：不允许旋转信封。
 *
 * 限制条件：
 *  （1）1 <= envelopes.length <= 5000
 *  （2）envelopes[i].length == 2
 *  （3）1 <= wi, hi <= 10^4
 * 
 * 示例：
 *  示例 1
 *      输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 *      输出：3
 *      解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *      
 *  示例 2
 *      输入：envelopes = [[1,1],[1,1],[1,1]]
 *      输出：1
 * 
 */
public class _354_RussianDollEnvelopes {

    public static void main(String[] args) {
        // test case1, output: 3
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        
        // test case2, output: 1
//        int[][] envelopes = {{1, 1}, {1, 1}, {1, 1}};
        
        _354Solution solution = new _354Solution();
        
        System.out.println(solution.maxEnvelopes(envelopes));
    }
}

/**
 * 最长上升子序列问题（LIS）
 * 因为要尽可能装更多的信封，所以一开始应尽量选择小的信封，然后逐渐选择大信封。在此过程中，相当于是选择一个上升子序列，所以可以考虑用最长子序列的方法解决。
 * 但是在最长子序列问题中，选择出来的子序列的元素先后顺序和原始序列中元素的先后顺序是一致的（即子序列中，A 在 B 前面，则在原始序列中 A 也在 B 前面）。
 * 在此题中，如果不进行排序处理，选择出来的子序列的元素选择顺序和原始序列中的元素先后顺序不一致，则不能直接用最长上升子序列的方法。
 * 为了能够转换成最长上升子序列问题，可以先对信封进行升序排序。因为选择信封时，是按照升序，而排序后，信封也是升序，所以此时子序列和原始序列的顺序一样，可以使用 LIS 解法。 
 */
class _354Solution {
    
    public int maxEnvelopes(int[][] envelopes) {
        int[] memo = new int[envelopes.length]; // memo[i] 表示以 envelopes[i] 结尾的子序列的长度
//        Arrays.sort(envelopes, new Comparator<int[]>() {
//
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] != o2[0]) {
//                    return o1[0] - o2[0];
//                }
//                
//                return o1[1] - o2[1];
//            }
//        });
        
        // lambda 表达式写法
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        
        Arrays.fill(memo, 1);

        for (int i = 1; i < envelopes.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (envelopes[i][0] > envelopes[j][0] /* 宽度符合要求 */
                        && envelopes[i][1] > envelopes[j][1] /* 高度符合要求 */ ) {
                    memo[i] = Math.max(memo[j] + 1, memo[i]);
                }
            }
        }
        int max = 0;
        for (int i : memo) {
            max = Math.max(max, i);
        }
        
        return max;
    }
}
