package com.foo.function;

import java.util.function.Supplier;

public class SupplierAsParameterDemo {
    public static void main(String[] args) {
        Developer developer = factory(Developer::new);
        System.out.println(developer.getName());

        //The supplier can be used in all contexts where there is no input but an output is expected.
        //thus it is not restricted to method ref
        Developer developer1 = factory(() -> new Developer("Tong"));
        System.out.println(developer1.getName());

    }

    public static Developer factory(Supplier<? extends Developer> s) {
        return s.get();
    }
}

class Developer {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // for factory(Developer::new);
    public Developer() {
        this.name = "Luke";
    }

    public Developer(String name) {
        this.name = name;
    }

}
