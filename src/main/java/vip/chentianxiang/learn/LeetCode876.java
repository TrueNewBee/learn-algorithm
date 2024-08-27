package vip.chentianxiang.learn;

import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/25 21:24
 * @Github: https://github.com/TrueNewBee
 * @Description:
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 */
public class LeetCode876 {

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 循环 fast节点不为空或者fast的next节点不为空
        while (fast != null && fast.next != null) {
            // 慢节点向后移动一位
            slow = slow.next;
            // 快节点向后移动两位
            fast = fast.next.next;
        }
        // 循环结束后,慢节点就是中间节点
        return slow;

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
