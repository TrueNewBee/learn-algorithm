package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/27 22:29
 * @Github: https://github.com/TrueNewBee
 * @Description: 19. 删除链表的倒数第 N 个结点
 * 初始化一个 dummyNode, 然后当right右移动n个节点, 开始移动left 与right同时向右移动,之间距离为n ,
 * 当right移动到最后一个节点时候,刚好left移动到倒数n+1, 直接left的next直接指向next.next即可
 */
public class LeetCode19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 初始化一个哨兵链表
        ListNode dummy = new ListNode(1, head);
        ListNode right = dummy;
        // 先把right向右移动n个
        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        ListNode left = dummy;
        // left和right同时向右移动,之间差n个
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        // right移动到最后一个,left是在倒数n+1位置,所以直接 删除倒数第n个位置节点即可
        left.next = left.next.next;
        return  dummy.next;

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
