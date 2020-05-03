package algorithm.sort;

import org.junit.Test;

/**
 * 排序算法
 *
 * @author wangtenglong
 * @date 2020-04-12
 */
public class SortTest {

    /**
     * 二分查找法（折半查找）：要求待查找的数组必须有序。每次取中间位置的值与待查找关键字比较。 如果中间位置的值比待查找的关键字大，则在前半部分循环这个查找的过程。
     * 如果中间位置的值比待查找的关键字小，则在后半部分循环这个查找的过程。
     */
    @Test
    public void test1() {
        int[] array = new int[] {1, 2, 3, 4};
        System.out.println(bitSearch(array, 3));
    }

    /**
     * 返回待查找字段在数组中的位置
     *
     * @param array
     * @param a
     * @return
     */
    private static int bitSearch(int[] array, int a) {
        int lo = 0;
        int hi = array.length - 1;
        int mid;

        while (lo <= hi) {
            //中间位置
            //运算符号："/"默认向下取整
            mid = (lo + hi) / 2;
            if (array[mid] == a) {
                return mid + 1;
            } else if (array[mid] < a) {
                //向右查找
                lo = mid + 1;
            } else {
                //向作查找
                hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 冒泡排序法算法：比较前后相邻的两个数，如果前面的数据大于后面的数据，就将这两个数据交换。 1：经过第一次排序，最大的一个数在最右边（倒数第一个；2： 经过第二次排序，第二大的一个数在倒数第二的位置
     */
    @Test
    public void test2() {

        int[] array = new int[] {4, 2, 6, 1};
        int i, j;
        for (i = 0; i < array.length - 1; i++) {
            for (j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("从小到大的排序结果是");

        for (i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    /**
     * 插入排序算法：通过构建有序序列，对于未排序的数据，在已排序的序列中从后往前扫描，找到相应的位置并插入。
     * 插入排序非常类似整理扑克牌。在开始摸牌时，左手是空的，牌面朝下放在桌上。接着，一次从桌上摸起一张牌，并将它插入到左手的牌的正确位置。
     */
    @Test
    public void test3() {

        int[] arr = new int[] {2, 4, 1};

        for (int i = 1; i < arr.length; i++) {
            //插入的数
            int insertVal = arr[i];
            //被插入的位置
            int index = i - 1;
            //如果插入的数比被插入的数小
            while (index >= 0 && insertVal < arr[index]) {
                //将把arr[index]项后移动（被插入位置）
                arr[index + 1] = arr[index];
                //让index向前移动
                index--;
            }
            //把插入的数据放到正确的位置
            arr[index + 1] = insertVal;
        }

        //左边小，右边大
        for (int m = 0; m < arr.length; m++) {
            System.out.println(arr[m]);
        }
    }

    /**
     * 快速排序算法:选择一个关键字作为基准值（一般选第一个）。比基准值小的都在左边序列（一般是无序的），比基准值大的都在右边（一般是无序的）。 一次循环：从后往前比较，用基准值和最后一个值比较，如果比基准值小的交换位置。
     * 如果没有，继续下一个，直到找到第一个比基准值小的值才交换。找到这个值又，又从前往后开始比较，如果有比基准值大的，交换位置，如果没有继续比较下一个，直到找到第一个比基准值大的
     * 值才交换。直到从前往后的比较索引>从后往前比较的索引，结束第一次循环，此时，对于基准值 来说，左右两边就是有序的了。
     */

    /**
     * 快速排序（QuickSort）是对冒泡排序的一种改进。快速排序由C. A. R. Hoare在1962年提出。它的基本思想是：
     * <p>
     * 从要排序的数据中取一个数为“基准数”。 通过一趟排序将要排序的数据分割成独立的两部分，其中左边的数据都比“基准数”小，右边的数据都比“基准数”大。
     * 然后再按步骤2对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     */
    @Test
    public void test4() {

        int[] arr = new int[] {12, 20, 5, 16, 15, 1, 30, 45};
        sort4(arr, 0, 7);
        for (int m = 0; m < arr.length; m++) {
            System.out.println(arr[m]);
        }
    }

    public void sort4(int[] a, int low, int high) {
        int start = low;
        int end = high;
        int key = a[low];
        while (end > start) {
            //从后往前比较
            while (end > start && a[end] >= key) {
                //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
                end--;
            }

            if (a[end] <= key) {
                int temp = a[end];
                a[end] = a[start];
                a[start] = temp;
            }
            //从前往后比较
            while (end > start && a[start] <= key) {
                //如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            }
            if (a[start] >= key) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
            }
            //此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的 值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用
        }
        //递归
        if (start > low) {
            //左边序列。第一个索引位置到关键值索引-1
            sort4(a, low, start - 1);
        }
        if (end < high) {
            //右边序列。从关键值索引+1 到最后一个 }
            sort4(a, end + 1, high);
        }
    }
}