package search.ordered;

/**
 * @description: 二分查找及相关运用
 * @author: Kevin
 * @createDate: 2020/2/17
 * @version: 1.0
 */
public class BinarySearch {

    public static void main(String[] args) {
        BinarySearch solution = new BinarySearch();
        int[] nums = {1, 2, 4, 4, 5, 6};
        int K = 4;
        int res = solution.GetNumberOfK(nums, K);
        System.out.println("res:"+res);
    }

    /**
     * 数字在排序数组中出现的次数
     * @param nums
     * @param K
     * @return
     */
    public int GetNumberOfK(int[] nums, int K) {
        int index = binarySearchWithR(nums, K);
        int index2 = binarySearchWithoutR(nums, K);
        System.out.println("binarySearchWithR res:" + index);
        System.out.println("binarySearchWithoutR res:" + index2);
        if(nums == null || nums.length == 0)    return 0;
        int first = left_bound(nums, K);
        int last = left_bound(nums, K + 1);
        int myfirst = leftBound(nums, K);
        int mylast = leftBound(nums, K + 1);
        System.out.println("left_bound res:" + (last - first));
        System.out.println("myleft_bound res:" + (mylast - myfirst));
        int lastr = right_bound(nums, K);
        int firstr = right_bound(nums, K - 1);
        int mylastr = rightBound(nums, K);
        int myfirstr = rightBound(nums, K - 1);
        System.out.println("right_bound res:" + (lastr - firstr));
        System.out.println("myright_bound res:" + (mylastr - myfirstr));
        System.out.println("right-left res:" + (mylastr - myfirst + 1));

        return last - first;
    }

    /**
     * 基本的二分查找：用于寻找一个数的下标，无则返回-1
     * @param nums
     * @param target
     * @return
     */
    private int binarySearchWithR(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1; // 注意 [left, right]

        while(left <= right) { // 注意
            int mid = (right - left) / 2 + left; // mid = (left + right) / 2 的优化形式，防止溢出！
            if(nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1; // 注意
        }
        return -1;
    }

    private int binarySearchWithoutR(int[] nums, int target) {
        int left = 0;
        int right = nums.length; // 注意 [left, right)

        while(left < right) { // 注意
            int mid = (right - left) / 2 + left;
            if(nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid; // 注意
        }
        return -1;
    }

    /**
     * 我的二分查找运用：寻找一个数的左侧边界，无则返回比它大的数的左侧边界
     * @param nums
     * @param target
     * @return
     */
    private int leftBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                right = mid -1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid -1;
            }
        }
        return left;
    }

    /**
     * 我的二分查找运用：寻找一个数的右侧边界，无则返回比它小的数的右侧边界
     * @param nums
     * @param target
     * @return
     */
    private int rightBound(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return right; // 注意
    }

    private int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        return left;
    }

    private int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1; // 注意
    }

}
