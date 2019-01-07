package project.programming.elliajah.studentknows1;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CheatSearchFragment extends Fragment {
    private String myOrar;
    private String myEmail;
    private FragmentPplListener mListener;
    private Button goToChatRoom;
    private RelativeLayout persContainer1;
    private ImageView persImg1;
    private TextView namePers1;
    private TextView msgPers1;

    public interface FragmentPplListener {
        void onInputFragmentPplSent(String name);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.chat_search_fragment, container, false);
            persContainer1=view.findViewById(R.id.persContainer1);
            persImg1=view.findViewById(R.id.studMaleImage1);
            namePers1=view.findViewById(R.id.personName1);
            msgPers1=view.findViewById(R.id.lastSentMsg1);



            persContainer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mListener.onInputFragmentPplSent(namePers1.getText().toString());
                }
            });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentPplListener) {
            mListener = (FragmentPplListener) context;
        } else {
            throw new RuntimeException(context.toString() + "You need to implement FragmentPplListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void retainToConnect(String orar, String email) {

        //in case we want data from chatActivity
        myOrar = orar;
        myEmail = email;
    }
}
