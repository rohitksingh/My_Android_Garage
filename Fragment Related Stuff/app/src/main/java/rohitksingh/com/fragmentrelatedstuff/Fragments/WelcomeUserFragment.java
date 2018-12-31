package rohitksingh.com.fragmentrelatedstuff.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import rohitksingh.com.fragmentrelatedstuff.R;

public class WelcomeUserFragment extends Fragment {

    private TextView message;
    private static String fragmentMessage;


    public static WelcomeUserFragment newInstance(String msg)
    {
        WelcomeUserFragment fragment = new WelcomeUserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragmentMessage = msg;
        return fragment;
    }

    /**********************************************************
     *   Callback methods
     *********************************************************/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle)
    {
        Log.d("Callback","onCreateView");
        View view = inflater.inflate(R.layout.welcome_user_fragment,parent,false);
        message = (TextView)view.findViewById(R.id.message);
        setMessage(fragmentMessage);
        return view;
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Log.d("Callback","onAttach");
    }


    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Log.d("Callback","onCreate");
    }


    @Override
    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        Log.d("Callback","onActivityCreated");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d("Callback","onStart");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("Callback","onResume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("Callback","onPause");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d("Callback","onStop");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d("Callback","onDestroy");
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        Log.d("Callback","onDestroyView");
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.d("Callback","onDetach");
    }

    /*********************************************************
     *     Private methods
     *********************************************************/

    private void setMessage(String msg)
    {
        message.setText(msg);
    }

}
