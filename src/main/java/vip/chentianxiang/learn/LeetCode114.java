package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/25 22:20
 * @Github: https://github.com/TrueNewBee
 * @Description: 环形链表
 * 其实就是876的快慢指针, 快指针比慢指针相对速度多1, 也就是说慢指针静止,快指针速度为,有环必相遇
 */
public class LeetCode114 {
    // 快指针每次移动2个 慢指针每次移动一个
    // 当快指针的下一个为空或者快指针为空的时候,慢指针就是最终中间节点(数学归纳法)
    public boolean hasCycle(ListNode head) {
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
                return true;
            }
        }
        // 如果不存在快慢节点相遇,返回false
        return false;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    
    
}
