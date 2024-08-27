package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/27 23:17
 * @Github: https://github.com/TrueNewBee
 * @Description: 82. 删除排序链表中的重复元素
 * // 思路
 * // 先初始化一个哨兵链表  dummy,  cur指向哨兵,开始比较 循环 cur.next和cur.next.next
 * // 如果两个值相同,则在循环next后面的值, 如果相同,就删除, 如果不同cur正常移动一位
 */
public class LeetCode82 {
    public ListNode deleteDuplicates(ListNode head) {
        // 初始化一个哨兵链表
        ListNode dummy = new ListNode(1, head);
        // 初始化一个cur节点
        ListNode cur = dummy;
        // 循环遍历 next和next.next 即cur后面两个节点(如果不为空)
        while (cur.next != null && cur.next.next != null) {
            int val = cur.next.val;
            // 如果next和next.next 值相同,则循环遍历next.next后面节点,相同则删除next.next
            // 如果值不同,则右移一位即可
            if (cur.next.next.val == val) {
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                // 右移一位
                cur = cur.next;
            }
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
