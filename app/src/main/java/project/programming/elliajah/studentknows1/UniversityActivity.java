package project.programming.elliajah.studentknows1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UniversityActivity extends AppCompatActivity {
    private RelativeLayout layCore;
    private LinearLayout maybeFragment, layHeader;
    private Button menuBttn;
    private boolean isMenuOpened = false;
    private Button chatBttn,searchUniv, closeSearch, orarBttn, homeBttn,logOutBttn;
    private EditText searchName;
    private LinearLayout layoutProfi;
    private TextView studentName;
    private List<RelativeLayout> myPers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.university_activity);
        studentName=findViewById(R.id.student_name);
        chatBttn=findViewById(R.id.groupBttn);
        layCore = findViewById(R.id.coreLay);
        closeSearch = findViewById(R.id.closeSrc);
        layHeader = findViewById(R.id.layHeader);
        maybeFragment = findViewById(R.id.maybeFragment);
        menuBttn = findViewById(R.id.menuBttnSwipe);
        searchUniv = findViewById(R.id.searchBttn);
        searchName = findViewById(R.id.inputSearch);
        orarBttn = findViewById(R.id.orarBttn);
        logOutBttn=findViewById(R.id.logOutBttn);
      homeBttn=findViewById(R.id.homeBttn);
        layoutProfi = findViewById(R.id.pers1);

        //getting menu ready
        String name = null;
        Student myStud=new Gson().fromJson(getIntent().getStringExtra("stud"),Student.class);
        name =new Utils().getNameFromMail(myStud.getMEmail()).get("first").toUpperCase();
        studentName.setText(name);
        logOutBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(UniversityActivity.this,LoginFireBase.class);
                startActivity(loginIntent);
            }
        });
        chatBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chatIntent = new Intent(UniversityActivity.this, ChatActivity.class);
                Gson json = new Gson();
                chatIntent.putExtra("orar", getIntent().getStringExtra("orar"));
                chatIntent.putExtra("stud", getIntent().getStringExtra("stud"));
                startActivity(chatIntent);
            }
        });
        homeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcomeIntent = new Intent(UniversityActivity.this,WelcomeActivity.class);
                welcomeIntent.putExtra("orar",getIntent().getStringExtra("orar"));
                welcomeIntent.putExtra("stud",getIntent().getStringExtra("stud"));
                startActivity(welcomeIntent);
            }
        });
        orarBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orarIntent = new Intent(UniversityActivity.this,OrarActivity.class);
                orarIntent.putExtra("orar",getIntent().getStringExtra("orar"));
                orarIntent.putExtra("stud",getIntent().getStringExtra("stud"));
                startActivity(orarIntent);
            }
        });
        myPers = new ArrayList<>();
        for (int i = 0; i < layoutProfi.getChildCount(); i++) {
            myPers.add((RelativeLayout) layoutProfi.getChildAt(i));
        }
        searchUniv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!searchName.getText().toString().isEmpty()) {
                    String firstName = null;
                    String lastName = null;
                    Utils util = new Utils();
                    if (searchName.getText().toString().contains(" ")) {
                        firstName = searchName.getText().toString().substring(0, searchName.getText().toString().indexOf(' ')).toLowerCase();
                        lastName = searchName.getText().toString().substring(firstName.length() + 1).toLowerCase();
                    } else {
                        firstName = searchName.getText().toString();
                    }

                    closeSearch.setVisibility(View.VISIBLE);
                    for (RelativeLayout per : myPers) {
                        TextView see = (TextView) per.getChildAt(2);
                        if (lastName == null) {

                            if (!firstName.contains(util.getNameFromMail(see.getText().toString()).get("first"))
                                    && !firstName.contains(util.getNameFromMail(see.getText().toString()).get("second"))) {
                                per.setVisibility(View.GONE);
                            }
                        } else {
                            if (!firstName.contains(util.getNameFromMail(see.getText().toString()).get("first"))
                                    && !firstName.contains(util.getNameFromMail(see.getText().toString()).get("second"))
                                    && !lastName.contains(util.getNameFromMail(see.getText().toString()).get("first"))
                                    && !lastName.contains(util.getNameFromMail(see.getText().toString()).get("second"))) {
                                per.setVisibility(View.GONE);
                            }
                        }
                    }


                }
            }
        });
        closeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (RelativeLayout per : myPers) {
                    if (per.getVisibility() == View.GONE)
                        per.setVisibility(View.VISIBLE);
                }
                closeSearch.setVisibility(View.INVISIBLE);
                searchName.setText("");
                if (searchName.isInputMethodTarget()) {
                    dismissKeyboardShortcutsHelper();
                }

            }
        });
        menuBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpened) {
                    maybeFragment.animate().translationX(0);
                    layHeader.animate().translationX(660);
                    layCore.animate().translationX(660);
                    isMenuOpened = true;
                } else {
                    maybeFragment.animate().translationX(-605);
                    layHeader.animate().translationX(0);
                    layCore.animate().translationX(0);
                    isMenuOpened = false;
                }
            }
        });
        maybeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maybeFragment.animate().translationX(-605);
                layHeader.animate().translationX(0);
                layCore.animate().translationX(0);
                isMenuOpened = false;

            }
        });


    }
}
