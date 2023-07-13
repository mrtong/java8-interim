package com.foo.datastructure;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueSample {
    public static void main(String[] args) {
        arrayDequeSample();
        priorityDequeSample();
    }

    private static void arrayDequeSample() {
        System.out.println("This is arrayDequeSample()");
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");

        System.out.println(queue);
        queue.poll();
        System.out.println("After poll() the queue becomes " + queue);

        queue.remove();
        System.out.println("After remove() the queue becomes " + queue);

    }

    private static void priorityDequeSample() {
        System.out.println("This is priorityDequeSample()");
        //A priority queue orders its elements based on their natural ordering or
        // by a custom Comparator provided at the time of creation.
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(13);
        queue.offer(4);
        queue.offer(2);
        queue.offer(24);
        queue.offer(5);
        queue.offer(10);
        //this is interesting, the result of the following line is
        //[2, 5, 4, 24, 13, 10]
        //The reason for this is that the toString() method of PriorityQueue does not guarantee
        // any particular order. It internally uses an array-based representation to store the
        // elements, and the order in which the elements are stored might not correspond to
        // their priority order.
        System.out.println(queue);
        //the same result for the below line
        //The stream() method returns a sequential stream of the elements in the queue,
        // and forEach() applies the provided lambda expression to each element in the stream.
        // While this code will print all the elements in the queue, the order of elements might
        // not match their priority order because the underlying implementation of PriorityQueue
        // does not guarantee a specific order when iterating over the elements using a stream.
        System.out.println("stram().forEach() approach...");
        queue.stream().forEach(c -> System.out.println(c));
        //ONLY in the poll() method, order is guaranteed.
        System.out.println("poll() in while approach... you can find it is in its nature order");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }
}
