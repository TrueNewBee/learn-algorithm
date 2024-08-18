package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/18 22:15
 * @Github: https://github.com/TrueNewBee
 * @Description: 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 */
public class LeetCode713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // l r
        // [l,r] [l+1,r].... [r,r] // 所有l到r之间的子数组
        // r-l+1
        if (k <= 1) {
            return 0;
        }
        int ans =0;
        int prod=1;
        int left = 0;
        // 遍历数组,右指针向右移动
        for (int right = 0; right <= nums.length ; right++) {
            prod *= nums[right];    // 更新成绩
            while (prod >=k){ // 不满足要求
                //缩小窗口
                prod /= nums[left++];
            }
            // 更新值
            ans += right -left +1;
        }
        return ans;
    }
}
