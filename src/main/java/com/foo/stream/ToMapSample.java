package com.foo.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

//based on https://www.jianshu.com/p/b2d78544df64
public class ToMapSample {
    public static void main(String[] args) {
        list2Map();
        list2MapWithMergefunc();
        list2mapWithMergefuncAndSorted();
        addTwoMaps();
    }

    static class User {
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        String id;
        String name;
    }

    public static void list2Map() {
        System.out.println("list2Map");
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "Luke"));
        userList.add(new User("2", "Jake"));
        //duplicate key is not allowed here. i.e. if you add another User("2","JJ"); then IllegalStateException will be raised
        Map<String, String> collect = userList.stream().collect(Collectors.toMap(User::getId, User::getName));
        //{1=Luke, 2=Jake}
        System.out.println(collect);
        Map<String, User> collect1 = userList.stream().collect(Collectors.toMap(User::getId, t -> t));
        //{1=com.foo.stream.ToMapSample$User@19dfb72a, 2=com.foo.stream.ToMapSample$User@17c68925}
        //the reason it is com.foo.stream.ToMapSample$User@17c68925 is because it calls the toString method which we do not have
        System.out.println(collect1);
    }

    public static void list2MapWithMergefunc() {
        System.out.println("list2MapWithMergefunc");
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "Luke"));
        userList.add(new User("2", "Jake"));
        userList.add(new User("2", "JJ"));

        Map<String, String> collect = userList.stream().collect(Collectors.toMap(User::getId, User::getName, (v1, v2) -> v1 + v2));
//        {1=Luke, 2=JakeJJ}
        System.out.println(collect);

    }

    public static void list2mapWithMergefuncAndSorted() {
        System.out.println("list2mapWithMergefuncAndSorted");
        List<User> userList = new ArrayList<>();
        userList.add(new User("2", "Jake"));
        userList.add(new User("1", "Luke"));
        userList.add(new User("3", "JJ"));
        userList.add(new User("5", "AA"));
        userList.add(new User("4", "KK"));
        userList.add(new User("6", "KK"));
        userList.add(new User("4", "duplicate Key"));

        TreeMap<String, String> collect = userList.stream().collect(Collectors.toMap(User::getId, User::getName, (n1, n2) -> n1, TreeMap::new));
//        {1=Luke, 2=Jake, 3=JJ, 4=KK, 5=AA}
        System.out.println(collect);
        //this is interesting.
        TreeMap<String, String> collect1 = userList.stream().collect(Collectors.toMap(User::getId, User::getName, (n1, n2) -> n2, TreeMap::new));
        System.out.println(collect1);
        //this is the way to use value as the key. But be noted, if there is any duplcate in the value, then the later(n2) will be used
        TreeMap<String, String> collect2 = userList.stream().collect(Collectors.toMap(User::getName, User::getId, (n1, n2) -> n2, TreeMap::new));
        System.out.println("collect2 is " + collect2);

    }

    private static void addTwoMaps() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5,6);
        Supplier<Map<Integer, Integer>> mapSupplier = () -> list.stream().collect(Collectors.toMap(x -> x, y -> y * y));

        Map<Integer, Integer> mapValueAdd = list.stream().collect(Collectors.toMap(x -> x, y -> y, (v1, v2) -> v1 + v2, mapSupplier));
        Map<Integer, Integer> mapValueAdd1 = list.stream().collect(Collectors.toMap(x -> x, y -> y));
        Map<Integer, Integer> mapValueAdd2 = list.stream().collect(Collectors.toMap(x -> x, y -> y, (v1, v2) -> v1 + v2));
        //mapValueAdd{1=2, 2=6, 3=12, 4=20, 5=30, 6=42}
        //this is a 等差数列 2/1=2 6/2=3 12/3=4 20/4=5 30/5=6 42/6=7
        System.out.println("mapValueAdd" + mapValueAdd);
        System.out.println("mapValueAdd1" + mapValueAdd1);
        System.out.println("mapValueAdd2" + mapValueAdd2);
    }
}
