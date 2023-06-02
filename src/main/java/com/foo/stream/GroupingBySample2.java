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

    public static void main(String[] args) throws Exception {
        List<String> words = List.of("apple", "banana", "cat", "dog", "elephant", "bbq");
        System.out.println("groupByFirstLetter(words)");
        groupByFirstLetter(words);

        System.out.println("groupByStringLength(words)");
        groupByStringLength(words);

        System.out.println("A Stream.of approach...");
        Map<Character, List<String>> collect1 = Stream.of("apple", "banana", "cat", "dog", "elephant", "bbq").collect(Collectors.groupingBy(c -> c.charAt(0)));
        System.out.println(collect1);

        System.out.println("calculateAverageSalariesByDepartment(List<Employee> employees)");
        GroupingBySample2.Employee employee1 = new GroupingBySample2.Employee("a", "da", 100d);
        GroupingBySample2.Employee employee2 = new GroupingBySample2.Employee("b", "da", 200d);
        GroupingBySample2.Employee employee3 = new GroupingBySample2.Employee("c", "db", 108d);
        GroupingBySample2.Employee employee4 = new GroupingBySample2.Employee("d", "db", 111d);
        GroupingBySample2.Employee employee5 = new GroupingBySample2.Employee("e", "db", 178d);
        //returns a map where the keys are the departments and the values are the average salaries of employees in each department.
        List<GroupingBySample2.Employee> employees = List.of(employee1, employee2, employee3, employee4, employee5);
        System.out.println(calculateAverageSalariesByDepartment(employees));

        System.out.println("calculateCustomerSummaries(List<Transaction> transactions)");
        GroupingBySample2.Transaction t1 = new GroupingBySample2.Transaction("1", "1", 2.1);
        GroupingBySample2.Transaction t2 = new GroupingBySample2.Transaction("2", "1", 2.4);
        GroupingBySample2.Transaction t3 = new GroupingBySample2.Transaction("3", "1", 1.8);
        GroupingBySample2.Transaction t4 = new GroupingBySample2.Transaction("4", "2", 1.3);
        GroupingBySample2.Transaction t5 = new GroupingBySample2.Transaction("5", "2", 1.6);
        GroupingBySample2.Transaction t6 = new GroupingBySample2.Transaction("6", "2", 2.3);
        GroupingBySample2.Transaction t7 = new GroupingBySample2.Transaction("7", "2", 1.9);

        List<GroupingBySample2.Transaction> transactions = List.of(t1, t2, t3, t4, t5, t6, t7);
        System.out.println(calculateCustomerSummaries(transactions));
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
    public static Map<String, Double> calculateAverageSalariesByDepartment(List<GroupingBySample2.Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(GroupingBySample2.Employee::getDepartment, Collectors.averagingDouble(GroupingBySample2.Employee::getSalary)));

    }

    public static Map<String, GroupingBySample2.CustomerSummary> calculateCustomerSummaries(List<GroupingBySample2.Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(GroupingBySample2.Transaction::getCustomerId,
                        Collectors.mapping(transaction -> new GroupingBySample2.CustomerSummary(transaction.getAmount(), 1),
                                Collectors.reducing(new GroupingBySample2.CustomerSummary(0, 0),
                                        (summary1, summary2) -> new GroupingBySample2.CustomerSummary(summary1.getTotalAmount() + summary2.getTotalAmount(),
                                                summary1.getNumTransactions() + summary2.getNumTransactions())))));
    }
}
