package com.foo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Wrapper2PrimitiveSample {
    public static void main(String... arhg){

        convertIntegerArrayToIntArray();
    }

    static void convertIntegerArrayToIntArray(){
        Integer[] integerArray = new Integer[]{
                new Integer("1"),new Integer("2")
        };

        int[] intArray = Stream.of(integerArray).mapToInt(Integer::intValue).toArray();
        System.out.println(intArray);
    }

    static void convertIntArrayToIntegerArray(){
        int[] intArray = new int[]{
                1,2
        };

        Integer[] integerArray = Arrays.stream(intArray).boxed().toArray(Integer[]::new);
        System.out.println(integerArray);
    }

    static void convertIntegerListToIntArray(){
        Integer[] integerArray = new Integer[]{
                new Integer("1"),new Integer("2")
        };

        List<Integer> list = Arrays.asList(integerArray);
        //there are two approaches
//        int[] intArray = list.stream().mapToInt(i -> i).toArray();

        int[] intArray = list.stream().mapToInt(Integer::intValue).toArray();;
        System.out.println(intArray);
    }
}
