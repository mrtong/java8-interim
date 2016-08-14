package com.foo;

import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 * User: p744228d
 * Date: 1/03/16
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
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