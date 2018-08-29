package com.foo.stream;

import java.util.stream.Stream;

/**
 * Created by yanjuntong on 2/04/17.
 * https://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
 */
public class TheOrderOfFilterMatters {
    public TheOrderOfFilterMatters(){
        bothMapAndFilterCalledFiveTimes();
        System.out.println("==============================================================");
        mapCallOnce();
    }

    private void bothMapAndFilterCalledFiveTimes(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("foreach: " + s));

    }

    private void mapCallOnce(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                            System.out.println("map: " + s);
                            return s.toUpperCase();
                        }
                )
                .forEach(s -> System.out.println("foreach: " + s));

    }

    public static void main(String ...a){
        new TheOrderOfFilterMatters();
    }
}
