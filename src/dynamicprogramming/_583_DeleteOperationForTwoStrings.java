package dynamicprogramming;

/**
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 * 
 * 题目描述：给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 * 
 * 限制条件：
 *  （1）给定单词的长度不超过500。
 *  （2）给定单词中的字符只含有小写字母。
 *  
 * 示例：
 *  输入: "sea", "eat"
 *  输出: 2
 *  解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 */
public class _583_DeleteOperationForTwoStrings {

    public static void main(String[] args) {
        // test case, output: 2
        String word1 = "sea", word2 = "eat";
        
        _583Solution solution = new _583Solution();
        
        System.out.println(solution.minDistance(word1, word2));
    }
}


/**
 * 动态规划（编辑距离）
 * 此题的思路与第 115 道题类似，只不过本题中，两个字符串都可以执行删除操作。
 * 用 memo[i][j] 表示字符串 word1[0...i-1]、word2[0...j-1] 相互转换成相同字符串所需的最小步数，状态转换的具体过程如下：
 * 如果 word1[i] = word2[j]，则有如下三种情况：
 *  （1）用 word1[i] 去匹配 word2[j]，则此时不需要执行删除操作，只需要缩减问题范围，将状态转移到 memo[i - 1][j - 1]
 *  （2）不用 word[i] 匹配 word2[j]，此时选择删除 word1[i]，将状态转移到 memo[i - 1][j]，操作次数 +1（因为已删除 word1[i]）
 *  （3）不用 word[i] 匹配 word2[j]，此时选择删除 word2[j]，将状态转移到 memo[i][j - 1]，操作次数 +1（因为已删除 word2[j]）
 * 如果 word1[i] != word2[j]，则有如下三种情况：
 *  （1）选择删除 word1[i]、word2[j]，将状态转移到 memo[i - 1][j - 1]，操作次数 +2（因为已经删除了两个字符）
 *  （2）选择删除 word1[i]，将状态转移到 memo[i - 1][j]，操作次数 +1（因为已删除 word1[i]）
 *  （3）选择删除 word2[j]，将状态转移到 memo[i][j - 1]，操作次数 +1（因为已删除 word2[j]）
 * 
 * 初始状态
 *  memo[0][i] = i，因为 word1 是空串，此时只需要将 word2[0...i-1] 删除完即可得到 word1
 *  memo[i][0] = i，因为 word2 是空串，此时只需要将 word1[0...i-1] 删除完即可得到 word2
 */
class _583Solution {
    
    public int minDistance(String word1, String word2) {
        // memo[i][j] 表示字符串 word1[0...i-1]、word2[0...j-1] 相互转换成相同字符串所需的最小步数
        int[][] memo = new int[word1.length() + 1][ word2.length() + 1];
        
        for (int i = 0; i <= word1.length(); ++i) {
            memo[i][0] = i; // word2 为空串，删除完 word1[0...i-1]
        }
        for (int i = 0; i <= word2.length(); ++i) {
            memo[0][i] = i; // word1 为空串，删除完 word2[0...i-1]
        }
        
        for (int i = 1; i <= word1.length(); ++i) {
            for (int j = 1; j <= word2.length(); ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
//                    memo[i][j] = Math.min(
//                            memo[i - 1][j - 1], /* 匹配，不删除，只缩减字符串长度 */
//                            Math.min(memo[i - 1][j] + 1, memo[i][j - 1] + 1) /* 不匹配，删除 word1[i - 1] 或 word2[j - 1] */
//                            ); 
                    // 此时可以选择删除，也可以选择不删除，但是因为是要求最小操作次数，所以此时直接匹配，可以减少操作次数。
                    // 不选择删除，直接匹配的写法如下所示；完整的写法，如上所示（两种写法的结果均正确）。
                    memo[i][j] = memo[i - 1][j - 1];
                } else {
                    memo[i][j] = Math.min(
                            memo[i - 1][j - 1] + 2, /* 匹配不上，删除 word1[i - 1]、word2[j - 1] */
                            Math.min(memo[i - 1][j] + 1, memo[i][j - 1] + 1) /* 匹配不上，删除 word1[i - 1] 或 word2[j - 1] */
                            );
                }
            }
        }
        
        return memo[word1.length()][word2.length()];
    }
}