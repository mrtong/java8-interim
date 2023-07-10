package com.foo.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class ArraysOpsSample {
    /*
    we can do binary search in an Array
    we can split an array via splitor
    we can sort an array
    we can reverse-sort an array
    we can use splitor to split an array
    we can find frequency
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
        int[] originalNumbers = Arrays.copyOf(numbers, numbers.length);

        System.out.println("The original array was :" + Arrays.toString(numbers));
        sortArrays(numbers);
        System.out.println("After sort the array has become to :" + Arrays.toString(numbers));
        //put it back
        numbers = Arrays.copyOf(originalNumbers, numbers.length);
        System.out.println("after put back it is " + Arrays.toString(numbers));

        reverseSortArrays(numbers);
        numbers = Arrays.copyOf(originalNumbers, numbers.length);
        //binary search
        searchIntArrays(numbers, 2);
        convertToString(numbers);
        searchObjectarrays();
        useSpliterator();
        useSpliterator2();

        int[] numbers1 = {1, 2, 3, 4, 2, 3, 2, 1, 2};
        int targetNumber = 2;
        findFrequency(numbers1, targetNumber);
        findFrequency2(numbers1, targetNumber);
    }

    private static void sortArrays(int[] numbers) {
        //the sort methods within Arrays does change the original array
        Arrays.sort(numbers);
    }

    private static void reverseSortArrays(int[] numbers) {
        //the sort methods within Arrays does change the original array

        Arrays.sort(numbers);
        Integer[] newNumbers = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        List<Integer> integerList = Arrays.asList(newNumbers);
        Collections.reverse(integerList);

        //Since the array is backed by the list, the reverse operation affects the original array.
        System.out.println("After reverse sort the array has become to :" + Arrays.toString(newNumbers));
        System.out.println("However the numbers array is still the same " + Arrays.toString(numbers));
        //the reason is Collections.reverse(integerList); integerList is from newNumbers not from numbers
    }

    private static void searchIntArrays(int[] numbers, int key) {
        //need to be sorted 1st
        Arrays.sort(numbers);
        int i = Arrays.binarySearch(numbers, key);
        System.out.println(" Key=" + key + " was found in array, the index was " + i);
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
        //this one is search by age. Even though in the below sample person's name is 'aaa', it does not affect the search result
        int index = Arrays.binarySearch(people, new Person("aaaa", searchAge), Comparator.comparing(Person::getAge));
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

    public static void findFrequency(int[] numbers, int targetNumber) {

        long count = Arrays.stream(numbers).filter(e -> e == targetNumber).count();
        System.out.println(targetNumber + " appeared " + count + " times. in findFrequency() aproach.");
    }

    public static void findFrequency2(int[] numbers, int targetNumber) {
        Integer[] integers = Arrays.stream(numbers).boxed().toArray(Integer[]::new);

        List<Integer> ints = Arrays.asList(integers);
        int frequency = Collections.frequency(ints, targetNumber);
        System.out.println(targetNumber + " appeared " + frequency + " times. in findFrequency2() aproach.");
    }
}
