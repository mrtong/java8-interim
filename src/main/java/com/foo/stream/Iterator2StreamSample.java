package com.foo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//In Java 8, we can use StreamSupport.stream to convert an Iterator into a Stream
public class Iterator2StreamSample {

    public Iterator2StreamSample() {
        this.iterable2Stream();
        this.iterator2Stream();
    }

    public static void main(String[] args) {
        new Iterator2StreamSample();
    }

    private void iterator2Stream() {
        System.out.println("iterator2Stream sample.");
        List<String> list = new ArrayList<>();
        list.add("mkyong");
        list.add("java");
        list.add("kotlin");
        list.add("spring boot");
        list.add("android");

        Iterator<String> iterator = list.iterator();
        List<Integer> result = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false)
                .map(x -> x.length())
                .collect(Collectors.toList());

        result.forEach(x -> System.out.println(x));
    }

    private void iterable2Stream() {
        System.out.println("iterable2Stream sample.");
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5);

        // Iterable -> Spliterators -> Stream -> List
        List<Integer> result = StreamSupport.stream(
                iterable.spliterator(), false)
                .map(x -> x * 10)
                .collect(Collectors.toList());

        result.forEach(x -> System.out.println(x));
    }
}
