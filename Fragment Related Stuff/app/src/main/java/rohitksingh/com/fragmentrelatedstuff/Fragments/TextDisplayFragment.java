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

    public static TextDisplayFragment getInstance()
    {
        return new TextDisplayFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.text_display_fragment_layout,parent,false);
        textView = (TextView)view.findViewById(R.id.counter);
        return view;
    }

    public void displayText(int index)
    {
        textView.setText(index);
    }

}
