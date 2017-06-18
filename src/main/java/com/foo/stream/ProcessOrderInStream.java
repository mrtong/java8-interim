package com.foo.stream;

import java.util.stream.Stream;

/**
 * Created by yanjuntong on 2/04/17.
 */
public class ProcessOrderInStream {
    public ProcessOrderInStream(){
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s->{
                    System.out.println("filter: " + s);
                    return false;
                })
                .forEach(s->System.out.println("for each: " + s));
    }
    public static void main(String ...args){
        new ProcessOrderInStream();
    }
}
