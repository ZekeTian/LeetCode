package list;

import java.util.HashMap;
import java.util.Map;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 *
 * 题目描述：存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中没有重复出现的数字。
 *        返回同样按升序排列的结果链表。
 *        
 * 条件限制：
 *  （1）链表中节点数目在范围 [0, 300] 内
 *  （2）-100 <= Node.val <= 100
 *  （3）题目数据保证链表已经按升序排列
 */
public class _82_RemoveDuplicatesFromSortedListII {

    public static void main(String[] args) {
        // test case 1, output: 1,2,5
        int[] nums = { 1, 2, 3, 3, 4, 4, 5 };

        // test case 2, output: 2, 3
        //        int[] nums = { 1, 1, 1, 2, 3 };

        // test case 3, output: null
        //        int[] nums = { 1, 1, 1 };

        ListNode head = ListUtil.createList(nums);

        //        _82Solution1 solution = new _82Solution1();
        _82Solution2 solution = new _82Solution2();

        head = solution.deleteDuplicates(head);
        ListUtil.print(head);
    }
}

/**
 * 解法一：使用 Map 记录元素出现的次数，通过两次遍历实现删除
 */
class _82Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        Map<Integer, Integer> countMap = new HashMap<>();
        ListNode cur = dummyHead.next;

        while (null != cur) {
            Integer count = countMap.getOrDefault(cur.val, 0);
            countMap.put(cur.val, ++count);
            cur = cur.next;
        }
        ListNode pre = dummyHead; // 前一个节点
        cur = dummyHead.next; // 当前节点
        while (null != cur) {
            if (countMap.get(cur.val) == 1) {
                pre.next = cur;
                pre = cur;
            }
            cur = cur.next;
        }
        // 最后一个节点的 next 置为 null，从而表示链表结束（当链表中最后一个节点需要删除时，这一步的操作必不可少）
        pre.next = null;

        return dummyHead.next;
    }
}

/**
 * 解法二：不使用 Map，一次遍历删除
 */
class _82Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead;
        ListNode cur = dummyHead.next;

        while (null != cur) {
            // 找到第一个与 cur 不相同的元素
            ListNode next = cur.next;
            boolean isRepeat = false; // 标记 cur 是否重复
            while (null != next && next.val == cur.val) {
                next = next.next;
                isRepeat = true;
            }

            if (isRepeat) {
                pre.next = next; // cur 有重复，则 pre 直接和第一个与 cur 不重重复的元素相连，相当于把所有 cur 全部删除
            } else {
                pre = cur; // cur 不重复，则 pre 向箭移动
            }
            cur = next;
        }

        return dummyHead.next;
    }
}
