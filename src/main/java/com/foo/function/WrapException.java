package com.foo.function;

import java.util.List;
import java.util.function.Consumer;

public class WrapException {

    public WrapException() {
        List<Integer> integers = List.of(1, 2, 3, 4, 0, 5);
        integers.forEach(lambdaWrapper(i -> System.out.println(1 / i)));

    }

    Consumer<Integer> lambdaWrapper(Consumer<Integer> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (ArithmeticException e) {
                System.out.println(e);
            }
        };

    }

    public static void main(String[] args) {
        new WrapException();
    }

}
