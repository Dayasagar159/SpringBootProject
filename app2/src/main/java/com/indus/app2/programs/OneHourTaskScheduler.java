package com.indus.app2.programs;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OneHourTaskScheduler {

    public static void main(String[] args) {
        // Create a scheduled executor service with a single thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule the task to run every 1 hour
        scheduler.scheduleAtFixedRate(new MyOneHourTask(), 0, 1, TimeUnit.HOURS);
    }
}

class MyOneHourTask implements Runnable {
    @Override
    public void run() {
        // Your task logic goes here
        System.out.println("Task executed every 1 hour.");
    }
}
