package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/18 21:12
 * @Github: https://github.com/TrueNewBee
 * @Description: 给定一个含有 `n` 个正整数的数组和一个正整数 `target` 。
 * 找出该数组中满足其总和大于等于 `target` 的长度最小的
 * 子数组
 * [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。**如果不存在符合条件的子数组，返回 0 。
 */
public class LeetCode209 {
    // 直接用左右双指针滑动窗口进行解题
    // 遍历数字,右指针一直向右移动,while使缩小子数组长度,左指针右移
    // 循环外更新
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int ans = n + 1;  // 默认返回n+1,也就是数组总长度,
        int left = 0;   // 左端点
        int sum = 0;    // 子数组之和
        for (int right = 0; right < n; right++) { // 枚举子数组右端点
            sum += nums[right];
            // 尽量缩小子数组长度
            while (sum - nums[left] >= target) {
                sum -= nums[left++]; // 左端点右移动
            }
            if (sum >= target) {
                // 判断 right-left什么时候+1 ,就是假设right=left时候,需要返回值,就+1
                ans = Math.min(ans, right - left + 1);
            }
        }
        return ans <= n ? ans : 0;
    }

    // 循环内更新
    public int minSubArrayLen2(int target, int[] nums) {
        int n = nums.length;
        int ans = n + 1;  // 默认返回n+1,也就是数组总长度,
        int left = 0;   // 左端点
        int sum = 0;    // 子数组之和
        for (int right = 0; right < n; right++) { // 枚举子数组右端点
            sum += nums[right];
            // 当满足要求,sum>=target ,先更新ans,然后右端点右移
            while (sum  >= target) {
                ans = Math.min(ans, right - left + 1);
                sum -= nums[left++]; // 左端点右移动
            }
        }
        return ans <= n ? ans : 0;
    }


}
