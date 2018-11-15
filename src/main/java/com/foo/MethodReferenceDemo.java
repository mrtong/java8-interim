package com.foo;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 * User: p744228d
 * Date: 9/02/16
 * Time: 4:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class MethodReferenceDemo {
    public MethodReferenceDemo() {

    }

    void MRDemo1() {
        int[] array = {10, 2, 19, 5, 17};
        Consumer<int[]> consumer = Arrays::sort;
        consumer.accept(array);
        for (int x : array)
            System.out.println(x);
        System.out.println();

        int[] array2 = {19, 5, 14, 3, 21, 4};
        Consumer<int[]> consumer2 = (a) -> Arrays.sort(a);
        consumer2.accept(array2);

        for (int x : array2)
            System.out.println(x);
    }

    void MRDemo2() {
        String s = "The quick brown fox jumped over the lazy dog";
        //Its syntax is objectName::instanceMethodName
        print(s::length);
        print(() -> s.length());

        print(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return s.length(); // closes over s
            }
        });
    }

    public void print(Supplier<Integer> supplier) {
        System.out.println(supplier.get());
    }

    public static void main(String[] args) {
        MethodReferenceDemo mrdemo = new MethodReferenceDemo();
//        mrdemo.MRDemo1();

        mrdemo.MRDemo2();
    }
}