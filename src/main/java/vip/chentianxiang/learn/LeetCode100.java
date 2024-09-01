package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/9/1 21:42
 * @Github: https://github.com/TrueNewBee
 * @Description: 左右对称二叉树
 */
public class LeetCode100 {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p==q;  // 必须都是 null
        }

        // 左右树节点值相等,并且左右节点继续递归,最终返回递归后的结果
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}


