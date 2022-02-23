package com.company;

public class Main {

    public static void main(String[] args) {
        defaultPrint();
//        alternatePrint();
    }

    private static void defaultPrint() {
        Runnable firstTask = () ->  System.out.println("-");
        Runnable secondTask = () ->  System.out.println("|");

        for (int j = 0; j < 100; j++) {
            var firstThread = new Thread(firstTask);
            var secondThread = new Thread(secondTask);

            firstThread.start();
            secondThread.start();
        }
    }

    private static void alternatePrint() {
        Runnable firstTask = () ->  System.out.println("-");
        Runnable secondTask = () ->  System.out.println("|");

        for (int j = 0; j < 100; j++) {
            var firstThread = new Thread(firstTask);
            var secondThread = new Thread(secondTask);

            firstThread.start();
            try {
                firstThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            secondThread.start();
            try {
                secondThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

