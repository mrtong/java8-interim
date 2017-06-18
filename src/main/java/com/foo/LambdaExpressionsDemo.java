package com.foo;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Arrays.asList;

/**
 In short, it is not easy to pass plain methods / functionalities in Java that can be passed as arguments.
 Due to this limitation Java 8 adds a brand new language level feature called Lambda Expressions.
 A lambda expression is an anonymous function (not 100% true for Java but lets assume it for time being).
 Simply put, it’s a method without a declaration, i.e., access modifier, return value declaration, and name.
 */
public class LambdaExpressionsDemo {

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }
    //Predicate <T> interface is a functional interface with a method test(Object) to return a Boolean value.
    public static void evaluate(List<Integer> list, Predicate<Integer> predicate) {
        for(Integer n: list)  {
            if(predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }

    public static void main(String args[]){
        LambdaExpressionsDemo lambdaExpressionsDemo = new LambdaExpressionsDemo();

        //with type declaration
//        MathOperation addition = (int a, int b) -> a + b;

        //with out type declaration
        MathOperation subtraction = (a, b) -> a - b;

        //with return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> { return a * b; };

        //without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + lambdaExpressionsDemo.operate(10, 5, (int a, int b) -> a + b));
        System.out.println("10 - 5 = " + lambdaExpressionsDemo.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + lambdaExpressionsDemo.operate(10, 5, multiplication));
        System.out.println("15 x 5 = " + multiplication.operation(15, 5));
        System.out.println("10 / 5 = " + lambdaExpressionsDemo.operate(10, 5, division));

        //with parenthesis
        GreetingService greetService1 = message ->
                System.out.println("Hello " + message);

        //without parenthesis
        GreetingService greetService2 = (message) ->
                System.out.println("Hello " + message);

        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");
        System.out.println();
        //============================
        System.out.println("Now lets try some number games.");
        List<Integer> list = asList(1, 2, 3, 4, 5, 6, 7);

        System.out.println("Print all numbers:");
        evaluate(list, (n)->true);

        System.out.println("Print no numbers:");
        evaluate(list, (n)->false);

        System.out.println("Print even numbers:");
        evaluate(list, (n)-> n%2 == 0 );

        System.out.println("Print odd numbers:");
        evaluate(list, (n)-> n%2 == 1 );

        System.out.println("Print numbers greater than 5:");
        evaluate(list, (n)-> n > 5 );
    }

}