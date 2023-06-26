package com.foo.stream;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingBySample2 {
    static class Employee {
        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        private String name;
        private String department;
        private double salary;

    }

    static class Transaction {
        private String transactionId;

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        private String customerId;
        private double amount;

        public Transaction(String transactionId, String customerId, double amount) {
            this.transactionId = transactionId;
            this.customerId = customerId;
            this.amount = amount;
        }
    }

    static class CustomerSummary {
        private double totalAmount;

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public int getNumTransactions() {
            return numTransactions;
        }

        public void setNumTransactions(int numTransactions) {
            this.numTransactions = numTransactions;
        }

        private int numTransactions;

        public CustomerSummary(double totalAmount, int numTransactions) {
            this.totalAmount = totalAmount;
            this.numTransactions = numTransactions;
        }

        @Override
        public String toString() {
            return "CustomerSummary{" +
                    "totalAmount=" + totalAmount +
                    ", numTransactions=" + numTransactions +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<String> words = List.of("apple", "banana", "cat", "dog", "elephant", "bbq");
        System.out.println("groupByFirstLetter(words)");
        groupByFirstLetter(words);

        System.out.println("groupByStringLength(words)");
        groupByStringLength(words);

        System.out.println("A Stream.of approach...");
        Map<Character, List<String>> collect1 = Stream.of("apple", "banana", "cat", "dog", "elephant", "bbq").collect(Collectors.groupingBy(c -> c.charAt(0)));
        System.out.println(collect1);

        System.out.println("calculateAverageSalariesByDepartment(List<Employee> employees)");
        Employee employee1 = new Employee("a", "da", 100d);
        Employee employee2 = new Employee("b", "da", 200d);
        Employee employee3 = new Employee("c", "db", 108d);
        Employee employee4 = new Employee("d", "db", 111d);
        Employee employee5 = new Employee("e", "db", 178d);
        //returns a map where the keys are the departments and the values are the average salaries of employees in each department.
        List<Employee> employees = List.of(employee1, employee2, employee3, employee4, employee5);
        System.out.println(calculateAverageSalariesByDepartment(employees));
        System.out.println("This is to get the same result but using toMap approach");
        System.out.println(calculateAverageSalariesByDepartment2(employees));

        System.out.println("calculateCustomerSummaries(List<Transaction> transactions)");
        Transaction t1 = new Transaction("1", "1", 2.1);
        Transaction t2 = new Transaction("2", "1", 2.4);
        Transaction t3 = new Transaction("3", "1", 1.8);
        Transaction t4 = new Transaction("4", "2", 1.3);
        Transaction t5 = new Transaction("5", "2", 1.6);
        Transaction t6 = new Transaction("6", "2", 2.3);
        Transaction t7 = new Transaction("7", "2", 1.9);

        List<Transaction> transactions = List.of(t1, t2, t3, t4, t5, t6, t7);
        System.out.println(calculateCustomerSummaries(transactions));

        System.out.println("calculateCustomerSummaries2(List<Transaction> transactions)");
        System.out.println(calculateCustomerSummaries2(transactions));
    }

    public static void groupByFirstLetter(List<String> words) {
        Map<Character, List<String>> collect = words.stream().collect(Collectors.groupingBy(c -> c.charAt(0)));
        System.out.println(collect);
    }

    public static void groupByStringLength(List<String> strings) {
        Map<Integer, List<String>> collect = strings.stream().collect(Collectors.groupingBy(c -> c.length()));
        System.out.println(collect);
    }

    //returns a map where the keys are the departments and the values are the average salaries of employees in each department.
    //this is a groupingBy approach
    public static Map<String, Double> calculateAverageSalariesByDepartment(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

    }

    //returns a map where the keys are the departments and the values are the average salaries of employees in each department.
    //this is a toMap approach
    public static Map<String, Double> calculateAverageSalariesByDepartment2(List<Employee> employees) {
        Map<String, Double> sumByDepartment = employees.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        Employee::getSalary,
                        Double::sum
                ));

        Map<String, Long> countByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));

        return sumByDepartment.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() / countByDepartment.get(entry.getKey())
                ));
    }


    /*
    In this challenge, you have the Transaction class representing a transaction's information, including the transaction ID, customer ID, and transaction amount. The GroupByCustomerExample class includes the main method, where you can initialize the list of transactions and call the calculateCustomerSummaries method.

    Your task is to implement the calculateCustomerSummaries method, which takes a list of transactions and returns a map where the keys are the customer IDs and the values are instances of the CustomerSummary class. The CustomerSummary class represents a summary of customer transactions, including the total amount and the number of transactions.
     */
    public static Map<String, CustomerSummary> calculateCustomerSummaries(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId,
                        Collectors.mapping(transaction -> new CustomerSummary(transaction.getAmount(), 1),
                                Collectors.reducing(new CustomerSummary(0, 0),
                                        (summary1, summary2) -> new CustomerSummary(summary1.getTotalAmount() + summary2.getTotalAmount(),
                                                summary1.getNumTransactions() + summary2.getNumTransactions())))));
    }

    //this is a toMap approach
    public static Map<String, CustomerSummary> calculateCustomerSummaries2(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.toMap(
                        Transaction::getCustomerId,
                        transaction -> new CustomerSummary(transaction.getAmount(), 1),
                        (summary1, summary2) -> new CustomerSummary(
                                summary1.getTotalAmount() + summary2.getTotalAmount(),
                                summary1.getNumTransactions() + summary2.getNumTransactions())
                ));
    }

}
