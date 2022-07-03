package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 * 
 * 题目描述：字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 *         返回一个表示每个字符串片段的长度的列表。
 *         
 * 限制条件：
 *  （1）S的长度在[1, 500]之间。
 *  （2）S只包含小写字母 'a' 到 'z' 。
 *  
 * 示例：
 *  示例 1
 *      输入：S = "ababcbacadefegdehijhklij"
 *      输出：[9,7,8]
 *      解释：
 *          划分结果为 "ababcbaca", "defegde", "hijhklij"。
 *          每个字母最多出现在一个片段中。
 *          像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *  
 *  示例 2
 *      输入：S = "eccbbbbdec"
 *      输出：[10]
 *      
 */
public class _763_PartitionLabels {

    public static void main(String[] args) {
        // test case1, output: [9, 7, 8]
//        String S = "ababcbacadefegdehijhklij";
        
        // test case1, output: [10]
        String S = "eccbbbbdec";
        
        
        _763Solution solution = new _763Solution();
        
        System.out.println(solution.partitionLabels(S));
    }
    
}

/**
 * 思路：
 *  （1）遍历字符串 s，确定各个字符出现的区间
 *  （2）对区间进行排序（按照区间起始位置的升序进行排序）
 *  （3）合并区间（利用第 56 题的思路）
 *  （4）计算合并后区间的长度
 */
class _763Solution {
    
    public List<Integer> partitionLabels(String s) {
        // 遍历字符串 s，确定各个字符出现的区间
        int[][] intervals = new int[26][2]; // 26 个字符，各个字符出现的区间
        for (int i = 0; i < intervals.length; ++i) {
            Arrays.fill(intervals[i], -1); // 区间值默认是 [-1, -1]
        }
        
        for (int i = 0; i < s.length(); ++i) {
            int ch = s.charAt(i) - 'a';
            if (intervals[ch][0] == -1) { // 字符 ch 首次出现
                intervals[ch][0] = intervals[ch][1] = i; // 注意，此时也要对 intervals[ch][1] 赋值，因为该字符可能只会出现一次
            } else { // 字符 ch 不是首次出现，则只需要更新对应区间的右边界即可，即 intervals[ch][1]
                intervals[ch][1] = i;
            }
        }
        
        // 对 intervals 进行排序，便于后面进行区间合并
        Arrays.sort(intervals, (interval1, interval2) -> (interval1[0] - interval2[0]));
        
        // 合并区间
        List<int[]> mergedIntervals = new ArrayList<>();
        for (int[] interval : intervals) {
            // 判断 interval 是否为有效区间。因为在 s 中，可能有部分字符不会出现，则这些未出现的字符对应的区间是无效区间
            if (interval[0] == -1) {
                continue; // 不处理无效区间
            }
            
            if (mergedIntervals.isEmpty() // interval 是第一个，直接放进 mergedIntervals
                    || interval[0] > mergedIntervals.get(mergedIntervals.size() - 1)[1]) { // interval 和 mergedIntervals 中的区间完全独立
                mergedIntervals.add(interval); // interval 无法合并
            } else { 
                int[] last = mergedIntervals.get(mergedIntervals.size() - 1);
                last[1] = Math.max(last[1], interval[1]);
            }
        }
        
        // 计算合并后的各个区间的长度
        List<Integer> resultList = new ArrayList<>();
        for (int[] interval : mergedIntervals) {
            resultList.add(interval[1] - interval[0] + 1);
        }
        
        return resultList;
    }
    
}
