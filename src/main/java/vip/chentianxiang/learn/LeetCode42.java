package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/13 22:58
 * @Github: https://github.com/TrueNewBee
 * @Description: 接雨水问题
 * 思路:
 * 可以类比上面盛水问题, 同样使用双指针,不过有区别的是,这次是算接水面积,
 * 前置和后置指针分别相对移动, 分别记录 pre_max  前置最大值 和suf_max 后置最大值
 * 由于盛水/接水面积 由于最短的线/最小高度决定, 所以谁短 移动谁,并且有效盛水面积就是当前max-height[i]
 * 其实就是 11题 的进阶版,有了一个减去当前高度的  很秀!! 暴力双指针
 */
public class LeetCode42 {
    public int trap(int[] height) {
        // 初始化数组长度
        int n = height.length;
        int ans = 0;
        int left = 0;
        int right = n - 1;
        // 前缀指针最大高度
        int pre_max = 0;
        // 后缀指针最大高度
        int suf_max = 0;
        while (left <= right) {
            pre_max = Math.max(pre_max, height[left]);
            suf_max = Math.max(suf_max, height[right]);
            if (pre_max < suf_max) {
                // 如果前缀最大高度小于后缀最大高度,水桶容量就是 前缀高度-数组高度,并且谁短谁移动
                // ans 进行每次累加
                ans += pre_max - height[left];
                left++;
            } else {
                ans += suf_max - height[right];
                right--;
            }
        }
        return ans;
    }
}
