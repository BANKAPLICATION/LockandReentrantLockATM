package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	    Lock lock = new ReentrantLock();
        new Employee("Syrym", lock);
        new Employee("Meyram", lock);
        new Employee("Mukagali", lock);
        Thread.sleep(5000);
        new Employee("Ayan", lock);
        new Employee("Roma", lock);
    }
}
class Employee extends Thread {
    String name;
    private Lock lock;
    public Employee(String name, Lock lock) {
        this.name = name;
        this.lock = lock;
        this.start();
    }
    public void run() {
        if(lock.tryLock()) {
            try {
//                System.out.println(name + " waits for... ");
//                lock.lock();
                System.out.println(name + " uses atm");
                Thread.sleep(2000);
                System.out.println(name + " has finished their deals");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        else {
            System.out.println(name + " doesn't want to wait for a queue");
        }
    }


}