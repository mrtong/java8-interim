package com.foo.multithread;

import java.util.concurrent.*;

/**
 * Created by yanjuntong on 22/06/17.
 */
public class ExecutorServiceExamples {
    public static void main(String... args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("From ExecutorService");
            }
        });

        System.out.println("End of Main");

        executorService.shutdown();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();

        // Creates a thread pool that reuses a fixed number of threads
        // operating off a shared unbounded queue. At any point, the parameter
        // specifies the most threads that will be active processing tasks.

        ExecutorService executorService2 = Executors.newFixedThreadPool(10);

        // Creates a thread pool that can schedule commands to run after a
        // given delay, or to execute periodically.
        ExecutorService executorService3 = Executors.newScheduledThreadPool(10);
        //below is lambda approach
//        Future future = executorService1.submit(() -> {
//            System.out.println("Task #2 is running");
//        });
//
//        System.out.println(future.get());
//
//        The below two samples tell me the submit can accept both Runnable and Callable
        //the return type of submit is Future; the return type of execute is void;
        Future future = executorService1.submit(new Runnable() {
            public void run(){
                System.out.println("From executorService1");
            }
        });

        System.out.println(future.get());
        //below is the lambda approach
//        Future futureFromCallable = executorService1.submit(()->{return "ddddd";});
        Future futureFromCallable = executorService1.submit(new Callable() {
            public String call() throws Exception {
                return "Result";
            }
        });

        System.out.println("futureFromCallable.get() = "
                + futureFromCallable.get());
        executorService1.shutdown();
    }
}
