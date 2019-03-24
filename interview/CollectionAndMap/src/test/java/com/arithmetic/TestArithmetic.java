package com.arithmetic;

import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;

public class TestArithmetic {
    //算法


    /**
     * 冒泡排序
     */
    @Test
    public void fun() {
        int[] arr = {1, 2, 5, 7, 12, 45};
        sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public int[] sort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tep = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tep;
                }
            }
        }
        return arr;
    }

    //    *****************************************************快速排序**************************************************************************
    @Test
    public void fun02() {
        int[] arr = {1, 5, 45, 3, 32, 22};
        qSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    //  选择一个枢轴值(关键字) 把它放到某个位置 使其左边的值都比它小 右边的值都比它大
    public int partition(int[] arr, int low, int high) {
        int pivotkey = arr[low];

        while (low < high) {
            while (pivotkey < arr[high] && low < high) {//从后往前找到比key小的放到前面去
                high--;
            }
            swap(arr, low, high);
            while (arr[low] < pivotkey && low < high) {//从前往后找到比key大的 放到后面去

                low++;
            }
            swap(arr, low, high);

        }//遍历所有记录  low的位置即为 key所在位置, 且固定,不用再改变
        return low;
    }

    public void swap(int[] array, int lo, int hi) { //交换位置
        int temp = array[lo];
        array[lo] = array[hi];
        array[hi] = temp;
    }

    public void qSort(int[] arr, int low, int high) {

        if (low < high) {
            int partition = partition(arr, low, high);
            //将L[low,high]一分为二,算出枢轴值pivot,该值得位置固定,不用再变化
//             对两边的数组进行排序
            qSort(arr, low, partition - 1);
            qSort(arr, partition + 1, high);

        }
    }

    //    *****************************************************二分查找法**************************************************************************

    @Test
    public void fun03() {
        int[] arr = {1, 6, 7, 9, 12, 50, 89};
        int key = 9;
        System.out.println(binSearch(arr, key));
    }


    public int binSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int middle = 0;

        if (key < arr[low] || key > arr[high] || low > high) {
            return -1;
        }

        while (low <= high) {
            middle = (low + high) / 2;

            if (key < arr[middle]) {
                high = middle - 1;
            } else if (key > arr[middle]) {
                low = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
}
