package com.wang.algorism;

public class AbsSorting {

    public static void main(String[] args) {

        // int[] integerValues = new int[]{-2, 9, -1, 2, -5, 1, 7, 0, -1, -3, -2, 16, -4, 15, 14, 13, -33};
        int[] integerValues = new int[]{-2, 9, -1, 2, -5, 1, 7};
        // 冒泡排序
        // absBubbleSort(integerValues);

        // 快排序
        // fastSort(integerValues, 0, integerValues.length - 1);

        // 插入排序
        // insertSort(integerValues);

        // 希尔排序
        shellSort(integerValues);

        for (int integerValue : integerValues) {
            System.out.print(integerValue + ",");
        }
        System.out.println("");


    }

    /**
     * 希尔排序
     * */
    private static int shellSort(int arr[]) {
        int len = arr.length;

        // Start with a big gap, then reduce the gap
        for (int gap = len / 2; gap > 0; gap /= 2) {
            // Do a gapped insertion sort for this gap size.
            // The first gap elements a[0..gap-1] are already
            // in gapped order keep adding one more element
            // until the entire array is gap sorted
            for (int i = gap; i < len; i += 1) {
                // add a[i] to the elements that have been gap
                // sorted save a[i] in temp and make a hole at
                // position i
                int temp = arr[i];

                // shift earlier gap-sorted elements up until
                // the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }

                // put temp (the original a[i]) in its correct
                // location
                arr[j] = temp;
            }
        }
        return 0;
    }

    // 插入排序
    private static void insertSort(int[] values) {
        int size = values.length;

        // 从第2个元素开始
        for (int i = 1; i < size; i++) {
            int iValue = values[i];
            // 跟所有的之前的元素相比
            // 这里注意是 >= 0
            for (int j = i - 1; j >= 0; j--) {
                if (iValue < values[j]) {
                    swapValue(values, j, j + 1);
                } else {
                    break;
                }
            }
        }
    }

    private static void absBubbleSort(int[] values) {
        int size = values.length;
        for (int j = size - 1; j > 0; j--) {
            for (int i = 0; i < j; i++) {
                if (Math.abs(values[i]) > Math.abs(values[i + 1])) {
                    swapValue(values, i, i + 1);
                }
            }
        }
    }

    /**
     * 自己写的快排序（非绝对值）
     */
    private static void fastSort(int[] values, int startIndex, int endIndex) {
        int i = startIndex, j = endIndex;
        // 基准值，一般采用数组的第1个值
        int pivot = values[startIndex];
        // 随着排序运算的进行，基准值的位置在数组中会发生变化
        int pivotIndex = startIndex;
        // i和j作为下标，分别从数组两端向中心移动
        // 在pivotIndex的右侧找小值，或者在pivotIndex的左侧找大值
        // 这是一个循环并且交替进行的操作
        while (i < j) {
            if (i == pivotIndex) {
            // 代表刚移动过左方下标，也就是i
                // 在pivotIndex右侧寻找比pivot小的值
                // 此处要注意的是，一定要找到小，而不是小于等于的。
                // 否则就陷入死循环
                while (values[j] >= pivot && j > pivotIndex) {
                    j--;
                }
                // 如果找到了
                if (j > pivotIndex) {
                    swapValue(values, j, pivotIndex);
                    pivotIndex = j;
                }
            } else if (values[j] == pivot) {
            // 代表刚移动过右方下标，也就是j
                // 在pivotIndex左侧寻找比pivot大的值
                // 此处要注意的是，一定要找到大，而不是小于等于的。
                // 否则就陷入死循环
                while (values[i] <= pivot && i < pivotIndex) {
                    i++;
                }
                // 如果找到了
                if (i < pivotIndex) {
                    swapValue(values, i, pivotIndex);
                    pivotIndex = i;
                }
            }
        }

        if (pivotIndex - startIndex > 1) {
            fastSort(values, startIndex, pivotIndex - 1);
        }
        if (endIndex - pivotIndex > 1) {
            fastSort(values, pivotIndex + 1, endIndex);
        }
    }

    private static void swapValue(int[] array, int xIdx, int yIdx) {
        int tmp = array[xIdx];
        array[xIdx] = array[yIdx];
        array[yIdx] = tmp;
    }
}