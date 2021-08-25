package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 * 
 * 题目描述：给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回反转后的链表 。
 * 
 * 条件限制：
 *  （1）链表中节点数目为 n
 *  （2）1 <= n <= 500
 *  （3）-500 <= Node.val <= 500
 *  （4）1 <= left <= right <= n
 *  
 *  示例：
 *   示例 1
 *    Input: head = [1,2,3,4,5], left = 2, right = 4
 *    Output: [1,4,3,2,5]
 *    
 *   示例 2
 *    Input: head = [5], left = 1, right = 1
 *    Output: [5]
 *
 */
public class _92_ReverseLinkedListII {

    public static void main(String[] args) {
        _92Solution1 solution = new _92Solution1();

        // test case 1, output: [1,4,3,2,5]
        int[] nums = { 1, 2, 3, 4, 5 };
        ListNode head = ListUtil.createList(nums);
        head = solution.reverseBetween(head, 2, 4);

        // test case 2, output: [5]
        //        int[] nums = { 5 };
        //        ListNode head = ListUtil.createList(nums);
        //        head = solution.reverseBetween(head, 1, 1);

        ListUtil.print(head);

    }
}

/**
 * 解法一：使用 206 的思路解决，只不过只对范围内的链表节点进行反转
 *
 */
class _92Solution1 {

    private ListNode tmpNewHead = null; // [left, right] 区间内翻转后形成的链表的头节点
    private ListNode tmpNextNode = null; // 原链表中，tmpNewHead 节点的下一个节点

    // cur：当前待反转的节点，reverseNum：总共需要反转的节点个数，reverseCount：已经反转的节点个数
    private ListNode reverse(ListNode cur, int reverseNum, int reverseCount) {
        if (reverseCount == reverseNum - 1) { // 到达需要翻转节点的最后一个节点，即原来链表中 right 处的节点 
            tmpNewHead = cur;
            tmpNextNode = cur.next;
            return cur;
        }

        ListNode res = reverse(cur.next, reverseNum, reverseCount + 1);
        res.next = cur;

        return cur;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (null == head) {
            return null;
        }

        // 遍历到需要反转的链表节点处
        ListNode cur = head;
        ListNode tmpPreNode = null; // cur 节点的前一个节点，用于连接 tmpNewHead
        for (int i = 0; i < left - 1; ++i) {
            tmpPreNode = cur;
            cur = cur.next;
        }

        ListNode tmpTailNode = reverse(cur, right - left + 1, 0);
        tmpTailNode.next = tmpNextNode;

        if (1 == left) {
            return tmpNewHead; // 从原始链表的头节点处开始翻转，则最终链表的头节点即为翻转后的链表的头节点
        }

        tmpPreNode.next = tmpNewHead;
        return head; // 原始链表的头节点不参与反转，所以其还是头节点
    }
}