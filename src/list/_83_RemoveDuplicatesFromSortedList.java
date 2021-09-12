package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/
 * 
 * 题目描述：存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素只出现一次。返回同样按升序排列的结果链表。
 * 
 * 条件限制：
 *  （1）链表中节点数目在范围 [0, 300] 内
 *  （2）-100 <= Node.val <= 100
 *  （3）题目数据保证链表已经按升序排列
 *  
 *  示例：
 *      示例 1： 1 -> 1 -> 2     ==> 1 -> 2
 *      Input: head = [1,1,2]
 *      Output: [1,2]
 *      
 *      示例 2： 1 -> 1 -> 2 -> 3 -> 3     ==> 1 -> 2 -> 3
 *      Input: head = [1,1,2,3,3]
 *      Output: [1,2,3]
 *
 */
public class _83_RemoveDuplicatesFromSortedList {

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        ListNode head = ListUtil.createList(nums);
        
        _83Solution1 solution = new _83Solution1();

        ListUtil.print(solution.deleteDuplicates(head));
    }
}

/**
 * 解法一：使用递归
 */
class _83Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return null;
        }
        
        if (null != head.next && head.val == head.next.val) {
            return deleteDuplicates(head.next); // 当前节点与下一个节点的值相同，则当前节点不能加入到链表中，直接返回后面删除重复元素的链表即可。
        } else {
            head.next = deleteDuplicates(head.next); // 当前节点与下一个节点的值不同，则当前节点可以加入到链表中
            return head;
        }
    }
}
