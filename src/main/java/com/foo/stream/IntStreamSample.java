package com.foo.stream;

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

        sumStringStream();
        convertFromStringStreamToIntList();

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
        return intStream.mapToObj(String::valueOf).collect(Collectors.joining(", ", "[", "]"));

    }

    private int[] intStreamToArray(IntStream intStream) {
        return intStream.toArray();
    }

    private void sumStringStream() {
        Optional<Integer> reduce = Stream.of("2", "3", "4")
                .map(Integer::valueOf)
                .reduce((partial, e) -> {
                    partial = partial + e;
                    return partial;
                });
        //9
        System.out.println("sumStringStream = " + reduce.get());
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
