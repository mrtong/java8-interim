package com.foo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by yanjuntong on 19/06/17.
 */
public class LambdaExpressionTest {
    //this test case is used to convert a traditional Java5 expression to Java8 features
    @Test
    public void sumOfOddNumbers_Usual() {
        List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);

        int sum = 0;

        for (int number : numbers){

            if (number % 2 != 0) {
                sum += number;
            }
        }

        assertEquals(11, sum);
    }

    @Test
    public void sumOfOddNumbers_Lambda(){
        List<Integer> numbers = Arrays.asList(1, 3, 4, 6, 2, 7);
        numbers.stream()
                .filter(number->number%2!=0)
                .forEach(System.out::println);

        int sum = numbers.stream()
                .filter(number->number%2!=0)
                .reduce(0, Integer::sum);

        assertEquals(11, sum);


    }
}
