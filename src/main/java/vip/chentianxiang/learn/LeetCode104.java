package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/31 22:15
 * @Github: https://github.com/TrueNewBee
 * @Description: 二叉树的最大深度
 */
public class LeetCode104 {

    public int maxDepth(TreeNode root) {

        // 递归就是 父类问题和子类问题都是共同公式,不关心中间过程,只关心开始和结束
        // 中间过程交给数学归纳法

        // 如果节点为空,直接返回0
        if (root == null) {
            return 0;
        }
        // 递归左节点
        int l_depth = maxDepth(root.left);
        // 递归右节点
        int r_depth = maxDepth(root.right);
        // 返回 深度最大值+1 (1为当前节点)
        return Math.max(l_depth, r_depth)+1;

    }

    // 方法2  有一个全局变量,每遍历完一次,更新ans全局变量,最后返回即可
    private int ans;

    public int maxDepth2(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        depth++;
        ans = Math.max(ans, depth);
        dfs(node.left, depth);
        dfs(node.right, depth);
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
