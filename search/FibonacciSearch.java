package com.atguigu.search;

import java.util.Arrays;

public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22};

        System.out.println("index=" + fibSearch(arr, 17));// 0

    }

    //因为后面我们mid=low+F(k-1)-1，需要使用到斐波那契数列，因此我们需要先获取到一个斐波那契数列
    //非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //编写斐波那契查找算法
    //使用非递归的方式编写算法

    /**
     * @param a   数组
     * @param key 我们需要查找的关键码(值)
     * @return 返回对应的下标，如果没有-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放mid值
        int f[] = fib(); //获取到斐波那契数列
        /**
         * keypoint
         * Q:为什么whlie循环中f[k]要减1？
         * A:因为while循环的目地是在找出斐波那契数列中找到一个长度值，该长度值要能容纳得下整个a数组，而high=a.length-1，f[k]表示的也是长度值，所以要减一，当退出while循环时，即表明high<=f[k]-1，也就是a.length<=f[k]，即找到了可以容纳a数组的斐波那契长度值
         */
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为 f[k] 值 可能大于 a 的 长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);//copyOf函数前面参数表示要复制的数组，后面的参数表示要复制的长度值
        //实际上需求使用a数组最后的数填充 temp
        //举例:
        //temp = {1,8, 10, 89, 1000, 1234, 0, 0}  => {1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }


        /**
         * keypoint
         * Q:为什么mid=low+f[k-1]-1
         * A:因为f[k]是整个数组的长度，f[k-1]是黄金分割点前半段的长度
         */
        // 使用while来循环处理，找到我们的数 key
        while (low <= high) { // 只要这个条件满足，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                //为甚是 k--
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                //即 在 f[k-1] 的前面继续查找 k--
                //即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) { // 我们应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是k -=2
                //说明
                //1. 全部元素 = 前面的元素 + 后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                //4. 即在f[k-2] 的前面进行查找 k -=2
                //5. 即下次循环 mid = f[k - 1 - 2] - 1
                /**
                 * keypoint
                 * Q:为什么k要减2?
                 * A:想象数组长度为f[k]，左右分为f[k-1]和f[k-2]，然后在左侧f[k-1]中的黄金分割点是f[k-1-1]，在右侧f[k-2]中的黄金分割点是f[k-2-1](因为f[k-2]=f[k-3]+f[k-4])
                 */
                k -= 2;
            } else { //找到
                /**
                 * keypoint
                 * Q:为什么要判断mid和high的大小
                 * A:因为high是数组a的下标，mid是斐波那契数列长度的数组temp的下表，有可能mid比high小
                 */
                //需要确定，返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

}
