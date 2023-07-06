package com.foo.algorithm;

import java.util.ArrayList;
import java.util.List;

public class CountPrimes {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 14, 113);

        List<Integer> primeNumbers = findPrimeNumbers(numbers);

        System.out.println("Prime numbers: " + primeNumbers);
    }

    public static List<Integer> findPrimeNumbers(List<Integer> numbers) {
        List<Integer> primeNumbers = new ArrayList<>();

        for (int number : numbers) {
            if (isPrime(number)) {
                primeNumbers.add(number);
            }
        }

        return primeNumbers;
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        //then I know all the numbers are odd
        if (number % 2 == 0) {
            return false;
        }
        if (number % 3 == 0) {
            return false;
        }

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
