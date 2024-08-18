package vip.chentianxiang.learn;

/**
 * @Author: TrueNewBee
 * @Date: 2024/8/9 23:43
 * @Github: https://github.com/TrueNewBee
 * @Description: 167.两数之和2-输入有序数组 双指针
 */
public class LeetCode167 {
    public int[] twoSum(int[] numbers, int target) {
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
}
