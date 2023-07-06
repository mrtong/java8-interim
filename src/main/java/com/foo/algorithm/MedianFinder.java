package com.foo.algorithm;

import java.util.Arrays;
import java.util.Random;

public class MedianFinder {
    public static double findMedianNoSort(int[] nums) {
        int n = nums.length;
        int k = (n + 1) / 2; // 中位数的位置，如果数组长度为奇数，则直接为中间位置；如果为偶数，则为中间两个数的左边位置

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int pivotIndex = partition(nums, left, right); // 使用快速选择算法找到一个基准元素的位置

            if (pivotIndex == k - 1) {
                break; // 找到了中位数
            } else if (pivotIndex < k - 1) {
                left = pivotIndex + 1; // 中位数在右侧子数组中
            } else {
                right = pivotIndex - 1; // 中位数在左侧子数组中
            }
        }

        if (n % 2 == 0) {
            // 如果数组长度为偶数，需要找到中间两个数并计算平均值
            int num1 = findKthSmallest(nums, k - 1);
            int num2 = findKthSmallest(nums, k);
            return (num1 + num2) / 2.0;
        } else {
            // 如果数组长度为奇数，直接返回中位数
            return findKthSmallest(nums, k - 1);
        }
    }

    private static int findKthSmallest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        while (true) {
            int pivotIndex = partition(nums, left, right);

            if (pivotIndex == k) {
                return nums[pivotIndex];
            } else if (pivotIndex < k) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, i, j);
                i++;
            }
        }

        swap(nums, i, right);
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static double findMedianSortApproach(int[] nums) {
        Arrays.sort(nums); // 对数组进行排序
        int length = nums.length;

        if (length % 2 == 0) {
            // 数组长度为偶数，取中间两个数的平均值作为中位数
            int middleIndex1 = length / 2 - 1;
            int middleIndex2 = length / 2;
            return (nums[middleIndex1] + nums[middleIndex2]) / 2.0;
        } else {
            // 数组长度为奇数，直接取中间的数作为中位数
            int middleIndex = length / 2;
            return nums[middleIndex];
        }
    }

    public static void main(String[] args) {
        Random random = new Random();

        int lowerBound = 1; // Specify the lower bound of the range
        int upperBound = 1000; // Specify the upper bound of the range

        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }


        double median = findMedianSortApproach(nums);
        System.out.println("中位数为: " + median);
        double median1 = findMedianNoSort(nums);
        System.out.println("中位数为: " + median1);
    }
}

