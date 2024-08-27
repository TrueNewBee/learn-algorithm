package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/25 19:02
 * @Github: https://github.com/TrueNewBee
 * @Description:
 * 给你单链表的头指针 `head` 和两个整数 `left` 和 `right` ，其中 `left <= right` 。请你反转从位置 `left` 到位置 `right` 的链表节点，返回 **反转后的链表** 。
 */
public class LeetCode92 {

    public ListNode reverseBetween(LeetCode92.ListNode head, int left, int right) {
        // 初始化一个哨兵链表
        ListNode dummy = new ListNode(0, head);
        // 哨兵节点
        ListNode p0 = dummy;
        // 找到需要反转节点的前一个节点
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }
        // 头节点
        ListNode pre = null;
        // 用于保存next节点
        ListNode cur = p0.next;
        for (int i = 0; i < right-left+1; i++) {
            // 下下一个节点
            ListNode next = cur.next;   // 每次循环只修改一个 next，方便大家理解
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        p0.next.next = cur;
        p0.next = pre;
        return dummy.next;

    }

    public class ListNode {
        int val;
        LeetCode92.ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, LeetCode92.ListNode next) { this.val = val; this.next = next; }
    }
}
