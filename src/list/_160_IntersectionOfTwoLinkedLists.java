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
        int[] numsA = {4, 1, 8, 4, 5};
        int[] numsB = {5, 0, 1};
        ListNode listA = ListUtil.createList(numsA);
        ListNode listB = ListUtil.createList(numsB);
        
        ListNode intersecionNode = ListUtil.get(listA, 2);
        ListUtil.lastElement(listB).next = intersecionNode;
        
        // test case, output: none
//        int[] numsA = {2, 6, 4};
//        int[] numsB = {1, 5};
//        ListNode listA = ListUtil.createList(numsA);
//        ListNode listB = ListUtil.createList(numsB);
        
        
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

/**
 * 解法二：使用双指针
 * 设置两个指针 a、b：
 *  a 指针，在遍历完 listA 之后，遍历 listB；否则，继续遍历 listA；
 *  b 指针，在遍历完 listB 之后，遍历 listA；否则，继续遍历 listB。
 * 
 * A、B 两个链表有交点时：
 *    A 链表： A1 -> A2 -> ... -> Ai
 *                                \
 *                                 C1 -> C2 -> ... -> Cn
 *                                /
 *    B 链表： B1 -> B2 -> ... -> Bj
 * 
 * 将 A 链表中 [A1, Ai] 这段区间的长度记为 a；B 链表中 [B1, Bj] 这段区间的长度记为 b；后面公共部分 [C1, Cn] 这段区间的长度记为 c。
 * a 指针到达 C1 节点时走过的长度 La = a + c + b
 * b 指针到达 C1 节点时走过的长度 Lb = b + c + a
 * 因为 La = Lb，并且 a、b 指针的速度一样，故 a、b 指针到达 C1 节点花费的时间也一样，又因为 a、b 同时出发，所以 a、b 会同时到达 C1 节点（即 a、b 在 C1 节点相遇）
 * 
 * 
 * A、B 两个链表无交点时：
 *    A 链表： A1 -> A2 -> ... -> Ai
 *    B 链表： B1 -> B2 -> ... -> Bj
 *    
 * 将 A 链表的长度记为 a，B 链表的长度记为 b。
 * a 指针在遍历完 listA 之后又会遍历 listB，当到达 listB 的结尾时，a 指针走过的长度 La = a + b
 * b 指针在遍历完 listB 之后又会遍历 listA，当到达 listA 的结尾时，b 指针走过的长度 Lb = b + a
 * 因为 La = Lb，并且 a、b 指针的速度一样，故 a、b 指针各自到达链表结尾（a 到达 listB 的结尾，b 到达 listA 的结尾）花费的时间也一样，
 * 又因为 a、b 同时出发，所以 a、b 指针同时到达对应链表结尾（即 a、b 最终同时都指向 null，相当于在 null 节点相遇）
 */
class _160Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode aPtr = headA; // a 指针，在遍历完 listA 之后，遍历 listB；否则，继续遍历 listA
        ListNode bPtr = headB; // b 指针，在遍历完 listB 之后，遍历 listA；否则，继续遍历 listB
        
        // 无论 listA、listB 两个链表是否有交点，a、b 两个指针按照上述的遍历方式遍历 listA、listB，最终一定会相遇，所以 while 循环一定会结束
        // 当 while 循环结束时，如果 listA、listB 两个链表有交点，则 a、b 两个指针同时指向交点，直接返回 a、b 指针中的一个即可；
        // 否则，a、b 指针同时指向 null（即同时到两个链表的结尾）
        while (aPtr != bPtr) {
            
            if (null == aPtr) {
                aPtr = headB; // 开始遍历 listB
            } else {
                aPtr = aPtr.next;
            }
            
            if (null == bPtr) {
                bPtr = headA; // 开始遍历 listA
            } else {
                bPtr = bPtr.next;
            }
        }
        
        return (aPtr == null) ? null : aPtr;
    }
}
