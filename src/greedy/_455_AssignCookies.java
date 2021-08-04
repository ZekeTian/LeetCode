package greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/assign-cookies/
 *
 * 题目描述：现有一群孩子和一些饼干，你需要将饼干分给这些孩子。但是，每个孩子你最多只能给一块饼干。
 *        每个孩子 i 有一个贪心指数 g[i]，孩子 i 分到的饼干大小至少要为 g[i]，孩子 i 才会满意；每块饼干有一个大小 s[j]。
 *        如果 s[j] >= g[i]，我们可以将饼干 j 分给孩子 i，并且孩子 i 将会满意。你的目标是尽量让更多的孩子满意，并输出孩子最多的数量。
 * 
 * 条件限制：
 *  （1） 1 <= g.length <= 3 * 10^4
 *  （2） 0 <= s.length <= 3 * 10^4
 *  （3） 1 <= g[i], s[j] <= 2^31 - 1
 *  
 * 示例：
 *  示例1：
 *      Input： g = [1,2,3], s = [1,1]
 *      Output：1
 *      解释：拥有的饼干只能让孩子 0 满足，所以能够让孩子满足的最大数量是 1。
 *  
 *  示例2：
 *      Input： g = [1,2], s = [1,2,3]
 *      Output： 2
 *      解释：拥有的饼干可以让孩子 0、1 满足，所以能够让孩子满足的最大数量是 2。
 */
public class _455_AssignCookies {

    public static void main(String[] args) {
        // test case1, output: 1
        //        int[] g = {1,2,3};
        //        int[] s = {1, 1};

        // test case2, output: 2
        //        int[] g = { 1, 2 };
        //        int[] s = { 1, 2, 3 };

        // test case3, output:0
        int[] g = { 1, 2 };
        int[] s = {};

        _455Solution solution = new _455Solution();
        System.out.println(solution.findContentChildren(g, s));
    }
}

class _455Solution {
    public int findContentChildren(int[] g, int[] s) {
        // 先对 g、s 进行排序 
        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        // 然后看最大的饼干 j 是否能满足信心指数最大的孩子 i，如果能满足，则分饼干 j 给孩子 i；否则，继续下一个孩子
        int j = s.length - 1;
        for (int i = g.length - 1; i >= 0; --i) {
            if (j < 0) {
                break;
            }

            if (s[j] >= g[i]) {
                ++count;
                --j;
            }
        }

        return count;
    }
}
