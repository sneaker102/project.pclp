package project.programming.elliajah.studentknows1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatRoomFragment extends Fragment {
    private String roomKy;
   private String myName;
    private ImageView sendMsg;
    private FragmentChatListener mListener;
    private LinearLayout msgContainer;
    private EditText inputText;
    private MessageBean showedMsg;
    private boolean isFirstTime;

    public interface FragmentChatListener {
        void onInputFragmentChatSent(Boolean goToPplRoom, Boolean goToWelcomeActivity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.chat_room_fragment, container, false);
        sendMsg = view.findViewById(R.id.sendImg);
        inputText = view.findViewById(R.id.inputText);
        isFirstTime = true;
        showedMsg = new MessageBean("","","1");
        msgContainer = view.findViewById(R.id.msgsContainer);
        DatabaseReference dbRef;
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("chat_rooms").child(roomKy);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot x : dataSnapshot.getChildren()) {
                    MessageBean someMsg = x.getValue(MessageBean.class);

                    if (someMsg != null && Float.valueOf(showedMsg.getHour()) < Float.valueOf(someMsg.getHour())) {

                        if (!someMsg.getPossersorName().contains("Ilie")) {
                            TextView gotMsgView = new EditText(getActivity());
                            gotMsgView.setBackgroundColor(Color.parseColor("#A4A4A4"));
                            gotMsgView.setTextColor(Color.parseColor("#FAFAFA"));
                            LinearLayout.LayoutParams gotMsgParams = new LinearLayout.LayoutParams(190, ViewGroup.LayoutParams.WRAP_CONTENT);
                            gotMsgParams.setMargins(8, 20, 0, 0);
                            gotMsgView.setLayoutParams(gotMsgParams);
                            gotMsgView.setPadding(8, 0, 0, 0);
                            gotMsgView.setText("Savulescu Cristian : \n\t" + someMsg.getMsg());
                            msgContainer.addView(gotMsgView);
                            showedMsg = someMsg;
                        } else {
                            TextView gotMsgView = new EditText(getActivity());
                            gotMsgView.setBackgroundColor(Color.parseColor("#0084ff"));
                            gotMsgView.setTextColor(Color.parseColor("#FAFAFA"));
                            LinearLayout.LayoutParams gotMsgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                           gotMsgParams.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                            gotMsgParams.setMargins(530, 20, 0, 0);
                            gotMsgView.setLayoutParams(gotMsgParams);
                            gotMsgView.setPadding(15, 8, 0, 0);
                            gotMsgView.setText("You : \n\t" + someMsg.getMsg());
                            msgContainer.addView(gotMsgView);
                            showedMsg = someMsg;
                        }

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageBean sendMsg = new MessageBean();
                sendMsg.setPossersorName("Ilie");
                sendMsg.setMsg(inputText.getText().toString());
                sendMsg.setHour(String.valueOf(System.currentTimeMillis()));

                DatabaseReference dbRef1;
                FirebaseDatabase db1 = FirebaseDatabase.getInstance();
                dbRef1 = db1.getReference("chat_rooms");
                dbRef1.child(roomKy).push().setValue(sendMsg);


            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentChatListener) {
            mListener = (FragmentChatListener) context;
        } else {
            throw new RuntimeException(context.toString() + "You need to implement FragmentPplListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void retainToConnect(String roomKey,String name) {
        roomKy = roomKey;
        myName = name;
    }
}
