package list;

import java.util.Stack;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/palindrome-linked-list/
 * 
 * 题目描述：给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * 
 * 限制条件：
 *  （1）链表中节点数目在范围[1, 10^5] 内
 *  （2）0 <= Node.val <= 9
 * 
 * 示例：
 *  示例 1
 *      输入：head = [1,2,2,1]
 *      输出：true
 *  
 *  示例 2
 *      输入：head = [1,2]
 *      输出：false
 *
 */
public class _234_PalindromeLinkedList {

    public static void main(String[] args) {
        // test case1, output: true
        int[] nums = { 1, 2, 2, 1 };
        
        // test case2, output: false
//        int[] nums = { 1, 2 };
        
        ListNode head = ListUtil.createList(nums);
        
//        _234Solution1 solution = new _234Solution1();
        
//        _234Solution2 solution = new _234Solution2();

        _234Solution3 solution = new _234Solution3();
        
        
        System.out.println(solution.isPalindrome(head));
    }
}

/**
 * 解法一：利用栈反转
 */
class _234Solution1 {
    
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        
        cur = head;
        while (cur != null) {
            if (stack.pop() != cur.val) {
                return false;
            }
            
            cur = cur.next;
        }
        
        return true;
    }
}

/**
 * 解法二：使用递归
 *      使用两个指针，刚开始时，这两个指针都指向头节点。然后，其中一个指针在递归过程中向前移动，当递归到结尾处时，会向上返回。
 *      在返回的过程，另一个指针再开始向前移动，这样就可以形成：一个指针向前移动，一个指针向后移动。
 */
class _234Solution2 {
    
    private ListNode forward = null; // 该指针负责向前移动
    
    // head 指针先递归向前移动，当递归到结尾处时，向后移动
    private boolean palindrome(ListNode head) {
        if (null == head) {
            return true; // 递归结束时，返回 true。相当于是一个空链表时，此时也是回文。
        }
        
        boolean ret = palindrome(head.next); // 继续向下递归
        // 递归返回时，判断当前节点是否与 forward 相等
        ret = ret && (forward.val == head.val);
        // 在继续向上递归之前，forward 先向前移动
        forward = forward.next;
        
        return ret;
    }
    
    public boolean isPalindrome(ListNode head) {
        forward = head;
        
        return palindrome(head);
    }
}

/**
 * 解法三：使用双指针
 * 思路：
 *  （1）先找到中点（注意：当节点数量是偶数时，中点是靠前的节点；当节点数量是奇数时，中点是正中间的节点）
 *  （2）反转后面部分的链表
 *  （3）一个指针从前面部分的链表开始向前遍历，另一个指针从后面反转后的链表开始向前遍历
 *  （4）如果两个指针对应的节点不相等，则不是回文；否则，继续向前遍历。如果最终能遍历完，则说明是回文。
 * 
 * 获取链表的中点，有如下两种写法，但是当链表的长度是偶数时，获取的中点略有不同：
 *  （1）while (fast != null && fast.next != null) { // 如果长度是偶数，中间点是偏后的节点
 *  （2）while (fast != null && fast.next != null && fast.next.next != null) { // 如果长度是偶数，中间点是偏前的节点
 */
class _234Solution3 {
    
    // 返回 head 对应链表的中间节点
    private ListNode middle(ListNode head) {
        ListNode fast = head, slow = head;
        
        while (fast != null && fast.next != null && fast.next.next != null) { // 如果长度是偶数，中间点是偏前的节点
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
    
    // 反转 head 链表
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
    
    public boolean isPalindrome(ListNode head) {
        // 找到中点，然后将链表一分为二
        ListNode mid = middle(head);
        ListNode reverseHead = mid.next;
        mid.next = null;
        
        // 反转后面部分的链表
        reverseHead = reverse(reverseHead);
        
        // 同时遍历两个链表，判断对应节点是否相等
        ListNode cur1 = head;
        ListNode cur2 = reverseHead;
        while (cur2 != null) {
            if (cur1.val != cur2.val) {
                return false; // 对应节点不相等，则说明不是回文
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        
        return true; // 能够遍历完，则说明是回文
    }
}

