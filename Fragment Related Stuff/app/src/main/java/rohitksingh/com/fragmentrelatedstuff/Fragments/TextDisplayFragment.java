package rohitksingh.com.fragmentrelatedstuff.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rohitksingh.com.fragmentrelatedstuff.R;

public class TextDisplayFragment extends Fragment {


    private TextView textView;
    private int counter;

    public static TextDisplayFragment getInstance()
    {
        return new TextDisplayFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.text_display_fragment_layout,parent,false);
        textView = (TextView)view.findViewById(R.id.counter);
        if(savedInstanceState!=null)
        {
            counter = savedInstanceState.getInt("COUNTER_VALUE");
        }
        textView.setText(counter+"");
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putInt("COUNTER_VALUE",counter);
    }


    public void increment()
    {
        counter = counter+1;
        textView.setText(counter+"");
    }

    public void decrement()
    {
        counter = counter-1;
        textView.setText(counter+"");
    }

}
