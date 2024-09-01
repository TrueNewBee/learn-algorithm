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



### 二分查找树 [搜索旋转排序数组【基础算法精讲 05】_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1AP41137w7/?spm_id_from=333.788)

### 8. [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

给你一个按照非递减顺序排列的整数数组 `nums`，和一个目标值 `target`。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 `target`，返回 `[-1, -1]`。

你必须设计并实现时间复杂度为 `O(log n)` 的算法解决此问题。

**示例 1：**

```
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
```

**示例 2：**

```
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
```



### 9. [162. 寻找峰值 ](https://leetcode.cn/problems/find-peak-element/) [搜索旋转排序数组【基础算法精讲 05】_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1QK411d76w/?spm_id_from=333.788)

峰值元素是指其值严格大于左右相邻值的元素。

给你一个整数数组 `nums`，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 **任何一个峰值** 所在位置即可。

你可以假设 `nums[-1] = nums[n] = -∞` 。

你必须实现时间复杂度为 `O(log n)` 的算法来解决此问题。

**示例 1：**

```
输入：nums = [1,2,3,1]
输出：2
解释：3 是峰值元素，你的函数应该返回其索引 2。
```

**示例 2：**

```
输入：nums = [1,2,1,3,5,6,4]
输出：1 或 5 
解释：你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
```



### 10. [153. 寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/)

已知一个长度为 `n` 的数组，预先按照升序排列，经由 `1` 到 `n` 次 **旋转** 后，得到输入数组。例如，原数组 `nums = [0,1,2,4,5,6,7]` 在变化后可能得到：

- 若旋转 `4` 次，则可以得到 `[4,5,6,7,0,1,2]`
- 若旋转 `7` 次，则可以得到 `[0,1,2,4,5,6,7]`

注意，数组 `[a[0], a[1], a[2], ..., a[n-1]]` **旋转一次** 的结果为数组 `[a[n-1], a[0], a[1], a[2], ..., a[n-2]]` 。

给你一个元素值 **互不相同** 的数组 `nums` ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 **最小元素** 。

你必须设计一个时间复杂度为 `O(log n)` 的算法解决此问题。

 

**示例 1：**

```
输入：nums = [3,4,5,1,2]
输出：1
解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
```

**示例 2：**

```
输入：nums = [4,5,6,7,0,1,2]
输出：0
解释：原数组为 [0,1,2,4,5,6,7] ，旋转 3 次得到输入数组。
```

**示例 3：**

```
输入：nums = [11,13,15,17]
输出：11
解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
```

### 11.[33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)
整数数组 `nums` 按升序排列，数组中的值 **互不相同** 。

在传递给函数之前，`nums` 在预先未知的某个下标 `k`（`0 <= k < nums.length`）上进行了 **旋转**，使数组变为 `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`（下标 **从 0 开始** 计数）。例如， `[0,1,2,4,5,6,7]` 在下标 `3` 处经旋转后可能变为 `[4,5,6,7,0,1,2]` 。

给你 **旋转后** 的数组 `nums` 和一个整数 `target` ，如果 `nums` 中存在这个目标值 `target` ，则返回它的下标，否则返回 `-1` 。

你必须设计一个时间复杂度为 `O(log n)` 的算法解决此问题。

 

**示例 1：**

```
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
```

**示例 2：**

```
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
```

**示例 3：**

```
输入：nums = [1], target = 0
输出：-1
```

###  [反转链表_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1sd4y1x7KN/?spm_id_from=333.788&vd_source=394ba7019e7f10f68c4eca0cd2e58166)    连表

### 12.[206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/)

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/rev1ex1.jpg)

```
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
```

**示例 2：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/rev1ex2.jpg)

```
	输入：head = [1,2]
输出：[2,1]
```

```java

public class LeetCode206 {
     // head -- cur
     //然后倒序,头作为 next变成pre
    // 头  1->2->3->4->5  尾
    // 1<-2<-3<-4<-5
    public ListNode reverseList(ListNode head) {
        // 反转后的头节点
        ListNode pre = null;
        ListNode cur = head;
        while (cur !=null){
            // 暂存当且节点下一个值
            ListNode next = cur.next;
            // 将头节点反转, 第一个的next为null
            cur.next  = pre;
            // pre为新的头结点
            pre = cur;
            // cur为下一个节点
            cur = next;
        }
        return pre;

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}

```

### 13. [92. 反转链表 II](https://leetcode.cn/problems/reverse-linked-list-ii/)  

给你单链表的头指针 `head` 和两个整数 `left` 和 `right` ，其中 `left <= right` 。请你反转从位置 `left` 到位置 `right` 的链表节点，返回 **反转后的链表** 。

 

**示例 1：**

​	![image-20240825185612946](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825185612946.png)

```
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
```

**示例 2：**

```
输入：head = [5], left = 1, right = 1
输出：[5]
```

 ![image-20240825185713762](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825185713762.png)

![image-20240825185728851](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825185728851.png)

**图解**

``部分节点反转,只有找到反转后链表pre(头节点)上一个节点,p0 , p0-> pre(反转过的头节点), 将未反转时,p0的next节点,指向``

``反转后的 cur节点, 即可 (cur节点时反转后最后一个节点的 next)``

```java
 public ListNode reverseBetween(LeetCode92.ListNode head, int left, int right) {
        // 初始化一个哨兵链表
        ListNode dummy = new ListNode(0, head);
        // 哨兵节点
        ListNode p0 = dummy;
        // 找到需要反转节点的前一个节点
        for (int i = 0; i < left - 1; i++) {
            p0 = p0.next;
        }
        // 头节点
        ListNode pre = null;
        // 用于保存next节点
        ListNode cur = p0.next;
        for (int i = 0; i < right-left+1; i++) {
            // 下下一个节点
            ListNode next = cur.next;   // 每次循环只修改一个 next，方便大家理解
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        p0.next.next = cur;
        p0.next = pre;
        return dummy.next;

    }

    public class ListNode {
        int val;
        LeetCode92.ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, LeetCode92.ListNode next) { this.val = val; this.next = next; }
    }

```

### 14.  [25. K 个一组翻转链表 - 力扣（LeetCode）](https://leetcode.cn/problems/reverse-nodes-in-k-group/solutions/1992228/you-xie-cuo-liao-yi-ge-shi-pin-jiang-tou-plfs/)

给你链表的头节点 `head` ，每 `k` 个节点一组进行翻转，请你返回修改后的链表。

`k` 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 `k` 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/reverse_ex1.jpg)

```
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
```

**示例 2：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/reverse_ex2.jpg)

```
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
```

**题解**

```java
// 其实画图更容易理解
//先判断链表长度n是是否大于k, 进行循环 n>k,  每次循环由于是变k个, 所以 先进行n-k
// 是在92基础之上迭代了下
// 注: // 保留变前的哨兵节点next值
// 保留变前的哨兵节点next值
// ListNode nxt = p0.next;
// p0.next.next = cur;
// p0.next = pre;
// // 反转后的第二组链表哨兵节点,就是翻转前哨兵节点的next
// p0= nxt;

    public ListNode reverseKGroup(ListNode head, int k) {
        int n =0;
        ListNode cur1 = head;
        // 计算链表长度
        while (cur1 !=null){
            n++;
            cur1 = cur1.next;
        }
        // 初始化一个哨兵链表
        ListNode dummy = new ListNode(0, head);
        // 哨兵节点
        ListNode p0 = dummy;
        // 头节点
        ListNode pre = null;
        // 用于保存next节点
        ListNode cur = p0.next;
        while (n>=k){
            n-=k;  // 每次只变动k个节点,先减去k
            for (int i = 0; i < k; i++) {
                // 下下一个节点
                ListNode next = cur.next;   // 每次循环只修改一个 next，方便大家理解
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            // 保留变前的哨兵节点next值
            ListNode nxt = p0.next;
            p0.next.next = cur;
            p0.next = pre;
            // 反转后的第二组链表哨兵节点,就是翻转前哨兵节点的next
            p0= nxt;
        }

        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }    
```

### 15. [876. 链表的中间结点 ](https://leetcode.cn/problems/middle-of-the-linked-list/)

[环形链表II【基础算法精讲 07】_哔哩哔哩_bilibili](https://www.bilibili.com/video/BV1KG4y1G7cu/?spm_id_from=333.788&vd_source=394ba7019e7f10f68c4eca0cd2e58166)

给你单链表的头结点 `head` ，请你找出并返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/lc-midlist1.jpg)

```
输入：head = [1,2,3,4,5]
输出：[3,4,5]
解释：链表只有一个中间结点，值为 3 。
```

**示例 2：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/lc-midlist2.jpg)

```
输入：head = [1,2,3,4,5,6]
输出：[4,5,6]
解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
```



```java
// 快指针每次移动2个 慢指针每次移动一个
// 当快指针的下一个为空或者快指针为空的时候,慢指针就是最终中间节点(数学归纳法)

public class LeetCode876 {
    // 快指针每次移动2个 慢指针每次移动一个
    // 当快指针的下一个为空或者快指针为空的时候,慢指针就是最终中间节点(数学归纳法)
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 循环 fast节点不为空或者fast的next节点不为空
        while (fast != null && fast.next != null) {
            // 慢节点向后移动一位
            slow = slow.next;
            // 快节点向后移动两位
            fast = fast.next.next;
        }
        // 循环结束后,慢节点就是中间节点
        return slow;

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
```



### 16. [141. 环形链表 - 力扣（LeetCode）](https://leetcode.cn/problems/linked-list-cycle/description/)

给你一个链表的头节点 `head` ，判断链表中是否有环。

如果链表中有某个节点，可以通过连续跟踪 `next` 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 `pos` 来表示链表尾连接到链表中的位置（索引从 0 开始）。**注意：`pos` 不作为参数进行传递** 。仅仅是为了标识链表的实际情况。

*如果链表中存在环* ，则返回 `true` 。 否则，返回 `false` 。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/circularlinkedlist.png)

```
输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
```

**示例 2：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/circularlinkedlist_test2.png)

```
输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
```

**示例 3：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/circularlinkedlist_test3.png)

```
输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
```



```java
// 其实就是876的快慢指针, 快指针比慢指针相对速度多1, 也就是说慢指针静止,快指针速度为,有环必相遇
   // 快指针每次移动2个 慢指针每次移动一个
    // 当快指针的下一个为空或者快指针为空的时候,慢指针就是最终中间节点(数学归纳法)
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 循环 fast节点不为空或者fast的next节点不为空
        while (fast != null && fast.next != null) {
            // 慢节点向后移动一位
            slow = slow.next;
            // 快节点向后移动两位
            fast = fast.next.next;
            // 如果快慢节点相遇了
            if (slow.equals(fast)) {
                return true;
            }
        }
        // 如果不存在快慢节点相遇,返回false
        return false;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
```



### 17 .[142. 环形链表 II - 力扣（LeetCode）](https://leetcode.cn/problems/linked-list-cycle-ii/solutions/1999271/mei-xiang-ming-bai-yi-ge-shi-pin-jiang-t-nvsq/)

给定一个链表的头节点  `head` ，返回链表开始入环的第一个节点。 *如果链表无环，则返回 `null`。*

如果链表中有某个节点，可以通过连续跟踪 `next` 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 `pos` 来表示链表尾连接到链表中的位置（**索引从 0 开始**）。如果 `pos` 是 `-1`，则在该链表中没有环。**注意：`pos` 不作为参数进行传递**，仅仅是为了标识链表的实际情况。

**不允许修改** 链表。



 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png)

```
输入：head = [3,2,0,-4], pos = 1
输出：返回索引为 1 的链表节点
解释：链表中有一个环，其尾部连接到第二个节点。
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png)

```
输入：head = [1,2], pos = 0
输出：返回索引为 0 的链表节点
解释：链表中有一个环，其尾部连接到第一个节点。
```

**示例 3：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png)

```
输入：head = [1], pos = -1
输出：返回 null
解释：链表中没有环。
```

![image-20240825223609870](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825223609870.png)

![image-20240825223626765](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825223626765.png)

![image-20240825223844638](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825223844638.png)

![image-20240825224026185](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825224026185.png)

```java
由于, 快指针比慢指针速度快1, 一开始第一次就在入口相遇,所以快指针走了环长-1, 快指针都没走完一个环长,所以慢指针移动距离小于环长
    
    // 快指针每次移动2个 慢指针每次移动一个
    // 当快指针的下一个为空或者快指针为空的时候,慢指针就是最终中间节点(数学归纳法)
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 循环 fast节点不为空或者fast的next节点不为空
        while (fast != null && fast.next != null) {
            // 慢节点向后移动一位
            slow = slow.next;
            // 快节点向后移动两位
            fast = fast.next.next;
            // 如果快慢节点相遇了
            if (slow.equals(fast)) {
                // 如果fast和slow相遇, 则head 和slow会在入口处相遇,所以看笔记题解
                while (slow != head) {
                    slow = slow.next;
                    head = head.next;
                }
                return  slow;
            }
        }
        // 如果不存在,返回null
        return  null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
```





### 18. [143. 重排链表](https://leetcode.cn/problems/reorder-list/)

给定一个单链表 `L` 的头节点 `head` ，单链表 `L` 表示为：

```
L0 → L1 → … → Ln - 1 → Ln
```

请将其重新排列后变为：

```
L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
```

不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/1626420311-PkUiGI-image.png)

```
输入：head = [1,2,3,4]
输出：[1,4,2,3]
```

**示例 2：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/1626420320-YUiulT-image.png)

```
输入：head = [1,2,3,4,5]
输出：[1,5,2,4,3]
```

![image-20240825225934888](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825225934888.png)

![image-20240825230042569](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825230042569.png)

![image-20240825230052296](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825230052296.png)

![image-20240825230057887](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825230057887.png)



```java
876 中间节点 + 206 反转节点
    // 这题是综合题, 先记录 
```

![image-20240825230244748](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825230244748.png)

![image-20240825230317429](https://gitee.com/TrueNewBee/typora/raw/master/image-20240825230317429.png)



### 19 删除链表 [237. 删除链表中的节点 - 力扣（LeetCode）](https://leetcode.cn/problems/delete-node-in-a-linked-list/description/)

有一个单链表的 `head`，我们想删除它其中的一个节点 `node`。

给你一个需要删除的节点 `node` 。你将 **无法访问** 第一个节点 `head`。

链表的所有值都是 **唯一的**，并且保证给定的节点 `node` 不是链表中的最后一个节点。

删除给定的节点。注意，删除节点并不是指从内存中删除它。这里的意思是：

- 给定节点的值不应该存在于链表中。
- 链表中的节点数应该减少 1。
- `node` 前面的所有值顺序相同。
- `node` 后面的所有值顺序相同。

**自定义测试：**

- 对于输入，你应该提供整个链表 `head` 和要给出的节点 `node`。`node` 不应该是链表的最后一个节点，而应该是链表中的一个实际节点。
- 我们将构建链表，并将节点传递给你的函数。
- 输出将是调用你函数后的整个链表。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/01/node1.jpg)

```
输入：head = [4,5,1,9], node = 5
输出：[4,1,9]
解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
```

**示例 2：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/node2.jpg)

```
输入：head = [4,5,1,9], node = 1
输出：[4,5,9]
解释：指定链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9
```

 

**提示：**

- 链表中节点的数目范围是 `[2, 1000]`
- `-1000 <= Node.val <= 1000`
- 链表中每个节点的值都是 **唯一** 的
- 需要删除的节点 `node` 是 **链表中的节点** ，且 **不是末尾节点**

![image-20240827220500540](https://gitee.com/TrueNewBee/typora/raw/master/image-20240827220500540.png)

![image-20240827220519105](https://gitee.com/TrueNewBee/typora/raw/master/image-20240827220519105.png)

```java
// 不知道head, 题目意思只要节点不存在即可,所以 直接把要删除节点copy下一个,下一个变成下下一个
// 直接删除
```





### 20 [19. 删除链表的倒数第 N 个结点 - 力扣（LeetCode）](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/solutions/2004057/ru-he-shan-chu-jie-dian-liu-fen-zhong-ga-xpfs/)



给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/remove_ex1.jpg)

```
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
```

**示例 2：**

```
输入：head = [1], n = 1
输出：[]
```

**示例 3：**

```
输入：head = [1,2], n = 1
输出：[1]
```

 

**提示：**

- 链表中结点的数目为 `sz`
- `1 <= sz <= 30`
- `0 <= Node.val <= 100`
- `1 <= n <= sz`
- ![image-20240827222257435](https://gitee.com/TrueNewBee/typora/raw/master/image-20240827222257435.png)
- ``如果头节点会被删除,则需要创建哨兵链表``

![image-20240827222542505](https://gitee.com/TrueNewBee/typora/raw/master/image-20240827222542505.png)

![image-20240827222830445](https://gitee.com/TrueNewBee/typora/raw/master/image-20240827222830445.png)



```java
// 初始化一个 dummyNode, 然后当right右移动n个节点, 开始移动left 与right同时向右移动,之间距离为n ,当right移动到最后一个节点时候,刚好left移动到倒数n+1, 直接left的next直接指向next.next即可

public ListNode removeNthFromEnd(ListNode head, int n) {
        // 初始化一个哨兵链表
        ListNode dummy = new ListNode(1, head);
        ListNode right = dummy;
        // 先把right向右移动n个
        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        ListNode left = dummy;
        // left和right同时向右移动,之间差n个
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        // right移动到最后一个,left是在倒数n+1位置,所以直接 删除倒数第n个位置节点即可
        left.next = left.next.next;
        return  dummy.next;

    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
```



### 21 [83. 删除排序链表中的重复元素](https://leetcode.cn/problems/remove-duplicates-from-sorted-list/)

给定一个已排序的链表的头 `head` ， *删除所有重复的元素，使每个元素只出现一次* 。返回 *已排序的链表* 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/01/04/list1.jpg)

```
输入：head = [1,1,2]
输出：[1,2]
```

**示例 2：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/list2.jpg)

```
输入：head = [1,1,2,3,3]
输出：[1,2,3]
```

 

**提示：**

- 链表中节点数目在范围 `[0, 300]` 内
- `-100 <= Node.val <= 100`
- 题目数据保证链表已经按升序 **排列**

![image-20240827225503348](https://gitee.com/TrueNewBee/typora/raw/master/image-20240827225503348.png)

```java
// 初始化一个节点 cur ,如果该节点下一个值存在就一直循环, 如果该节点的值与下一个节点值相同, 就删除,否则就移动到下一个节点,直到next为null

 public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        // cur指向头姐点
        ListNode cur  = head;
        while (cur.next != null) {
            // 如果next的值与当前节点值相同,则删除该节点
            if (cur.next.val == cur.val) {
                cur.next = cur.next.next;
            } else {
                // 反之则右移
                cur = cur.next;
            }
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

```

### 22. [82. 删除排序链表中的重复元素 II - 力扣（LeetCode）](https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/description/)

给定一个已排序的链表的头 `head` ， *删除原始链表中所有重复数字的节点，只留下不同的数字* 。返回 *已排序的链表* 。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/linkedlist1.jpg)

```
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
```

**示例 2：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/linkedlist2.jpg)

```
输入：head = [1,1,1,2,3]
输出：[2,3]
```

 

**提示：**

- 链表中节点数目在范围 `[0, 300]` 内
- `-100 <= Node.val <= 100`
- 题目数据保证链表已经按升序 **排列**

![image-20240827231412128](https://gitee.com/TrueNewBee/typora/raw/master/image-20240827231412128.png)

![](https://gitee.com/TrueNewBee/typora/raw/master/image-20240827231427003.png)



```java
// 思路
// 先初始化一个哨兵链表  dummy,  cur指向哨兵,开始比较 循环 cur.next和cur.next.next
// 如果两个值相同,则在循环next后面的值, 如果相同,就删除, 如果不同cur正常移动一位
 public ListNode deleteDuplicates(ListNode head) {
        // 初始化一个哨兵链表
        ListNode dummy = new ListNode(1, head);
        // 初始化一个cur节点
        ListNode cur = dummy;
        // 循环遍历 next和next.next 即cur后面两个节点(如果不为空)
        while (cur.next != null && cur.next.next != null) {
            int val = cur.next.val;
            // 如果next和next.next 值相同,则循环遍历next.next后面节点,相同则删除next.next
            // 如果值不同,则右移一位即可
            if (cur.next.next.val == val) {
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                // 右移一位
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
```



### 23. [104. 二叉树的最大深度 - 力扣（LeetCode）](https://leetcode.cn/problems/maximum-depth-of-binary-tree/solutions/2010612/kan-wan-zhe-ge-shi-pin-rang-ni-dui-di-gu-44uz/)

给定一个二叉树 `root` ，返回其最大深度。

二叉树的 **最大深度** 是指从根节点到最远叶子节点的最长路径上的节点数。

 

**示例 1：**

![image-20240831214736646](https://gitee.com/TrueNewBee/typora/raw/master/image-20240831214736646.png)

 

```
输入：root = [3,9,20,null,null,15,7]
输出：3
```

**示例 2：**

```java
输入：root = [1,null,2]
输出：2
```

 

```java
// 递归的理解
太NB了，一下就把递归的核心讲明白了。下面说说我的理解：
其实可以把递归过程类比于一个传话游戏，假设每个人只能解码一段密码中的很小部分字符，那要做的就是每个人解码自己会的，把不会的传递给下一个人，如果最后一个人解码成功，那通过不断把答案回给传递给他的人并组装自己解码的部分，等回到第一个人手里整段密码就会全部解开。这就是递归的过程，问题随着传递不断简化，最后一次递归返回的答案回到第一层，已经是一个庞大的问题的最终答案了。想清楚这一点就能明白递归能解决什么问题，一个能不断分解且子问题解法和主问题相似。
能不能递归首先要分析问题，求二叉树最大深度的问题，只需要知道，根节点的左右子树中谁最高，最高的加上1就是树最大深度。那怎么知道左右子树中谁最高？那如果能知道左右子树的左右子树里谁最高，同样再加一就能得到答案。把这个答案返回给上一层，就是根节点的左右子树中谁最高的，加一得到最终答案。可以看到第二层解决的问题和第一层相似，但规模要小。传到最后到叶子节点，往上返回当前高度，最后就是整颗树的高度。
    1. 如何思考二叉树相关问题？
- 不要一开始就陷入细节，而是思考整棵树与其左右子树的关系。
2. 为什么需要使用递归？
- 子问题和原问题是相似的，他们执行的代码也是相同的（类比循环），但是子问题需要把计算结果返回给上一级，这更适合用递归实现。
3. 为什么这样写就一定能算出正确答案？
- 由于子问题的规模比原问题小，不断“递”下去，总会有个尽头，即递归的边界条件 ( base case )，直接返回它的答案“归”；
- 类似于数学归纳法（多米诺骨牌），n=1时类似边界条件；n=m时类似往后任意一个节点
4. 计算机是怎么执行递归的？
- 当程序执行“递”动作时，计算机使用栈保存这个发出“递”动作的对象，程序不断“递”，计算机不断压栈，直到边界时，程序发生“归”动作，正好将执行的答案“归”给栈顶元素，随后程序不断“归”，计算机不断出栈，直到返回原问题的答案，栈空。
5. 另一种递归思路
- 维护全局变量，使用二叉树遍历函数，不断更新全局变量最大值。
    
处理递归，核心就是千万不要想子问题的过程，你脑子能处理几层？马上就绕迷糊了。要想子问题的结果，思路就清晰了
    的，只要代码的边界条件和非边界条件的逻辑写对了，其他的事情交给数学归纳法就好了。也就是说，写对了这两个逻辑，你的代码自动就是正确的了，没必要想递归是怎么一层一层走的。
```



```java
// ps: 计算机在递归时候,有个栈 用于存储每次递归结果并返回给上层节点,这样就是 从上倒下一层一层递归
// 结果也会自下向上传递
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
```



### 24. [100. 相同的树](https://leetcode.cn/problems/same-tree/)

给你两棵二叉树的根节点 `p` 和 `q` ，编写一个函数来检验这两棵树是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

 

**示例 1：**

![image-20240901211316849](https://gitee.com/TrueNewBee/typora/raw/master/image-20240901211316849.png)

```
输入：p = [1,2,3], q = [1,2,3]
输出：true
```

**示例 2：**

![image-20240901211331146](https://gitee.com/TrueNewBee/typora/raw/master/image-20240901211331146.png)

```
输入：p = [1,2], q = [1,null,2]
输出：false
```

**示例 3：**

![image-20240901211342646](https://gitee.com/TrueNewBee/typora/raw/master/image-20240901211342646.png)

```
输入：p = [1,2,1], q = [1,1,2]
输出：false
```



```java
public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p==q;  // 必须都是 null
        }

        // 左右树节点值相等,并且左右节点继续递归,最终返回递归后的结果
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
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
```



### 25. [101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree/)

给你一个二叉树的根节点 `root` ， 检查它是否轴对称。

 

**示例 1：**

![img](https://gitee.com/TrueNewBee/typora/raw/master/1698026966-JDYPDU-image.png)

```
输入：root = [1,2,2,3,4,4,3]
输出：true
```

**示例 2：**

![img](https://pic.leetcode.cn/1698027008-nPFLbM-image.png)

```
输入：root = [1,2,2,null,3,null,3]
输出：false
```

 

```java
 public boolean isSymmetric(TreeNode root) {
        return isSameTree(root.left, root.right);
    }

    public boolean isSameTree(LeetCode100.TreeNode p, LeetCode100.TreeNode q) {
        if (p == null || q == null) {
            return p==q;  // 必须都是 null
        }

        // 同一个根节点,由于是判断是否是对称的,所以 p.left 和 q.right 比较
        return p.val == q.val && isSameTree(p.left, q.right) && isSameTree(p.right, q.left);
    }

    public class TreeNode {
        int val;
        LeetCode100.TreeNode left;
        LeetCode100.TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, LeetCode100.TreeNode left, LeetCode100.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
```



### 26. [110. 平衡二叉树](https://leetcode.cn/problems/balanced-binary-tree/)

给定一个二叉树，判断它是否是 

平衡二叉树

 



 

**示例 1：**

![image-20240901224644850](https://gitee.com/TrueNewBee/typora/raw/master/image-20240901224644850.png)

```
输入：root = [3,9,20,null,null,15,7]
输出：true
```

**示例 2：**

![image-20240901224658492](https://gitee.com/TrueNewBee/typora/raw/master/image-20240901224658492.png)

```
输入：root = [1,2,2,3,3,null,null,4,4]
输出：false
```

**示例 3：**

```
输入：root = []
输出：true
```



```java
// 看注释即可

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
```



### 27 [199. 二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/)

给定一个二叉树的 **根节点** `root`，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

 

**示例 1:**

![image-20240901230620048](https://gitee.com/TrueNewBee/typora/raw/master/image-20240901230620048.png)

```
输入: [1,2,3,null,5,null,4]
输出: [1,3,4]
```

**示例 2:**

```
输入: [1,null,3]
输出: [1,3]
```

**示例 3:**

```
输入: []
输出: []
```



### 28. [199. 二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/)

给定一个二叉树的 **根节点** `root`，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

 

**示例 1:**

![img](https://assets.leetcode.com/uploads/2021/02/14/tree.jpg)

```
输入: [1,2,3,null,5,null,4]
输出: [1,3,4]
```

**示例 2:**

```
输入: [1,null,3]
输出: [1,3]
```

**示例 3:**

```
输入: []
输出: []
```

 

![image-20240901233959011](https://gitee.com/TrueNewBee/typora/raw/master/image-20240901233959011.png)



```java
// 初始化一个 数组ans记录下当前存入节点
// depth如果 =ans长度,则放进数组中

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

```

