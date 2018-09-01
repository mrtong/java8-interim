package com.foo.stream;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This intermediate operation returns a IntStream consisting of the results of applying the given mapper function to the elements of this stream.
 */
public class MapToIntDemo {
    public static void main(String... args){
        sample1();
        sample2();
    }
    static void sample1(){
        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3
    }
    static void sample2(){
        String[] s = {"one", "two", "three", "four"};
        Stream<String> stringStream = Stream.of(s);
        IntStream intStream = stringStream.mapToInt(e -> e.length());
        IntSummaryStatistics stats = intStream.peek(System.out::println)
                .summaryStatistics();
        System.out.println(stats);
    }
}
