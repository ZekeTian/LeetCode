package array;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/merge-intervals/
 *
 */
public class _56_MergeIntervals {

    public static void main(String[] args) {
//        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals = {{1,4}, {1,5}};
        
        _56Soluion1 solution = new _56Soluion1();
        
        int[][] results = solution.merge(intervals);
        
        for (int i = 0; i < results.length; ++i) {
            System.out.println(Arrays.toString(results[i]));
        }
    }
}

/**
 * 解法一：对区间排序，然后使用 Map 解决
 */
class _56Soluion1 {
    
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
