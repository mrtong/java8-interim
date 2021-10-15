package com.foo.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamSample {
    public IntStreamSample() {
        IntStream intStream = IntStream.of(1, 2, 3);

        System.out.println("sum of intstream is " + this.getSum(intStream));

        intStream = IntStream.of(1, 2, 3);
        System.out.println("aveage of intstream is " + this.getAverage(intStream));

        intStream = IntStream.of(1, 2, 3);
        System.out.println("Convert int stream to a string. The result is " + intStreamToString(intStream));

        intStream = IntStream.of(1, 2, 3);
        System.out.println("Convert int stream to an array. The result is " + intStreamToArray(intStream));

        System.out.println("Convert an array to int stream. The result is " + this.array2IntStream());

        sum_A_StringStream_By_reduce();
        sum_A_StringStream_By_mapToInt();
        convertFromStringStreamToIntList();

        intStream = IntStream.of(1, 2, 3);
        intStream2Stream(intStream);

    }

    private int getSum(IntStream intStream) {
        return intStream.sum();
    }

    private double getAverage(IntStream intStream) {
        OptionalDouble average = intStream.average();
        if (average.isPresent()) {
            return average.getAsDouble();
        }

        return 0;
    }

    private String intStreamToString(IntStream intStream) {
        //[1, 2, 3]
        //mapToObj go back to Stream
        return intStream.mapToObj(String::valueOf).collect(Collectors.joining(", ", "[", "]"));

    }

    private void intStream2Stream(IntStream intStream) {
        Stream<Integer> boxed = intStream.boxed();
        Optional<Integer> max = boxed.max(Integer::compareTo);

        System.out.println("intStream2Stream = " + max.get());
    }

    private int[] intStreamToArray(IntStream intStream) {
        return intStream.toArray();
    }

    private IntStream array2IntStream() {
        int[] array = {1, 2, 3};
        IntStream stream = Arrays.stream(array);
        return stream;
    }

    private void sum_A_StringStream_By_reduce() {
        Optional<Integer> reduce = Stream.of("2", "3", "4")
                .map(Integer::valueOf)
                .reduce((partial, e) -> {
                    partial = partial + e;
                    return partial;
                });
        //9
        System.out.println("um_A_StringStream_By_reduce = " + reduce.get());
    }

    private void sum_A_StringStream_By_mapToInt() {
        int sum = Stream.of("2", "3", "4")
                .mapToInt(Integer::valueOf)
                .sum();

        //9
        System.out.println("sum_A_StringStream_By_mapToInt = " + sum);
    }

    private void convertFromStringStreamToIntList() {
        List<Integer> collect = Stream.of("2", "3", "4")
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        //[2, 3, 4]
        System.out.println("convertFromStringStreamToIntList = " + collect);
    }

    public static void main(String[] args) {
        new IntStreamSample();

    }
}
