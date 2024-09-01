package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/9/1 22:18
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public class LeetCode101 {

    public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    public boolean isSameTree(LeetCode100.TreeNode p, LeetCode100.TreeNode q) {
        if (p == null || q == null) {
            return p==q;  // 必须都是 null
        }

        // 同一个根节点,由于是判断是否是对称的,所以 p.left 和 q.right 比较
        return p.val == q.val && isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }

    public class TreeNode {
        int val;
        LeetCode100.TreeNode left;
        LeetCode100.TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, LeetCode100.TreeNode left, LeetCode100.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}


