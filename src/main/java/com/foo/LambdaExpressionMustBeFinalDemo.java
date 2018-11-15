package com.foo;

/**
 * Created by yanjuntong on 5/07/17.
 */
public class LambdaExpressionMustBeFinalDemo {
    public static void main(String ...args){
        String name = "Hello World";
        Runnable r1 = ()-> System.out.println(name);

        String name1 = "";
        //line 14 will cause aline 15 a compile issue
        //the issue is "Variable used in Lambda expression must be final"
//        name1 = name.toUpperCase();
//        Runnable r2 = ()-> System.out.println(name1);
        String name2 = name.toUpperCase();
        Runnable r3 = ()-> System.out.println(name2);

        r3.run();
    }
}
