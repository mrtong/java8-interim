package com.foo.lambda;

import java.util.function.Predicate;

//Predicate <T> interface is a functional interface with a method test(Object) to return a Boolean value.
public class PredicateDemo {
    public static void main(String... args) {
        Predicate<Integer> lessThan = i -> i < 18;
        if (lessThan.test(10)) {
            System.out.println("10 is less than 18");
        } else {
            System.out.println("10 is more than 18");
        }
    }
}
