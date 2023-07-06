package com.foo.algorithm;

public class FindMaximumNumber {
    public static void main(String[] args) {
        int[] numbers = {12, 45, 67, 23, 9, 56, 78, 98, 34, 7, 87, 1, 32, 65, 43, 21, 76, 54, 89, 3, 10, 5, 43, 2, 99, 67, 34, 11, 8, 6, 90, 32, 56, 78, 34, 67, 55, 21, 43, 87, 10, 76, 54, 98, 43, 34, 1, 99, 76, 54};

        int maximum = findMaximumNumber(numbers);
        System.out.println("The maximum number is: " + maximum);
    }

    public static int findMaximumNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("The array must not be empty.");
        }

        return findMaximum(numbers);
    }
    //I do not think there is any better approach for this one
    //as Collections.max is using similar approach
    public static int findMaximum(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("The array must not be empty.");
        }

        int maximum = numbers[0];

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > maximum) {
                maximum = numbers[i];
            }
        }

        return maximum;
    }
}

