package com.foo.stream;

import com.foo.pojo.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yanjuntong on 4/04/17.
 */
public class CollectBasic {
    public CollectBasic(){
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));
        List<Person> filtered = persons
                .stream()
                .filter(p->p.name.startsWith("P"))
                .collect(Collectors.toList());

        filtered.stream()
                .forEach(System.out::println);

    }
    public static void main(String ... args){
        new CollectBasic();
    }
}
