package com.foo.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ReduceSample2 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Java", "Python", "C++", "JavaScript", "Ruby");
        String longestString = findLongestString(strings);
        String longestString2 = findLongestString2(strings);

        System.out.println("Longest string: " + longestString);
        System.out.println("Longest string approach2: " + longestString2);

        List<Integer> numbers = Arrays.asList(2, -3, 4, -5, 6, -7, 8);
        int product = calculatePositiveProduct(numbers);
        int product2 = calculatePositiveProduct2(numbers);
        System.out.println("Product of positive numbers: " + product);
        System.out.println("Product of positive numbers approach2: " + product2);
    }

    //calculate the product of all the positive numbers in the given list
    public static int calculatePositiveProduct(List<Integer> numbers) {

        // Use the reduce operation to calculate the product of positive numbers
        return numbers.stream().reduce(0, (a, b) -> {
            if (b < 0) {
                return a;
            } else {
                return a + b;
            }
        });

    }

    public static int calculatePositiveProduct2(List<Integer> numbers) {

        // Use the reduce operation to calculate the product of positive numbers
        Optional<Integer> reduce = numbers.stream().filter(n -> n > 0).reduce((a, b) -> a + b);
        return reduce.isPresent() ? reduce.get() : 0;
    }

    public static String findLongestString(List<String> strings) {

        // Use the reduce operation to find the longest string
        String reduce = strings.stream().reduce("", (a, b) -> {
            if (a.length() > b.length()) {
                return a;
            } else {
                return b;
            }
        });

        // Return the longest string
        return reduce;
    }
    public static String findLongestString2(List<String> strings) {
        return strings.stream().max((a,b)->Integer.compare(a.length(),b.length())).orElse("");
    }

    public static String findLongestString3(List<String> strings) {
        return strings.stream().max(Comparator.comparingInt(String::length)).orElse("");
    }

}
