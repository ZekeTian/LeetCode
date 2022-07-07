package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.com/problems/open-the-lock/
 * 
 * 题目描述：你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 *         每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 *         锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 *         列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 *         字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * 
 * 限制条件：
 *  （1）1 <= deadends.length <= 500
 *  （2）deadends[i].length == 4
 *  （3）target.length == 4
 *  （4）target 不在 deadends 之中
 *  （5）target 和 deadends[i] 仅由若干位数字组成
 *  
 * 示例：
 *  示例 1
 *      输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 *      输出：6
 *      解释：
 *          可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 *          注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 *          因为当拨动到 "0102" 时这个锁就会被锁定。
 *  
 *  示例 2
 *      输入: deadends = ["8888"], target = "0009"
 *      输出：1
 *      解释：把最后一位反向旋转一次即可 "0000" -> "0009"。
 *      
 *  示例 3
 *      输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 *      输出：-1
 *      解释：无法旋转到目标数字且不被锁定。
 *
 */
public class _752_OpenTheLock {

    public static void main(String[] args) {
        // test case1, output: 6
//        String[] deadends = { "0201", "0101", "0102", "1212", "2002" };
//        String target = "0202";
        
        // test case2, output: 1
//        String[] deadends = { "8888" };
//        String target = "0009";
        
        // test case3, output: -1
//        String[] deadends = { "8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888" };
//        String target = "8888";
        
        // test case3, output: -1
        String[] deadends = { "0201","0101","0102","1212","2002" };
        String target = "0000";
        
        _752Solution solution = new _752Solution();
        
        System.out.println(solution.openLock(deadends, target));
        
    }
    
}


class _752Solution {
    
    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        Set<String> deadendSet = new HashSet<>(); // 记录死锁状态
        Set<String> generated = new HashSet<>(); // 记录已经生成过的、合法的锁状态

        for (String s : deadends) {
            deadendSet.add(s);
        }

        if (deadendSet.contains("0000")) {
            return -1;
        }

        // BFS 遍历
        LinkedList<String> queue = new LinkedList<>();
        int count = 0;
        queue.addLast("0000");
        generated.add("0000");

        while (!queue.isEmpty()) {
            ++count; // 到新的一层，相当于是旋转一次锁，使得锁进入一个新的状态

            // 遍历当前层中，锁所有可能的状态
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String poll = queue.poll();

                // 对 poll 对应状态的锁进行旋转，从而得到下一层
                List<String> newLocks = turnLock(poll);
                for (String lock : newLocks) {
                    if (lock.equals(target)) {
                        return count;
                    }

                    if (!generated.contains(lock) && !deadendSet.contains(lock)) {
                        queue.addLast(lock); // lock 对应的锁状态合法，则添加到队列中，以便后面访问
                        generated.add(lock);
                    }
                }
            }
        }

        return -1;
    }

    // 对锁 lock 进行旋转，返回所有可能的旋转情况
    private List<String> turnLock(String lock) {
        char[] charArray = lock.toCharArray();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < charArray.length; ++i) {
            char ch = charArray[i];

            // 对当前字符 ch 进行正向旋转
            charArray[i] = next(ch);
            list.add(new String(charArray));
            // 对当前字符 ch 进行反向旋转
            charArray[i] = prev(ch);
            list.add(new String(charArray));

            charArray[i] = ch; // 将 charArray[i] 进行复原，以便对下一个字符进行旋转
        }

        return list;
    }

    // 正向旋转时，ch 下一个字符
    private char next(char ch) {
        return (char) (ch == '9' ? '0' : ch + 1);
    }

    // 反向旋转时，ch 下一个字符
    private char prev(char ch) {
        return (char) (ch == '0' ? '9' : ch - 1);
    }
    
}

