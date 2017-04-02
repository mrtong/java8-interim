package com.foo.stream;

import java.util.stream.Stream;

/**
 * Created by yanjuntong on 2/04/17.
 * convert a String Stream to Int Stream and find the max
 */
public class ChangeStreamTypes {
    public ChangeStreamTypes(){
        Stream.of("a1", "a3","a3")
                .map(s->s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);
    }
    public static void main(String ...args){
        new ChangeStreamTypes();
    }
}
