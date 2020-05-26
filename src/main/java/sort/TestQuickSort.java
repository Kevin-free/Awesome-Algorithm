package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @description:
 * @author: Kevin
 * @createDate: 2020/3/2
 * @version: 1.0
 */
public class TestQuickSort {
    private static int opCount = 1000000;
//    private static int opCount = 1000;
    private static int countQSOs = 0;
    private static int countQSs = 0;
    private static int countQDs = 0;

    private static void testQSO() {
        Random random = new Random();
        int[] nums = new int[opCount];
        for (int i = 0; i < nums.length; i++) {
            int temp = random.nextInt(100);//随机产生一个 1~10 的整数
            nums[i] = temp;//将产生的数添加到数组
        }
        int l = 0, r = nums.length - 1;

        // 记录开始时间
        long startTime = System.nanoTime();

        quickSortO(nums, l, r);
        for (int i : nums) {
            System.out.print(i + ",");
        }

        // 记录结束时间
        long endTime = System.nanoTime();
        double useTime = (endTime - startTime) / 1000000000.0;
        System.out.println("\n quickSortO, time: " + useTime + "s");
        System.out.println(countQSOs);
    }

    private static void testQS() {
        Random random = new Random();
        int[] nums = new int[opCount];
        for (int i = 0; i < nums.length; i++) {
            int temp = random.nextInt(100);//随机产生一个 1~10 的整数
            nums[i] = temp;//将产生的数添加到数组
        }
        int l = 0, r = nums.length - 1;

        // 记录开始时间
        long startTime = System.nanoTime();

        quickSort(nums, l, r);
        /*for (int i : nums) {
            System.out.print(i + ",");
        }*/

        // 记录结束时间
        long endTime = System.nanoTime();
        double useTime = (endTime - startTime) / 1000000000.0;
        System.out.println("\n quickSort, time: " + useTime + "s");
        System.out.println(countQSs);
    }

    private static void testQD() {
        Random random = new Random();
        int[] nums = new int[opCount];
        for (int i = 0; i < nums.length; i++) {
            int temp = random.nextInt(100);//随机产生一个 1~10 的整数
            nums[i] = temp;//将产生的数添加到数组
        }
        int l = 0, r = nums.length - 1;

        // 记录开始时间
        long startTime = System.nanoTime();

        quickSortDig(nums, l, r);
        /*for (int i : nums) {
            System.out.print(i + ",");
        }*/

        // 记录结束时间
        long endTime = System.nanoTime();
        double useTime = (endTime - startTime) / Math.pow(10,9);
        System.out.println("\n quickDigSort, time: " + useTime + "s");
        System.out.println(countQDs);
    }

    private static void test() {
//        int[] nums = {3, 5, 4, 2, 8, 7, 9, 1, 6};
        int[] nums = {3, 2, 3, 4, 3, 4, 7, 1, 6};
        int l = 0, r = nums.length - 1;
//        quickSort(nums, l, r);
//        quickSortO(nums, l, r);
        threeWayQuickSort(nums, l, r);
//        quickSortDig(nums, l, r);
        for (int i : nums) {
            System.out.print(i + ",");
        }
    }

    public static void main(String[] args) {
//        test();
        testQS();
        testQD();
//        testQSO();
    }

    private static void quickSortO(int[] arr, int left, int right) {
        if (left < right) {
            countQSOs ++;
            int mid = partitionO(arr, left, right);
            quickSortO(arr, left, mid - 1);
            quickSortO(arr, mid + 1, right);
        }
    }

    /**
     * 双向快排
     * +----------------------------------------------------------+
     * |  pivot |        < pivot          |     > pivot  |
     * +----------------------------------------------------------+
     *      ^                           ^  ^
     *      |                           |  |
     *      v                           j  i
     */
    private static int partitionO(int[] nums, int left, int right) {
        int i = left, j = right + 1;
        int v = nums[left];
        while (true) {
            while (nums[++i]<= v && i != right) ;
            while (v <= nums[--j] && j != left) ;
            if (i >= j)
                break;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        nums[left] = nums[j];
        nums[j] = v;
        return j;
    }

    /**
     * 三向快排
     *   left part         center part                  right part
     * +----------------------------------------------------------+
     * | < pivot    |         pivot          |     > pivot  |
     * +----------------------------------------------------------+
     *              ^                        ^  ^
     *              |                        |  |
     *             lt                       gt  i
     */
    private static void threeWayQuickSort(int[] nums,int l, int h){
        if(h<l)
            return;
        int lt = l, i=l+1, gt = h;
        int v = nums[l];
        while(i<=gt){
            int cmp = nums[i] - v;
            if (cmp < 0){
                swap(nums,lt++,i++);
            } else if (cmp > 0) {
                swap(nums, i, gt--);
            }else{
                i++;
            }
        }
        threeWayQuickSort(nums, l, lt - 1);
        threeWayQuickSort(nums, gt + 1, h);
    }

    private static void swap(int[] nums,int l ,int h){
        int temp = nums[l];
        nums[l] = nums[h];
        nums[h] = temp;
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            countQSs ++;
            int mid = partition(arr, left, right);
            quickSort(arr, left, mid - 1);
            quickSort(arr, mid + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left + 1;
        int j = right;
        while (true) {
            while (i != right && arr[i] <= pivot)
                ++i;
            while (j != left && arr[j] >= pivot)
                --j;
            if (i >= j)
                break;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[left] = arr[j];
        arr[j] = pivot;
        return j;
    }

    private static void quickSortDig(int s[], int l, int r) {
        if (l < r) {
            countQDs ++;
            int mid = partitionDig(s, l, r);
            quickSortDig(s, l, mid - 1);
            quickSortDig(s, mid + 1, r);
        }
    }

    private static int partitionDig(int s[], int l, int r) {
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x)
                    j--;
                if (i < j){
//                    s[i++] = s[j];
                    s[i] = s[j];
                    ++i;
                }
                while (i < j && s[i] <= x)
                    i++;
                if (i < j){
//                    s[j--] = s[i];
                    s[j] = s[i];
                    --j;
                }
            }
            s[i] = x;
            return i;
    }

    /*private static void quickSortDig(int s[], int l, int r) {
        if (l < r) {
            countQDs++;
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) {
                    j--;
                }
                if (i < j) {
                    s[i++] = s[j];
                }
                while (i < j && s[i] <= x) {
                    i++;
                }
                if (i < j) {
                    s[j--] = s[i];
                }
            }
            s[i] = x;
            quickSortDig(s, l, i - 1);
            quickSortDig(s, i + 1, r);
        }
    }*/
}
