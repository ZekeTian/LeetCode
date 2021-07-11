package queue;

import java.util.LinkedList;

/**
 *  https://leetcode.com/problems/perfect-squares/
 *  
 *  题目描述：给定一个整数 n，n 可以由 m 个完全平方数累加得到，试求出 m 的最小值，即求出 n 最少需要多少个完全平方数累加才能得到。
 *         完全平方数是指一个整数的平方，如：1，4，9，16 是完全平方数，而 3，11 不是。
 *  
 *  条件限制：1 <= n <= 10^4
 *
 */
public class _279_PerfectSquares {

    public static void main(String[] args) {
        //        int n = 12;
        int n = 13;
        //        int n = 1;
        _279Solution1 solution = new _279Solution1();

        System.out.println(solution.numSquares(n));
    }
}

/**
 * 将问题转换成求无权图的最短路径，然后利用队列辅助求解。即将 0、1、2、……、n 这 n+1 个数组成一个图，只要其中一个数与另外一个数之间相差一个完全平方数，则这两个数在图之间存在一条边。
 * 如：5 和 1 之间相差 4 （完全平方数），则在图中 5、1 之间存在一条边；同时，5 和 4 之间相差 1 （完全平方数），则在图中 5 和 4 之间存在一条边。
 *
 */
class _279Solution1 {
    private class Pair {
        int num;
        int step; // 在图中，从 n（输入） 到 num 需要的步数 

        Pair(int num, int step) {
            this.num = num;
            this.step = step;
        }
    }

    public int numSquares(int n) {
        LinkedList<Pair> queue = new LinkedList<Pair>();
        int[] visited = new int[n + 1]; //  visited[i] 标记图中 i 这个点是否被访问到
        queue.add(new Pair(n, 0));

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            int num = poll.num;
            int step = poll.step;

            for (int i = 1; num - i * i >= 0; ++i) {
                if (0 == num - i * i) { // num - i*i = 0，相当于已经把 n 完全分解成多个完全平方数之和（实际上即 step+1 个）
                    return step + 1;
                }

                if (0 == visited[num - i * i]) { // 在图中，从 n 到 num - i*i 会存在多条路径，因此 num - i*i 这个点会被多次访问到。
                                                 // 但是实际上只有第一次访问时对应的路径才是最短的，所以后面再次访问到 num - i*i 时不需要再入队
                    queue.add(new Pair(num - i * i, step + 1)); // 在图中，从 num 到 num - i*i，步数 +1
                    visited[num - i * i] = 1;
                }
            }
        }

        return 0;
    }
}
