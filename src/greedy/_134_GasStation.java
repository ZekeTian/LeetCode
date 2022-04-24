package greedy;

/**
 * https://leetcode.com/problems/gas-station/
 * 
 * 题目描述：在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 *         你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 *         你从其中的一个加油站出发，开始时油箱为空。
 *         给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。
 *         如果存在解，则 保证 它是 唯一 的。
 *         
 * 限制条件：
 *  （1）gas.length == n
 *  （2）cost.length == n
 *  （3）1 <= n <= 10^5
 *  （4）0 <= gas[i], cost[i] <= 10^4
 * 
 * 示例：
 *  示例 1
 *      输入: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 *      输出: 3
 *      解释:
 *      从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 *      开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 *      开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 *      开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 *      开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 *      开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 *      因此，3 可为起始索引。
 * 
 *  示例 2
 *      输入: gas = [2,3,4], cost = [3,4,3]
 *      输出: -1
 *      解释:
 *      你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 *      我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 *      开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 *      开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 *      你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 *      因此，无论怎样，你都不可能绕环路行驶一周。
 *      
 */
public class _134_GasStation {
    
    public static void main(String[] args) {
        // test case1, output: 3
//        int[] gas = { 1, 2, 3, 4, 5 }, cost = { 3, 4, 5, 1, 2 };
        
        // test case2, output: -1
        int[] gas = { 2, 3, 4 }, cost = { 3, 4, 3 };
        
        
//        _134Solution1 solution = new _134Solution1();
        
        _134Solution2 solution = new _134Solution2();
        
        
        System.out.println(solution.canCompleteCircuit(gas, cost));
    }

}

/**
 * 解法一：从不同的位置不断地尝试
 *       对于一组加油站： i, i+1, ..., j, j+1, ..., n
 *       如果 gas[i] + gas[i+1] + ... + gas[j] + ... + gas[n] >= cost[i] + cost[i+1] + ... + cost[j] + ... + gas[n] ，
 *       则可以跑完所有加油站。反之，则无法跑完所有加油站。
 *       如果从 i 出发，无法跑到加油站 j， 则 gas[i] + gas[i+1] + ... + gas[j] < cost[i] + cost[i+1] + ... + cost[j]
 *       并且，[i, j] 之间的任意一个加油站 x ，都无法跑到加油站 y。证明如下：
 *       gas[x] + gas[x+1] + ... + gas[j] = gas[i] + ... + gas[j] - (gas[i] + ... + gas[x-1])
 *       因为 i 无法跑到 j，但是 i 可以跑到 [i+1...j-1] 之间的任意一个加油站，即 i 可以跑到 x-1，所以有：
 *          gas[i] + ... + gas[j] < cost[i] + ... + cost[j] （根据 i 无法跑到 j 得到）
 *          gas[i] + ... + gas[x-1] >= cost[i] + ... + cost[x-1] （根据 i 可以跑到 x-1 得到）
 *       又因为 gas[x] + gas[x+1] + ... + gas[j] = gas[i] + ... + gas[j] - (gas[i] + ... + gas[x-1])
 *                                              < cost[i] + ... + cost[j] - (cost[i] + ... + cost[x-1])
 *                                              < cost[x] + ... + cost[j]
 *       所以 x 无法到达 j。
 */
class _134Solution1 {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int i = 0;
        while (i < gas.length) {
            int sumGas = 0; // 从位置 i 出发，总共可以加的汽油量
            int sumCost = 0; // 从位置 i 出发，总共消耗的汽油量
            int count = 0; // 从位置 i 出发，可以走到的加油站数量
            
            while (count < gas.length) {
                int next = (i + count) % gas.length; // 下一个加油站
                sumGas += gas[next];
                sumCost += cost[next];
                if (sumCost > sumGas) {
                    break;
                }
                ++count;
            }
            
            if (count == gas.length) {
                return i; // 从位置 i 出发，可以走完所有加油站
            }
            i = i + count + 1; // 从下一个位置开始出发
        }
        
        return -1;
    }
    
}

/**
 * 
 * 解法二：贪心算法
 *        一次循环，记录最少油量的位置
 */
class _134Solution2 {
    
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int remainGas = 0; // 油箱目前剩余的汽油量
        int minGas = Integer.MAX_VALUE; // 油箱剩余的最小汽油量
        int minIdx = 0;
        
        for (int i = 0; i < gas.length; ++i) {
            remainGas = remainGas + gas[i] - cost[i];
            // 寻找到剩余油量的最低点
            if (remainGas < minGas) {
                minGas = remainGas;
                minIdx = i;
            }
        }
        
        return (remainGas >= 0 ? (minIdx + 1) % gas.length : -1);
    }
    
}

