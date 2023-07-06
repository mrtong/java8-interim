package com.foo.multithread;

import java.util.Random;

/*
Implement a program that simulates a race between two runners using threads.
Each runner should be represented by a separate thread, and they should race a distance of 100 meters.
The threads should print their progress at regular intervals, indicating the distance covered by each runner
until one of them crosses the finish line.
 */
public class ThreadSample2 {
    public static int totalDistance = 100;
    public static volatile boolean raceNotFinished = true;

    public static void main(String[] args) {
        Thread runner1 = new Thread(() -> {
            try {
                run("runner1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread runner2 = new Thread(() -> {
            try {
                run("runner2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        runner1.start();
        runner2.start();

        try {
            runner1.join();
            runner2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread exiting.");
    }

    private static void run(String name) throws InterruptedException {
        Random random = new Random();
        int completedDistance = 0;
        int randomNumber = 0;
        while (raceNotFinished && completedDistance < totalDistance) {

            randomNumber = random.nextInt(5) + 1;
            completedDistance += randomNumber;
            System.out.println(name + " has completed " + completedDistance + "meters");
            Thread.sleep(500);

        }
        raceNotFinished = false;
        System.out.println("Yeah " + name + " has completed.");
    }
}
