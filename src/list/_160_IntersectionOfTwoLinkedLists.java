package list;

import java.util.HashSet;
import java.util.Set;

import datastructure.ListNode;
import datastructure.ListUtil;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * 
 * 题目描述：给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 *      
 *        a1 -> a2
 *                \
 *                c1 -> c2 -> c3
 *                /
 *  b1 -> b2 -> b3
 *  如上图所示的两个链表，它们相交于 c1
 * 题目数据保证整个链式结构中不存在环。注意，函数返回结果后，链表必须 保持其原始结构 。
 *
 * 条件限制：
 *  （1）listA 中节点数目为 m
 *  （2）listB 中节点数目为 n
 *  （3）0 <= m, n <= 3 * 10^4
 *  （4）1 <= Node.val <= 10^5
 *  （5）0 <= skipA <= m
 *  （6）0 <= skipB <= n
 *  （7）如果 listA 和 listB 没有交点，intersectVal 为 0
 *  （8）如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
 *  
 * 示例：
 * 示例 1：
 * A 链表：     4 -> 1 
 *                  \
 *                   8 -> 4 -> 5
 *                  /
 * B 链表：5 -> 0 -> 1
 * A、B 链表相交于 8 
 * 
 * 示例 2：
 * A 链表： 2 -> 6 -> 4
 * B 链表： 1 -> 5
 *
 */
public class _160_IntersectionOfTwoLinkedLists {

    public static void main(String[] args) {
        // test case 1, output: 8
//        int[] numsA = {4, 1, 8, 4, 5};
//        int[] numsB = {5, 0, 1};
//        ListNode listA = ListUtil.createList(numsA);
//        ListNode listB = ListUtil.createList(numsB);
//        
//        ListNode intersecionNode = ListUtil.get(listA, 2);
//        ListUtil.lastElement(listB).next = intersecionNode;
        
        // test case, output: none
        int[] numsA = {2, 6, 4};
        int[] numsB = {1, 5};
        ListNode listA = ListUtil.createList(numsA);
        ListNode listB = ListUtil.createList(numsB);
        
        
        _160Solution1 solution = new _160Solution1();
        
        
        ListNode resultNode = solution.getIntersectionNode(listA, listB);
        if (null == resultNode) {
            System.out.println("None");
        } else {
            System.out.println(resultNode.val);
        }
    }
}

/**
 * 解法一：使用 Set 
 */
class _160Solution1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = headA;
        
        while (null != cur) {
            set.add(cur);
            cur = cur.next;
        }
        
        cur = headB;
        while (null != cur) {
            if (set.contains(cur)) { // set 中找到 cur，则说明 cur 是 ListA、ListB 第一个公共节点，即两个链表的交点
                return cur;
            }
            cur = cur.next;
        }
        
        return null;
    }
}

