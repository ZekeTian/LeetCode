package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/add-two-numbers/
 * 
 * 题目描述：给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *        请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *  
 * 条件限制：
 *  （1）每个链表中的节点数在范围 [1, 100] 内
 *  （2）每个链表中的节点数在范围 [1, 100] 内
 *  （3）题目数据保证列表表示的数字不含前导零
 *
 * 示例：
 *      2 -> 4 -> 3
 *      5 -> 6 -> 4
 *   Input: l1 = [2,4,3], l2 = [5,6,4]
 *   Output: [7,0,8]
 *   解释：342 + 465 = 807.
 * 
 */
public class _2_AddTwoNumbers {

    public static void main(String[] args) {
        // test case1, output: [7,0,8]
        int[] num1 = { 2, 4, 3 };
        int[] num2 = { 5, 6, 4 };

        // test case1, output: [8,9,9,9,0,0,0,1]
        //        int[] num1 = { 9, 9, 9, 9, 9, 9, 9 };
        //        int[] num2 = { 9, 9, 9, 9 };

        ListNode l1 = ListUtil.createList(num1);
        ListNode l2 = ListUtil.createList(num2);

        //        _2Solution1 solution = new _2Solution1();
        _2Solution2 solution = new _2Solution2();

        ListNode result = solution.addTwoNumbers(l1, l2);
        ListUtil.print(result);
    }
}

/**
 * 解法一：利用循环模拟两个数相加
 */
class _2Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode cur = dummyHead;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        boolean flag = false;

        while (null != cur1 || null != cur2) {
            int num1 = 0;
            if (null != cur1) {
                num1 = cur1.val;
                cur1 = cur1.next;
            }

            int num2 = 0;
            if (null != cur2) {
                num2 = cur2.val;
                cur2 = cur2.next;
            }

            int num = (flag ? num1 + num2 + 1 : num1 + num2);
            flag = (num >= 10);
            ListNode node = new ListNode();
            node.val = num % 10;
            cur.next = node;
            cur = node;
        }

        // 最后需要再进一位，则补 1
        if (flag) {
            ListNode node = new ListNode();
            node.val = 1;
            cur.next = node;
        }

        return dummyHead.next;
    }
}

/**
 * 解法二：利用递归模拟两个数相加
 */
class _2Solution2 {

    private ListNode add(ListNode head1, ListNode head2, boolean flag) {
        if (null == head1 && null == head2) {
            // 两个链表都遍历完，则结束整个相加的过程。但是在结束时，需要判断最后一次相加是否产生进位，如果产生进位，则还需要创建一个节点
            ListNode node = null;
            if (flag) {
                node = new ListNode();
                node.val = 1;
            }
            return node;
        }
        ListNode next1 = null;
        int num1 = 0;
        if (null != head1) {
            num1 = head1.val;
            next1 = head1.next;
        }

        ListNode next2 = null;
        int num2 = 0;
        if (null != head2) {
            num2 = head2.val;
            next2 = head2.next;
        }

        ListNode node = new ListNode();
        int num = (flag ? num1 + num2 + 1 : num1 + num2);
        node.val = num % 10;
        node.next = add(next1, next2, num >= 10);

        return node;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return add(l1, l2, false);
    }
}
