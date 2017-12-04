package rohksin.com.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Illuminati on 11/28/2017.
 */

public class MyFragment extends Fragment {


    private static String text1;

    public static MyFragment createInstance(String text)
    {
        text1 = text;
        MyFragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle)
    {
        super.onCreateView(inflater,parent,bundle);

        View view = inflater.inflate(R.layout.fragment,parent,false);
        TextView textView = (TextView)view.findViewById(R.id.fragmentText);
        textView.setText(text1);
        return view;
    }
}
