package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/non-overlapping-intervals/
 * 
 * 题目描述：给定一个区间数组 intervals，其中 intervals[i] = [start_i, end_i]。
 *        你现在要在这个区间数组中删除一些区间，使得区间数组中的区间不重叠。请问，你至少需要删除多个区间？
 *
 * 条件限制：
 *  （1）1 <= intervals.length <= 10^5
 *  （2）intervals[i].length == 2
 *  （3）-5 * 10^4 <= start_i < end_i <= 5 * 10^4
 * 
 */
public class _435_NonOverlappingIntervals {

    public static void main(String[] args) {
        // test case 1, output: 1
        //        int[][] intervals = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };

        // test case 2, output: 2
        //        int[][] intervals = { { 1, 2 }, { 1, 2 }, { 1, 2 } };

        // test case 3, output: 0 
        int[][] intervals = { { 1, 2 }, { 2, 3 } };

        //        _435Solution1 solution = new _435Solution1();

        _435Solution2 solution = new _435Solution2();

        System.out.println(solution.eraseOverlapIntervals(intervals));
    }
}

/**
 * 解法一：递归
 */
class _435Solution1 {

    private class Pair {
        int start;
        int end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private List<Pair> sortedIntervals = null;
    private List<Pair> putedIntervals = null; // 可以放进去的区间

    // 尝试放 sortedIntervals[i] 区间
    private int tryPut(int i) {
        if (i == sortedIntervals.size()) {
            return putedIntervals.size();
        }

        // 不放 sortedIntervals[i]
        int num = tryPut(i + 1);

        // 放 sortedIntervals[i]
        if (putedIntervals.size() == 0
                || sortedIntervals.get(i).start >= putedIntervals.get(putedIntervals.size() - 1).end) {

            putedIntervals.add(sortedIntervals.get(i));
            num = Math.max(num, tryPut(i + 1));
            putedIntervals.remove(putedIntervals.size() - 1);
        }

        return num;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        sortedIntervals = new ArrayList<Pair>();
        putedIntervals = new ArrayList<Pair>();
        for (int i = 0; i < intervals.length; ++i) {
            sortedIntervals.add(new Pair(intervals[i][0], intervals[i][1]));
        }

        Collections.sort(sortedIntervals, new Comparator<Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {

                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                }

                return o1.end - o2.end;
            }
        });

        return intervals.length - tryPut(0);
    }
}

/**
 * 解法二：动态规范
 */
class _435Solution2 {

    private class Pair {
        int start;
        int end;

        Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private List<Pair> sortedIntervals = null;
    private int[] memo = null; // memo[i] 表示在尝试放 sortedIntervals[i] 区间时，最多能够放入的区间的个数

    public int eraseOverlapIntervals(int[][] intervals) {
        sortedIntervals = new ArrayList<Pair>();
        memo = new int[intervals.length];
        Arrays.fill(memo, 1); // 最开始，至少所有的区间把可以把自己这一个区间放进去，所以初始值为 1

        for (int i = 0; i < intervals.length; ++i) {
            sortedIntervals.add(new Pair(intervals[i][0], intervals[i][1]));
        }

        Collections.sort(sortedIntervals, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.start != o2.start) {
                    return o1.start - o2.start;
                }

                return o1.end - o2.end;
            }
        });

        for (int i = 1; i < sortedIntervals.size(); ++i) {
            for (int j = 0; j < i; ++j) {
                if (sortedIntervals.get(i).start >= sortedIntervals.get(j).end) {
                    memo[i] = Math.max(memo[i], memo[j] + 1);
                } else {
                    memo[i] = Math.max(memo[i], memo[j]);
                }
            }
        }

        int max = -1;
        for (int i = 0; i < memo.length; ++i) {
            max = Math.max(max, memo[i]);
        }

        return intervals.length - max;
    }
}
