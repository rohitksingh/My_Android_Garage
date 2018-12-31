package rohitksingh.com.fragmentrelatedstuff.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import rohitksingh.com.fragmentrelatedstuff.NextButtonListener;
import rohitksingh.com.fragmentrelatedstuff.R;

public class UserNameFragment extends Fragment {

    private EditText username;
    private EditText password;
    private Button next;

    private NextButtonListener listener;


    public static UserNameFragment newInstance()
    {
        UserNameFragment fragment = new UserNameFragment();

        Bundle args = new Bundle();
        args.putString("Key","Username Fragment String");
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        listener = (NextButtonListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle)
    {
        View view = inflater.inflate(R.layout.user_name_fragment,parent,false);

        username = (EditText)view.findViewById(R.id.username);
        password = (EditText)view.findViewById(R.id.password);
        next = (Button)view.findViewById(R.id.confirmButton);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.nextButtonClicked(username.getText().toString());
            }
        });

        Log.d("Orientation", "onCreateView");

        return view;
    }

    // Saving state while orientation change

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putString("USER_NAME",username.getText().toString());
        outState.putString("PASSWORD",password.getText().toString());
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null)
        {
            String usern= savedInstanceState.getString("USER_NAME");

            Log.d("Orientation", usern);
            username.setText("Billa");
            username.setText(savedInstanceState.getString("USER_NAME"));
            password.setText(savedInstanceState.getString("PASSWORD"));
        }
    }

}
