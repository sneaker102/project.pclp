package project.programming.elliajah.studentknows1;

import android.arch.persistence.room.Database;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrarActivity extends AppCompatActivity {
    private Orar myOrar;
    private Spinner specSpin, anSpin, saptSpin;
    private Button menuSwipe, homeBttn, searchBttn, universityBttn, logOutBttn, chatBttn;
    private String[] chosenSpin;
    private boolean isMenuOpen;
    private LinearLayout menuLayout, layotHeader, searchLay, luniMerge, martiMerge, miercuriMerge, joiMerge, vineriMerge;
    private TextView studentName, ora8L, ora10L, ora12L, ora14L, ora16L, ora18L, ora20L, ora8M, ora10M, ora12M, ora14M, ora16M, ora18M, ora20M, ora8MI, ora10MI, ora12MI, ora14MI, ora16MI, ora18MI, ora20MI, ora8J, ora10J, ora12J, ora14J, ora16J, ora18J, ora20J, ora8V, ora10V, ora12V, ora14V, ora16V, ora18V, ora20V, luniGo, martiGo, miercuriGo, joiGo, vineriGo;
    private HorizontalScrollView luniScroll, martiScroll, miercuriScroll, joiScroll, vineriScroll;
    private boolean isNotified;
    private Student myStud;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orar);
        chatBttn = findViewById(R.id.groupBttn);
        martiMerge = findViewById(R.id.martiMerge);
        miercuriMerge = findViewById(R.id.miercuriMerge);
        joiMerge = findViewById(R.id.joiMerge);
        vineriMerge = findViewById(R.id.vineriMerge);
        martiGo = findViewById(R.id.martiGo);
        miercuriGo = findViewById(R.id.miercuriGo);
        joiGo = findViewById(R.id.joiGo);
        vineriGo = findViewById(R.id.vineriGo);
        //initialize scrolls
        luniScroll = findViewById(R.id.luniScroll);
        martiScroll = findViewById(R.id.martiScroll);
        miercuriScroll = findViewById(R.id.miercuriScroll);
        joiScroll = findViewById(R.id.joiScroll);
        vineriScroll = findViewById(R.id.vineriScroll);
        //initialize my utils class

        final Utils util = new Utils();
        //initialize spinners
        chosenSpin = new String[3];
        final String[] anArray = new String[]{"AN I ", "AN II ", "AN III ", "AN IV "};
        final String[] saptArray = new String[]{"IMPARA", "PARA"};
        final String[] specArray = new String[]{"ROBO", "TI", "CALC", "AIA"};
        specSpin = findViewById(R.id.spinnerSpec);
        anSpin = findViewById(R.id.spinnerAn);
        saptSpin = findViewById(R.id.spinnerSapt);
        final ArrayAdapter<String> specAdapter = new ArrayAdapter<String>(
                this, R.layout.for_list_adapter, R.id.tvlist, specArray);
        specAdapter.setDropDownViewResource(R.layout.for_list_adapter);
        final ArrayAdapter<String> anAdapter = new ArrayAdapter<String>(
                this, R.layout.for_list_adapter, R.id.tvlist, anArray);
        final ArrayAdapter<String> saptAdapter = new ArrayAdapter<String>(
                this, R.layout.for_list_adapter, R.id.tvlist, saptArray);
        saptAdapter.setDropDownViewResource(R.layout.for_list_adapter);
        specAdapter.setDropDownViewResource(R.layout.for_list_adapter);
        anAdapter.setDropDownViewResource(R.layout.for_list_adapter);
        specAdapter.setDropDownViewResource(R.layout.for_list_adapter);
        anSpin.setAdapter(anAdapter);
        saptSpin.setAdapter(saptAdapter);
        specSpin.setAdapter(specAdapter);
        //initialize text views
        luniGo = findViewById(R.id.luniGo);
        studentName = findViewById(R.id.student_name);
        ora8L = findViewById(R.id.ora8L);
        ora10L = findViewById(R.id.ora10L);
        ora12L = findViewById(R.id.ora12L);
        ora14L = findViewById(R.id.ora14L);
        ora16L = findViewById(R.id.ora16L);
        ora18L = findViewById(R.id.ora18L);
        ora20L = findViewById(R.id.ora20L);
        ora8M = findViewById(R.id.ora8M);
        ora10M = findViewById(R.id.ora10M);
        ora12M = findViewById(R.id.ora12M);
        ora14M = findViewById(R.id.ora14M);
        ora16M = findViewById(R.id.ora16M);
        ora18M = findViewById(R.id.ora18M);
        ora20M = findViewById(R.id.ora20M);
        ora8MI = findViewById(R.id.ora8MI);
        ora10MI = findViewById(R.id.ora10MI);
        ora12MI = findViewById(R.id.ora12MI);
        ora14MI = findViewById(R.id.ora14MI);
        ora16MI = findViewById(R.id.ora16MI);
        ora18MI = findViewById(R.id.ora18MI);
        ora20MI = findViewById(R.id.ora20MI);
        ora8J = findViewById(R.id.ora8J);
        ora10J = findViewById(R.id.ora10J);
        ora12J = findViewById(R.id.ora12J);
        ora14J = findViewById(R.id.ora14J);
        ora16J = findViewById(R.id.ora16J);
        ora18J = findViewById(R.id.ora18J);
        ora20J = findViewById(R.id.ora20J);
        ora8V = findViewById(R.id.orar8V);
        ora10V = findViewById(R.id.orar10V);
        ora12V = findViewById(R.id.orar12V);
        ora14V = findViewById(R.id.orar14V);
        ora16V = findViewById(R.id.orar16V);
        ora18V = findViewById(R.id.orar18V);
        ora20V = findViewById(R.id.orar20V);
        //getting extras

        isNotified = getIntent().getBooleanExtra("notify", false);

        String orarExtra = null;
        String studExtra = null;
        if (!isNotified) {
            orarExtra = getIntent().getStringExtra("orar");
            studExtra = getIntent().getStringExtra("stud");
            myStud = new Gson().fromJson(studExtra, Student.class);
            String name = null;
            name = util.getNameFromMail(myStud.getMEmail()).get("first").toUpperCase();
            studentName.setText(name);
        }
        //initialize layouts
        luniMerge = findViewById(R.id.luniMerge);
        martiMerge = findViewById(R.id.martiMerge);
        miercuriMerge = findViewById(R.id.miercuriMerge);
        joiMerge = findViewById(R.id.joiMerge);
        vineriMerge = findViewById(R.id.vineriMerge);
        searchLay = findViewById(R.id.searchLay);
        menuLayout = findViewById(R.id.maybeFragment);
        layotHeader = findViewById(R.id.layHeader);

        //initialize variables
        isMenuOpen = false;
        //initialize buttons
        menuSwipe = findViewById(R.id.menuBttnSwipe);
        homeBttn = findViewById(R.id.homeBttn);
        searchBttn = findViewById(R.id.searchBttn);
        universityBttn = findViewById(R.id.universityBttn);
        logOutBttn = findViewById(R.id.logOutBttn);
        //drop box
        specSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        chosenSpin[0] = parent.getItemAtPosition(0).toString();
                        break;
                    case 1:
                        chosenSpin[0] = parent.getItemAtPosition(1).toString();
                        break;
                    case 2:
                        chosenSpin[0] = parent.getItemAtPosition(2).toString();
                        break;
                    case 3:
                        chosenSpin[0] = parent.getItemAtPosition(3).toString();
                        break;
                    default:
                        chosenSpin[0] = parent.getItemAtPosition(0).toString();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        anSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        chosenSpin[1] = parent.getItemAtPosition(0).toString();
                        break;
                    case 1:
                        chosenSpin[1] = parent.getItemAtPosition(1).toString();
                        break;
                    case 2:
                        chosenSpin[1] = parent.getItemAtPosition(2).toString();
                        break;
                    case 3:
                        chosenSpin[1] = parent.getItemAtPosition(3).toString();
                        break;
                    default:
                        chosenSpin[1] = parent.getItemAtPosition(0).toString();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        saptSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        chosenSpin[2] = parent.getItemAtPosition(0).toString();
                        break;
                    case 1:
                        chosenSpin[2] = parent.getItemAtPosition(1).toString();
                        break;
                    case 2:
                        chosenSpin[2] = parent.getItemAtPosition(2).toString();
                        break;
                    case 3:
                        chosenSpin[2] = parent.getItemAtPosition(3).toString();
                        break;
                    default:
                        chosenSpin[2] = parent.getItemAtPosition(0).toString();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //on  search
        searchBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference dbRef = db.getReference("orar");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String an = chosenSpin[1].replace("AN ", "").trim();
                        switch (an) {
                            case "I":
                                an = "1";
                                break;
                            case "II":
                                an = "2";
                                break;
                            case "III":
                                an = "3";
                                break;
                            case "IV":
                                an = "4";
                                break;
                        }
                        String sapt = chosenSpin[2];
                        switch (sapt) {
                            case "IMPARA":
                                sapt = "1";
                                break;
                            case "PARA":
                                sapt = "2";
                                break;
                        }
                        for (DataSnapshot x : dataSnapshot.getChildren()) {
                            myOrar = x.getValue(Orar.class);

                            if (myOrar.getMSpecialitate().equals(chosenSpin[0]) && String.valueOf(myOrar.getMAn()).equals(an)
                                    && String.valueOf(myOrar.getMSapt()).equals(sapt)) {
                                util.setOraronViews("luni", myOrar, ora8L, ora10L, ora12L, ora14L, ora16L, ora18L, ora20L);
                                util.setOraronViews("marti", myOrar, ora8M, ora10M, ora12M, ora14M, ora16M, ora18M, ora20M);
                                util.setOraronViews("miercuri", myOrar, ora8MI, ora10MI, ora12MI, ora14MI, ora16MI, ora18MI, ora20MI);
                                util.setOraronViews("joi", myOrar, ora8J, ora10J, ora12J, ora14J, ora16J, ora18J, ora20J);
                                util.setOraronViews("vineri", myOrar, ora8V, ora10V, ora12V, ora14V, ora16V, ora18V, ora20V);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        if (isNotified) {
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference dbStud = db.getReference("studenti");
            dbStud.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot x : dataSnapshot.getChildren()) {
                        myStud = x.getValue(Student.class);
                        if (myStud.getMEmail().equals(getIntent().getStringExtra("email").replace("true", ""))) {
                            String name = null;
                            name = util.getNameFromMail(myStud.getMEmail()).get("first").toUpperCase();
                            studentName.setText(name);
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            DatabaseReference dbRef = db.getReference("orar");
            dbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String an = anArray[1].replace("AN ", "").trim();
                    switch (an) {
                        case "I":
                            an = "1";
                            break;
                        case "II":
                            an = "2";
                            break;
                        case "III":
                            an = "3";
                            break;
                        case "IV":
                            an = "4";
                            break;
                    }
                    String sapt = saptArray[0];
                    switch (sapt) {
                        case "IMPARA":
                            sapt = "1";
                            break;
                        case "PARA":
                            sapt = "2";
                            break;
                    }
                    String spec = specArray[0];
                    for (DataSnapshot x : dataSnapshot.getChildren()) {
                        myOrar = x.getValue(Orar.class);

                        if (myOrar.getMSpecialitate().equals(spec) && String.valueOf(myOrar.getMAn()).equals(an)
                                && String.valueOf(myOrar.getMSapt()).equals(sapt)) {
                            writeCache(myOrar.toString(), x.getKey());
                            util.setOraronViews("luni", myOrar, ora8L, ora10L, ora12L, ora14L, ora16L, ora18L, ora20L);
                            util.setOraronViews("marti", myOrar, ora8M, ora10M, ora12M, ora14M, ora16M, ora18M, ora20M);
                            util.setOraronViews("miercuri", myOrar, ora8MI, ora10MI, ora12MI, ora14MI, ora16MI, ora18MI, ora20MI);
                            util.setOraronViews("joi", myOrar, ora8J, ora10J, ora12J, ora14J, ora16J, ora18J, ora20J);
                            util.setOraronViews("vineri", myOrar, ora8V, ora10V, ora12V, ora14V, ora16V, ora18V, ora20V);
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {

            myOrar = new Gson().fromJson(orarExtra, Orar.class);

            util.setOraronViews("luni", myOrar, ora8L, ora10L, ora12L, ora14L, ora16L, ora18L, ora20L);
            util.setOraronViews("marti", myOrar, ora8M, ora10M, ora12M, ora14M, ora16M, ora18M, ora20M);
            util.setOraronViews("miercuri", myOrar, ora8MI, ora10MI, ora12MI, ora14MI, ora16MI, ora18MI, ora20MI);
            util.setOraronViews("joi", myOrar, ora8J, ora10J, ora12J, ora14J, ora16J, ora18J, ora20J);
            util.setOraronViews("vineri", myOrar, ora8V, ora10V, ora12V, ora14V, ora16V, ora18V, ora20V);
        }
        //get menu ready
        homeBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(OrarActivity.this, WelcomeActivity.class);


                home.putExtra("orar", new Gson().toJson(myOrar));


                home.putExtra("stud", new Gson().toJson(myStud));

                startActivity(home);


            }
        });
        universityBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent univIntent = new Intent(OrarActivity.this, UniversityActivity.class);
                univIntent.putExtra("orar", getIntent().getStringExtra("orar"));
                univIntent.putExtra("stud", getIntent().getStringExtra("stud"));
                startActivity(univIntent);
            }
        });
        logOutBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(OrarActivity.this, LoginFireBase.class);
                startActivity(loginIntent);
            }
        });

        //slide menu view




        menuSwipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpen) {
                    menuLayout.animate().translationX(0);
                    layotHeader.animate().translationX(660);
                    searchLay.animate().translationX(660);
                    if (luniMerge.getTranslationX() == 0) {
                        luniMerge.animate().translationX(660).setDuration(300);
                    } else {
                        luniScroll.animate().translationX(660).setDuration(300);
                    }
                    if (martiMerge.getTranslationX() == 0) {
                        martiMerge.animate().translationX(660).setDuration(300);
                    } else {
                        martiScroll.animate().translationX(660).setDuration(300);
                    }
                    if (miercuriMerge.getTranslationX() == 0) {
                        miercuriMerge.animate().translationX(660).setDuration(300);
                    } else {
                        miercuriScroll.animate().translationX(660).setDuration(300);
                    }
                    if (joiMerge.getTranslationX() == 0) {
                        joiMerge.animate().translationX(660).setDuration(300);
                    } else {
                        joiScroll.animate().translationX(660).setDuration(300);
                    }
                    if (vineriMerge.getTranslationX() == 0) {
                        vineriMerge.animate().translationX(660).setDuration(300);
                    } else {
                        vineriScroll.animate().translationX(660).setDuration(300);
                    }
                    isMenuOpen = true;
                } else {
                    menuLayout.animate().translationX(-605);
                    layotHeader.animate().translationX(0);
                    searchLay.animate().translationX(0);
                    if (luniMerge.getTranslationX() == 660) {
                        luniMerge.animate().translationX(0).setDuration(300);
                    } else {
                        luniScroll.animate().translationX(0).setDuration(300);
                    }
                    if (martiMerge.getTranslationX() == 660) {
                        martiMerge.animate().translationX(0).setDuration(300);
                    } else {
                        martiScroll.animate().translationX(0).setDuration(300);
                    }
                    if (miercuriMerge.getTranslationX() == 660) {
                        miercuriMerge.animate().translationX(0).setDuration(300);
                    } else {
                        miercuriScroll.animate().translationX(0).setDuration(300);
                    }
                    if (joiMerge.getTranslationX() == 660) {
                        joiMerge.animate().translationX(0).setDuration(300);
                    } else {
                        joiScroll.animate().translationX(0).setDuration(300);
                    }
                    if (vineriMerge.getTranslationX() == 660) {
                        vineriMerge.animate().translationX(0).setDuration(300);
                    } else {
                        vineriScroll.animate().translationX(0).setDuration(300);
                    }
                    isMenuOpen = false;
                }
            }
        });

        layotHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMenuOpen) {
                    menuLayout.animate().translationX(-605);
                    layotHeader.animate().translationX(0);
                    searchLay.animate().translationX(0);
                    if (luniMerge.getTranslationX() == 660) {
                        luniMerge.animate().translationX(0).setDuration(300);
                    } else {
                        luniScroll.animate().translationX(0).setDuration(300);
                    }
                    if (martiMerge.getTranslationX() == 660) {
                        martiMerge.animate().translationX(0).setDuration(300);
                    } else {
                        martiScroll.animate().translationX(0).setDuration(300);
                    }
                    if (miercuriMerge.getTranslationX() == 660) {
                        miercuriMerge.animate().translationX(0).setDuration(300);
                    } else {
                        miercuriScroll.animate().translationX(0).setDuration(300);
                    }
                    if (joiMerge.getTranslationX() == 660) {
                        joiMerge.animate().translationX(0).setDuration(300);
                    } else {
                        joiScroll.animate().translationX(0).setDuration(300);
                    }
                    if (vineriMerge.getTranslationX() == 660) {
                        vineriMerge.animate().translationX(0).setDuration(300);
                    } else {
                        vineriScroll.animate().translationX(0).setDuration(300);
                    }
                    isMenuOpen = false;
                }
            }
        });


//        //merging the scrolls
//        luniScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                martiScroll.scrollTo(scrollX, scrollY);
//                miercuriScroll.scrollTo(scrollX, scrollY);
//                joiScroll.scrollTo(scrollX, scrollY);
//                vineriScroll.scrollTo(scrollX, scrollY);
//            }
//        });
//        martiScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                luniScroll.scrollTo(scrollX, scrollY);
//                miercuriScroll.scrollTo(scrollX, scrollY);
//                joiScroll.scrollTo(scrollX, scrollY);
//                vineriScroll.scrollTo(scrollX, scrollY);
//            }
//        });
//
//        miercuriScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                martiScroll.scrollTo(scrollX, scrollY);
//                luniScroll.scrollTo(scrollX, scrollY);
//                joiScroll.scrollTo(scrollX, scrollY);
//                vineriScroll.scrollTo(scrollX, scrollY);
//            }
//        });
//        joiScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                martiScroll.scrollTo(scrollX, scrollY);
//                miercuriScroll.scrollTo(scrollX, scrollY);
//                luniScroll.scrollTo(scrollX, scrollY);
//                vineriScroll.scrollTo(scrollX, scrollY);
//            }
//        });
//
//        vineriScroll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                martiScroll.scrollTo(scrollX, scrollY);
//                miercuriScroll.scrollTo(scrollX, scrollY);
//                joiScroll.scrollTo(scrollX, scrollY);
//                luniScroll.scrollTo(scrollX, scrollY);
//            }
//        });
        luniGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpen) {
                    if (vineriMerge.getTranslationX() != 0) {
                        vineriScroll.animate().translationX(-1200).setDuration(600);
                        vineriMerge.animate().translationX(0).setDuration(600);
                    }
                    if (joiMerge.getTranslationX() != 0) {
                        joiScroll.animate().translationX(-1200).setDuration(600);
                        joiMerge.animate().translationX(0).setDuration(600);
                    }
                    if (miercuriMerge.getTranslationX() != 0) {
                        miercuriScroll.animate().translationX(-1200).setDuration(600);
                        miercuriMerge.animate().translationX(0).setDuration(600);
                    }
                    if (martiMerge.getTranslationX() != 0) {
                        martiScroll.animate().translationX(-1200).setDuration(600);
                        martiMerge.animate().translationX(0).setDuration(600);
                    }
                    if (luniMerge.getTranslationX() == 0) {
                        luniMerge.animate().translationX(900).setDuration(600);
                        luniScroll.animate().translationX(0).setDuration(600);

                    }
                }
            }
        });
        martiGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpen) {
                    if (vineriMerge.getTranslationX() != 0) {
                        vineriScroll.animate().translationX(-1200).setDuration(600);
                        vineriMerge.animate().translationX(0).setDuration(600);
                    }
                    if (joiMerge.getTranslationX() != 0) {
                        joiScroll.animate().translationX(-1200).setDuration(600);
                        joiMerge.animate().translationX(0).setDuration(600);
                    }
                    if (miercuriMerge.getTranslationX() != 0) {
                        miercuriScroll.animate().translationX(-1200).setDuration(600);
                        miercuriMerge.animate().translationX(0).setDuration(600);
                    }
                    if (martiMerge.getTranslationX() == 0) {
                        martiScroll.animate().translationX(0).setDuration(600);
                        martiMerge.animate().translationX(900).setDuration(600);
                    }
                    if (luniMerge.getTranslationX() != 0) {
                        luniMerge.animate().translationX(0).setDuration(600);
                        luniScroll.animate().translationX(-1200).setDuration(600);

                    }
                }
            }
        });
        miercuriGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpen) {
                    if (vineriMerge.getTranslationX() != 0) {
                        vineriScroll.animate().translationX(-1200).setDuration(600);
                        vineriMerge.animate().translationX(0).setDuration(600);
                    }
                    if (joiMerge.getTranslationX() != 0) {
                        joiScroll.animate().translationX(-1200).setDuration(600);
                        joiMerge.animate().translationX(0).setDuration(600);
                    }
                    if (miercuriMerge.getTranslationX() == 0) {
                        miercuriScroll.animate().translationX(0).setDuration(600);
                        miercuriMerge.animate().translationX(900).setDuration(600);
                    }
                    if (martiMerge.getTranslationX() != 0) {
                        martiScroll.animate().translationX(-1200).setDuration(600);
                        martiMerge.animate().translationX(0).setDuration(600);
                    }
                    if (luniMerge.getTranslationX() != 0) {
                        luniMerge.animate().translationX(0).setDuration(600);
                        luniScroll.animate().translationX(-1200).setDuration(600);

                    }
                }
            }
        });
        joiGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpen) {
                    if (vineriMerge.getTranslationX() != 0) {
                        vineriScroll.animate().translationX(-1200).setDuration(600);
                        vineriMerge.animate().translationX(0).setDuration(600);
                    }
                    if (joiMerge.getTranslationX() == 0) {
                        joiMerge.animate().translationX(900).setDuration(600);
                        joiScroll.animate().translationX(0).setDuration(600);

                    }
                    if (miercuriMerge.getTranslationX() != 0) {
                        miercuriScroll.animate().translationX(-1200).setDuration(600);
                        miercuriMerge.animate().translationX(0).setDuration(600);
                    }
                    if (martiMerge.getTranslationX() != 0) {
                        martiScroll.animate().translationX(-1200).setDuration(600);
                        martiMerge.animate().translationX(0).setDuration(600);
                    }
                    if (luniMerge.getTranslationX() != 0) {
                        luniMerge.animate().translationX(0).setDuration(600);
                        luniScroll.animate().translationX(-1200).setDuration(600);

                    }
                }
            }
        });
        vineriGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isMenuOpen) {
                    if (vineriMerge.getTranslationX() == 0) {
                        vineriScroll.animate().translationX(0).setDuration(600);
                        vineriMerge.animate().translationX(900).setDuration(600);
                    }
                    if (joiMerge.getTranslationX() != 0) {
                        joiMerge.animate().translationX(0).setDuration(600);
                        joiScroll.animate().translationX(-1200).setDuration(600);

                    }
                    if (miercuriMerge.getTranslationX() != 0) {
                        miercuriScroll.animate().translationX(-1200).setDuration(600);
                        miercuriMerge.animate().translationX(0).setDuration(600);
                    }
                    if (martiMerge.getTranslationX() != 0) {
                        martiScroll.animate().translationX(-1200).setDuration(600);
                        martiMerge.animate().translationX(0).setDuration(600);
                    }
                    if (luniMerge.getTranslationX() != 0) {
                        luniMerge.animate().translationX(0).setDuration(600);
                        luniScroll.animate().translationX(-1200).setDuration(600);

                    }
                }
            }
        });

        chatBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatBttn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent chatIntent = new Intent(OrarActivity.this, ChatActivity.class);
                        Gson json = new Gson();
                        chatIntent.putExtra("orar", json.toJson(myOrar));
                        chatIntent.putExtra("stud", json.toJson(myStud));
                        startActivity(chatIntent);
                    }
                });
            }
        });
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
