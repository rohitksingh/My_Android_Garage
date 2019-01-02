package rohitksingh.com.fragmentrelatedstuff.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rohitksingh.com.fragmentrelatedstuff.R;

public class ChildFragment1 extends BasicFragment {

    private TextView textView;

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle bundle) {

        View view =inflater.inflate(R.layout.welcome_user_fragment,parent,false);
        textView = (TextView)view.findViewById(R.id.message);

        String msg = getArguments().getString("MSG");
        textView.setText(msg);

        return view;
    }

}