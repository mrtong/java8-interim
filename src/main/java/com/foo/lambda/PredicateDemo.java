package com.foo.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

//Predicate <T> interface is a functional interface with a method test(Object) to return a Boolean value.
public class PredicateDemo {
    public static void main(String... args) {
        Predicate<Integer> lessThan = i -> i < 18;
        if (lessThan.test(10)) {
            System.out.println("10 is less than 18");
        } else {
            System.out.println("10 is more than 18");
        }

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        System.out.println("Print all numbers:");
        evaluate(list, n->true);

        System.out.println("Print no numbers:");
        evaluate(list, n->false);

        System.out.println("Print even numbers:");
        evaluate(list, n-> n%2 == 0 );

        System.out.println("Print odd numbers:");
        evaluate(list, n-> n%2 == 1 );

        System.out.println("Print numbers greater than 5:");
        evaluate(list, n-> n > 5 );
    }

    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        list.stream()
                .filter(s->predicate.test(s))
                .forEach(System.out::println);

    }
}
