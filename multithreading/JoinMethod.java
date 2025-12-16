package org.example.multithreading;

public class JoinMethod extends Thread{

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinMethod t1 = new JoinMethod();
        t1.start();
        t1.join();
        System.out.println("Hello"); // It will print after 5 sec
    }
}
