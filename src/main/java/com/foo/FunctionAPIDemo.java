package com.foo;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.LongToDoubleFunction;

//Java 8 provides function API . These API are functional interface.
// We can assign it as lambda expression. Function accepts arguments, perform some processing and finally produces result.
// We need to define data type of arguments and result. Java 8 provides different in-built functions for different data type.
public class FunctionAPIDemo {
    private static void functionSample(){
        Function<Integer,String> ob = f1 -> "Age:"+f1;
        System.out.println(ob.apply(20));
    }

    private static void doubleFunctionExample (){
        DoubleFunction<String> df = d -> String.valueOf(d*5.3);
        System.out.println(df.apply(43.7));
    }

    private static void doubleToIntFunctionExample (){
        DoubleToIntFunction ob = f -> new Double(f*4.8).intValue();
        System.out.println(ob.applyAsInt(43.7));
    }

    private static void longToDoubleFunctionExample (){
        LongToDoubleFunction ob = f -> f*f;
        System.out.println(ob.applyAsDouble(43));
    }

    public static void main(String ... args){
        System.out.println("This is Function Sample");
        functionSample();

        System.out.println("This is double Function Example");
        doubleFunctionExample();

        System.out.println("This is doubleToIntFunctionExample");
        doubleToIntFunctionExample();

        System.out.println("This is longToDoubleFunctionExample");
        longToDoubleFunctionExample();
    }
}