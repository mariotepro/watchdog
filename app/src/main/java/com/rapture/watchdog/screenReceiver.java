package com.rapture.watchdog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by mariote on 15/10/15.
 */
public class screenReceiver extends BroadcastReceiver {
    private boolean screenOn;
    private boolean screenLock;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_ON))             screenOn    = true;
        else if (intent.getAction().equals(Intent.ACTION_USER_PRESENT))     screenLock  = false;
        else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF))       {
                                                                            screenOn    = false;
                                                                            screenLock  = true;
        }
        Intent i = new Intent(context, screenWatchdog.class);
        i.putExtra("isScreenOn", screenOn);
        i.putExtra("isScreenUnlocked", screenLock);
        context.startService(i);
    }
}
