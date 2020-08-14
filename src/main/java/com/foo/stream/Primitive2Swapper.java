package com.foo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Primitive2Swapper {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};
        doTask(numbers);
        doTask1(numbers);
        doTask2(numbers);
    }

    private static void doTask(int[] numbers) {
        Integer[] boxed = new Integer[numbers.length];
        Arrays.setAll(boxed, i -> numbers[i]);
        List<Integer> list = Arrays.asList(boxed);
        System.out.println("Arrays.setAll(...) Approach");
        System.out.println(list);
    }

    private static void doTask1(int[] numbers) {
        List<Integer> list = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        System.out.println("Arrays.stream(...).boxed(...) approach.");
        System.out.println(list);
    }

    private static void doTask2(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        Arrays.stream(numbers).forEach(list::add);
        System.out.println("Normal approach.");
        System.out.println(list);
    }

}
