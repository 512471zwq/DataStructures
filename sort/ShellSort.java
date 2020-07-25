package com.atguigu.sort;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

    public static void main(String[] args) {
        //int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };

        // 创建要给80000个的随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        //shellSort(arr); //交换式
        shellSort2(arr);//移位方式

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);

        //System.out.println(Arrays.toString(arr));
    }

    // 使用逐步推导的方式来编写希尔排序
    // 希尔排序时， 对有序序列在插入时采用交换法,
    public static void shellSort(int[] arr) {

        int temp = 0;
        int count = 0;
        // 根据前面的逐步分析，使用循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            /**
             * @key
             * Q:i循环的作用？
             * A:不同于交换排序，希尔排序使用交换法的时候，每一次循环都能使得前面参与交换的元素有序，
             * 具体过程是默认左边第一个元素有序，然后第二个元素参与交换，使得两个元素有序，接着第三个元素参与交换，
             * 使得3个元素有序，因为每一次循环都是在前面元素有序的基础上让一个新的元素参与交换，所以新元素参与交换后也是有序的。
             * Q:为什么交换法的时间比插入法（移位法）速度慢很多？
             * A:本质上插入法是交换法的改进和优化，插入法是每次在有序序列中寻找合适的位置，将该位置后面的元素后移一位，然后插入，
             * 而交换法也是对有序的序列进行交换操作，但是交换法要从末尾比较到头部，而插入法会比较到合适的位置就停止了，
             * 而且交换法每次交换要涉及到三次存储操作，很费时间。
             */
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素(共gap组，每组有个元素), 步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("希尔排序第" + (++count) + "轮 =" + Arrays.toString(arr));
        }
		/*

		// 希尔排序的第1轮排序
		// 因为第1轮排序，是将10个数据分成了 5组
		for (int i = 5; i < arr.length; i++) {
			// 遍历各组中所有的元素(共5组，每组有2个元素), 步长5
			for (int j = i - 5; j >= 0; j -= 5) {
				// 如果当前元素大于加上步长后的那个元素，说明交换
				if (arr[j] > arr[j + 5]) {
					temp = arr[j];
					arr[j] = arr[j + 5];
					arr[j + 5] = temp;
				}
			}
		}

		System.out.println("希尔排序1轮后=" + Arrays.toString(arr));//


		// 希尔排序的第2轮排序
		// 因为第2轮排序，是将10个数据分成了 5/2 = 2组
		for (int i = 2; i < arr.length; i++) {
			// 遍历各组中所有的元素(共5组，每组有2个元素), 步长5
			for (int j = i - 2; j >= 0; j -= 2) {
				// 如果当前元素大于加上步长后的那个元素，说明交换
				if (arr[j] > arr[j + 2]) {
					temp = arr[j];
					arr[j] = arr[j + 2];
					arr[j + 2] = temp;
				}
			}
		}

		System.out.println("希尔排序2轮后=" + Arrays.toString(arr));//

		// 希尔排序的第3轮排序
		// 因为第3轮排序，是将10个数据分成了 2/2 = 1组
		for (int i = 1; i < arr.length; i++) {
			// 遍历各组中所有的元素(共5组，每组有2个元素), 步长5
			for (int j = i - 1; j >= 0; j -= 1) {
				// 如果当前元素大于加上步长后的那个元素，说明交换
				if (arr[j] > arr[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}

		System.out.println("希尔排序3轮后=" + Arrays.toString(arr));//
		*/
    }

    //对交换式的希尔排序进行优化->移位法
    public static void shellSort2(int[] arr) {

        // 增量gap, 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            /**
             * @key
             * Q:为什么i从gap开始？
             * A:希尔排序分成了很多组，gap的值就是组数，默认每一组的第一个元素是有序的，i从gap开始表示从第一组的第二个元素开始进行插入排序
             * Q:i每一次循环的作用？
             * A:i的每一趟循环依次对每一组进行了部分的插入排序（将第i个元素插入到前面属于自己那一组的元素序列之中），而并没有进行完全的插入排序。
             * Q:i的一轮循环的作用？
             * A:当i=gap，且i的所有循环完成时，表示将gap个组的每个组都进行了插入排序并使得每个组都是有序的，比如当gap=5，表明将5个组变有序，当gap=2，将2个组变有序，当gap=1，将1个组变有序
             * Q:i循环的内部代码进行了什么操作？
             * A:i循环的内部代码进行了一次插入排序的过程，一次插入排序指的是将一个元素插入到有序序列中，此时该元素后面的元组还是无序的，只有i的循环全部结束了，才将该各组元素变有序
             * Q:为什么while循环中j-gap>=0要在temp<arr[j-gap]之前？
             * A:因为只有j-gap>=0的时候，arr[j-gap]才有意义，不然数组下标在最后的时候会报错
             */
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }
        }


    }
}
