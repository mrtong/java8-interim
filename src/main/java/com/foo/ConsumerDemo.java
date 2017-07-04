package com.foo;

import java.util.function.Consumer;

/**
 * Consumer is a kind of functional interface. Thus it can be used in Optional.ifPresent();
 * And you can use Lambada expression in it.
 */
public class ConsumerDemo {
    public static void main(String ... args){
        String x = "Hello World";
        Consumer<String> consumer = y ->{
            System.out.println("x = " + x); // Statement A
            System.out.println("y = " + y);

            if(x.equals(y)){

                System.out.println("I am NOT surprised x.equals(y)"); // Statement A
            }

            if (x==y){
                System.out.println("I am surprised x==y"); // Statement A
            }
            //variables within lambda must be final
            //x++;

        };
        consumer.accept(x);
    }
}