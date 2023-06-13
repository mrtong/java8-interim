package com.foo.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
ref:
https://mkyong.com/java8/java-8-collectors-groupingby-and-mapping-example/
so for groupingBy the 1st param is: what will be grouped
 */
public class GroupingBySample {
    private List<Item> items = new ArrayList<>();

    private void simpleSample() {
        Map<String, List<Item>> names = items.stream().collect(Collectors.groupingBy(Item::getName));
        System.out.println(names);
        //{papaya=[Item{name='papaya', qty=20, price=9.99}], banana=[Item{name='banana', qty=20, price=19.99}, Item{name='banana', qty=10, price=19.99}], apple=[Item{name='apple', qty=10, price=9.99}, Item{name='apple', qty=10, price=9.99}, Item{name='apple', qty=20, price=9.99}], orang=[Item{name='orang', qty=10, price=29.99}], watermelon=[Item{name='watermelon', qty=10, price=29.99}]}
        Map<Item, Long> collect = items.stream().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
        //{Item{name='apple', qty=10, price=9.99}=1, Item{name='watermelon', qty=10, price=29.99}=1, Item{name='apple', qty=20, price=9.99}=1, Item{name='banana', qty=10, price=19.99}=1, Item{name='papaya', qty=20, price=9.99}=1, Item{name='orang', qty=10, price=29.99}=1, Item{name='banana', qty=20, price=19.99}=1, Item{name='apple', qty=10, price=9.99}=1}

        Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.counting()));
        //{papaya=1, banana=2, apple=3, orang=1, watermelon=1}
        System.out.println(counting);

        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)));
        //{papaya=20, banana=30, apple=40, orang=10, watermelon=10}
        System.out.println(sum);
    }

    private void mappingSample() {

        Map<BigDecimal, Set<String>> result =
                items.stream().collect(
                        Collectors.groupingBy(Item::getPrice,
                                Collectors.mapping(c -> c.getName(), Collectors.toSet())
                        )
                );
        //{19.99=[banana], 29.99=[orang, watermelon], 9.99=[papaya, apple]}
        System.out.println(result);

        Map<BigDecimal, List<Item>> collect1 = items.stream().collect(
                Collectors.groupingBy(Item::getPrice
                )
        );
        //{19.99=[Item{name='banana', qty=20, price=19.99}, Item{name='banana', qty=10, price=19.99}], 29.99=[Item{name='orang', qty=10, price=29.99}, Item{name='watermelon', qty=10, price=29.99}], 9.99=[Item{name='apple', qty=10, price=9.99}, Item{name='papaya', qty=20, price=9.99}, Item{name='apple', qty=10, price=9.99}, Item{name='apple', qty=20, price=9.99}]}
        System.out.println(collect1);

        //same functionality
        HashMap<BigDecimal, Set<String>> collect = items.stream().collect(
                Collectors.groupingBy(Item::getPrice,
                        HashMap::new, Collectors.mapping(Item::getName, Collectors.toSet()))
        );
        //{19.99=[banana], 29.99=[orang, watermelon], 9.99=[papaya, apple]}
        System.out.println(collect);
    }

    public GroupingBySample() {
        items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orang", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
        simpleSample();
        mappingSample();
    }

    public static void main(String[] args) {
        new GroupingBySample();
    }

    class Item {
        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", qty=" + qty +
                    ", price=" + price +
                    '}';
        }

        public Item(String name, int qty, BigDecimal price) {
            this.name = name;
            this.qty = qty;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        private String name;
        private int qty;
        private BigDecimal price;

    }
}
