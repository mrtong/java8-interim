package com.foo.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TakeWhileDemo {
    public TakeWhileDemo() {
        Stream<String> stream
                = Stream.of("aman", "amar", "suraj",
                "suvam", "Zahafuj","adar");

        // apply takeWhile to take all the names
        // matches passed predicate
        List<String> list
                = stream.takeWhile(name -> (name.charAt(0) == 'a'))
                .collect(Collectors.toList());
        //the result will be [aman, amar] although adar starts with a as well.
        System.out.println(list);
    }

    public static void main(String[] args) {
        new TakeWhileDemo();
    }
}
