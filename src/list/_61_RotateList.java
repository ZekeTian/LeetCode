package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/rotate-list/
 * 
 * 题目描述：给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * 
 * 限制条件：
 *  （1）链表中节点的数目在范围 [0, 500] 内
 *  （2）-100 <= Node.val <= 100
 *  （3）0 <= k <= 2 * 10^9
 * 
 * 示例：
 *  示例 1
 *      输入：head = [1,2,3,4,5], k = 2
 *      输出：[4,5,1,2,3]
 *  
 *  示例 2
 *      输入：head = [0,1,2], k = 4
 *      输出：[2,0,1]
 *
 */
public class _61_RotateList {

    public static void main(String[] args) {
        // test case1, output: [4,5,1,2,3]
//        int[] nums = { 1, 2, 3, 4, 5 };
//        int k = 2;
        
        // test case2, output: [2,0,1]
        int[] nums = { 0, 1, 2 };
        int k = 4;
        
        
        ListNode head = ListUtil.createList(nums);
        
        _61Solution solution = new _61Solution();
        
        head = solution.rotateRight(head, k);
        
        ListUtil.print(head);
    }
}

/**
 * 思路步骤：
 * （1）统计出链表长度
 * （2）计算出 k 值
 * （3）寻找到倒数第 k+1 个节点
 * （4）然后从倒数第 k+1 个节点处断开，后面部分的链表即为要翻转部分的链表
 * （5）反转待翻转的链表
 * （6）将反转后链表的节点逐个插入到前面部分的链表中
 */
class _61Solution {
    
    // 获取 head 对应链表的长度
    private int length(ListNode head) {
        int len = 0;
        ListNode cur = head;
        
        while (cur != null) {
            ++len;
            cur = cur.next;
        }
        
        return len;
    }
    
    // 获取 head 对应链表中倒数第 k 个节点
    private ListNode getLastKNode(ListNode head, int k) {
        ListNode fast = head, slow = head;
        
        for (int i = 0; i < k; ++i) {
            fast = fast.next;
        }
        
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        return slow;
    }
    
    // 反转 head 对应链表
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        
        return pre;
    }
    
    // 将 dst 链表中的节点逐个插入到 src 链表的头部
    private ListNode insert(ListNode src, ListNode dst) {
        ListNode cur = dst;
        ListNode head = src;
        
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = head;
            head = cur;
            cur = next;
        }
        
        return head;
    }
    
    public ListNode rotateRight(ListNode head, int k) {
        int len = length(head);
        if (len <= 1) {
            return head;
        }
        
        k = k % len; // 因为 k 可能会比 len 大，但是每移动 len 次会恢复成原始状态，所以实际相当于只移动 (k % len) 次
        // 断开待移动的链表
        ListNode tail = getLastKNode(head, k + 1);
        ListNode rotateHead = tail.next;
        tail.next = null;
        
        // 反转待移动的链表
        rotateHead = reverse(rotateHead);
        
        // 将反转后的链表插入到前面部分的链表，即可得到最终的链表
        return insert(head, rotateHead);
    }
}
