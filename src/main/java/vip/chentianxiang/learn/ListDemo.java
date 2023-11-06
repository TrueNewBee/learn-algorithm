package vip.chentianxiang.learn;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.NewThreadAction;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: TrueNewBee
 * @Date: 2023/11/5 23:06
 * @Github: https://github.com/TrueNewBee
 * @Description:
 */
public class ListDemo {

    public static void main(String[] args) {
        searchTest();
    }

    public static void searchTest() {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        nums.add(5);
        int search1 = search1(nums, 4);
        int search2 = search2(nums, 4);
        System.out.println("查询结果1:" + search1);
        System.out.println("查询结果2:" + search2);

    }

    // 2分查找写法(1)  left  <= right
    public static int search1(ArrayList<Integer> nums, int targetNum) {
        int left = 0;
        // 定义target 在左闭右闭的区间, 即[left,right]
        int right = nums.size() - 1;
        // 当left == right 时, [left,right] 区间依然有效,所以使用<=
        while (left <= right) {
            // 防止溢出,等同于 (left+right)/2
            int middle = left + ((right - left) / 2);
            if (nums.get(middle) > targetNum) {
                right = middle - 1; // target 在左区间[left,middle-1]
            } else if (nums.get(middle) < targetNum) {
                left = middle + 1; // target 在右区间[middle+1,right]
            } else {
                return middle;
            }
        }
        return -1;
    }

    // 2分查找写法(2) left < right
    public static int search2(ArrayList<Integer> nums, int targetNum) {
        int left = 0;
        int right = nums.size() ; //
        while (left < right) {
            //  >> 1  是一个右移操作，相当于将 right - left 的结果除以 2。
            int middle = left + ((right - left) >> 1);
            if (nums.get(middle) > targetNum) {
                right = middle ;  // target 在左区间 [left, middle)
            } else if (nums.get(middle) < targetNum) {
                left = middle + 1; // target 在右区间[middle+1, right)
            } else { // nums[middle] == target
                return middle;
            }
        }
        return -1;
    }
}
