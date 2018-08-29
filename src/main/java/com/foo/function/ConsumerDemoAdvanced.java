package com.foo.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer can be used in all contexts where an object needs to be consumed,i.e. taken as input, and some operation is to be performed on the object without returning any result.
 * https://www.javabrahman.com/java-8/java-8-java-util-function-consumer-tutorial-with-examples/
 */
public class ConsumerDemoAdvanced {
    public static void main(String... ar){
        Consumer<Integer> consumer=i-> System.out.print(" " + i);
        Consumer<Integer> consumerAndThen=consumer.andThen(i-> System.out.println("(printed " + i + ")"));
        List<Integer> integerList= Arrays.asList(new Integer(1),
                new Integer(10), new Integer(200),
                new Integer(101), new Integer(-10),
                new Integer(0));
        printList(integerList,consumerAndThen);

    }

    public static void printList(List<Integer> listOfIntegers, Consumer<Integer> consumerAndThen){
        listOfIntegers.stream().forEach(s->consumerAndThen.accept(s));
    }
}
