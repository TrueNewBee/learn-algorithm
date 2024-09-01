package vip.chentianxiang.learn;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2024/9/1 23:46
 * @Github: https://github.com/TrueNewBee
 * @Description: 二叉树的右视图
 */
public class LeetCode199 {


    public List<Integer> rightSideView(TreeNode root) {
        // 答案数组
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0,ans);
        return ans;
    }

    public void dfs(TreeNode node, int depth,List<Integer> ans) {
        // 如果节点为空就跳
        if (node == null) {
            return;
        }
        // 如果深度等于数组长度就将数字添加到数组中
        if (depth == ans.size()) {
            ans.add(node.val);
        }
        // 由于是右视图,先递归右节点 (如果左视图先递归左节点)
        dfs(node.right, depth + 1,ans);
        dfs(node.left, depth + 1,ans);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
