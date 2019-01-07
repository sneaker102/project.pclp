package project.programming.elliajah.studentknows1;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Map;

public class DbPlay {
    private Orar myOrar;
    private FirebaseDatabase db;
    private DatabaseReference myDbRef;

    public void changeOrar(String ziua, int ora, String clas) {
        db = FirebaseDatabase.getInstance();
        myDbRef = db.getReference("orar");
        String oraLaSchimbare = new String();
        switch (ora) {
            case 0:
                oraLaSchimbare = "08:00-09:40";
                break;
            case 1:
                oraLaSchimbare = "10:00-11:40";
                break;
            case 2:
                oraLaSchimbare = "12:00-13:40";
                break;
            case 3:
                oraLaSchimbare = "14:00-15:40";
                break;
            case 4:
                oraLaSchimbare = "16:00-17:40";
                break;
            case 5:
                oraLaSchimbare = "18:00-19:40";
                break;
            case 6:
                oraLaSchimbare = "20:00-21:40";
                break;
        }
        OrarOra clasaSchimbata = new OrarOra(oraLaSchimbare, clas);
        myDbRef.child("-LTZIp_B_kUNcZKLLtLC").child("mdayAndClass").child(ziua).child(String.valueOf(ora)).setValue(clasaSchimbata);

    }

    public String addChatRoom() {
        db = FirebaseDatabase.getInstance();
        myDbRef = db.getReference("chat_rooms");
        String key ;
                myDbRef.child(key=myDbRef.push().getKey()).setValue(key);
        return key;


    }
}
