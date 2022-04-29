package backtrack;

/**
 * https://leetcode.com/problems/24-game/
 * 
 * 题目描述：给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。
 *         您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。
 *         你须遵守以下规则:
 *          （1）除法运算符 '/' 表示实数除法，而不是整数除法。
 *              例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
 *          （2）每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
 *              例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
 *          （3）你不能把数字串在一起
 *              例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
 *          （4）如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
 *          
 * 限制条件：
 *  （1）cards.length == 4
 *  （2）1 <= cards[i] <= 9
 *  
 * 示例：
 *  示例 1
 *      输入: cards = [4, 1, 8, 7]
 *      输出: true
 *      解释: (8-4) * (7-1) = 24
 *      
 *  示例 2
 *      输入: cards = [1, 2, 1, 2]
 *      输出: false
 * 
 */
public class _679_24Game {

    public static void main(String[] args) {
        // test case1, output: true
//        int[] cards = { 4, 1, 8, 7 };
        
        // test case1, output: false
        int[] cards = { 1, 2, 1, 2 };
        
        
        _679Solution solution = new _679Solution();
        
        
        System.out.println(solution.judgePoint24(cards));
    }
    
}

/**
 * 回溯法，把所有的可能全部枚举出来，直到找到答案。
 * 此题的思路类似于组合问题，从 cards 中随机选择两个数字组合成一个新数字，然后再继续下一次递归。
 * 在递归的过程中，cards 中数字数量逐渐减少，直到最终只剩一个数字时结束递归。
 * 但是需要注意的是，当是 - 或 / 操作时，前后两个操作数有先后顺序，即 a - b 和  b - a 不同，所以需要尝试所有可能的方案。
 * 
 * 至于题目中 “括号 ()” 的添加，实际上是通过不同的选择方案实现。
 * 如 4 / (1 - 2 / 3)，相当于是从 [4, 1, 2, 3] 中随机选择 2、3，然后进行 / 操作，最后得到新的 cards = [4, 1, 2/3]
 * 在下一次递归时，相当于是从 [4, 1, 2/3] 中选择 1, 2/3 ，然后进行 - 操作，最后得到新的 cards = [4, 1 - 2/3]
 * 在下一次递归时，相当于是从 [4, 1 - 2/3] 中选择 4, 1 - 2/3 ，然后进行 / 操作，最后得到新的 cards = [4 / (1 - 2/3)]
 *
 */
class _679Solution {

    private static final double TARGET = 24.0;
    
    // 将 oldCards 中除了 oldCards[i]、oldCards[j] 之外的数字全部复制到 newCards 中
    private void copy(double[] oldCards, double[] newCards, int i, int j) {
        for (int k = 0, idx = 0; k < oldCards.length; ++k) {
            if (k != i && k != j) {
                newCards[idx++] = oldCards[k];
            }
        }
    }
    
    // 计算 cards 是否可以构成 24 
    private boolean calculate(double[] cards) {
        if (cards.length == 1) { // 当 cards 长度为 1 时，则其就是最终的结果。
            return Math.abs(cards[0] - TARGET) < 1e-6; // 因为结果是实数类型，所以不能直接用 “=” 判断结果是否和 target 相等，而需要判断两者之间的差是否足够小。 
        }
        
        // 从 cards 中选择两个数字，进行 +、-、*、/ 操作
        for (int i = 0; i < cards.length; ++i) {
            for (int j = i + 1; j < cards.length; ++j) {
                double[] newCards = new double[cards.length - 1];
                copy(cards, newCards, i, j);// 将 cards 中除了 cards[i]、cards[j] 之外的数字全部复制到 newCards 中
                
                // cards[i]、cards[j] 进行 “+” 操作
                newCards[newCards.length - 1] = cards[i] + cards[j]; // 将结果放进 newCards 中
                if (calculate(newCards)) {
                    return true;
                }
                
                // cards[i]、cards[j] 进行 “-” 操作
                newCards[newCards.length - 1] = cards[i] - cards[j]; // 将结果放进 newCards 中
                if (calculate(newCards)) {
                    return true;
                }
                
                newCards[newCards.length - 1] = cards[j] - cards[i]; // 将结果放进 newCards 中
                if (calculate(newCards)) {
                    return true;
                }
                
                // cards[i]、cards[j] 进行 “*” 操作
                newCards[newCards.length - 1] = cards[i] * cards[j]; // 将结果放进 newCards 中
                if (calculate(newCards)) {
                    return true;
                }
                
                // cards[i]、cards[j] 进行 “/” 操作
                newCards[newCards.length - 1] = cards[i] / cards[j]; // 将结果放进 newCards 中
                if (calculate(newCards)) {
                    return true;
                }
                
                newCards[newCards.length - 1] = cards[j] / cards[i]; // 将结果放进 newCards 中
                if (calculate(newCards)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    
    public boolean judgePoint24(int[] cards) {
        // 将 cards 转换成 double[]
        double[] doubleCards = new double[cards.length];
        
        for (int i = 0; i < cards.length; ++i) {
            doubleCards[i] = cards[i];
        }
        
        return calculate(doubleCards);
    }
}
