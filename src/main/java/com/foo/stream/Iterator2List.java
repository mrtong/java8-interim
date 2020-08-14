package com.foo.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class Iterator2List {
    public static void main(String[] args) {
        Iterator<Integer> iterator = IntStream.range(1, 4).iterator();
        List<Integer> list = iteratorToList(iterator);
        System.out.println(list);

        System.out.println("---------iteratorToList2--------------");
        iterator = IntStream.range(1, 4).iterator();
        list=iteratorToList2(iterator);
        System.out.println(list);

        System.out.println("-----------iteratorToList3------------");
        iterator = IntStream.of(1,3,2).iterator();
        list=iteratorToList3(iterator);
        System.out.println(list);

        System.out.println("---------iteratorToList4--------------");
        list=iteratorToList4();
        System.out.println(list);


    }

    private static<T> List<T> iteratorToList(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }

    private static<T> List<T> iteratorToList2(Iterator<T> iterator) {
        Iterable<T> iterable = ()->iterator;
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    private static<T> List<T> iteratorToList3(Iterator<T> iterator) {
        //Like Iterator and ListIterator, Spliterator is a Java Iterator,
        // which is used to iterate elements one-by-one from a List implemented object.
        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED);
        return StreamSupport.stream(spliterator, false).collect(Collectors.toList());
    }

    private static List<Integer> iteratorToList4() {
        Spliterator.OfInt spliterator = IntStream.range(1, 4).spliterator();
        return StreamSupport.stream(spliterator, false).collect(Collectors.toList());
    }

}
