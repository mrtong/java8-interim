package com.foo.stream;

import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntStreamSample {
    public IntStreamSample() {
        IntStream intStream = IntStream.of(1,2,3);

        System.out.println(this.getSum(intStream));
        intStream = IntStream.of(1,2,3);
        System.out.println(this.getAverage(intStream));

    }

    private int getSum(IntStream intStream){
        return intStream.sum();
    }

    private double getAverage(IntStream intStream){
        OptionalDouble average = intStream.average();
        if(average.isPresent()){
             return average.getAsDouble();
        }

        return 0;
    }

    public static void main(String[] args) {
        new IntStreamSample();

    }
}
