package list;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 * 
 * 题目描述：给定一个单向链表的头节点，删除链表中倒数第 n 个节点，然后返回链表的头节点。
 * 
 * 条件限制：
 *  （1）链表中节点的个数为 sz
 *  （2）1 <= sz <= 30
 *  （3）0 <= Node.val <= 100
 *  （4）1 <= n <= sz
 *
 * 示例：
 *  Input: head = [1,2,3,4,5], n = 2
 *  Output: [1,2,3,5]
 */
public class _19_RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5 };
        //        int[] nums = { 1 };
        ListNode head = ListUtil.createList(nums);

        ListUtil.print(head);
        head = new _19Solution2().removeNthFromEnd(head, 2);
        ListUtil.print(head);
    }
}

/**
 * 解法一：遍历两次，第一次遍历获取链表的长度，第二次遍历定位到待删除节点的前一个
 */
class _19Solution1 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        // 第一次遍历，获取链表的长度
        int len = 0;
        ListNode cur = dummyHead.next;

        while (null != cur) {
            ++len;
            cur = cur.next;
        }

        // 第二次遍历，定位到待删除节点的前一个节点
        ListNode pre = dummyHead;
        for (int i = 0; i < len - n; ++i) {
            pre = pre.next;
        }
        ListNode del = pre.next; // 待删除的节点
        pre.next = del.next;
        del.next = null;

        return dummyHead.next;
    }
}

/**
 * 解法二：只遍历一次。使用双指针技术，使得双指针之间的间距始终为 n 个节点。
 */
class _19Solution2 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode node1 = dummyHead;
        ListNode node2 = dummyHead;

        // node2 向后移动 (n + 1) 次，拉开 node1、node2 的距离
        for (int i = 0; i < n + 1; ++i) {
            node2 = node2.next;
        }

        // node1、node2 同时向后移动，直到 node2 移到链表尾部
        while (null != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }

        // 删除指定节点
        ListNode del = node1.next;
        node1.next = del.next;
        del.next = null;

        return dummyHead.next;
    }
}
