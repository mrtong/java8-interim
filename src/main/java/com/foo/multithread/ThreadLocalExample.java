package com.foo.multithread;

public class ThreadLocalExample {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());
        Thread t3 = new Thread(new MyRunnable());
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //the result is
        //Thread 12: threadLocal=56, hashCode=735372964
        //Thread 13: threadLocal=78, hashCode=735379139
        //Thread 14: threadLocal=23, hashCode=735385314

    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            int randomNum = (int) (Math.random() * 100) + 1;
            threadLocal.set(randomNum);
            System.out.println("Thread " + Thread.currentThread().getId() +
                    ": threadLocal=" + threadLocal.get() +
                    ", hashCode=" + System.identityHashCode(threadLocal.get()));
        }
    }
}

