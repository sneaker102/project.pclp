package project.programming.elliajah.studentknows1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginFireBase extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText inputEmail;
    private EditText inputPass;
    private Button conBttn;
    private CheckBox saveEmail;
    private Boolean isEmailSaved = false;
    private TextView forgotAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_fire_base);

        //put in FirebaseDB
//        Handler handleAdd = new Handler();
//       fireBaseAddOrar x= new fireBaseAddOrar();
//        handleAdd.post(x);
//        x.run();
        forgotAcc = findViewById(R.id.forgotPass);
        saveEmail = findViewById(R.id.saveEmail);
        mAuth = FirebaseAuth.getInstance();
        conBttn = findViewById(R.id.conBttn);
        inputEmail = findViewById(R.id.inputMail);
        inputPass = findViewById(R.id.inputPass);
        conBttn.setOnClickListener(this);
        saveEmail.setOnClickListener(this);
        forgotAcc.setOnClickListener(this);
        rememberEmail();

    }

    public void rememberEmail() {
        String emailSaved = readFromCache().get("email");
        String passSaved = readFromCache().get("pass");
        if (emailSaved != null && !emailSaved.isEmpty()
                && passSaved != null && !passSaved.isEmpty()) {

            if (emailSaved.contains("true") || emailSaved.contains("false")) {
                if (emailSaved.contains("true")) {
                    isEmailSaved = true;
                    saveEmail.setChecked(true);
                    inputEmail.setText(emailSaved.substring(0, emailSaved.length() - 4));
                    inputPass.setText(passSaved);
                } else {
                    isEmailSaved = false;
                    saveEmail.setChecked(false);
                    inputEmail.setText("");
                    inputPass.setText("");
                }
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.conBttn:
                if (!inputEmail.getText().toString().isEmpty() && !inputPass.getText().toString().isEmpty()) {
                    mAuth.signInWithEmailAndPassword(inputEmail.getText().toString(), inputPass.getText().toString());

                    switch (v.getId()) {

                        case R.id.conBttn:
                            if (mAuth.getCurrentUser() != null) {
                                mAuth.signInWithEmailAndPassword(inputEmail.getText().toString(),
                                        inputPass.getText().toString())
                                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getApplicationContext(), "LOGGED", Toast.LENGTH_LONG).show();


                                                    Intent loggged = new Intent(LoginFireBase.this, WelcomeActivity.class);

                                                    loggged.putExtra("email", inputEmail.getText().toString());
                                                    startActivity(loggged);

                                                } else {
                                                    Toast.makeText(getApplicationContext(), "NU a mers", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });


                            }
                    }
                } else {
                    Toast.makeText(this.getApplicationContext(), "TE ROG INTRODU UN UTILIZATOR!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.saveEmail:
                if (saveEmail.isChecked()) {
                    isEmailSaved = true;
                    if (!inputEmail.getText().toString().isEmpty()
                            && !inputPass.getText().toString().isEmpty()) {
                        writeCacheAcc(inputEmail.getText().toString(), inputPass.getText().toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "First insert your credentials!",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    isEmailSaved = false;
                    writeCacheAcc("", "");
                }

                break;
            case R.id.forgotPass: {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://selfpasswd.unitbv.ro/"));
                startActivity(intent);
                break;
            }
        }

    }

    private void writeCacheAcc(String email, String pass) {

        String passFile = "passFile";
        String emailFile = "emailFile";
        deleteFile(emailFile);
        deleteFile(passFile);
        FileOutputStream outputStream;
        outputStream = null;
        try {
            outputStream = openFileOutput(emailFile, Context.MODE_PRIVATE);
            outputStream.write(email.getBytes());
            outputStream.write(isEmailSaved.toString().getBytes());

            outputStream = openFileOutput(passFile, MODE_PRIVATE);
            outputStream.write(pass.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private Map<String, String> readFromCache() {

        int j = 0;
        char d;
        StringBuilder buildPass = new StringBuilder();
        StringBuilder buildEmail = new StringBuilder();

        try {
            FileInputStream orarFile = openFileInput("emailFile");

            while ((j = orarFile.read()) != -1) {
                d = (char) j;
                buildEmail.append(d);
            }

            orarFile.close();
            orarFile = openFileInput("passFile");
            while ((j = orarFile.read()) != -1) {
                d = (char) j;
                buildPass.append(d);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> emailAndPass = new HashMap<>();
        emailAndPass.put("email", buildEmail.toString());
        emailAndPass.put("pass", buildPass.toString());
        return emailAndPass;
    }
}
