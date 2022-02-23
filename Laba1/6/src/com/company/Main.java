package com.company;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        var counter = new Counter();
        var incrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    counter.increment();
                    System.out.println(counter.getValue());
                }
            }
        });
        var decrementThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    counter.decrement();
                    System.out.println(counter.getValue());
                }
            }
        });

        incrementThread.start();
        decrementThread.start();

//        //  Блокування об'єкта
//        var counter = new Counter();
//        ReentrantLock locker = new ReentrantLock();
//        var incrementThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    locker.lock();
//                    for (int i = 0; i < 100000; i++) {
//                        counter.increment();
//                        System.out.println(counter.getValue());
//                    }
//                } finally {
//                    locker.unlock();
//                }
//            }
//        });
//        var decrementThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    locker.lock();
//                    for (int i = 0; i < 100000; i++) {
//                        counter.decrement();
//                        System.out.println(counter.getValue());
//                    }
//                }
//                finally {
//                    locker.unlock();
//                }
//            }
//        });
//
//        incrementThread.start();
//        decrementThread.start();
    }
}

class Counter {
  //  Синхронізований метод
    private int Value = 0;

    public synchronized void increment() {
        Value++;
    }

    public synchronized void decrement() {
        Value--;
    }

//  //  Синхронізований блок
//    private int Value = 0;

//    public void increment() {
//        synchronized (this) {
//            Value++;
//        }
//    }
//
//    public void decrement() {
//        synchronized (this) {
//            Value--;
//        }
//    }

//    //  Блокування об'єкта
//    private int Value = 0;
//
//    public void increment() {
//        Value++;
//    }
//
//    public void decrement() {
//        Value--;
//    }

    public int getValue() {
        return Value;
    }
}