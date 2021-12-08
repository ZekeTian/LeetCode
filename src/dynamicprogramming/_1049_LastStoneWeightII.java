package dynamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/last-stone-weight-ii/
 * 
 * 题目描述：有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 *        每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *          （1）如果 x == y，那么两块石头都会被完全粉碎；
 *          （2）如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 *        最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 *        
 * 限制条件：
 *  （1）1 <= stones.length <= 30
 *  （2）1 <= stones[i] <= 100
 *  
 * 示例：
 *  示例 1
 *      输入：stones = [2,7,4,1,8,1]
 *      输出：1
 *      解释：
 *         组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 *         组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 *         组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 *         组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *  
 *  示例 2
 *      输入：stones = [31,26,33,21,40]
 *      输出：5
 *  
 *  示例 3
 *      输入：stones = [1,2]
 *      输出：1
 */
public class _1049_LastStoneWeightII {

    public static void main(String[] args) {
        // test case 1, output: 1
//        int[] stones = {2, 7, 4, 1, 8, 1};
        
        // test case 2, output: 5
//        int[] stones = {31, 26, 33, 21, 40};
        
        // test case 3, output: 1
        int[] stones = {1, 2};
        
        _1049Solution1 soltuion = new _1049Solution1();
        
        
        System.out.println(soltuion.lastStoneWeightII(stones));
    }
}

/**
 * 解法一：暴力法（递归回溯）
 */
class _1049Solution1 {

    
    private int min = Integer.MAX_VALUE;
    
    private void getLastWeight(List<Integer> list, int num) {
        if (1 == num) { // 只有一个有效的数字，则递归结束
            // 因为 list 中此时只有一个数是非零，所以 list 中数字之和即为那个非零数，也就是最后一个石头的重量
            int sum = 0;
            for (int i : list) {
                sum += i;
            }
            min = Math.min(sum, min);
            return;
        }
        
        for (int i = 0; i < list.size(); ++i) {
            if (0 == list.get(i)) {
                continue; // 当前石头重量是 0，则当前石头实际无效，无需处理
            }
            // 当前石头与后面的石头两两比较
            for (int j = i + 1; j < list.size(); ++j) {
                if (0 == list.get(j)) {
                    continue;
                }
                
                int x = list.get(i);
                int y = list.get(j);
                // i、j 两块石头的三种情况分别进行处理
                if (x > y) {
                    list.set(j, 0);
                    list.set(i, x - y);
                    getLastWeight(list, num - 1);
                } else if (y > x ) {
                    list.set(i, 0);
                    list.set(j, y - x);
                    getLastWeight(list, num - 1);
                } else {
                    list.set(i, 0);
                    list.set(j, 0);
                    getLastWeight(list, num - 2);
                }
                // 当前处理完毕之后，恢复原始值
                list.set(i, x); 
                list.set(j, y);
            }
        }
    }
    
    public int lastStoneWeightII(int[] stones) {
        List<Integer> list = new ArrayList<>();
        for (int i : stones) {
            list.add(i);
        }
        
        getLastWeight(list, stones.length);
        
        return min;
    }
}


/**
 * 解法二：转换成 01 背包问题，然后使用动态规划解决
 */
class _1049Solution2 {
    public int lastStoneWeightII(int[] stones) {
        
        return 0;
    }
}
