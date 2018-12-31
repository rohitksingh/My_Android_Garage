package rohitksingh.com.fragmentrelatedstuff.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import rohitksingh.com.fragmentrelatedstuff.NextButtonListener;
import rohitksingh.com.fragmentrelatedstuff.R;

public class ValidationFrgamentDialog extends DialogFragment {

    private NextButtonListener listener;
    private Button button;

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

        getDialog().setCancelable(true);
        getDialog().setTitle("UserName");

        Button button = (Button)view.findViewById(R.id.confirmButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.nextButtonClicked("This message from Fragment Dialog");
            }
        });

        return view;
    }


}
