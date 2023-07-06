package com.foo.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadNotifyExample {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Message message = new Message();
        Thread receiver = new Thread(() -> {
            lock.lock();
            try {
                condition.await();
                System.out.println("what we got is " + message.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread sender = new Thread(() -> {
            try {
                lock.lock();
                message.setMessage("hello world");
                condition.signal();
            } finally {
                lock.unlock();
            }

        });

        receiver.start();
        sender.start();

    }

    static class Message {
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        private String message;

    }
}
