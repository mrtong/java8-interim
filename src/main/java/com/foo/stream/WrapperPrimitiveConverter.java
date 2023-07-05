package com.foo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WrapperPrimitiveConverter {
    public static void main(String[] args) {
        wrapper2primitive();
        primitive2wrapper();
        integerList2array();
        integerArray2List();
    }

    private static void wrapper2primitive() {
        System.out.println("Start of wrapper2primitive()");

        Integer[] integers = new Integer[7];
        Arrays.setAll(integers, index -> index * 2);
        System.out.println("The value of Integer[].");
        System.out.println(Arrays.toString(integers));

        System.out.println("After conversion, the value of int[] is ");

        int[] ints = Arrays.stream(integers).mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(ints));

        System.out.println("Complete of wrapper2primitive()");
    }

    private static void primitive2wrapper() {
        System.out.println("start of primitive2wrapper()");

        int[] ints = new int[7];
        Arrays.setAll(ints, index -> index / 2);
        System.out.println("The value of int[].");
        System.out.println(Arrays.toString(ints));

        System.out.println("After conversion, the value of integers[] is ");

        Integer[] integers = Arrays.stream(ints).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));

        System.out.println("Complete of primitive2wrapper()");
    }

    private static void integerList2array() {
        System.out.println("start of integerList2array()");
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 65);
        Integer[] integers = integerList.toArray(new Integer[0]);
        System.out.println(Arrays.toString(integers));
        System.out.println("Complete of integerList2array()");
    }

    private static void integerArray2List() {
        System.out.println("start of integerArray2List()");
        Integer[] integers = new Integer[7];
        Arrays.setAll(integers, index -> index * 2);
        List<Integer> integerList = Arrays.stream(integers).collect(Collectors.toList());
        integerList.stream().forEach(c -> System.out.println(c));
        System.out.println("Complete of integerList2array()");
    }
}
