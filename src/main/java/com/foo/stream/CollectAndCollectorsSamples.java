package com.foo.stream;

import com.foo.pojo.Person;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by yanjuntong on 4/04/17.
 */
public class CollectAndCollectorsSamples {
    CollectAndCollectorsSamples(){
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        this.groupingBySample(persons);
        this.summarizingIntSample(persons);
        joiningSample(persons);

    }

    private void groupingBySample(final List<Person> persons){
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p->p.age));
        personsByAge.forEach((age, p)->System.out.format("age %s: %s\n", age, p));
    }

    //the summarizing collectors return a special built-in summary statistics object.
    // So we can simply determine min, max and arithmetic average age of the persons as well as the sum and count.
    private void summarizingIntSample(final List<Person> persons){
        IntSummaryStatistics intSummaryStatistics =
                persons
                .stream()
                .collect(Collectors.summarizingInt(p->p.age));

        System.out.println(intSummaryStatistics);
    }

    //The join collector accepts a delimiter as well as an optional prefix and suffix.
    private void joiningSample(final List<Person> persons){
    final String phase = persons
            .stream()
            .filter(p->p.age>=18)
            .map(p->p.name)
            .collect(Collectors.joining(" and ", "In German ", " are of legal age."));

        System.out.println(phase);
    }

    public static void main(String ... args){
        new CollectAndCollectorsSamples();
    }
}
