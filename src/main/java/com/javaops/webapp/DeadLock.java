package com.javaops.webapp;


public class DeadLock {
    public static void main(String[] args) {

        Object objectOne = 1;
        Object objectTwo = 2;

        Dead deadlock1 = new Dead(objectOne, objectTwo);
        Dead deadlock2 = new Dead(objectTwo, objectOne);

        Thread thread1 = new Thread(deadlock1);
        thread1.start();
        Thread thread2 = new Thread(deadlock2);
        thread2.start();
    }
}

class Dead implements Runnable {
    final Object objectOne;
    final Object objectTwo;

    public Dead(Object objectOne, Object objectTwo) {
        this.objectOne = objectOne;
        this.objectTwo = objectTwo;
    }

    @Override
    public void run() {
        synchronized (objectOne) {
            System.out.println(Thread.currentThread().getName());
            synchronized (objectTwo) {
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
}