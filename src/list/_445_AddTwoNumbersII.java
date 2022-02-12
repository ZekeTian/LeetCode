package list;

import java.util.Stack;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/add-two-numbers-ii/
 * 
 * 题目描述：给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *        你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *        
 * 限制条件：
 *  （1）链表的长度范围为 [1, 100]
 *  （2）0 <= node.val <= 9
 *  （3）输入数据保证链表代表的数字无前导 0
 *  
 * 示例：
 *  输入：l1 = [7,2,4,3], l2 = [5,6,4]
 *  输出：[7,8,0,7]
 *
 */
public class _445_AddTwoNumbersII {

    public static void main(String[] args) {
        // test case1, output: [7,8,0,7]
//        int[] nums1 = { 7, 2, 4, 3 };
//        int[] nums2 = { 5, 6, 4 };
        
        // test case2, output: [1,0]
        int[] nums1 = { 5 };
        int[] nums2 = { 5 };
        
        ListNode l1 = ListUtil.createList(nums1);
        ListNode l2 = ListUtil.createList(nums2);
        
//        _445Solution1 solution = new _445Solution1();

        _445Solution2 solution = new _445Solution2();

//        _445Solution3 solution = new _445Solution3();
        
        ListNode head = solution.addTwoNumbers(l1, l2);
        
        ListUtil.print(head);
    }
}


/**
 * 解法一：反转链表，然后相加
 */
class _445Solution1 {
    
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
    
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode head = null; // 结果链表的头节点
        ListNode tail = null; // 结果链表的尾节点
        boolean carryFlag = false;
        
        while (cur1 != null || cur2 != null) {
            int num1 = 0, num2 = 0;
            
            if (cur1 != null) {
                num1 = cur1.val;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                num2 = cur2.val;
                cur2 = cur2.next;
            }
            // 相加，然后判断是否进位
            int sum = (carryFlag ? num1 + num2 + 1 : num1 + num2);
            ListNode node = new ListNode(sum % 10);
            carryFlag = (sum > 9);
            
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node; // 结果链表向后移动
        }
        
        // 判断最高位是否进位
        if (carryFlag) {
            tail.next = new ListNode(1); // 如果最高位进位，则再加一个 1
            tail = tail.next;
        }
        
        // 反转结果链表，即可得到最终的链表
        head = reverse(head);
        
        return head;
    }
}

/**
 * 解法二：不直接反转链表，而是利用栈辅助，相当于间接反转链表
 */
class _445Solution2 {
    
    // 将 head 对应链表转换成栈
    private Stack<Integer> convert2Stack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        
        return stack;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 将 l1、l2 转换成栈
        Stack<Integer> s1 = convert2Stack(l1);
        Stack<Integer> s2 = convert2Stack(l2);
        
        // 模拟竖式相加
        Stack<Integer> s3 = new Stack<>();
        boolean carryFlag = false;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int num1 = (s1.isEmpty() ? 0 : s1.pop());
            int num2 = (s2.isEmpty() ? 0 : s2.pop());
            int sum = (carryFlag ? num1 + num2 + 1 : num1 + num2);
            carryFlag = (sum > 9);
            s3.push(sum % 10);
        }
        
        // 判断最高位是否进位
        if (carryFlag) {
            s3.push(1);
        }
        
        // 将栈 s3 的数据转换成链表节点
        ListNode head = null, tail = null; // 结果链表的头节点、尾节点
        while (!s3.isEmpty()) {
            ListNode node = new ListNode(s3.pop());
            
            if (null == head) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        
        return head;
    }
}

/**
 * 解法三：将两个链表补成长度一样，然后递归到两个链表的结尾处，然后从后面开始向前相加。
 *      即将如下两个链表
 *          7 -> 2 -> 4 -> 3
 *               5 -> 6 -> 4
 *      中的第 2 个链表补长，变成如下两个链表
 *          7 -> 2 -> 4 -> 3
 *          0 -> 5 -> 6 -> 4 （用 0 进行补充）
 */
class _445Solution3 {
    
    private int length(ListNode head) {
        int len = 0;
        ListNode cur = head;
        
        while (cur != null) {
            ++len;
            cur = cur.next;
        }
        
        return len;
    }
    
    // 将长度相同的两个链表 l1、l2 相加（模拟竖式相加）
    private ListNode add(ListNode l1, ListNode l2) {
        if (l1.next == null) { // 递归到两个链表的结尾处，然后向前加
            return new ListNode(l1.val + l2.val); // 两个链表中最后一个节点的和
        }
        
        ListNode node = add(l1.next, l2.next);
        ListNode ret = null;
        if (node.val > 9) { // 存在进位
            ret = new ListNode(l1.val + l2.val + 1);
            node.val = node.val % 10;
        } else { // 不存在进位
            ret = new ListNode(l1.val + l2.val);
        }
        
        ret.next = node;
        return ret;
    }
    
    // 在 head 对应链表的前面补充 k 个 0
    private ListNode fillZero(ListNode head, int k) {
        for (int i = 0; i < k; ++i) {
            ListNode newHead = new ListNode(0);
            newHead.next = head;
            head = newHead;
        }
        
        return head;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 统计出 len1、len2 的长度
        int len1 = length(l1);
        int len2 = length(l2);
        
        ListNode head1 = l1, head2 = l2;
            
        // 为短链表补充 0 ，从而使得两个链表的长度一样长
        if (len1 > len2) { 
            head2 = fillZero(l2, len1 - len2);
        } else if (len1 < len2) {
            head1 = fillZero(l1, len2 - len1);
        }
        
        // 两个链表的长度一样长之后，利用递归模拟竖式相加
        ListNode newHead = add(head1, head2);
        
        // 判断最高位是否存在进位
        if (newHead.val < 10) {
            return newHead; // 最高位无进位
        }
        // 最高位存在进位
        newHead.val = newHead.val % 10;
        ListNode ret = new ListNode(1);
        ret.next = newHead;
        
        return ret;
    }
}

