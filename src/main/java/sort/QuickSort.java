package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

/**
 * @description:
 * @author: Kevin
 * @createDate: 2020/3/2
 * @version: 1.0
 */
public class QuickSort<T extends Comparable<T>> {

    public static void main(String[] args) {
        QuickSort<Integer> sort = new QuickSort<>();
        Integer[] nums = {3, 5, 4, 2, 8, 7, 9, 1, 6};
//        Integer[] nums = {3, 2, 3, 4, 3, 4, 7, 1, 6};
//        sort.sort(nums);
        sort.threeWayQuickSort(nums,0,nums.length - 1);
        for(int i:nums){
            System.out.print(i+", ");
        }
    }

    private void sort(T[] nums){
        // 随机打乱，防止最坏情况
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] arr, int left, int right) {
        if (right <= left){
            return;
        }
        int mid = partition(arr, left, right);
        sort(arr, left, mid - 1);
        sort(arr, mid + 1, right);
    }

    /**
     * 双向快排：最好情况时间复杂度 O(NlogN)，最坏情况（原数组有序）时间复杂度 O(N^2)
     * +----------------------------------------------------------+
     * |  pivot |        < pivot          |     > pivot  |
     * +----------------------------------------------------------+
     *      ^                           ^  ^
     *      |                           |  |
     *      v                           j  i
     */
    private int partition(T[] nums, int left, int right) {
        int i = left, j = right + 1;
        T v = nums[left];
        while (true) {
            while (nums[++i].compareTo(v)<0 && i != right) ;
            while (nums[--j].compareTo(v)>0 && j != left) ;
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        nums[left] = nums[j];
        nums[j] = v;
        return j;
    }

    /**
     * 三向快排：对于有大量重复元素的随机数组可以在线性时间内完成排序。
     *   left part         center part                  right part
     * +----------------------------------------------------------+
     * | < pivot    |         pivot          |     > pivot  |
     * +----------------------------------------------------------+
     *              ^                        ^  ^
     *              |                        |  |
     *             lt                       gt  i
     */
    private void threeWayQuickSort(T[] nums,int l, int h){
        if(h<l)
            return;
        int lt = l, i=l+1, gt = h;
        T v = nums[l];
        while(i<=gt){
            int cmp = nums[i].compareTo(v);
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

    // 随机打乱
    private void shuffle(T[] nums){
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    // 交换元素
    private void swap(T[] nums,int l ,int h){
        T temp = nums[l];
        nums[l] = nums[h];
        nums[h] = temp;
    }

}
