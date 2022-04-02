package array;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 * 
 * 题目描述：编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *          （1）每行中的整数从左到右按升序排列。
 *          （2）每行的第一个整数大于前一行的最后一个整数。
 *          
 * 限制条件：
 *  （1）m == matrix.length
 *  （2）n == matrix[i].length
 *  （3）1 <= m, n <= 100
 *  （4）-10^4 <= matrix[i][j], target <= 10^4
 * 
 * 示例：  
 *  示例 1
 *          1   3   5   7
 *          10  11  16  20
 *          23  30  34  60
 *      输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 *      输出：true
 *
 *  示例 2
 *          1   3   5   7
 *          10  11  16  20
 *          23  30  34  60
 *      输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 *      输出：false
 *      
 */
public class _74_SearchA2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = { { 1, 3, 5, 7 }, 
                           { 10, 11, 16, 20 }, 
                           { 23, 30, 34, 60 } };
        
        // test case1, outptu: true
//        int target = 3;
        
        // test case2, outptu: false
        int target = 13;
        
//        _74Solution1 solution = new _74Solution1();

        _74Solution2 solution = new _74Solution2();
        
        
        System.out.println(solution.searchMatrix(matrix, target));
        
    }
}

/**
 * 解法一：暴力求解
 */
class _74Solution1 {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

/**
 * 解法二：对每一行使用二分搜索
 */
class _74Solution2 {
    
    private boolean search(int[] array, int target) {
        int left = 0, right = array.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return true;
            }
            
            if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        
        for (int i = 0; i < matrix.length; ++i) {
            if (search(matrix[i], target)) {
                return true; // 对每行使用二分搜索
            }
        }
        
        return false;
    }
}

/**
 * 解法三：对整个矩阵使用二分搜索（该问题和剑指 Offer 中的第 4 题一样）
 * 
 * 思路：将矩阵逆时针旋转 45 度，然后利用二分搜索的思想进行二分搜索。以如下矩阵为例
 *      1 3 5 6                             6
 *      2 4 7 8    --逆时针旋转 45 度-->      5  8
 *      5 7 8 9                          3  7  9
 *                                      1  4  8
 *                                        2  7
 *                                          5 
 * 将矩阵逆时针旋转 45 度后，右上角的元素作为根顶点，此时旋转后的矩阵相当于是一棵二分搜索树，根顶点左边比根顶点小，根顶点的右边比根顶点大
 */
class _74Solution3 {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        if (null == matrix || matrix.length == 0) {
            return false;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            }
            
            if (matrix[x][y] < target) {
                ++x;
            } else {
                --y;
            }
        }
        
        return false;
    }
}

