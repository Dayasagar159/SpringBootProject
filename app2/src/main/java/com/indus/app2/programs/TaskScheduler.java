package com.indus.app2.programs;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler {

    public static void main(String[] args) {

        // Initialize the Timer object
        Timer timer = new Timer();
        // Schedule the task which execute on every 1 second interval
        timer.schedule(new MyTask(), 0, 1000);

    }
}

class MyTask extends TimerTask {

    // Define your task
    @Override
    public void run() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current date & time is: " + now);
    }
}