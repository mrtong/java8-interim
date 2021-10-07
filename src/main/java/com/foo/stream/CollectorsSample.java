package com.foo.stream;

import com.foo.pojo.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yanjuntong on 4/04/17.
 * groupingBy sample can be found in GroupingBySample.java
 */
public class CollectorsSample {
    CollectorsSample() {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        joiningSample(persons);

    }

    //The join collector accepts a delimiter as well as an optional prefix and suffix.
    private void joiningSample(final List<Person> persons) {
        final String phase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In German ", " are of legal age."));

        //In German Max and Peter and Pamela are of legal age.
        System.out.println(phase);
    }

    public static void main(String... args) {
        new CollectorsSample();
    }
}
