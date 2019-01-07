package project.programming.elliajah.studentknows1;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

public class KeepAliveService extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "studenthelper:startnotify");
        wl.acquire();
        Intent notifyService = new Intent(context.getApplicationContext(), BackgroundNotifyService.class);
        //checks if your service is up
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (BackgroundNotifyService.class.getName().equals(service.service.getClassName()))
                break;
            }
            // Put here YOUR code.


        Toast.makeText(context, "Our broadcast receiver was hit!", Toast.LENGTH_LONG).show();
            notifyService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startService(notifyService);


            wl.release();


        }

        public void setAlarm (Context context){
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent i = new Intent(context, KeepAliveService.class);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
            am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 60, pi); // Millisec * Second * Minute
        }

        public void cancelAlarm (Context context){
            Intent intent = new Intent(context, KeepAliveService.class);
            PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(sender);
        }


    }