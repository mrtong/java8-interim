package com.foo.stream;

import com.foo.pojo.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The reduction operation combines all elements of the stream into a single result.
 * T reduce(T identity, BinaryOperator<T> accumulator);
 * <p>
 * Where, identity is initial value
 * of type T and accumulator is a
 * function for combining two values
 */
public class ReduceSample {
    public static void main(String... args) {
        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        persons.stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);

        Person result = persons.stream()
                .reduce(new Person("", 0), (p1, p2) -> {
                    p1.age += p2.age;
                    p1.name += p2.name;
                    return p1;
                });
        System.out.format("name=%s; age=%s", result.name, result.age);

        List<String> words = Arrays.asList("GFG", "Geeks", "for",
                "GeeksQuiz", "GeeksforGeeks");
        Optional<String> longestString = words.stream()
                .reduce((word1, word2) -> word1.length() > word2.length()
                        ? word1 : word2);

        // Displaying the longest String
        longestString.ifPresent(System.out::println);

    }

}
