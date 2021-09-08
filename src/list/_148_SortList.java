package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/sort-list/
 *
 * 题目描述：给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *                  你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *                  
 *                  
 */
public class _148_SortList {

    public static void main(String[] args) {
        // test case 1, output: 1, 2, 3, 4
        int[] nums = { 4, 2, 1, 3 };

        // test case 2, output: 1, 2, 3, 4, 4, 8, 10
        //        int[] nums = {4, 2, 1, 3, 10, 4, 8};
        ListNode head = ListUtil.createList(nums);

        _148Solutin1 solution = new _148Solutin1();
        head = solution.sortList(head);

        ListUtil.print(head);
    }
}

/**
 * 解法一：使用插入排序法。注意：此方法在解决这道题时会超时。
 */
class _148Solutin1 {

    public ListNode sortList(ListNode head) {
        ListNode cur = head; // 待处理的节点
        ListNode dummyHead = new ListNode(); // 排序后链表的虚头节点
        dummyHead.next = null;
        dummyHead.val = Integer.MIN_VALUE;

        while (null != cur) {
            ListNode next = cur.next; // 因为会将 cur 插入到新链表中，所以在此先保存 cur 的下一个节点

            // 将 cur 节点插入到排序链表中的合适位置
            ListNode tmp = dummyHead; // 用于遍历新链表
            ListNode pre = dummyHead; // 新链表中 tmp 节点的前一个节点
            while (null != tmp) {
                // 找到合适的位置，并且该位置不是最后一个
                if (cur.val > pre.val && cur.val <= tmp.val) {
                    cur.next = tmp;
                    pre.next = cur;
                    break;
                }

                //到达链表尾处，依然未找到合适的位置，则此时 cur 的值是最大的，直接将其插入到链表尾处即可
                if (tmp.next == null) {
                    cur.next = null;
                    tmp.next = cur;
                    break;
                }

                // 在新链表中向后移动
                pre = tmp;
                tmp = tmp.next;
            }

            cur = next;
        }

        return dummyHead.next;
    }
}

/**
 * 解法二：使用归并排序 
 */
class _148Solution2 {

    private ListNode middle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (null != fast.next && null != fast.next.next) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private ListNode mergeOrderedList(ListNode head1, ListNode head2) {
        if (null == head1) {
            return head2;
        } else if (null == head2) {
            return head1;
        } else if (head1.val < head2.val) {
            head1.next = mergeOrderedList(head1.next, head2);
            return head1;
        } else {
            head2.next = mergeOrderedList(head1, head2.next);
            return head2;
        }
    }

    public ListNode sortList(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }

        // 找到链表的中点，将链表一分为二
        ListNode middle = middle(head);
        ListNode rightHead = middle.next;
        middle.next = null;

        // 对左右两个链表分别进行排序 
        ListNode head1 = sortList(head);
        ListNode head2 = sortList(rightHead);

        // 将排序后的两个链表进行归并 
        return mergeOrderedList(head1, head2);
    }
}
