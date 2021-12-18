package backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-watch/
 * 
 * 题目描述：二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或 1，最低位在右侧。
 *       给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 *       小时不会以零开头：
 *          例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 *       分钟必须由两位数组成，可能会以零开头：
 *          例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 * 
 * 限制条件：0 <= turnedOn <= 10
 *  
 *  
 * 示例：
 *  示例 1
 *      输入：turnedOn = 1
 *      输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 *      
 *  示例 2
 *      输入：turnedOn = 9
 *      输出：[]
 *      
 */
public class _401_BinaryWatch {

    public static void main(String[] args) {
        // test case1
//        int turnedOn = 2;
        
        // test case2
        int turnedOn = 9;
        
        _401Solution1 solution = new _401Solution1();
        
        
        System.out.println(solution.readBinaryWatch(turnedOn));
    }
}

/**
 * 解法一：回溯法
 */
class _401Solution1 {
    
    private int[] hours = { 1, 2, 4, 8 };
    private int[] minutes = { 1, 2, 4, 8, 16, 32 };
    private final int HOURS_MAX_NUM = 3; // 时钟最多的亮灯数量
    private final int MINUTES_MAX_NUM = 5; // 分钟最多的亮灯数量
    private List<String> resultList = null;
    
    // 在 values[index...len-1] 中选择 turnedOn 个数，然后将和添加到 list 中
    private List<Integer> tryRead(int[] values, int turnedOn, int v, int index, List<Integer> list) {
        if (turnedOn < -1) {
            return list;
        }
        if (turnedOn == 0) {
            list.add(v);
            return list;
        }
        
        for (int i = index; i < values.length; ++i) {
            v += values[i];
            tryRead(values, turnedOn - 1, v, i + 1, list);
            v -= values[i];
        }
        
        return list;
    }
    
    // 读取时钟
    private List<Integer> tryReadHours(int turnedOn, int h, int index, List<Integer> list) {
        return tryRead(hours, turnedOn, h, index, list);
    }
    
    // 读取分钟
    private List<Integer> tryReadMinutes(int turnedOn, int m, int index, List<Integer> list) {
        return tryRead(minutes, turnedOn, m, index, list);
    }

    private void tryReadBinaryWatch(int hTurnOn, int mTurnOn) {
        List<Integer> hList = tryReadHours(hTurnOn, 0, 0, new ArrayList<>());
        List<Integer> mList = tryReadMinutes(mTurnOn, 0, 0, new ArrayList<>());
        
        for (Integer h : hList) {
            for (Integer m : mList) {
                if (h <= 11 && m <= 59) {
                    resultList.add(String.format("%d:%02d", h, m));
                }
            }
        }
    }
    
    public List<String> readBinaryWatch(int turnedOn) {
        this.resultList = new ArrayList<>();
        if (turnedOn > HOURS_MAX_NUM + MINUTES_MAX_NUM) {
            return resultList; // 超过最大的亮灯数量，则当前输入无法得到正确解
        }
        for (int i = 0; i <= HOURS_MAX_NUM; ++i) {
            if (turnedOn < i) {
                break;
            }
            tryReadBinaryWatch(i, turnedOn - i);
        }
        
        return resultList;
    }
}

