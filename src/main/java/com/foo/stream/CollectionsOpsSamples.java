package com.foo.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
        Integer integerMax = numbers.stream().max(Comparator.comparingInt(Integer::intValue)).get();
        Integer integerMin = numbers.stream().min(Comparator.naturalOrder()).get();
        System.out.println("The max of this List<Integer> is " + integerMax);
        System.out.println("The min of this List<Integer> is " + integerMin);
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
