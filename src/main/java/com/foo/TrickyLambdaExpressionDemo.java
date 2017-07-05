package com.foo;

/**
 * Created by yanjuntong on 5/07/17.
 */
public class TrickyLambdaExpressionDemo {
    public static void main(String ...args){
        String name = "Hello World";
        Runnable r1 = ()-> System.out.println(name);

        //the below block will cause a compile issue
        //the issue is "Variable used in Lambda expression must be final"
//        String name1 = "";
//        name1 = name.toUpperCase();
//        Runnable r2 = ()-> System.out.println(name1);
        String name1 = name.toUpperCase();
        Runnable r2 = ()-> System.out.println(name1);

        r1.run();
    }
}
