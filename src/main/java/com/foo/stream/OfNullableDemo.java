package com.foo.stream;

import java.util.ArrayList;
import java.util.stream.Stream;

public class OfNullableDemo {
    public OfNullableDemo() {
        whenOnlyOneItemThenPrintEmpty();

        //the result of the following methods are the same;
        //the result is [Aman, , Suraj, null, Zufaq]
        doNothingWhenMoreThanOneItem();
        //the result is [Aman, , Suraj, null, Zufaq]
        doTheSameAsOfWhenMoreThanOneItem();
    }

    void doNothingWhenMoreThanOneItem() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Aman");
        list.add("");
        list.add("Suraj");
        list.add(null);
        list.add("Zufaq");

        // create a stream with ArrayList
        Stream<ArrayList<String>> value
                = Stream.of(list);

        // print values
        System.out.println("Values of Stream:");
        value.forEach(System.out::println);
    }

    void doTheSameAsOfWhenMoreThanOneItem() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("Aman");
        list.add("");
        list.add("Suraj");
        list.add(null);
        list.add("Zufaq");

        // create a stream with ArrayList
        Stream<ArrayList<String>> value
                = Stream.ofNullable(list);

        // print values
        System.out.println("Values of Stream:");
        value.forEach(System.out::println);
    }

    void whenOnlyOneItemThenPrintEmpty() {
        Stream<String> value
                = Stream.ofNullable(null);

        // Print values
        System.out.println("Values of Stream:");
        //Print nothing
        value.forEach(System.out::println);
    }

    public static void main(String[] args) {
        new OfNullableDemo();
    }

}
