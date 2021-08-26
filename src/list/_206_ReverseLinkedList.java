package list;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/reverse-linked-list/
 * 题目描述：给定一个单链表的头节点，反转该链表，并返回反转后的链表
 * 
 * 条件限制：
 * （1）链表长度范围：[0, 5000]
 * （2）-5000 <= node.val <= 5000
 *
 * 示例：
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * 
 */
public class _206_ReverseLinkedList {

    public static void main(String[] args) {
//        _206Solution1 solution = new _206Solution1();
//        _206Solution2 solution = new _206Solution2();
        _206Solution3 solution = new _206Solution3();

//        int[] nums = { 1, 2, 3, 4, 5 };
        int[] nums = { 1 };
        ListNode head = ListUtil.createList(nums);
        
//        ListNode head = null;

        System.out.println("链表反转之前：");
        ListUtil.print(head);

        head = solution.reverseList(head);

        System.out.println("链表反转之后：");
        ListUtil.print(head);
    }
}

/**
 * 实现方式一
 */
class _206Solution1 {
    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }

        ListNode tmpHead = new ListNode(); // 临时的头节点
        tmpHead.next = head;
        ListNode pre = null;
        ListNode cur = tmpHead.next;
        ListNode nxt = cur.next;

        while (nxt != null) {
            // 反转链表
            ListNode tmp = nxt.next; // 暂存 next 的 下一个节点，然后反转
            nxt.next = cur;
            cur.next = pre;

            // 向后移动
            pre = cur;
            cur = nxt;
            nxt = tmp;
        }

        head = cur;

        return head;
    }
}

/**
 * 实现方式二
 */
class _206Solution2 {
    public ListNode reverseList(ListNode head) {
        ListNode tmpHead = new ListNode();
        tmpHead.next = head;
        
        ListNode pre = null;
        ListNode cur = tmpHead.next;
        ListNode nxt = null;
        
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre; // 反转
            
            // 向后移动
            pre = cur;
            cur = nxt;
        }
        
        head = pre;
        
        return head;
    }
}

/**
 * 递归实现
 */
class _206Solution3 {
    ListNode newHead = null;
    
    private ListNode reverse(ListNode cur) {
        if (null == cur.next) {
            newHead = cur; // 到达尾节点，将尾节点标记成新的头节点
            return cur;
        }
        
        ListNode res = reverse(cur.next);
        res.next = cur; // 进行反转
        
        return cur;
    }
    
    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        
        ListNode tail = reverse(head);
        tail.next = null;
        
        return newHead;
    }
}

/**
 * 使用头插法实现
 */
class _206Solution4 {
    public ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode tailNode = head; // 链表反转后的尾节点

        while (null != tailNode.next) {
            ListNode cur = tailNode.next; // 正在反转的节点
            tailNode.next = cur.next;
            // 头插法插入 cur 节点
            cur.next = dummyHead.next;
            dummyHead.next = cur;
        }
        
        return dummyHead.next;
    }
}

