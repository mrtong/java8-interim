package com.foo.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Spliterator;

public class ArraysOpsSample {
    /*
    we can do binary search in an Array
    we can splite an array
    we can sort an array
     */
    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person [name=" + name + ", age=" + age + "]";
        }
    }

    public static void main(String[] args) {
        int[] numbers = {5, 2, 8, 1, 9};
        System.out.println("The original array was :" + Arrays.toString(numbers));
        sortArrays(numbers);
        System.out.println("After sort the array has become to :" + Arrays.toString(numbers));
        //binary search
        searchIntArrays(numbers, 2);
        convertToString(numbers);
        searchObjectarrays();
        useSpliterator();
        useSpliterator2();
    }

    private static void sortArrays(int[] numbers) {
        //the sort methods within Arrays does change the original array
        Arrays.sort(numbers);
    }

    private static void searchIntArrays(int[] numbers, int key) {
        int i = Arrays.binarySearch(numbers, key);
        System.out.println(" Key=" + key + " was found in array, the index was " + i);
        //how to do a binary seach of Object?
    }

    private static void convertToString(int[] numbers) {
        String s = Arrays.toString(numbers);
        System.out.println("After converting to string it becomes " + s);
    }

    private static void searchObjectarrays() {
        Person[] people = {
                new Person("John", 25),
                new Person("Alice", 30),
                new Person("Bob", 20),
                new Person("Luke", 30),
                new Person("Jane", 35)
        };
        //If you do not provide a sorted array to the binarySearch() method, the result is unpredictable.
        Arrays.sort(people, Comparator.comparingInt(Person::getAge));
        int searchAge = 30;
        int index = Arrays.binarySearch(people, new Person("", searchAge), Comparator.comparing(Person::getAge));
        if (index >= 0) {
            System.out.println("Person found at index: " + index);
            System.out.println(people[index]);
        } else {
            System.out.println("Person not found.");
        }
    }

    //Arrays.spliterator is a useful way to iterate an array
    private static void useSpliterator() {
        System.out.println("--Below is to show how to use Spliterator. --");
        String[] names = {"John", "Alice", "Bob", "Jane"};
        Spliterator<String> spliterator = Arrays.spliterator(names);
        spliterator.forEachRemaining(c -> System.out.println(c));

        System.out.println("--Complete useSpliterator--");
    }

    private static void useSpliterator2() {
        System.out.println("--Below is to show how to use Split the spliterator into three sub-spliterators. --");
        String[] names = {"John", "Alice", "Bob", "Jane", "Tom", "Linda", "Luke", "Asher"};
        // Get a spliterator for the names array
        Spliterator<String> spliterator = Arrays.spliterator(names, 0, 7);
        // Split the spliterator into three sub-spliterators
        Spliterator<String> spliterator1 = spliterator.trySplit();
        Spliterator<String> spliterator2 = spliterator.trySplit();
        Spliterator<String> spliterator3 = spliterator;
//
//        // Process the elements of the spliterators in parallel
//        System.out.println("Processing spliterator1 elements:");
//        spliterator1.forEachRemaining(System.out::println);
//
//        System.out.println("Processing spliterator2 elements:");
//        spliterator2.forEachRemaining(System.out::println);
//        System.out.println("Processing spliterator3 elements:");
//        spliterator3.forEachRemaining(System.out::println);

        while (spliterator1.tryAdvance(a -> {
            System.out.println(a);
        })) ;
        System.out.println("Processing spliterator2 elements:");
        while (spliterator2.tryAdvance(a -> {
            System.out.println(a);
        })) ;
        System.out.println("Processing spliterator3 elements:");
        while (spliterator3.tryAdvance(a -> {
            System.out.println(a);
        })) ;

    }
}
