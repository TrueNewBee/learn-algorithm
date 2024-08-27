package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/27 22:07
 * @Github: https://github.com/TrueNewBee
 * @Description: 237. 删除链表中的节点
 * 不知道head, 题目意思只要节点不存在即可,所以 直接把要删除节点copy下一个,下一个变成下下一个
 * 直接删除
 */
public class LeetCode237 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public class ListNode {
        int val;
        LeetCode142.ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, LeetCode142.ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
