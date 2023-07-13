package com.foo.datastructure;

import java.util.ArrayDeque;
import java.util.Deque;

public class StackSample {
    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("first");
        stack.push("second");
        stack.push("third");
        //it is weird if Im using the add method, then the order will be first second third
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println("After peek the value of stack is " + stack);

        stack.poll();
        System.out.println("After poll() the value of stack is " + stack);
    }
}
