package com.foo.function;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerAsAParameter {
    public static void main(String[] args) {

        List<Integer> list = List.of(1, 2, 3, 4, 5);
        forEach(list, x-> System.out.println(x));
    }
    static <T> void forEach(List<T> list, Consumer<T> consumer){
        for (T t : list) {
            consumer.accept(t);
        }

    }
}
