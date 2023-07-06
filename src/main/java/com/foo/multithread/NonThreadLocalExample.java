package com.foo.multithread;
public class NonThreadLocalExample {
    private static Integer sharedVariable = 0;

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
        //Thread 12: sharedVariable=1, hashCode=735372964
        //Thread 13: sharedVariable=2, hashCode=735372964
        //Thread 14: sharedVariable=3, hashCode=735372964
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            sharedVariable++; // Increment the shared variable
            System.out.println("Thread " + Thread.currentThread().getId() +
                    ": sharedVariable=" + sharedVariable +
                    ", hashCode=" + System.identityHashCode(sharedVariable));
        }
    }
}
