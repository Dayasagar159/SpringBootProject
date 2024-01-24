package com.indus.app2.programs;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DailyTaskScheduler {

    public static void main(String[] args) {
        // Create a scheduled executor service with a single thread
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Get the current time
        long currentTime = System.currentTimeMillis();

        // Calculate the delay until the next day at 10 AM
        long initialDelay = getNextDelay();

        // Schedule the task to run every 24 hours at 10 AM
        scheduler.scheduleAtFixedRate(new MyDailyTask(), initialDelay, 24 * 60 * 60 * 1000, TimeUnit.MILLISECONDS);
    }

    private static long getNextDelay() {
        // Get the current time
        long currentTime = System.currentTimeMillis();

        // Set the desired time for 10 AM
        int desiredHour = 10;
        int desiredMinute = 0;
        int desiredSecond = 0;

        // Calculate the milliseconds until the next day at 10 AM
        long nextDayMillis = TimeUnit.DAYS.toMillis(1);
        long currentMillis = currentTime % nextDayMillis;
        long desiredMillis = TimeUnit.HOURS.toMillis(desiredHour) +
                             TimeUnit.MINUTES.toMillis(desiredMinute) +
                             TimeUnit.SECONDS.toMillis(desiredSecond);

        long initialDelay = nextDayMillis - (currentMillis - desiredMillis);
        return initialDelay;
    }
}

class MyDailyTask implements Runnable {
    @Override
    public void run() {
        // Your task logic goes here
        System.out.println("Task executed at 10 AM.");
    }
}
