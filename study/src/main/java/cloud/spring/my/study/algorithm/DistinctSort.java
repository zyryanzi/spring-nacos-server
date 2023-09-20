package cloud.spring.my.study.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistinctSort {

    public static List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        // 排序
        mergeSort(nums, 0, length - 1);

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < length - 2; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int third = length - 1;
            for (int j = i + 1; j < length - 1 && j < third; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (third > j + 1 && nums[i] + nums[j] + nums[third] > 0) {
                    third--;
                }

                if (nums[i] + nums[j] + nums[third] == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[third]));
                }

            }
        }
        return res;
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (r <= l) {
            return;
        }
        int m = (l + r) >> 1;
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int i = l;
        int j = m + 1;
        int[] sortedArr = new int[r - l + 1];
        int idx = 0;
        /*
            从最小元素开始，比较左右两边数组（已经有序）元素，
            并将较小的元素放入新数组，
            直到一边的数组元素全部放入新数组
        */
        while (i <= m && j <= r) {
            sortedArr[idx++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        // 左边剩余数组（已经有序）直接加入新数组
        while (i <= m) {
            sortedArr[idx++] = arr[i++];
        }
        // 右边剩余数组（已经有序）直接加入新数组
        while (j <= r) {
            sortedArr[idx++] = arr[j++];
        }

        for (int k = 0; k < sortedArr.length; k++) {
            arr[k + l] = sortedArr[k];
        }
    }

    public static void main(String[] args) {
//        -2 0 1 3
        int[] nums = {3, -2, 1, 0};
        System.out.println(threeSum(nums));
//        mergeSort(nums, 0, nums.length - 1);
//        Arrays.stream(nums).forEach(System.out::println);
    }

}
