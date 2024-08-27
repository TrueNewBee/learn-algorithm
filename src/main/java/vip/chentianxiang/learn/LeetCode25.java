package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/25 19:31
 * @Github: https://github.com/TrueNewBee
 * @Description: 困难
 *
 */
public class LeetCode25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        int n =0;
        ListNode cur1 = head;
        // 计算链表长度
        while (cur1 !=null){
            n++;
            cur1 = cur1.next;
        }
        // 初始化一个哨兵链表
        ListNode dummy = new ListNode(0, head);
        // 哨兵节点
        ListNode p0 = dummy;
        // 头节点
        ListNode pre = null;
        // 用于保存next节点
        ListNode cur = p0.next;
        while (n>=k){
            n-=k;  // 每次只变动k个节点,先减去k
            for (int i = 0; i < k; i++) {
                // 下下一个节点
                ListNode next = cur.next;   // 每次循环只修改一个 next，方便大家理解
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            // 保留变前的哨兵节点next值
            ListNode nxt = p0.next;
            p0.next.next = cur;
            p0.next = pre;
            // 反转后的第二组链表哨兵节点,就是翻转前哨兵节点的next
            p0= nxt;
        }

        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
