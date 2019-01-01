package rohitksingh.com.fragmentrelatedstuff.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import rohitksingh.com.fragmentrelatedstuff.Listener.NextButtonListener;
import rohitksingh.com.fragmentrelatedstuff.R;

public class ValidationDialog extends Dialog {

    /*****************************************************************
     *    This is the simple demo for Dialog.
     *    This is the less prefered way of creatting dialogs
     *****************************************************************/

    private NextButtonListener listener;
    private Button button;

    public ValidationDialog(@NonNull Context context) {
        super(context);
        listener = (NextButtonListener)context;
    }

    @Override
    public void onCreate(Bundle savedInstaceState)
    {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.user_name_fragment);

        button = (Button)findViewById(R.id.confirmButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.nextButtonClicked("This is the simple dialog");
            }
        });
    }



}
