package com.foo.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CollectionsOpsSamples {
    /*
     we can reverse a List
     we can sort a List
     we can reverseSort a List
     we can find Min Max of a List
     we can swap numbers of a List
     we can find frequency of an element within a List
     we can do binary search of a sorted List
     */
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);

        List<Integer> originalNumbers = new ArrayList<>();
        originalNumbers.addAll(numbers);

        reverseIntegerList(numbers);
        Collections.copy(numbers, originalNumbers);

        sortIntegerList(numbers);
        Collections.copy(numbers, originalNumbers);

        reverseSortIntegerList(numbers);
        Collections.copy(numbers, originalNumbers);

        findMinMax(numbers);

        findMinMax2(numbers);

        swapNumbers(numbers);
        findFrequencyOfANumber();

        Collections.copy(numbers, originalNumbers);
        binarySearch(numbers);
    }

    private static void reverseIntegerList(List<Integer> numbers) {
        System.out.println("Before reversing: " + numbers);
        Collections.reverse(numbers);
        System.out.println("After reversing: " + numbers);
    }

    private static void sortIntegerList(List<Integer> numbers) {
        System.out.println("Before sort: " + numbers);
        Collections.sort(numbers);
        System.out.println("After sort: " + numbers);
    }

    private static void reverseSortIntegerList(List<Integer> numbers) {
        System.out.println("Before reverseSort: " + numbers);
        Collections.sort(numbers, Comparator.reverseOrder());
        System.out.println("After reverseSort: " + numbers);

    }

    private static void findMinMax(List<Integer> numbers) {
        System.out.println("The min of this collection is " + Collections.min(numbers));
        System.out.println("The max of this collection is " + Collections.max(numbers));
    }

    private static void findMinMax2(List<Integer> numbers) {
        //although we can use Comparator.naturalOrder() here, I think `Integer::compareTo` is better
        //the reason is I know the type of the List is Integer.
        //Integer::compareTo: This is a method reference to the compareTo method of the Integer class.
        // It explicitly specifies the comparison logic using the method reference.
        //Comparator.naturalOrder(): This uses a built-in comparator provided by the Comparator interface.
        // It represents the natural ordering of elements based on their natural order defined by the Comparable interface.
        Optional<Integer> integerMax = numbers.stream().max(Integer::compareTo);
        if (integerMax.isPresent()) {
            System.out.println("The max of this List<Integer> is " + integerMax.get());
        } else {
            System.out.println("Im surpriced we cant find the max element within the List.");
        }
        Optional<Integer> integerMin = numbers.stream().min(Integer::compareTo);
        if (integerMin.isPresent()) {

            System.out.println("The min of this List<Integer> is " + integerMin.get());
        } else {
            System.out.println("Im surpriced we cant find the min element within the List.");
        }
    }

    private static void swapNumbers(List<Integer> numbers) {
        System.out.println("Before swap: " + numbers);
        Collections.swap(numbers, 1, 3);
        System.out.println("After swap: " + numbers);
        numbers.stream().forEach(c -> System.out.println(c));
    }

    private static void findFrequencyOfANumber() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);
        numbers.add(1);
        numbers.add(8);

        System.out.println("8 appears " + Collections.frequency(numbers, 8) + " times in the List.");

    }

    private static void binarySearch(List<Integer> numbers) {
        System.out.println("start of binarySearch");
        Collections.sort(numbers);
        int i = Collections.binarySearch(numbers, 8);
        System.out.println("8 was found in the List in " + i);
    }

}
