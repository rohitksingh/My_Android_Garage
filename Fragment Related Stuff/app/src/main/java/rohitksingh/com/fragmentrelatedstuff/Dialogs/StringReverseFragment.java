package rohitksingh.com.fragmentrelatedstuff.Dialogs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import rohitksingh.com.fragmentrelatedstuff.R;

public class StringReverseFragment extends Fragment {


    private String message;
    private TextView textView;
    private Button button;


    public static StringReverseFragment getInstance(String name){

        StringReverseFragment fragment = new StringReverseFragment();
        Bundle args = new Bundle();
        args.putString("REVERSE_STRING",name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.reverse_fragment_layout,parent,false);

        message = getArguments().getString("REVERSE_STRING");

        textView = (TextView)view.findViewById(R.id.reverseString);
        button = (Button)view.findViewById(R.id.reverseButton);
        textView.setText(message);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reverse();
            }
        });


        // Getting back the stored data
        if(savedInstanceState!=null)
        {
            textView.setText(savedInstanceState.getString("REVERSE_STRING_KEY"));
        }

        return view;
    }


    /************************************
     *    Saving State
     ***********************************/

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putString("REVERSE_STRING_KEY",textView.getText().toString());
    }


    /*************************************
     *   Fragment related methods
     ************************************/

    public void reverse()
    {
        int length = message.length();
        char[] reverseString = new char[length];

        for(int i = 0;i<length;i++)
        {
            reverseString[i] = message.charAt(length-1-i);
        }

        message = new String(reverseString);
        textView.setText(message);
    }


}
