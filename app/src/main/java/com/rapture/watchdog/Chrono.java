package com.rapture.watchdog;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by mariote on 28/10/15.
 */
public class Chrono extends Thread {

    private int time;
    private static Chrono instance = null;

    protected Chrono(){}

    @Override
    public void run() {
        super.run();
            try {
                Thread.sleep(this.time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static Chrono getInstance() {
        if (instance == null) instance = new Chrono();
        return instance;
    }

    public void setTime (int time) {
        this.time = time;
    }
}