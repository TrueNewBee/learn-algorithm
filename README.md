## 算法学习

### 相向指针↓

### 1.  167.两数之和2-输入有序数组  

![image-20240809230317575](https://gitee.com/TrueNewBee/typora/raw/master/167-%E4%B8%A4%E6%95%B0%E4%B9%8B%E5%92%8C-%E8%BE%93%E5%85%A5%E6%9C%89%E5%BA%8F%E6%95%B0%E7%BB%84.png)

```java
两数之和: 由于数组是有序的,所以 采用双指针解法,头和尾各一个指针(即最小和最大数字), 如果之和大于target数,则右指针向左移动 反之 之和小于target,则左指针像右移动,一直循环知道之和为target
(ps: 题设中条件必须要用到, 通过花费o(1)时间复杂度,可以知道整个O(n)的信息)
```

**java代码**

```java
public int[] twoSum(int[] numbers, int target) {
    // 时间复杂度 O(n)
    // 空间复杂度 O(1)
        int left = 0;
        int right = numbers.length - 1;
        while(true){
            int s = numbers[left] + numbers[right];
            if (s == target){
                // 题目要求下标从 1 开始 
                return new int[]{left+1,right+1};
            }

            if (s >target){
                // 如果 2数之和大于target 则右边指针向左移动
                right--;
            }else {
                // 如果 2数之和小于target 则左边指针向右移动
                left++;
            }
        }
    }
```

### 2.  15.三数之和

![image-20240812225422604](https://gitee.com/TrueNewBee/typora/raw/master/15.三数之和.png)

````java
思路:
1. 由于题目中对顺序没有要求,那就按照 两数之和那样,直接排序
2. 三数之和可以看做, 第一个元素 与 后面两数之和 相加, 就转换成了, 
遍历数组第一个枚举值  +  两数之和, 后面两数之和 取首尾双指针的值 + 枚举值 = 0 即可
    
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
````



### 3. 11 [盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/)

给定一个长度为 `n` 的整数数组 `height` 。有 `n` 条垂线，第 `i` 条线的两个端点是 `(i, 0)` 和 `(i, height[i])` 。

找出其中的两条线，使得它们与 `x` 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

**说明：**你不能倾斜容器。



**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/question_11.jpg)

```java
输入：[1,8,6,2,5,4,8,3,7]
输出：49 
解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
```

**示例 2：**

```java
输入：height = [1,1]
输出：1
```



```java
思路:
1.  水桶面积是两根线条距离*最短线条高度, 故可以采取双指针,从两头开始进行计算, 
2. 两端线条的面积为为初始化面积, 两根线条短的移动,如果最新的面积比初始化面积大,就更新,直到 两个指针重合.
    
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
```

### 4. [42. 接雨水 ](https://leetcode.cn/problems/trapping-rain-water/description/)

给定 `n` 个非负整数表示每个宽度为 `1` 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/rainwatertrap.png)

```
输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
输出：6
解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
```

**示例 2：**

```
输入：height = [4,2,0,3,2,5]
输出：9
```



```java
思路:
可以类比上面盛水问题, 同样使用双指针,不过有区别的是,这次是算接水面积,
前置和后置指针分别相对移动, 分别记录 pre_max  前置最大值 和suf_max 后置最大值
由于盛水/接水面积 由于最短的线/最小高度决定, 所以谁短 移动谁,并且有效盛水面积就是当前max-height[i]
 其实就是 11题 的进阶版,有了一个减去当前高度的  很秀!! 暴力双指针
```

![image-20240813231054968](https://gitee.com/TrueNewBee/typora/raw/master/接雨水.png)



```java
java代码版
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

```

[盛最多水的容器 接雨水_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1Qg411q7ia/?spm_id_from=333.788&vd_source=394ba7019e7f10f68c4eca0cd2e58166)

**真的好离谱~~ 超越了100% 这个题思路真的牛逼 双指针YYDS,作者牛逼**

![image-20240813235641825](https://gitee.com/TrueNewBee/typora/raw/master/接雨水问题离大谱.png)



### 滑动窗口(左右指针 最长/最短)↓↓

### 5. [209. 长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum/)

// while>值

给定一个含有 `n` 个正整数的数组和一个正整数 `target` 。找出该数组中满足其总和大于等于 `target` 的长度最小的 

**子数组**

`[numsl, numsl+1, ..., numsr-1, numsr]` ，并返回其长度**。**如果不存在符合条件的子数组，返回 `0` 。

**示例 1：**

```
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
```

**示例 2：**

```
输入：target = 4, nums = [1,4,4]
输出：1
```

**示例 3：**

```
输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0
```

```java
// 直接用左右双指针滑动窗口进行解题
// 遍历数字,右指针一直向右移动,while使缩小子数组长度,左指针右移
//  时间复杂度 O(n)
//  空间复杂度 O(1)
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

```



### 6. [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/)

给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长子串** 的长度。

``这题解法很秀, 用到了boolean数组``

**示例 1:**

```
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

**示例 3:**

```
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```



```java
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
```





### 7.[713. 乘积小于 K 的子数组](https://leetcode.cn/problems/subarray-product-less-than-k/)

// while >=值

给你一个整数数组 `nums` 和一个整数 `k` ，请你返回子数组内所有元素的乘积严格小于 `k` 的连续子数组的数目。



**示例 1：**

```
输入：nums = [10,5,2,6], k = 100
输出：8
解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2]、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
```

**示例 2：**

```
输入：nums = [1,2,3], k = 0
输出：0
```



```java
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
```

