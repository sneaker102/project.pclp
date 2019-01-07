package project.programming.elliajah.studentknows1;


import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;

public class WelcomeActivity extends AppCompatActivity {

    private HorizontalScrollView orarZi;
private ImageView chatImg, orarImg;
    private DatabaseReference dbRef;
    private Student myStud;
    private Orar myOrar;
    private Button menuBttn, universityBttn, chatBttn, gradesBttn, orarBttn, logOutBttn;
    private RelativeLayout layotHeader, layHome;
    private LinearLayout menuLayout;
    private boolean isMenuOpened;
    private TextView clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20, studentHelperText, studentName, orarZiHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DbPlay changeOrar = new DbPlay();
//        changeOrar.changeOrar("luni",1,"Engleza, S,NS1,Soica_s");
        setContentView(R.layout.activity_welcome);
       chatBttn=findViewById(R.id.groupBttn);
        chatImg=findViewById(R.id.chatFastImg);
        orarImg=findViewById(R.id.orarImg);
         //de adaugat
        //goToChat and Chat Alert
        //initializing my utils class
        final Utils util = new Utils();
        //Layouts
        layHome = findViewById(R.id.layHome);
        layotHeader = findViewById(R.id.layHeader);
        menuLayout = findViewById(R.id.maybeFragment);

        //scroll views
        orarZi = findViewById(R.id.orarZi);
        //Buttons
        menuBttn = findViewById(R.id.menuBttnSwipe);
        orarBttn = findViewById(R.id.orarBttn);
        logOutBttn = findViewById(R.id.logOutBttn);
        universityBttn = findViewById(R.id.universityBttn);
        gradesBttn = findViewById(R.id.gradesBttn);

        chatBttn = findViewById(R.id.groupBttn);

        //TextViews
        studentName = findViewById(R.id.student_name);
        orarZiHeader = findViewById(R.id.orarZiHeader);
        studentHelperText = findViewById(R.id.textStudentHelper);
        clasa20 = findViewById(R.id.clasa20);
        clasa18 = findViewById(R.id.clasa18);
        clasa16 = findViewById(R.id.clasa16);
        clasa14 = findViewById(R.id.clasa14);
        clasa12 = findViewById(R.id.clasa12);
        clasa10 = findViewById(R.id.clasa10);
        clasa8 = findViewById(R.id.clasa8);
        //member variables
        isMenuOpened = false;
        //getting extras
        String orarExtra = getIntent().getStringExtra("orar");
        String studExtra = getIntent().getStringExtra("stud");
        //slide menu view
        menuBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpened) {
                    menuLayout.animate().translationX(0);
                    layotHeader.animate().translationX(660);
                    layHome.animate().translationX(660);
                    isMenuOpened = true;
                } else {
                    menuLayout.animate().translationX(-605);
                    layotHeader.animate().translationX(0);
                    layHome.animate().translationX(0);
                    isMenuOpened = false;
                }
            }
        });
        layotHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuOpened) {
                    menuLayout.animate().translationX(-605);
                    layotHeader.animate().translationX(0);
                    layHome.animate().translationX(0);
                    isMenuOpened = false;
                }
            }
        });
        menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuOpened) {
                    menuLayout.animate().translationX(-605);
                    layotHeader.animate().translationX(0);
                    layHome.animate().translationX(0);
                    isMenuOpened = false;
                }
            }
        });

        studentHelperText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuOpened) {
                    menuLayout.animate().translationX(-605);
                    layotHeader.animate().translationX(0);
                    layHome.animate().translationX(0);
                    isMenuOpened = false;
                }
            }
        });
        layHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuOpened) {
                    menuLayout.animate().translationX(-605);
                    layotHeader.animate().translationX(0);
                    layHome.animate().translationX(0);
                    isMenuOpened = false;
                }
            }
        });
        if (orarExtra == null && studExtra == null) {
            //getting student name, specialization and year
            FirebaseDatabase db1 = FirebaseDatabase.getInstance();
            dbRef = db1.getReference("studenti");
            final String email = getIntent().getStringExtra("email");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot x : dataSnapshot.getChildren()) {
                        myStud = x.getValue(Student.class);
                        if (myStud.getMEmail().equals(email)) {
                            break;
                        } else {
                            myStud = null;
                        }
                    }
                    String name = util.getNameFromMail(myStud.getMEmail()).get("first").toUpperCase();
                    studentName.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            //getting student orar
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            dbRef = db.getReference("orar");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (myStud != null) {
                        for (DataSnapshot x : dataSnapshot.getChildren()) {
                            myOrar = x.getValue(Orar.class);
                            if (myOrar.getMAn() == myStud.getMAn() && myOrar.getMSpecialitate().equals(myStud.getMSpec())) {


                                //trying WorkerManager
                                //final PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class,2,TimeUnit.MINUTES).build();

                                //starting backgroundNotifyService
                                writeCache(myOrar.toString(), x.getKey());
                                startService();
                                Calendar calendar = Calendar.getInstance();
                                int zi = calendar.get(Calendar.DAY_OF_WEEK);
                                switch (zi) {
                                    case Calendar.MONDAY:
                                        orarZi.setVisibility(View.VISIBLE);
                                        orarZiHeader.setText("LUNI");
                                        orarZiHeader.setVisibility(View.VISIBLE);
                                        util.setOraronViews("luni", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                                        break;
                                    case Calendar.TUESDAY:
                                        orarZi.setVisibility(View.VISIBLE);
                                        orarZiHeader.setText("MARTI");
                                        orarZiHeader.setVisibility(View.VISIBLE);
                                        util.setOraronViews("marti", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                                        break;
                                    case Calendar.WEDNESDAY:
                                        orarZi.setVisibility(View.VISIBLE);
                                        orarZiHeader.setText("MIERCURI");
                                        orarZiHeader.setVisibility(View.VISIBLE);
                                        util.setOraronViews("miercuri", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                                        break;
                                    case Calendar.THURSDAY:
                                        orarZi.setVisibility(View.VISIBLE);
                                        orarZiHeader.setText("JOI");
                                        orarZiHeader.setVisibility(View.VISIBLE);
                                        util.setOraronViews("joi", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                                        break;
                                    case Calendar.FRIDAY:
                                        orarZi.setVisibility(View.VISIBLE);
                                        orarZiHeader.setText("VINERI");
                                        orarZiHeader.setVisibility(View.VISIBLE);
                                        util.setOraronViews("vineri", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                                        break;
                                    case Calendar.SATURDAY:
                                        orarZi.setVisibility(View.VISIBLE);
                                        orarZiHeader.setText("LUNI");
                                        orarZiHeader.setVisibility(View.VISIBLE);
                                        util.setOraronViews("luni", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                                        break;
                                    case Calendar.SUNDAY:
                                        orarZi.setVisibility(View.VISIBLE);
                                        orarZiHeader.setText("LUNI");
                                        orarZiHeader.setVisibility(View.VISIBLE);
                                        util.setOraronViews("luni", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                                        break;
                                }

                                break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        } else {
            Gson json = new Gson();
            myOrar = json.fromJson(orarExtra, Orar.class);
            myStud = json.fromJson(studExtra, Student.class);
            String name = util.getNameFromMail(myStud.getMEmail()).get("first").toUpperCase();
            studentName.setText(name);
            if (myOrar.getMAn() == myStud.getMAn() && myOrar.getMSpecialitate().equals(myStud.getMSpec())) {
                Calendar calendar = Calendar.getInstance();
                int zi = calendar.get(Calendar.DAY_OF_WEEK);
                switch (zi) {
                    case Calendar.MONDAY:
                        orarZi.setVisibility(View.VISIBLE);
                        orarZiHeader.setText("LUNI");
                        orarZiHeader.setVisibility(View.VISIBLE);
                        util.setOraronViews("luni", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                        break;
                    case Calendar.TUESDAY:
                        orarZi.setVisibility(View.VISIBLE);
                        orarZiHeader.setText("MARTI");
                        orarZiHeader.setVisibility(View.VISIBLE);
                        util.setOraronViews("marti", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                        break;
                    case Calendar.WEDNESDAY:
                        orarZi.setVisibility(View.VISIBLE);
                        orarZiHeader.setText("MIERCURI");
                        orarZiHeader.setVisibility(View.VISIBLE);
                        util.setOraronViews("miercuri", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                        break;
                    case Calendar.THURSDAY:
                        orarZi.setVisibility(View.VISIBLE);
                        orarZiHeader.setText("JOI");
                        orarZiHeader.setVisibility(View.VISIBLE);
                        util.setOraronViews("joi", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                        break;
                    case Calendar.FRIDAY:
                        orarZi.setVisibility(View.VISIBLE);
                        orarZiHeader.setText("VINERI");
                        orarZiHeader.setVisibility(View.VISIBLE);
                        util.setOraronViews("vineri", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                        break;
                    case Calendar.SATURDAY:
                        orarZi.setVisibility(View.VISIBLE);
                        orarZiHeader.setText("LUNI");
                        orarZiHeader.setVisibility(View.VISIBLE);
                        util.setOraronViews("luni", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                        break;
                    case Calendar.SUNDAY:
                        orarZi.setVisibility(View.VISIBLE);
                        orarZiHeader.setText("LUNI");
                        orarZiHeader.setVisibility(View.VISIBLE);
                        util.setOraronViews("luni", myOrar, clasa8, clasa10, clasa12, clasa14, clasa16, clasa18, clasa20);
                        break;
                }
            }
        }


        orarBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orarIntent = new Intent(WelcomeActivity.this, OrarActivity.class);
                Gson json = new Gson();
                orarIntent.putExtra("orar", json.toJson(myOrar));
                orarIntent.putExtra("stud", json.toJson(myStud));
                startActivity(orarIntent);
            }
        });

        universityBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent universityIntent = new Intent(WelcomeActivity.this, UniversityActivity.class);
                Gson json = new Gson();
                universityIntent.putExtra("orar", json.toJson(myOrar));
                universityIntent.putExtra("stud", json.toJson(myStud));
                startActivity(universityIntent);

            }
        });
        logOutBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(WelcomeActivity.this, LoginFireBase.class);
                startActivity(loginIntent);
            }
        });
        chatImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(WelcomeActivity.this, ChatActivity.class);
                Gson json = new Gson();
                chatIntent.putExtra("orar", json.toJson(myOrar));
                chatIntent.putExtra("stud", json.toJson(myStud));
                startActivity(chatIntent);
            }
        });
        chatBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(WelcomeActivity.this, ChatActivity.class);
                Gson json = new Gson();
                chatIntent.putExtra("orar", json.toJson(myOrar));
                chatIntent.putExtra("stud", json.toJson(myStud));
                startActivity(chatIntent);
            }
        });
    }


    private void startService() {
        if (!isMyServiceRunning(BackgroundNotifyService.class)) {


//            keepAlive.putExtra("orarID", id);
//            keepAlive.putExtra("orar", new Gson().toJson(myOrar));

//            PendingIntent keepAliveIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, keepAlive, PendingIntent.FLAG_CANCEL_CURRENT);
//            AlarmManager alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            alarms.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+1000 , 60000, keepAliveIntent);
            Thread runWithNoBound = new Thread() {
                @Override
                public void run() {

                    Intent backgroundService = new Intent(getApplicationContext(), BackgroundNotifyService.class);
                    startService(backgroundService);
                }
            };
            runWithNoBound.start();
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        //checks if your service is up
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    private void writeCache(String orar, String id) {

        String orarFile = "orarFile";
        deleteFile(orarFile);
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(orarFile, Context.MODE_PRIVATE);
            outputStream.write(orar.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String orarID = "orarIDFile";
        deleteFile(orarID);

        try {
            outputStream = openFileOutput(orarID, Context.MODE_PRIVATE);
            outputStream.write(id.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
