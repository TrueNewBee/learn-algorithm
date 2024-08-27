package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/27 22:57
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public class LeetCode83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // cur指向头姐点
        ListNode cur  = head;
        while (cur.next != null) {
            // 如果next的值与当前节点值相同,则删除该节点
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            } else {
                // 反之则右移
                cur = cur.next;
            }
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
