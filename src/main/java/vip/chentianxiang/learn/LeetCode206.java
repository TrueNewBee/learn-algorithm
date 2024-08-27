package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/25 16:48
 * @Github: https://github.com/TrueNewBee
 * @Description: 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 */
public class LeetCode206 {
     // head -- cur
     //然后倒序,头作为 next变成pre
    // 头  1->2->3->4->5  尾
    // 1<-2<-3<-4<-5
    // 时间 O(n)   空间 O(1)
    public ListNode reverseList(ListNode head) {
        // 反转后的头节点
        ListNode pre = null;
        ListNode cur = head;
        while (cur !=null){
            // 暂存当且节点下一个值
            ListNode next = cur.next;
            // 将头节点反转, 第一个的next为null
            cur.next  = pre;
            // pre为新的头结点
            pre = cur;
            // cur为下一个节点
            cur = next;
        }
        return pre;

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
