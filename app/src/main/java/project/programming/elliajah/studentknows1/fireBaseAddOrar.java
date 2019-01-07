package project.programming.elliajah.studentknows1;

import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fireBaseAddOrar implements Runnable {


    @Override
    public void run() {
        //creating fireDBcon to the TABLE studenti
 final    FirebaseDatabase db =  FirebaseDatabase.getInstance();
        DatabaseReference myDbRef = db.getReference("studenti");
        //getting the key under which the object will stay for easy update object members

        Student x = new Student("razvan.ilie@student.unitbv.ro", "Seven7rays", "IESC", "ROBO", 2);
        Student y = new Student("alexandra.arsene@student.unitbv.ro", "Seven7rays", "IESC", "CALC", 3);
        Student z = new Student("cristian.savulescu@student.unitbv.ro", "Seven7rays", "IESC", "TI", 1);
        //poop table
//        myDbRef.child(myDbRef.push().getKey()).setValue(x);
//        myDbRef.child(myDbRef.push().getKey()).setValue(y);
//        myDbRef.child(myDbRef.push().getKey()).setValue(z);


        myDbRef = db.getReference("orar");


       List <OrarOra> oraSiClasa = new ArrayList<OrarOra>();
       oraSiClasa.add(new OrarOra("08:00-09:40",
               "ORATI, L," +
                       "VIV5A," +
                       "Popa_Lumi"));
        oraSiClasa.add(  new OrarOra("10:00-11:40", ""));
        oraSiClasa.add(    new OrarOra("12:00-13:40", "ORATI, C," +
                       "GP3," +
                       "Antonya_C"));
        oraSiClasa.add(   new OrarOra("14:00-15:40", ""));
        oraSiClasa.add(   new OrarOra("16:00-17:40", ""));
        oraSiClasa.add(   new OrarOra("18:00-19:40", "ORATI, C," +
                       "DI3," +
                       "Velean_M_N"));
        oraSiClasa.add(    new OrarOra("20:00-21:40", ""));
        Map<String, List<OrarOra>> ziuaSiOra = new HashMap<>();
        ziuaSiOra.put("luni", oraSiClasa);

        List<OrarOra> oraSiClasa1 = new ArrayList<>();
        oraSiClasa1.add(new OrarOra("08:00-09:40",
                "ORATI, C," +
                        "DI3," +
                        "Velea_M_N"));
        oraSiClasa1.add(   new OrarOra("10:00-11:40", "ORATI, S," +
                        "DI3," +
                        "Velea_M_N"));
        oraSiClasa1.add(    new OrarOra("12:00-13:40", ""));
        oraSiClasa1.add    (new OrarOra("14:00-15:40", "ORATI, S," +
                        "NS1," +
                        "Soica_s"));
        oraSiClasa1.add (    new OrarOra("16:00-17:40", ""));
        oraSiClasa1.add   (new OrarOra("18:00-19:40", ""));
        oraSiClasa1.add   (  new OrarOra("20:00-21:40", ""));

        ziuaSiOra.put("marti", oraSiClasa1);

        List<OrarOra> oraSiClasa2 = new ArrayList<>();
        oraSiClasa2.add(new OrarOra("08:00-09:40",
                "ORATI, L," +
                        "VIV5A," +
                        "Cristoiu_C_L"));
        oraSiClasa2.add(  new OrarOra("10:00-11:40", ""));
        oraSiClasa2.add(     new OrarOra("12:00-13:40", "ORATI, L," +
                        "VIII11," +
                        "Maceseanu_G"));
        oraSiClasa2.add(     new OrarOra("14:00-15:40", "ORATI, C," +
                        "VIII11," +
                        "Maceseanu_G"));
        oraSiClasa2.add(    new OrarOra("16:00-17:40", "ORATI, C," +
                        "VIV7," +
                        "Moraru_S_a"));
        oraSiClasa2.add(    new OrarOra("18:00-19:40", ""));
        oraSiClasa2.add(    new OrarOra("20:00-21:40", ""));
        ziuaSiOra.put("miercuri", oraSiClasa2);

        List<OrarOra> oraSiClasa3 = new ArrayList<>();
        oraSiClasa3.add(new OrarOra("08:00-09:40",""));
        oraSiClasa3.add(     new OrarOra("10:00-11:40", "ORATI, C," +
                        "HI32," +
                        "Ivanoiu_M"));
        oraSiClasa3.add(      new OrarOra("12:00-13:40", "ORATI, P," +
                        "VIII15," +
                        "Moraru_S_A"));
        oraSiClasa3.add(  new OrarOra("14:00-15:40", "ORATI, P," +
                        "DI5," +
                        "Butila_e_V"));
        oraSiClasa3.add(   new OrarOra("16:00-17:40", ""));
        oraSiClasa3.add(   new OrarOra("18:00-19:40", ""));
        oraSiClasa3.add(      new OrarOra("20:00-21:40", ""));
        ziuaSiOra.put("joi", oraSiClasa3);

        List<OrarOra> oraSiClasa4 = new ArrayList<>();
       oraSiClasa4.add(new OrarOra("08:00-09:40",""));
        oraSiClasa4.add(   new OrarOra("10:00-11:40", ""));
        oraSiClasa4.add(   new OrarOra("12:00-13:40", ""));
        oraSiClasa4.add(    new OrarOra("14:00-15:40", ""));
        oraSiClasa4.add(    new OrarOra("16:00-17:40", ""));
        oraSiClasa4.add(     new OrarOra("18:00-19:40", ""));
        oraSiClasa4.add(     new OrarOra("20:00-21:40", ""));
        ziuaSiOra.put("vineri", oraSiClasa4);

        Orar orar = new Orar(1, "TI", 2,ziuaSiOra );
     //   String uid = myDbRef.push().getKey();
        myDbRef.child(myDbRef.push().getKey()).setValue(orar);

        //update member
      //  myDbRef.child("-LR666SfXh3AY8BgaqQG").child("mdayAndClass").child("luni").child("5").child("mclass").setValue("RezMater, C, DI3, Velean_M_N");

    }

//    public String returnOrar(String Spec, int an,) {
//        DatabaseReference getOrarManager = FirebaseDatabase.getInstance().getReference("orar");
//        final int ann = an;
//
//
//        Query query = getOrarManager.orderByChild("mSpecialitate").equalTo(Spec);
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot childSnap : dataSnapshot.getChildren()) {
//                    Orar theOrar = childSnap.getValue(Orar.class);
//                    if (theOrar.mAn == ann) {
//
//                    }
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//        return "";
//    }
}
