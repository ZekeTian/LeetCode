package math;

import java.util.Random;

/**
 * https://leetcode.com/problems/random-pick-with-weight/
 * 
 * 题目描述：给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。
 *         请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。
 *         选取下标 i 的 概率 为 w[i] / sum(w) 。
 *         例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），
 *         而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 *         
 * 限制条件：
 *  （1）1 <= w.length <= 10^4
 *  （2）1 <= w[i] <= 10^5
 *  （3）pickIndex 将被调用不超过 10^4 次   
 *  
 * 示例：
 *  示例 1
 *      输入：
 *          ["Solution","pickIndex"]
 *          [[[1]],[]]
 *      输出：
 *          [null,0]
 *      解释：
 *          Solution solution = new Solution([1]);
 *          solution.pickIndex(); // 返回 0，因为数组中只有一个元素，所以唯一的选择是返回下标 0。
 *   
 * 示例 2
 *      输入：
 *          ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 *          [[[1,3]],[],[],[],[],[]]
 *      输出：
 *          [null,1,1,1,1,0]
 *      解释：
 *          Solution solution = new Solution([1, 3]);
 *          solution.pickIndex(); // 返回 1，返回下标 1，返回该下标概率为 3/4 。
 *          solution.pickIndex(); // 返回 1
 *          solution.pickIndex(); // 返回 1
 *          solution.pickIndex(); // 返回 1
 *          solution.pickIndex(); // 返回 0，返回下标 0，返回该下标概率为 1/4 。
 *          
 *          由于这是一个随机问题，允许多个答案，因此下列输出都可以被认为是正确的:
 *          [null,1,1,1,1,0]
 *          [null,1,1,1,1,1]
 *          [null,1,1,1,0,0]
 *          [null,1,1,1,0,1]
 *          [null,1,0,1,0,0]
 *          ......
 *          诸若此类。
 *
 */
public class _528_RandomPickWithWeight {

    public static void main(String[] args) {
        // test case1
//        int[] w = { 1 };
//        _528Solution1 solution = new _528Solution1(w);
//        
//        System.out.println(solution.pickIndex());
        
        
        // test case2
        int[] w = { 1, 3 };
        _528Solution1 solution = new _528Solution1(w);
        
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        System.out.println(solution.pickIndex());
        
    }
    
}

/**
 * 解法一：前缀和
 * 思路：
 *  （1）计算出权重数组 w 的前缀和 prefixSum 、所有权重的总和 total
 *  （2）随机生成一个 [1, total] 之间的权重值 randomWeight
 *  （3）遍历 prefixSum ，如果 randomWeight <= prefixSum[i]，则选择数组 w 中的第 i-1 个位置
 *  
 */
class _528Solution1 {
    
    private int[] prefixSum = null; // 前缀和
    private int total = 0; // 权重的总和
    private Random random = null;
    
    public _528Solution1(int[] w) {
        this.prefixSum = new int[w.length + 1];
        this.random = new Random();
        
        // 计算前缀和、总权重
        for (int i = 1; i <= w.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + w[i - 1];
            total += w[i - 1];
        }
    }
    
    public int pickIndex() {
        int randomWeight = random.nextInt(total) + 1; // 随机生成一个 [1, total] 之间的值
        
        // 遍历 prefixSum，确定位置
        for (int i = 1; i < prefixSum.length; ++i) {
            if (randomWeight <= prefixSum[i]) {
                return i - 1;
            }
        }
        
        return 0;
    }
    
}


/**
 * 解法二：前缀和 + 二分搜索
 *       在解法一中，遍历 prefixSum 寻找 randomWeight <= prefixSum[i] 的过程实际上可以进一步优化。
 *       因为 1 <= w[i] <= 10^5，所以 prefixSum 是升序，故可以对prefixSum 执行二分搜索，而该过程实际上
 *       相当于是 lowerBound ，即寻找第一个大于等于 randomWeight 的权重。
 */
class _528Solution2 {
    
    private int[] prefixSum = null; // 前缀和
    private int total = 0; // 权重总和
    private Random random = null;
    
    public _528Solution2(int[] w) {
        this.prefixSum = new int[w.length + 1];
        this.random = new Random();
        
        for (int i = 1; i <= w.length; ++i) {
            this.prefixSum[i] = this.prefixSum[i - 1] + w[i - 1];
            this.total += w[i - 1];
        }
    }
    
    public int pickIndex() {
        // 随机生成一个 [1, total] 之间的权重 
        int randomWeight = random.nextInt(total) + 1;
        
        // 在 prefixSum 中寻找第一个大于等于 randomWeight 的权重 
        int left = 0, right = prefixSum.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (randomWeight <= prefixSum[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return (left < prefixSum.length ? left - 1 : 0); // 因为 left 是 prefixSum 中的下标，所以转换成 w 的下标时，还需要 -1
    }
    
}

