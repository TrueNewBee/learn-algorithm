package vip.chentianxiang.learn;

import java.util.HashMap;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/18 23:04
 * @Github: https://github.com/TrueNewBee
 * @Description: 给定一个字符串 `s` ，请你找出其中不含有重复字符的 最长子串的长度。
 */
public class LeetCode3 {

    // 用了boolean数组,真的秀
    //时间复杂度：O(n)，其中 n 为 s 的长度。注意 left 至多增加 n 次，所以整个二重循环至多循环 O(n) 次。
    // 空间复杂度：O(∣Σ∣)，其中 ∣Σ∣ 为字符集合的大小，本题中字符均为 ASCII 字符，所以 ∣Σ∣≤128。
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray(); // 转换成 char[] 加快效率（忽略带来的空间消耗）
        int n = chars.length, ans = 0, left = 0;
        boolean[] has = new boolean[128]; // 也可以用 HashSet<Character>，这里为了效率用的数组
        for (int right = 0; right < n; right++) {
            char c = chars[right];
            // 如果窗口内已经包含 c，那么再加入一个 c 会导致窗口内有重复元素
            // 所以要在加入 c 之前，先移出窗口内的 c
            while (has[c]) { // 窗口内有 c
                has[chars[left++]] = false; // 缩小窗口
            }
            has[c] = true; // 加入 c
            ans = Math.max(ans, right - left + 1); // 更新窗口长度最大值
        }
        return ans;
    }
}
