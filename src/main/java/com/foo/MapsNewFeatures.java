package com.foo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by yanjuntong on 7/07/17.
 */
public class MapsNewFeatures {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        //putIfAbsent performs the check to see
        // if the same Key already existed in the Map and
        // will only add a new element if it's not already there ( Match by the Key )
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));


        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));             // val33

        System.out.println("Before doing computeIfPresent map.containsKey(9)=" + map.containsKey(9)); //true
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println("After doing computeIfPresent map.containsKey(9)=" + map.containsKey(9));     // false

        System.out.println("The value of key23 before computeIfAbsent is " + map.get(23) + " since it is absent.");             // null
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println("After computeIfAbsent map.containsKey(23) is " +map.containsKey(23));    // true
        System.out.println("The value of key23 after computeIfAbsent is " + map.get(23));             // val23

        System.out.println("The value of key3 before computeIfAbsent is " + map.get(3));             // val33
        map.computeIfAbsent(3, num -> "bam");
        System.out.println("The value of key3 after computeIfAbsent is " + map.get(3) + " since key=3 is existing");             // val33
        //Basically this method is to return a default value whenever the value was not found using the key specified on the HashMap.
        System.out.println(map.getOrDefault(42, "not found"));      // not found

        System.out.println("The value of map.get(3) before remove is " + map.get(3));             // val33
        map.remove(3, "val3");
        System.out.println("The value of map.get(3) after remove is " + map.get(3));             // val33

        map.remove(3, "val33");
        System.out.println("The value of map.get(3) after remove is " + map.get(3) + " since it is a correct value");             // null

        System.out.println("The value of map.get(9) before merge is " + map.get(9));             // null
        //the second parameter is used as the new value if there is no current mapping or the mapping is null
        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        System.out.println("The value of map.get(9) after merge is " + map.get(9));             // val9

        //this time the second parameter is  non-null value to merge with existing value
        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(9));             // val9concat

        System.out.println("==============================");
        addTwoMaps();
    }

    private static void addTwoMaps(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Supplier<Map<Integer,Integer>> mapSupplier = () -> list.stream().collect(Collectors.toMap(x->x, y-> y * y));

        Map<Integer, Integer> mapValueAdd = list.stream().collect(Collectors.toMap(x->x, y->y, (v1,v2) -> v1+v2, mapSupplier));
        Map<Integer, Integer> mapValueAdd1 = list.stream().collect(Collectors.toMap(x->x, y->y));
        System.out.println(mapValueAdd);
        System.out.println(mapValueAdd1);
    }

}
