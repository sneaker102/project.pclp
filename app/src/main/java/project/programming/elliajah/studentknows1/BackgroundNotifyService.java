package project.programming.elliajah.studentknows1;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BackgroundNotifyService extends Service {
private Intent myIntent;
    private static final Intent[] POWERMANAGER_INTENTS = {
            new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
            new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
            new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
            new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")),
            new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")),
            new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.MainActivity"))};
  private String email;
    private String orarID;
    private String orar;
    private Context context;
    private static String TAG = "MyService";
    private Handler handler;
    private Runnable runnable;
    //1800000 juma de ora
    private final int runTime = 300000;
    private DatabaseReference dbRef;
    private boolean isFirstInit;
    private KeepAliveService keepAlive = new KeepAliveService();
    private Handler broadcastReceiverHandler = null;
    private HandlerThread broadcastReceiverThread = null;
    private Looper broadcastReceiverThreadLooper = null;

    @Override
    public void onCreate() {

        super.onCreate();
        //for running non stop service
        final SharedPreferences.Editor pref = getSharedPreferences("allow_notify", MODE_PRIVATE).edit();
        pref.apply();
        final SharedPreferences sp = getSharedPreferences("allow_notify", MODE_PRIVATE);
        if (!sp.getBoolean("protected", false)) {
            for (final Intent intent : POWERMANAGER_INTENTS) {
                if (getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Alert Title").setMessage("Alert Body")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(intent);
                                    sp.edit().putBoolean("protected", true).apply();

                                }
                            })
                            .setCancelable(false)
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .create().show();
                    break;
                }
            }
        }
        Log.i(TAG, "onCreate");
        //for getting context getApplicationContext()
        context = getApplicationContext();

        keepAlive.setAlarm(this);


    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        keepAlive.setAlarm(context);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
myIntent=intent;
        readFromCache();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("orar").child(orarID);


        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Orar acelOrar = dataSnapshot.getValue(Orar.class);

                if (!acelOrar.toString().equals(orar))

                {
                    CallNotify("UPDATE", "ORARUL A FOST SCHIMBAT!");

                }
              //  CallNotify("UPDATE", "ORARUL A FOST SCHIMBAT!");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        stopSelf();

        return START_NOT_STICKY;
    }


    private void CallNotify(String title, String task) {

        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("simplifiedcoding", "simplifiedcoding", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        Intent notifyIntent = new Intent(getApplicationContext(), OrarActivity.class);
        notifyIntent.putExtra("notify", true);
        notifyIntent.putExtra("email", email);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(getApplicationContext(), "simplifiedcoding")
                .setContentTitle(title)
                .setContentText(task)
                .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0,
                        notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT))
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, notification.build());
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        keepAlive.setAlarm(context);
        super.onTaskRemoved(rootIntent);
    }

    private void readFromCache() {
        int i = 0;int k=0;
        int j = 0;
        char c, d,r;
        StringBuilder buildOrar = new StringBuilder();
        StringBuilder buildID = new StringBuilder();
        StringBuilder buildEmail= new StringBuilder();
        try {


            FileInputStream orarFile = openFileInput("orarFile");
            FileInputStream idFile = openFileInput("orarIDFile");
            FileInputStream emailFile=openFileInput("emailFile");
            while ((i = idFile.read()) != -1) {
                c = (char) i;
                buildID.append(c);
            }
            idFile.close();
            while ((j = orarFile.read()) != -1) {
                d = (char) j;
                buildOrar.append(d);
            }

            orarFile.close();
            while ((k = emailFile.read()) != -1) {
                r=(char)k;
                buildEmail.append(r);
            }
            emailFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        orar = buildOrar.toString();
        orarID = buildID.toString();
        email=buildEmail.toString();

    }
}
