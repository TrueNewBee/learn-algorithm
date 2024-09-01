package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/9/1 22:49
 * @Github: https://github.com/TrueNewBee
 * @Description: 平衡二叉树
 */
public class LeetCode110 {

    public boolean isBalanced(TreeNode root) {
       return  getHeight(root) != -1;
    }

    public int getHeight(TreeNode node) {
        // 如果节点为空就返回 0
        if (node == null) {
            return 0;
        }
        // 递归左节点获取高度
        int leftHeight = getHeight(node.left);
        // 如果高度为-1 就向上一直返回
        if (leftHeight == -1) {
            return -1;
        }
        // 递归右节点获取高度
        int rightHeight = getHeight(node.right);
        // 如果右节点高度为-1或者 左右高度差绝对值大于1 则返回-1
        if (rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        // 返回左右高度最大值+1 (1为当前节点)
        return Math.max(leftHeight,rightHeight) +1;
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
