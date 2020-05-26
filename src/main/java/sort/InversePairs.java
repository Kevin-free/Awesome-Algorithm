package sort;

/**
 * @description: 数组中的逆序对
 * https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 题目描述
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 *
 * 时间复杂度：O(nlogn)
 * 空间复杂度：O(n)
 * 稳定排序
 * 非原地排序
 * @author: Kevin
 * @createDate: 2020/2/13
 * @version: 1.0
 */
public class InversePairs {
    private long count = 0;
    private int[] temp;

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 0};
        InversePairs solution = new InversePairs();
        int count = solution.InversePairs(arr);
        System.out.println("逆序对："+count);
    }

    public int InversePairs(int[] arr) {
        temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1);
        return (int) (count % 1000000007);
    }

    private void mergeSort(int[] arr, int left, int right) {
        //终止条件 --> left == right 的时候，就递归到只有一个元素,则不用递归
        if (left < right) {
            // [分]: 将数组一分为二
//            int center = (left + right) / 2;
            int center = (left+right)>>1;
            // [治]: 将左边的数组排序(left --> center)
            mergeSort(arr, left, center);
            // [治]: 将右边的数组排序(center+1 --> right)
            mergeSort(arr, center + 1, right);
            // [合]: 合并两个有序数组
            merge(arr, left, center, right);
        }
    }

    private void merge(int[] arr, int left, int center, int right) {
        //先用一个临时数组把他们合并汇总起来
        int i = left, j = center + 1;
        // 先通过比较将两个有序数组合并为一个有序数组，结果暂存到 temp 数组
        for (int k = left; k <= right; k++) {
            // 如果左边数组 arr[left...center] 中的元素取完[即比较完](i>center)，
            // 则直接复制右边数组的元素到辅助数组。右边数组同理
            if (i > center) {
                temp[k] = arr[j++];
            } else if (j > right) {
                temp[k] = arr[i++];
            }

            // 合并时，比较两有序数组值，将小的放入辅助数组中
            else if (arr[i] <= arr[j]) {
                temp[k] = arr[i++];
            } else {
                // 与归并排序所不同的就是添加了如下这句，统计count的关键！
                this.count += center-i+1; // arr[i] > arr[j]，说明 arr[i...center] 都大于 arr[j]
                temp[k] = arr[j++];
            }
        }
        // 再将已经排序好的辅助数组中的值复制到原数组 arr 中
        for (int k = left; k <= right; k++) {
            arr[k] = temp[k];
        }
    }
}
