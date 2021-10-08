package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * 
 * 题目描述：给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。k 是一个正整数，它的值小于或等于链表的长度。
 *        如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 
 * 进阶：你可以设计一个只使用常数额外空间的算法来解决此问题吗？你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * 
 * 条件限制：
 *  （1）列表中节点的数量在范围 sz 内
 *  （2）1 <= sz <= 5000
 *  （3）0 <= Node.val <= 1000
 *  （4）1 <= k <= sz
 *  
 * 示例：
 *  示例 1
 *      链表：1 -> 2 -> 3 -> 4 -> 5  转换成 2 -> 1 -> 4 -> 3 -> 5
 *      输入：head = [1,2,3,4,5], k = 2
 *      输出：[2,1,4,3,5]
 *      
 *  示例 2
 *      链表：1 -> 2 -> 3 -> 4 -> 5  转换成 3 -> 2 -> 1 -> 4 -> 5
 *      输入：head = [1,2,3,4,5], k = 3
 *      输出：[3,2,1,4,5]
 *
 */
public class _25_ReverseNodesInKGroup {

    public static void main(String[] args) {
        // test case 1, output: [2,1,4,3,5]
        //        int[] nums = { 1, 2, 3, 4, 5 };
        //        int k = 2;

        // test case 2, output: [3,2,1,4,5]
        int[] nums = { 1, 2, 3, 4, 5 };
        int k = 3;

        ListNode head = ListUtil.createList(nums);
        _25Solution solution = new _25Solution();

        head = solution.reverseKGroup(head, k);

        ListUtil.print(head);
    }

}

/**
 * 直接模拟，先确定每次需要翻转的链表区间，然后将翻转区间的链表与原链表断开，之后对翻转区间的链表的进行翻转。
 * 等翻转完之后，再将翻转后的链表与原来链表连接起来，最后更新相应指针，继续下一次翻转。
 */
class _25Solution {

    private ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode pre = dummyHead; // 待翻转区间链表的前一个节点
        ListNode start = dummyHead; // 待翻转区间链表的头节点
        ListNode end = dummyHead; // 待翻转区间链表的尾节点

        while (null != end) {
            // 利用 end 找到翻转区间的结束位置
            for (int i = 0; i < k && null != end; ++i) {
                end = end.next;
            }
            if (null == end) { // 最后剩余的节点个数不满 K 个，则无需翻转
                break;
            }

            // 将翻转区间的链表与原来链表断开
            ListNode next = end.next; // 因为翻转后，还要与原来的链表进行连接，所以需要记录 next 位置
            end.next = null;

            // 对翻转区间（[start, end]）的链表进行翻转
            start = pre.next;
            // 将翻转后的链表与原来链表进行连接
            pre.next = reverse(start); // pre 与翻转后的链表的头节点进行连接
            start.next = next; // 因为 [start, end] 区间的链表已经翻转了，所以翻转后，start 节点在后，end 节点在前

            // 更新指针位置
            pre = start; // 因为之后翻转是从 start 节点之后开始，所以 pre、end 实际上都要指向 start 节点
            end = pre;
        }

        return dummyHead.next;
    }
}
