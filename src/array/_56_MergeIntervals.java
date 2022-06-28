package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 * 题目描述：以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 *         请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *         
 * 限制条件：
 *  （1）1 <= intervals.length <= 10^4
 *  （2）intervals[i].length == 2
 *  （3）0 <= starti <= endi <= 10^4
 * 
 * 示例：
 *  示例 1
 *      输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 *      输出：[[1,6],[8,10],[15,18]]
 *      解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *      
 *  示例 2
 *      输入：intervals = [[1,4],[4,5]]
 *      输出：[[1,5]]
 *      解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 
 */
public class _56_MergeIntervals {

    public static void main(String[] args) {
        // test case1, output: [[1, 6], [8, 10], [15, 18]]
//        int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        
        // test case2, output: [[1, 5]]
        int[][] intervals = { { 1, 4 }, { 1, 5 } };
        
//        _56Solution1 solution = new _56Solution1();
        
        _56Solution2 solution = new _56Solution2();
        
        int[][] results = solution.merge(intervals);
        
        for (int i = 0; i < results.length; ++i) {
            System.out.println(Arrays.toString(results[i]));
        }
    }
}

/**
 * 解法一：对区间排序，然后使用 Map 解决
 */
class _56Solution1 {
    
    private class Interval implements Comparable<Interval>{
        int start;
        int end;
        
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval o) {
            if (this.start != o.start) {
                return this.start - o.start;
            }
            
            return this.end - o.end;
        }
    }
    
    public int[][] merge(int[][] intervals) {
        TreeSet<Interval> intervalSet = new TreeSet<>();
        for (int i = 0; i < intervals.length; ++i) {
            intervalSet.add(new Interval(intervals[i][0], intervals[i][1]));
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>(); // 保存最终合并的所有区间，key：区间左端点，value：区间右端点
        for (Interval e : intervalSet) {
            Map.Entry<Integer, Integer> entry =  map.floorEntry(e.start); // 在比 e 区间左端点小的区间中，找到左端点最大的区间
            if (null == entry) {
                map.put(e.start, e.end);
            } else {
                if (e.start <= entry.getValue()) { // e 对应的区间与 entry 区间有重合，则合并区间
                    map.put(entry.getKey(), Math.max(e.end, entry.getValue()));
                } else {
                    map.put(e.start, e.end);
                }
            }
        }
        
        // 将 map 的结果保存到 results 中
        int[][] results = new int[map.size()][2];
        Set<Map.Entry<Integer,Integer>> entrySet = map.entrySet();
        int idx = 0;
        for (Map.Entry<Integer, Integer> e : entrySet) {
            results[idx][0] = e.getKey();
            results[idx++][1] = e.getValue();
        }
        
        return results;
    }
}

/**
 * 解法二：对区间进行排序，但是使用 list 解决
 */
class _56Solution2 {
    
    public int[][] merge(int[][] intervals) {
        // 按照区间起始位置的升序，对 intervals 进行排序
        Arrays.sort(intervals, (interval1, interval2) -> (interval1[0] - interval2[0])); // 因为是按照起始位置大小进行排序，所以使用 interval[0] 
        
        // 遍历排序后的 intervals，合并区间
        List<int[]> list =  new ArrayList<>(); // 存储最终合并后的区间
        for (int[] interval : intervals) {
            // 因为 intervals 是有序的，所以如果 interval 能进行合并的话，则其只可能会与 list 中最后一个区间合并。
            // 因此，在这里只需要将 interval 和 list 中最后一个区间进行比较即可
            if (list.isEmpty() // list 为空时，interval 作为第一个区间，直接将其放进 list 中即可
                    || interval[0] > list.get(list.size() - 1)[1]) { // interval 和 list 中最后一个区间无重叠部分，则 interval 可以作为一个新的区间放进 list 中
                list.add(interval);
            } else { // interval 和 list 中最后一个区间可以进行合并
                int[] last = list.get(list.size() - 1);
                last[1] = Math.max(last[1], interval[1]); // 取两个区间的右边界的最大值即可
            }
        }
        
        return list.toArray(new int[list.size()][]);
    }
    
}
