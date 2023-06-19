package com.foo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class SortedSample {
    static class Employee {
        private String name;
        private int age;

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }

    static class CustomComparator implements Comparator<Employee> {

        @Override
        public int compare(Employee o1, Employee o2) {
            return Integer.compare(o1.getAge(), o2.getAge());
        }
    }

    static class Product {
        private String name;
        private double price;
        private int quantity;

        public Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Product{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    static class CustomProductComparator implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            return Comparator.comparing(Product::getName).thenComparing(Product::getPrice).thenComparing(Product::getQuantity).compare(o1, o2);
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 9, 3, 6);

        // Sorting in natural order
        List<Integer> sortedNumbers = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("Sorted numbers: " + sortedNumbers);
        List<Integer> reverseSortedNumbers1 = numbers.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
        System.out.println("Reverse sorted numbers approach1: " + reverseSortedNumbers1);
        List<Integer> reverseSortedNumbers2 = numbers.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("Reverse sorted numbers approach2: " + reverseSortedNumbers2);

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John", 25));
        employees.add(new Employee("Alice", 32));
        employees.add(new Employee("Bob", 28));
        employees.add(new Employee("Emily", 21));

        List<Employee> collect = employees.stream().sorted(Comparator.comparing(Employee::getAge)).collect(Collectors.toList());
        System.out.println("Sorted By Age" + collect);
        List<Employee> collect1 = employees.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).collect(Collectors.toList());
        System.out.println("Sorted By Age(reverse order)" + collect1);

        List<Employee> collect2 = employees.stream().sorted(new CustomComparator()).collect(Collectors.toList());
        System.out.println("Sorted By Age by Customised Comparator" + collect2);

        System.out.println("--------------Below is to show Comparator.naturalOrder()--------------");
        List<Employee> collect3 = employees.stream().sorted(Comparator.comparing(Employee::getAge, Comparator.naturalOrder())).collect(Collectors.toList());
        System.out.println("Sorted By Age by Comparator.naturalOrder" + collect3);

        //you are not alled to use CustomComparator with Comparator.naturalOrder() as below
        //Comparator.naturalOrder() provides a comparator
        //CustomComparator is a custom implementation of the Comparator
        //employees.stream().sorted(new CustomComparator(), Comparator.naturalOrder()).collect(Collectors.toList());

        System.out.println("--------------Below is to show Comparator.nullsFirst(...)--------------");
        //Arrays.asList allows null
        List<String> names = Arrays.asList("John", "Alice", null, "Bob", null, "Emily");
        List<String> collect4 = names.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.toList());
        System.out.println("Sorted by Comparator.nullsFirst" + collect4);

        employees.add(null);
        List<Employee> collect5 = employees.stream().sorted(Comparator.nullsFirst(Comparator.comparing(Employee::getAge))).collect(Collectors.toList());
        System.out.println("Sorted by Customised Comparator.nullsFirst" + collect5);

        List<Product> products = new ArrayList<>();
        products.add(new Product("Apple", 2.99, 10));
        products.add(new Product("Banana", 1.99, 15));
        products.add(new Product("Orange", 2.99, 5));
        products.add(new Product("Grapes", 3.49, 8));
        products.add(new Product("Mango", 2.99, 12));
        products.add(new Product("Strawberry", 1.99, 20));
        products.add(new Product("Pineapple", 3.49, 15));

        List<Product> collect6 = products.stream().sorted(Comparator.comparing(Product::getName).thenComparing(Comparator.comparingDouble(Product::getPrice).thenComparing(Product::getQuantity))).collect(Collectors.toList());
        System.out.println("Multiple sorting criteria" + collect6);

        List<Product> collect7 = products.stream().sorted(new CustomProductComparator()).collect(Collectors.toList());
        System.out.println("Multiple sorting criteria(Customised Comparator)" + collect7);

    }
}
