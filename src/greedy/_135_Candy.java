package greedy;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/candy/
 * 
 * 题目描述：n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *         你需要按照以下要求，给这些孩子分发糖果：
 *          （1）每个孩子至少分配到 1 个糖果。
 *          （2）相邻两个孩子评分更高的孩子会获得更多的糖果。
 *         请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *         
 * 限制条件：
 *  （1）n == ratings.length
 *  （2）1 <= n <= 2 * 10^4
 *  （3）0 <= ratings[i] <= 2 * 10^4
 *  
 * 示例：
 *  示例 1
 *      输入：ratings = [1,0,2]
 *      输出：5
 *      解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 *      
 *  示例 2
 *      输入：ratings = [1,2,2]
 *      输出：4
 *      解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *           第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 *           
 */
public class _135_Candy {

    public static void main(String[] args) {
        // test case1, output: 5
//        int[] ratings = { 1, 0, 2 };
        
        // test case2, output: 4
//        int[] ratings = { 1, 2, 2 };
        
        // test case3, output: 12
        int[] ratings = { 29, 51, 87, 87, 72, 12 };
        
//        _135Solution1 solution = new _135Solution1();

//        _135Solution2 solution = new _135Solution2();
        
        _135Solution3 solution = new _135Solution3();
        
        
        System.out.println(solution.candy(ratings));
        
    }
}

/**
 * 贪心算法（解法一的实现方式一）
 * 从最低评分的位置处分发糖果。在每个位置分发糖果时，列举出所有可能的情况，然后根据各种情况分发糖果。
 */
class _135Solution1 {
    
    public int candy(int[] ratings) {
        if (null == ratings || ratings.length == 0) {
            return 0;
        }
        
        // 将 ratings 按照评分升序进行排序
        int[][] sortedRatings = new int[ratings.length][2];
        for (int i = 0; i < ratings.length; ++i) {
            sortedRatings[i] = new int[] {ratings[i], i};
        }
        Arrays.sort(sortedRatings, (arr1, arr2) -> (arr1[0] - arr2[0]));
        
        // 从评分最低的开始分糖果
        int[] candyNums = new int[ratings.length];
        int sum = 0;
        for (int i = 0; i < sortedRatings.length; ++i) {
            int idx = sortedRatings[i][1]; // 当前评分对应的原始下标
            int preRating = (idx - 1 >= 0 ? ratings[idx - 1] : Integer.MAX_VALUE); // 上一个位置对应的评分。如果当前位置是左边界，则将上一个评分设为最大值，从而让左边界能够成为谷底
            int curRating = ratings[idx]; // 当前评分
            int nextRating = (idx + 1 < ratings.length ? ratings[idx + 1] : Integer.MAX_VALUE); // 下一个位置对应评分。如果当前位置是右边界，则将下一个评分设为最大值，从而让右边界能够成为谷底
            int preCandy = (idx - 1 >= 0 ? candyNums[idx - 1] : 0); // 上一个位置的糖果
            int nextCandy = (idx + 1 < candyNums.length ? candyNums[idx + 1] : 0); // 下一个位置的糖果
            
             // 总共有 9 种情况（但是在实际实现时，需要对第一种情况进行特殊处理，所以最终有些情况是重复多余的）
             if (curRating <= preRating && curRating <= nextRating) {
                 // 当前位置是谷底，则当前位置可以只发一个糖果。但是此题比较特殊的是，当前位置与左右均相等时，依然可以设置成 1
                 // 如：2, 2, 2。可以全部设置成 1
                 candyNums[idx] = 1; 
             } else if (curRating > preRating && curRating < nextRating) {
                 // 因为是从评分低的开始分糖，所以此种情况下，上一个位置的糖果已经分好。又因为当前位置的评分比上一个位置高，所以要比上一个位置的糖果多一个
                 candyNums[idx] = preCandy + 1;
             } else if (curRating < preRating && curRating > nextRating) {
                 // 因为当前评分比下一个位置的评分要高，所以下一个位置的糖果已经分好。同上一种情况一样，当前位置的糖果要多分一个糖果
                 candyNums[idx] = nextCandy + 1; 
             } else if (curRating > preRating && curRating > nextRating) {
                 // 此处情况下，上一个位置、下一个位置都已经分发好糖果，则从两个位置中取较大值，然后当前位置比最大值多发一个
                 candyNums[idx] = Math.max(preCandy, nextCandy) + 1; 
             } else if (curRating == preRating && curRating > nextRating) {
                 // 因为当前位置和上一个位置的评分一样，所以上一个位置的糖果不会影响当前位置糖果的发放。但是又因为当前位置比下一个位置的评分要高，所以当前位置的糖果要比下一个位置多一个。
                 candyNums[idx] = nextCandy + 1; 
             } else if (curRating == preRating && curRating == nextRating) {
                 // 当前位置和上一个、下一个位置的评分一样，则当前位置可以只分一个糖果。
                 candyNums[idx] = 1;
             } else if (curRating == preRating && curRating < nextRating) {
                 // 因为当前位置的评分要比下一个位置的评分低，所以下一个位置还没有分糖果，则只需要看上一个位置的糖果即可。因为上一个位置的评分和当前位置的评分一样，所以当前位置可以直接设置成 1
                 candyNums[idx] = 1;
             } else if (curRating > preRating && curRating == nextRating) {
                 // 和第 5 种情况（curRating == preRating && curRating > nextRating）类似
                 candyNums[idx] = preCandy + 1;
             } else if (curRating < preRating && curRating == nextRating) {
                 // 和第 7 种情况（curRating == preRating && curRating < nextRating）类似
                 candyNums[idx] = 1;
             }
               
            sum += candyNums[idx];
        }
        
        return sum;
    }
}

/**
 * 贪心算法（解法一的实现方式二，方式一的简化版本）
 *  在实现方式一中，情况 1 包含情况 6、7、9，所以情况 6、7、9 可以省略
 *               情况 2 中（curRating > preRating && curRating < nextRating）
 *                  因为 preRatgin < curRating 并且是按照评分升序来分发糖果，所以前一个位置已经分好糖果，而下一个位置没有分好糖果，
 *                  也就意味着 preCandy > 0，nextCandy = 0，即 preCandy = Math.max(preCandy, nextCnady)
 *               情况 3 中（curRating < preRating && curRating > nextRating）
 *                  同理，nextCandy = Math.max(preCandy, nextCnady)
 *               所以情况 2、3 可以综合成 candyNums[idx] = Math.max(preCandy, nextCnady) + 1，即与情况 4 一样。
 *               情况 5 中（curRating == preRating && curRating > nextRating）
 *                  只需要看下一个位置糖果数量即可，所以只能是：candyNums[idx] = nextCandy + 1;
 *               而情况 8 中（curRating > preRating && curRating == nextRating）和情况 5 类似
 *  综上，实现方式一中的 9 种情况可以简化成如下几种：
 *               if (curRating <= preRating && curRating <= nextRating) { // 原来的情况 1、6、7、9
 *                  candyNums[idx] = 1;
 *               } else if (curRating == preRating && curRating > nextRating) { // 原来的情况 5
 *                  candyNums[idx] = nextCandy + 1;
 *               } else if (curRating > preRating && curRating == nextRating) { // 原来情况 8
 *                  candyNums[idx] = preCandy + 1;
 *               } else { // 原来的情况 2、3、4
 *                  candyNums[idx] = Math.max(preCandy, nextCandy) + 1;
 *               }
 *               
 */
class _135Solution2 {
    
    public int candy(int[] ratings) {
        if (null == ratings || ratings.length == 0) {
            return 0;
        }
        
        // 将 ratings 按照评分升序进行排序
        int[][] sortedRatings = new int[ratings.length][2];
        for (int i = 0; i < ratings.length; ++i) {
            sortedRatings[i] = new int[] {ratings[i], i};
        }
        Arrays.sort(sortedRatings, (arr1, arr2) -> (arr1[0] - arr2[0]));
        
        // 从评分最低的开始分糖果
        int[] candyNums = new int[ratings.length];
        int sum = 0;
        for (int i = 0; i < sortedRatings.length; ++i) {
            int idx = sortedRatings[i][1]; // 当前评分对应的原始下标
            int preRating = (idx - 1 >= 0 ? ratings[idx - 1] : Integer.MAX_VALUE); // 上一个位置对应的评分。如果当前位置是左边界，则将上一个评分设为最大值，从而让左边界能够成为谷底
            int curRating = ratings[idx]; // 当前评分
            int nextRating = (idx + 1 < ratings.length ? ratings[idx + 1] : Integer.MAX_VALUE); // 下一个位置对应评分。如果当前位置是右边界，则将下一个评分设为最大值，从而让右边界能够成为谷底
            int preCandy = (idx - 1 >= 0 ? candyNums[idx - 1] : 0); // 上一个位置的糖果
            int nextCandy = (idx + 1 < candyNums.length ? candyNums[idx + 1] : 0); // 下一个位置的糖果
            
            if (curRating <= preRating && curRating <= nextRating) {
                candyNums[idx] = 1; 
            } else if (curRating == preRating && curRating > nextRating) {
                candyNums[idx] = nextCandy + 1;
            } else if (curRating > preRating && curRating == nextRating) {
                candyNums[idx] = preCandy + 1;
            } else {
                candyNums[idx] = Math.max(preCandy, nextCandy) + 1;
            }
            
            sum += candyNums[idx];
        }
        
        return sum;
    }
}

/**
 * 解法二
 * 对于 A、B 三个评分（A 在前、B 在后），根据题目要求。
 * 若 A > B ，则 A 的糖果数量应当大于 B 的糖果数量，为了使得糖果数量最少，可以让 A 糖果数量 = B 糖果数量 + 1，将之称为右规则；
 * 若 A < B ，则 A 的糖果数量应当小于 B 的糖果数量，为了使得糖果数量最少，可以让 B 糖果数量 = A 糖果数量 + 1，将之称为左规则；
 * 
 * A、B、C 三个评分，如果 B > A，则 B 糖果数量 = A 糖果数量 + 1；C > B，则 C 糖果数量 = B 糖果数量 + 1，对应左规则；（即从左向右遍历）
 *                如果 B > C，则 B 糖果数量 = C 糖果数量 + 1；A > B，则 A 糖果数量 = B 糖果数量 + 1，对应右规则；（即从右向左遍历）
 * 
 * 因为左右两个规则都要满足，所以需要从左向右、从右向左各自遍历一遍 ratings，并计算出两种情况需要的糖果数量。
 * 为了同时满足两个规则，所以要取两种规则对应糖果数量的最大值，作为最终的糖果数量。
 *    
 */
class _135Solution3 {
    
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int[] leftCandy = new int[ratings.length]; // 满足左规则时，各个位置需要的糖果数量
        int[] rightCandy = new int[ratings.length]; // 满足右规则时，各个位置需要的糖果数量
        Arrays.fill(leftCandy, 1); // 每个位置默认最少为 1 个糖果
        Arrays.fill(rightCandy, 1); // 每个位置默认最少为 1 个糖果
        // 从左向右开始遍历，设置 leftCandy
        for (int i = 1; i < ratings.length; ++i) {
            if (ratings[i] > ratings[i - 1]) {
                leftCandy[i] = leftCandy[i - 1] + 1;
            }
        }
        
        // 将 leftCandy、rightCandy 最后一个位置的最大值作为 sum 的初始值
        int sum = Math.max(leftCandy[ratings.length - 1], 1);
        // 从右向左遍历，设置 rightCandy 的值，同时计算出 sum
        for (int i = ratings.length - 2; i >= 0; --i) {
            if (ratings[i] > ratings[i + 1]) {
                rightCandy[i] = rightCandy[i + 1] + 1;
            }
            
            sum += Math.max(rightCandy[i], leftCandy[i]); // 将 leftCandy 和 rightCandy 中的最大值作为最终糖果数量
        }
        
        return sum;
    }
    
}


