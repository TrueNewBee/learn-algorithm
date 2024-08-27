package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/25 22:46
 * @Github: https://github.com/TrueNewBee
 * @Description: 142. 环形链表 II
 */
public class LeetCode142 {
    // 快指针每次移动2个 慢指针每次移动一个
    // 当快指针的下一个为空或者快指针为空的时候,慢指针就是最终中间节点(数学归纳法)
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 循环 fast节点不为空或者fast的next节点不为空
        while (fast != null && fast.next != null) {
            // 慢节点向后移动一位
            slow = slow.next;
            // 快节点向后移动两位
            fast = fast.next.next;
            // 如果快慢节点相遇了
            if (slow.equals(fast)) {
                // 如果fast和slow相遇, 则head 和slow会在入口处相遇,所以看笔记题解
                while (slow != head) {
                    slow = slow.next;
                    head = head.next;
                }
                return  slow;
            }
        }
        // 如果不存在,返回null
        return  null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
