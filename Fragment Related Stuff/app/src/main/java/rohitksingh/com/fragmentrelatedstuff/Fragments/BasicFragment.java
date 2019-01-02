package rohitksingh.com.fragmentrelatedstuff.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public abstract class BasicFragment extends Fragment {


    public abstract View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle bundle);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle)
    {
        return provideYourFragmentView(inflater,parent,bundle);
    }


}
