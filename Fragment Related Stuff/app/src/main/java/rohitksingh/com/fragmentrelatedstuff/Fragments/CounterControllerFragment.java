package rohitksingh.com.fragmentrelatedstuff.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rohitksingh.com.fragmentrelatedstuff.R;

public class CounterControllerFragment extends Fragment{

    private Button plus;
    private Button minus;


    public static CounterControllerFragment getInstance()
    {
        return new CounterControllerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.counter_controller_frag_layout,parent,false);

        plus = (Button)view.findViewById(R.id.plusButton);
        minus = (Button)view.findViewById(R.id.minusButton);

        return view;
    }


}
