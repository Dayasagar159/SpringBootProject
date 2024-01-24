package com.indus.app2.programs;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TenMinuteTaskScheduler {

    public static void main(String[] args) {
        // Create a scheduled executor service with a single thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Schedule the task to run every 10 minutes
        scheduler.scheduleAtFixedRate(new MyTenMinuteTask(), 0, 10, TimeUnit.MINUTES);
    }
}

class MyTenMinuteTask implements Runnable {
    @Override
    public void run() {
        // Your task logic goes here
        System.out.println("Task executed every 10 minutes.");
    }
}
