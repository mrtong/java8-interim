package com.foo.stream;

import com.foo.pojo.Person;

import java.util.ArrayList;
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
        System.out.println("=================");
        collectSample(persons);

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

    //how to convert a kind of List another type of List
    private void collectSample(final List<Person> persons) {
        ArrayList<NewPerson> collect = persons.stream().collect(ArrayList<NewPerson>::new, (partial, element) -> {
            NewPerson newPerson = new NewPerson(element.name, element.age, "Male");
            partial.add(newPerson);

        }, ArrayList::addAll);
        
        //[Max-18-Male, Peter-23-Male, Pamela-23-Male, David-12-Male]
        System.out.println(collect);
    }

    class NewPerson {
        public String name;
        public int age;
        public String gender;

        public NewPerson(String name, int age, String gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return name + "-" + age + "-" + gender;
        }
    }

    public static void main(String... args) {
        new CollectorsSample();
    }
}
