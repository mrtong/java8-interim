package com.foo.stream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yanjuntong on 2/04/17.
 */
public class HowStreamWorks {
    public HowStreamWorks(){
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2","c1");
        //Once you have a Stream object, you can use a variety of methods to transform it into another Stream object.
        // The first such method we’re going to look at is the map method.
        // It takes a lambda expression as its only argument, and uses it to change every individual element in the stream.
        // Its return value is a new Stream object containing the changed elements.
        myList
                .stream()
                .filter(s->s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
    public static void main(String ... args){
        new HowStreamWorks();
    }
}
