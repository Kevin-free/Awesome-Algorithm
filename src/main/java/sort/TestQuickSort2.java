package sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @description:
 * @author: Kevin
 * @createDate: 2020/3/29
 * @version: 1.0
 */
public class TestQuickSort2 {

    public static void main(String[] args) {
        TestQuickSort2 solution = new TestQuickSort2();
        int[] nums = {3, 5, 4, 2, 8, 7, 9, 1, 6};
        solution.sort(nums);
//        solution.threeWaySort(nums);
        for(int i:nums){
            System.out.print(i+", ");
        }
    }

    public void sort(int[] nums){
        // 随机打乱，防止最坏情况
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int left, int right){
        if (left >= right) {
            return;
        }

        int mid = partition(nums, left, right);
//        int mid = partition2(nums, left, right);
        sort(nums, left, mid - 1);
        sort(nums, mid + 1, right);
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
    private int partition(int[] nums, int left, int right){
        int v = nums[left];
        int i = left + 1, j = right;
        while (true){
            while (nums[i] < v && i != right){
                ++ i;
            }
            while (nums[j] > v && j != left){
                -- j;
            }
            if(i >= j)
                break;
//            swap(nums, i, j);
            swapbit(nums, i, j);
        }
        nums[left] = nums[j];
        nums[j] = v;
        return j;
    }

    // 优化，判断更少的次数
    private int partition2(int[] nums, int left, int right){
        int v = nums[left];
        int i = left, j = right + 1; // 注意！不同点
        while (true){
            // 因为初始化 i, j 不同，可以用先 ++ / -- 即不用在判断已调换的元素
            while (nums[++i] < v && i != right);
            while (nums[--j] > v && j != left);
            if(i >= j)
                break;
//            swap(nums, i, j);
            swapbit(nums, i, j);
        }
        nums[left] = nums[j];
        nums[j] = v;
        return j;
    }

    public void threeWaySort(int[] nums){
        threeWaySort(nums, 0, nums.length - 1);
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
    private void threeWaySort(int[] nums, int left, int right){
        if (left >= right){
            return;
        }
        int v = nums[left];
        int lt = left, i = left + 1, gt = right;
        while (i <= gt) {
            if (nums[i] < v){
                swap(nums, i++, lt++);
            }else if(nums[i] > v){
                swap(nums, i, gt--);
            }else {
                i++;
            }
        }
        threeWaySort(nums, left, lt - 1);
        threeWaySort(nums, gt + 1, right);
    }

    // 交换元素
    private void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    // 位运算交换数值
    private void swapbit(int[] nums, int a, int b){
        nums[a] = nums[a] ^ nums[b];
        nums[b] = nums[a] ^ nums[b];
        nums[a] = nums[a] ^ nums[b];
    }

    private Random rand = new Random();

    // 随机打乱
    private void shuffle(int[] nums){
        int length = nums.length;
        for (int i = length; i > 0; i--){
            int randIndex = rand.nextInt(i);
            swap(nums, randIndex, i - 1);
        }
    }

}
