package sort;

/**
 * @description: 归并排序
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 * 稳定排序
 * 非原地排序
 * @author: Kevin
 * @createDate: 2020/2/13
 * @version: 1.0
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 5, 3, 9};
        userMergeSort(arr);
        System.out.println("sorted arr:");
        for (Integer i:arr) {
            System.out.println(i);
        }
    }

    private static void userMergeSort(int[] arr){
        if (arr == null || arr.length == 0) {
            return;
        }
        mergeSort(arr,0,arr.length-1);
    }

    /**
     * 将数组 arr[left] --> arr[right] 进行归并排序
     * @param arr 要排序的数组
     * @param left 左边界
     * @param right 右边界
     */
    private static void mergeSort(int[] arr,int left,int right){
        //终止条件 --> left == right 的时候，就递归到只有一个元素,则不用递归
        if (left < right) {
            // [分]: 将数组一分为二
            int center = (left+right)/2;
//            int center = (left+right)>>1;
            // [治]: 将左边的数组排序(left --> center)
            mergeSort(arr,left,center);
            // [治]: 将右边的数组排序(center+1 --> right)
            mergeSort(arr,center+1,right);
            // [合]: 合并两个有序数组
            merge(arr, left, center, right);
        }
    }

    /**
     * 将 arr[left...center] 和 arr[center+1...right] 两个有序数组合并为一个有序数组
     * @param arr
     * @param left
     * @param center
     * @param right
     */
    private static void merge(int[] arr, int left, int center, int right) {
        //先用一个临时数组把他们合并汇总起来
        int[] temp = new int[arr.length];
        int i = left,j = center+1;
        // 先通过比较将两个有序数组合并为一个有序数组，结果暂存到 temp 数组
        for (int k = left;k<=right;k++){
            // 如果左边数组 arr[left...center] 中的元素取完[即比较完](i>center)，
            // 则直接复制右边数组的元素到辅助数组。右边数组同理
            if (i>center)    { temp[k] = arr[j++];  }
            else if(j>right) { temp[k] = arr[i++];  }

            // 合并时，比较两有序数组值，将小的放入辅助数组中
            else if(arr[i]<=arr[j]) {temp[k] = arr[i++]; }
            else                    {temp[k] = arr[j++]; }
        }
        // 再将已经排序好的辅助数组中的值复制到原数组 arr 中
        for (int k=left;k<=right;k++){
            arr[k] = temp[k];
        }
    }
}
