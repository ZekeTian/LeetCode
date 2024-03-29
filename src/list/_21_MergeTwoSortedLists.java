package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * 
 * 题目描述：将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 
 * 条件限制：
 *  （1）两个链表的节点数目范围是 [0, 50]
 *  （2）-100 <= Node.val <= 100
 *  （3）l1 和 l2 均按 非递减顺序 排列
 *  
 * 示例：
 *  Input：l1 = [1,2,4], l2 = [1,3,4]
 *  Output: [1,1,2,3,4,4]
 */
public class _21_MergeTwoSortedLists {
    public static void main(String[] args) {
        // test case1, output: [1,1,2,3,4,4]
        //        int[] nums1 = { 1, 2, 4 };
        //        int[] nums2 = { 1, 3, 4 };

        // test case2, output: []
        //        int[] nums1 = {};
        //        int[] nums2 = {};

        // test case3, output: [0]
        int[] nums1 = {};
        int[] nums2 = { 0 };

        ListNode l1 = ListUtil.createList(nums1);
        ListNode l2 = ListUtil.createList(nums2);

        //        _21Solution1 solution = new _21Solution1();

        _21Solution2 solution = new _21Solution2();

        ListNode newList = solution.mergeTwoLists(l1, l2);
        ListUtil.print(newList);
    }
}

/**
 * 解法一：利用循环实现
 */
class _21Solution1 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }

        if (null == l2) {
            return l1;
        }

        ListNode dummHead = new ListNode(0); // 合并后的新链表的虚拟头节点
        ListNode cur = dummHead;
        ListNode cur1 = l1;
        ListNode cur2 = l2;

        while (null != cur1 && null != cur2) {
            // 选择较小值，放入新链表中
            if (cur1.val < cur2.val) {
                cur.next = new ListNode(cur1.val);
                cur1 = cur1.next;
            } else {
                cur.next = new ListNode(cur2.val);
                cur2 = cur2.next;
            }
            cur = cur.next;
        }

        while (null != cur1) {
            cur.next = new ListNode(cur1.val);
            cur1 = cur1.next;
            cur = cur.next;
        }

        while (null != cur2) {
            cur.next = new ListNode(cur2.val);
            cur2 = cur2.next;
            cur = cur.next;
        }

        return dummHead.next;
    }
}

/**
 * 解法二：利用递归实现（本质上和解法一一样，只不过在两个链表中选择较小节点的过程用递归实现）
 */
class _21Solution2 {

    // 在 l1、l2 两个链表中选择出较小的节点，并返回较小节点
    private ListNode minNode(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        } else if (null == l2) {
            return l1;
        } else if (l1.val < l2.val) {
            // 选出较小的节点：l1 链表的头节点，然后在 [l1.next...l1.tail] 这个区间的链表和 l2 这个链表中选择较小的节点，
            // 之后将该较小的节点连到 l1 头节点之后，从而保证 l1 头节点代表的新链表是非递减的
            l1.next = minNode(l1.next, l2);
            return l1;
        } else {
            l2.next = minNode(l1, l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return minNode(l1, l2);
    }
}
