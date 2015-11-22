package com.rapture.watchdog;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by mariote on 13/10/15.
 */
public class screenWatchdog extends Service {
    BroadcastReceiver mReceiver = null;

    Chrono c = Chrono.getInstance();;
    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        mReceiver = new screenReceiver();
        registerReceiver(mReceiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean     screenOn    = true;
        boolean     screenLock  = true;
        int         time        = 0;


        try     {   screenOn    = intent.getBooleanExtra("isScreenOn", false);
                    screenLock  = intent.getBooleanExtra("isScreenUnlocked", false);}
        catch   (   Exception e                                                         ){}

        if (screenOn == true && screenLock == false) {
            c.run();

            try {
                c.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeOut();
        } else {
            c.interrupt();
        }

        return START_NOT_STICKY;
    }

    public void timeOut() {
        Toast.makeText(getApplicationContext(), "HE TERMINADO", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        if(mReceiver!=null) unregisterReceiver(mReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) { return null; }
}