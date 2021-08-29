package list;

import java.util.HashMap;
import java.util.Map;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/reorder-list/
 * 
 * 题目描述：给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 *        L0 -> L1 -> ... -> Ln-1 -> Ln
 *        现在，请将其重新排列成如下形式：
 *        L0 -> Ln -> L1 -> Ln-1 -> L2 -> Ln-2 -> ...
 * 
 * 注意：不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 
 * 条件限制：
 *  （1）链表的长度范围为 [1, 5 * 10^4]
 *  （2）1 <= node.val <= 1000
 * 
 * 示例：
 *  示例 1
 *    原始：  1 -> 2 -> 3 -> 4
 *    转换：  1 -> 4 -> 2 -> 3
 *  Input： head = [1,2,3,4]
 *  Output: [1,4,2,3]
 *  
 *  示例 2
 *    原始：  1 -> 2 -> 3 -> 4 -> 5
 *    转换：  1 -> 5 -> 2 -> 4 -> 3
 */
public class _143_ReorderList {

    public static void main(String[] args) {
        // test case1, output: [1,4,2,3]
        int[] nums = { 1, 2, 3, 4 };

        // test case2, output: [1,5,2,4,3]
        //        int[] nums = { 1, 2, 3, 4, 5 };

        ListNode head = ListUtil.createList(nums);

//        _143Solutio1 solution = new _143Solutio1();
        
        _143Solutio2 solution = new _143Solutio2();

        solution.reorderList(head);
        ListUtil.print(head);
    }
}

/**
 * 解法一：使用 map 存储各个节点，然后根据规则拼接成转换后的链表
 */
class _143Solutio1 {
    public void reorderList(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>();
        int count = 0;
        ListNode cur = head;

        while (null != cur) {
            map.put(count++, cur);
            cur = cur.next;
        }

        if (count <= 2) {
            return;
        }

        head.next = map.get(count - 1);
        ListNode pre = map.get(count - 1);

        for (int i = 1; i <= count / 2; ++i) { // 要 <= count / 2，否则链表长度为奇数时，会处理错误
            ListNode node1 = map.get(i);
            ListNode node2 = map.get(count - 1 - i);

            node1.next = node2;
            pre.next = node1;
            pre = node2;
        }

        map.get(count / 2).next = null; // 最后一个节点的 next 置为 null，表示链表结束
    }
}

/**
 * 解法二：使用双指针。先找到链表的中间节点，将链表分成两个链表。前面一部分的链表记为链表 1，后面一部分的链表记为链表 2。
 *      之后，将链表 2 进行反转。最后，将链表 1 和反转后的链表 2 进行合并即为所需的链表。
 */
class _143Solutio2 {

    // 返回链表 head 的中间节点
    private ListNode middle(ListNode head) {
        if (null == head) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // 利用头插法反转链表
    private void reverse1(ListNode dummyHead) {
        ListNode tail = dummyHead.next;

        if (null == tail) {
            return;
        }

        while (tail.next != null) {
            ListNode node = tail.next;
            tail.next = node.next;
            node.next = dummyHead.next;
            dummyHead.next = node;
        }
    }

    // 利用循环反转链表
    private void reverse2(ListNode dummyHead) {
        ListNode cur = dummyHead.next;
        ListNode pre = null;
        ListNode next  = null;
        
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        
        dummyHead.next = pre;
    }

    // 按照规则合并 head1、head2 两个链表
    private void merege(ListNode head1, ListNode head2) {
        ListNode cur1 = head1;
        ListNode cur2 = head2;

        while (cur1 != null && cur2 != null) {
            ListNode next1 = cur1.next;
            ListNode next2 = cur2.next;
            cur1.next = cur2;
            cur2.next = next1;
            cur1 = next1;
            cur2 = next2;
        }
    }

    public void reorderList(ListNode head) {
        // 寻找到链表的中间节点
        ListNode mid = middle(head);
        // 对后面部分的链表进行反转
        //        reverse1(mid);
        reverse2(mid);
        // 获取后面部分链表的头节点
        ListNode head2 = mid.next;
        // 将前面部分的链表和后面部分的断开
        mid.next = null;
        // 合并两部分的链表
        merege(head, head2);
    }
}
