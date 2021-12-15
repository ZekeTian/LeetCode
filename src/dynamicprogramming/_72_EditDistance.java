package dynamicprogramming;

/**
 * https://leetcode.com/problems/edit-distance/
 * 
 * 题目描述：给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *        你可以对一个单词进行如下三种操作：
 *          插入一个字符
 *          删除一个字符
 *          替换一个字符
 * 
 * 限制条件：
 *  （1）0 <= word1.length, word2.length <= 500
 *  （2）word1 和 word2 由小写英文字母组成
 * 
 * 示例：
 *  示例 1
 *      输入：word1 = "horse", word2 = "ros"
 *      输出：3
 *      解释：
 *          horse -> rorse (将 'h' 替换为 'r')
 *          rorse -> rose (删除 'r')
 *          rose -> ros (删除 'e')
 *          
 *  示例 2
 *      输入：word1 = "intention", word2 = "execution"
 *      输出：5
 *      解释：
 *          intention -> inention (删除 't')
 *          inention -> enention (将 'i' 替换为 'e')
 *          enention -> exention (将 'n' 替换为 'x')
 *          exention -> exection (将 'n' 替换为 'c')
 *          exection -> execution (插入 'u')
 *          
 */
public class _72_EditDistance {

    public static void main(String[] args) {
        // test case1, output: 3
//        String word1 = "horse", word2 = "ros";

        // test case2, output: 5
        String word1 = "intention", word2 = "execution";
        
        _72Solution solution = new _72Solution();
        
        
        System.out.println(solution.minDistance(word1, word2));
    }
}

/**
 * 动态规划（编辑距离）
 * 此题的思路与第 583 道题类似，只不过本题中，两个字符串可以执行的操作更多，包含删除、替换、添加。
 * 用 memo[i][j] 表示字符串 word1[0...i-1]、word2[0...j-1] 相互转换成相同字符串所需的最小步数，状态转换的具体过程如下：
 * 
 * 因为处理对象有 word1、word2 两个，处理操作有删除、替换、添加三种，所以在处理 word1[i]、word2[j] 时，总共有 3 * 3 = 9 种，即分别为：
 *  （1）对  word1[i] 执行删除，对  word2[i] 执行删除，操作次数增加 2
 *  （2）对  word1[i] 执行删除，不对 word2[i] 执行操作，操作次数增加 1
 *  （3）不对 word1[i] 执行操作，对 word2[i] 执行删除，操作次数增加 1
 *  （4）对  word1[i] 执行替换，对  word2[i] 执行替换，操作次数增加 2
 *  （5）对  word1[i] 执行替换，不对 word2[i] 执行操作，操作次数增加 1
 *  （6）不对 word1[i] 执行操作，对 word2[i] 执行替换，操作次数增加 1
 *  （7）对  word1[i] 执行添加，对  word2[i] 执行添加，操作次数增加 2
 *  （8）对  word1[i] 执行添加，不对 word2[i] 执行操作，操作次数增加 1
 *  （9）不对 word1[i] 执行操作，对 word2[i] 执行添加，操作次数增加 1
 * 因为是为了得到最少的操作次数，所以第 1、4、7 三种情况不予考虑，这三种情况得到的结果不可能为最少（实际上，每次操作最多为 1 次即可，无需两次）。
 * 
 * 假设 word1 = "ab"、 word2 = "abc" ，然后进行如下两种操作：
 *  （1）word1 添加 'c'，wrod2 不变，得到 word1 = "abc"、word2 = "abc"，操作次数加 1
 *  （2）word1 不变，wrod2 删除 'c'，得到 word1 = "ab"、word2 = "ab"，操作次数加 1
 * 从该示例中可以看出， word1 添加字符和 word2 删除字符这两种情况是等价的，最终操作次数是一样，即第 8 种情况等价于第 3 种情况、第 9 种情况等价于第 2 种情况。
 * 为了方便处理，删除第 8、9 两种情况。去掉添加操作，是为了将所有的处理操作统一成缩小字符串的范围，更易于实现。
 * 
 * 假设 word1 = "ab"、 word2 = "ac" ，然后进行如下两种操作：
 *  （1）word1 将 'b' 替换成 'c'，wrod2 不变，得到 word1 = "ac"、word2 = "ac"，操作次数加 1
 *  （2）word1 不变，wrod2 将 'c' 替换成 'b'，得到 word1 = "ab"、word2 = "ab"，操作次数加 1
 *  从该示例中可以看出，无论是 word1 替换还是 word2 替换，效果是等价的，即第 5、6 两种情况等价。
 *  
 * 因此，最终上述 9 种情况在删除 1、4、6、7、8、9 两种情况后，还剩余如下三种情况：
 *  （2）对  word1[i] 执行删除，不对 word2[i] 执行操作，操作次数增加 1
 *  （3）不对 word1[i] 执行操作，对 word2[i] 执行删除，操作次数增加 1
 *  （5）对  word1[i] 执行替换，不对 word2[i] 执行操作，操作次数增加 1
 * 
 * 
 * 状态转移过程
 * 如果 word1[i] = word2[j]，则有如下三种情况：
 *  （1）用 word1[i] 去匹配 word2[j]，则此时不需要执行删除操作，只需要缩减问题范围，将状态转移到 memo[i - 1][j - 1]
 *  （2）不用 word[i] 匹配 word2[j]，此时选择删除 word1[i]，将状态转移到 memo[i - 1][j]，操作次数 +1（因为已删除 word1[i]）
 *  （3）不用 word[i] 匹配 word2[j]，此时选择删除 word2[j]，将状态转移到 memo[i][j - 1]，操作次数 +1（因为已删除 word2[j]）
 *  因为此时能够匹配到，执行替换没有任何意义，所以不执行替换。但是保留删除操作，是因为转换的情况可能有多种，即一个字符串中相同字符有多个，如下所示：
 *      假设 word1 = "acc"、 word2 = "ac" ，word1[2] = word2[1] = 'c'，可以进行如下两种操作：
 *      1）删除 word1[2]，得到 word1 = "ac"、 word2 = "ac"。此时，从 word1 转换成 word2，不需要进行任何操作。 
 *      2）删除 word2[1]，得到 word1 = "acc"、word2 = "c"。此时，从 word1 转换成 word2，需要两次操作（这种情况反而增加了操作步骤） 。
 *  从该例中可以看出，考虑第 2、3 两种删除情况，操作次数反而增加。所以为了获得最少的操作次数，只需要考虑第 1 种情况即可。
 *  
 * 如果 word1[i] != word2[j]，则有如下三种情况：
 *  （1）替换 word1[i]，此时就 word1[i] 可以匹配到 word2[j]，则缩减问题范围，将状态转移到 memo[i - 1][j - 1]，操作次数 +1（因为已经替换了一个字符）
 *  （2）选择删除 word1[i]，将状态转移到 memo[i - 1][j]，操作次数 +1（因为已删除 word1[i]）
 *  （3）选择删除 word2[j]，将状态转移到 memo[i][j - 1]，操作次数 +1（因为已删除 word2[j]）
 * 
 * 初始状态
 *  memo[0][i] = i，因为 word1 是空串，此时只需要将 word2[0...i-1] 删除完即可得到 word1
 *  memo[i][0] = i，因为 word2 是空串，此时只需要将 word1[0...i-1] 删除完即可得到 word2
 */
class _72Solution {
    
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        
        for (int i = 0;  i <= word1.length(); ++i) {
            memo[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); ++j) {
            memo[0][j] = j;
        }
        
        for (int i = 1; i <= word1.length(); ++i) {
            for (int j = 1; j <= word2.length(); ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    memo[i][j] = memo[i - 1][j - 1]; // 如果相等，则直接匹配
                } else {
                    memo[i][j] = Math.min(
                            memo[i - 1][j - 1] + 1, /* 替换 */
                            Math.min(memo[i - 1][j] + 1, memo[i][j - 1] + 1) /* 删除 */ );
                }
            }
        }
        
        return memo[word1.length()][word2.length()];
    }
}

