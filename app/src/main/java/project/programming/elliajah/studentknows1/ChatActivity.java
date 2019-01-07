package project.programming.elliajah.studentknows1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ChatActivity extends AppCompatActivity implements CheatSearchFragment.FragmentPplListener, ChatRoomFragment.FragmentChatListener {

    //    private SwitchFragmentAdapter adapter;
//    private ViewPager viePage;
    private String roomKey;
    private String myName = null;
    private boolean isMenuOpened;
    private TextView studentName;
    private FrameLayout chatLay;
    private LinearLayout menuLayout, menuBttnLay;
    private Button menuBttn, universityBttn, homeBttn, gradesBttn, orarBttn, logOutBttn;
    private ChatRoomFragment fragmentChat;
    private CheatSearchFragment fragmentPpl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        studentName = findViewById(R.id.student_name);
        homeBttn = findViewById(R.id.homeBttn);
        menuBttn = findViewById(R.id.menuBttnSwipe);
        orarBttn = findViewById(R.id.orarBttn);
        logOutBttn = findViewById(R.id.logOutBttn);
        universityBttn = findViewById(R.id.universityBttn);
        gradesBttn = findViewById(R.id.gradesBttn);
        menuBttnLay = findViewById(R.id.menuBttnLay);
        menuLayout = findViewById(R.id.maybeFragment);
        menuBttn = findViewById(R.id.menuBttnSwipe);
        chatLay = findViewById(R.id.container_fragment);
        fragmentChat = new ChatRoomFragment();
        fragmentPpl = new CheatSearchFragment();


        Student myStud = new Gson().fromJson(getIntent().getStringExtra("stud"), Student.class);
        myName = new Utils().getNameFromMail(myStud.getMEmail()).get("first").toUpperCase();
        studentName.setText(myName);

        menuBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpened) {
                    menuLayout.animate().translationX(0);
                    chatLay.animate().translationX(660);
                    menuBttnLay.animate().translationX(660);
                    isMenuOpened = true;
                } else {
                    menuLayout.animate().translationX(-605);
                    chatLay.animate().translationX(0);
                    menuBttnLay.animate().translationX(0);
                    isMenuOpened = false;
                }

            }
        });
        menuBttnLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpened) {
                    menuLayout.animate().translationX(0);
                    chatLay.animate().translationX(660);
                    menuBttnLay.animate().translationX(660);
                    isMenuOpened = true;
                } else {
                    menuLayout.animate().translationX(-605);
                    chatLay.animate().translationX(0);
                    menuBttnLay.animate().translationX(0);
                    isMenuOpened = false;
                }

            }
        });
        menuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLayout.animate().translationX(-605);
                chatLay.animate().translationX(0);
                menuBttnLay.animate().translationX(0);
                isMenuOpened = false;

            }
        });
        homeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(ChatActivity.this, WelcomeActivity.class);


                home.putExtra("orar", getIntent().getStringExtra("orar"));

                home.putExtra("stud", getIntent().getStringExtra("stud"));

                startActivity(home);


            }
        });
        orarBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orarIntent = new Intent(ChatActivity.this, OrarActivity.class);
                Gson json = new Gson();
                orarIntent.putExtra("orar", getIntent().getStringExtra("orar"));
                orarIntent.putExtra("stud", getIntent().getStringExtra("stud"));
                startActivity(orarIntent);
            }
        });

        universityBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent universityIntent = new Intent(ChatActivity.this, UniversityActivity.class);
                Gson json = new Gson();
                universityIntent.putExtra("orar", getIntent().getStringExtra("orar"));
                universityIntent.putExtra("stud", getIntent().getStringExtra("stud"));
                startActivity(universityIntent);

            }
        });
        logOutBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ChatActivity.this, LoginFireBase.class);
                startActivity(loginIntent);
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragmentPpl).commit();


        //swipe between fragments
//        adapter = new SwitchFragmentAdapter(getSupportFragmentManager());
//        viePage = (ViewPager) findViewById(R.id.viewPager);
//        setupViewAsFragment(viePage);
//        TabLayout tabLay = findViewById(R.id.chatTab);
//        tabLay.setupWithViewPager(viePage);

    }

    @Override
    public void onInputFragmentChatSent(Boolean goToPplRoom, Boolean goToWelcomeActivity) {
//        if (goToPplRoom && !goToWelcomeActivity) {
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragmentPpl).commit();
//        } else {
//            if (goToWelcomeActivity && !goToPplRoom) {
//                Intent home = new Intent(ChatActivity.this, WelcomeActivity.class);
//                home.putExtra("orar", getIntent().getStringExtra("orar")).putExtra("stud", getIntent().getStringExtra("stud"));
//
//            }
//        }
    }

    @Override
    public void onInputFragmentPplSent(String name) {
        String lineRoom = null;
        if (fileExists(getApplicationContext())) {
            lineRoom = readFromFile(getApplicationContext(), name);
            if (lineRoom.isEmpty()) {
                roomKey = new DbPlay().addChatRoom();
                writeCache(getApplicationContext(), name,myName, roomKey);

            } else {

                roomKey = lineRoom.substring(lineRoom.indexOf('-') + 1, lineRoom.length());
            }
        } else {
            roomKey = new DbPlay().addChatRoom();
            writeCache(getApplicationContext(), name,myName, roomKey);
        }
        fragmentChat.retainToConnect(roomKey, myName);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragmentChat).commit();

//        if (goToChatRoom && !goToWelcome) {
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragmentChat).commit();
//        } else {
//            if (goToWelcome && !goToChatRoom) {
//                Intent home = new Intent(ChatActivity.this, WelcomeActivity.class);
//                home.putExtra("orar", getIntent().getStringExtra("orar")).putExtra("stud", getIntent().getStringExtra("stud"));
//
//            }
//        }

    }

    public boolean fileExists(Context context) {
        File file = context.getFileStreamPath("chatFile");
        if (file == null || !file.exists()) {
            return false;
        }
        return true;
    }

    private void writeCache(Context context, String nameTo,String nameFrom, String roomKey) {

        String chatFile = "chatFile";

        FileOutputStream outputStream;
        String toCache = nameFrom+","+nameTo + "-" + roomKey + "\n";

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(chatFile, Context.MODE_PRIVATE));
            outputStreamWriter.write(toCache);
            outputStreamWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String readFromFile(Context context, String name) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("chatFile");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    if (receiveString.contains(name)) {
                        stringBuilder.append(receiveString);
                    }

                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

//    private void setupViewAsFragment(ViewPager viewPage) {
//    SwitchFragmentAdapter adapterC = new SwitchFragmentAdapter(getSupportFragmentManager());
//    adapterC.addFragment(new ChatRoomFragment(),"CHAT_ROOM_FRAGMENT");
//    adapterC.addFragment(new CheatSearchFragment(),"CHAT_SEARCH_FRAGMENT");
//    viewPage.setAdapter(adapterC);
//    }
}
