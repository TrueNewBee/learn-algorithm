package vip.chentianxiang.learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/12 23:15
 * @Github: https://github.com/TrueNewBee
 * @Description: 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
public class LeetCode5 {
    public List<List<Integer>> treeSum(int[] nums) {
        // 由于对顺序不做要求,首先进行排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; ++i) {
            int x = nums[i];
            // 跳过重复数字
            if (i > 0 && x == nums[i - 1]) {
                continue;
            }
            // 由于该数组是递增的,相邻3个数之和都已经大于0了,后面都肯定大于0,所以没必要,直接break即可
            if (x + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            // 如果当前枚举值 + 最后面两个数字之和 已经小于0,就没必要遍历当前枚举值 到n-2 之间元素之值了,
            // 肯定都小于0的,直接跳过该枚举值即可
            if (x + nums[n - 2] + nums[n - 1] < 0) {
                continue;
            }
            // 开始进行 该枚举值后面的 2数之和
            int j = i + 1, k = n - 1;
            while (j < k) {
                // 3数之和
                int s = x + nums[j] + nums[k];
                // 3数之和大于0, 最右指针向左移,最后的数大了, 反之最左指针向右移动
                if (s > 0) {
                    --k;
                } else if (s < 0) {
                    ++j;
                } else {
                    // 这个添加list的api List.of 应该是leetCode已知可以用的,反正idea是报错
                    ans.add(List.of(x, nums[j], nums[k]));
                    // 对j去重
                    for (++j; j < k && nums[j] == nums[j - 1]; ++j);
                    // 对k去重
                    for (--k; k > j && nums[k] == nums[k + 1]; --k);
                }
            }

        }
        return ans;
    }
}
