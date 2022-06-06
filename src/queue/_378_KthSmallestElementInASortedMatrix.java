package queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * 
 * 题目描述：给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 *         请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。
 *         你必须找到一个内存复杂度优于 O(n^2) 的解决方案。
 * 
 * 进阶：
 *  你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
 *  你能在 O(n) 的时间复杂度下解决这个问题吗?
 * 
 * 限制条件：
 *  （1）n == matrix.length
 *  （2）n == matrix[i].length
 *  （3）1 <= n <= 300
 *  （4）-10^9 <= matrix[i][j] <= 10^9
 *  （5）题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
 *  （6）1 <= k <= n^2
 *  
 * 示例：
 *  示例 1
 *      输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
 *      输出：13
 *      解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
 *      
 *  示例 2
 *      输入：matrix = [[-5]], k = 1
 *      输出：-5
 *
 */
public class _378_KthSmallestElementInASortedMatrix {

    public static void main(String[] args) {
        // test case1, output: 13
        int[][] matrix = { { 1, 5, 9 }, 
                           { 10, 11, 13 }, 
                           { 12, 13, 15 } };
        int k = 8;

        // test case2, output: -5
//        int[][] matrix = { { -5 } };
//        int k = 1;
        
//        _378Solution1 soltuion = new _378Solution1();
        
        _378Solution2 soltuion = new _378Solution2();
        
        
        System.out.println(soltuion.kthSmallest(matrix, k));
    }
    
}

/**
 * 解法一：排序
 */
class _378Solution1 {
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int m = matrix.length, n = matrix[0].length;
        int[] arr = new int[m * n]; // 存储 matrix  
        int idx = 0; // arr 中的下标
        
        // 将 matrix 转换成一维数组 arr
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                arr[idx++] = matrix[i][j];
            }
        }
        
        // 对 arr 进行排序
        Arrays.sort(arr);
        
        return arr[k - 1];
    }
    
}


/**
 * 解法二：最大堆
 *       创建一个最大堆，保证堆中元素个数最多只有 k 个，则堆顶元素即为排序后的第 k 小元素
 */
class _378Solution2 {
    
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int m = matrix.length, n = matrix[0].length;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((x, y) -> (y - x)); // 大小为 k 的最大堆
        
        // 将 matrix 中的元素添加到最大堆 maxHeap 中
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (maxHeap.size() < k) {
                    // 当最大堆不足 k 个元素时，直接将 matrix[i][j] 添加进最大堆中
                    maxHeap.add(matrix[i][j]); 
                } else if (matrix[i][j] < maxHeap.peek()) {
                    // 当最大堆已满 k 个元素，并且 matrix[i][j] < 堆顶元素时，则 matrix[i][j] 会将堆顶元素挤出 topK
                    maxHeap.poll(); // 删除堆顶元素
                    maxHeap.add(matrix[i][j]); // 将 matrix[i][j] 添加进堆中
                }
            }
        }
        
        return maxHeap.peek();
    }

}