package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/13 22:17
 * @Github: https://github.com/TrueNewBee
 * @Description: 盛水最多的容器 https://leetcode.cn/problems/container-with-most-water/
 * 给定一个长度为 `n` 的整数数组 `height` 。有 `n` 条垂线，第 `i` 条线的两个端点是 `(i, 0)` 和 `(i, height[i])` 。
 * 找出其中的两条线，使得它们与 `x` 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 */
public class LeetCode11 {
    public int maxArea(int[] height) {
        // 采用双指针暴力解题
        // 初始化水桶面积
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        while (left <right){
            // 计算当前两条线之间面积
            int area = (right - left)* Math.min(height[left],height[right]);
            // 取最大的面积
            ans = Math.max(ans, area);
            //  谁短谁移动
            if (height[left] < height[right]) left++;
            else right--;
        }
        return ans;
    }
}
