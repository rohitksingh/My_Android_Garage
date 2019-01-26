package rohitksingh.com.doubletaplistener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rohitksingh.com.doubletaplistener.DoubleTapListener.DoubleTapCallback;
import rohitksingh.com.doubletaplistener.DoubleTapListener.DoubleTapListener;

public class MainActivity extends AppCompatActivity implements DoubleTapCallback{

    private RelativeLayout layout;
    private Button button;
    private TextView textView;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        button   = (Button)findViewById(R.id.button);
        layout   = (RelativeLayout)findViewById(R.id.mainLayout);

        layout.setOnClickListener(new DoubleTapListener(this));
        button.setOnClickListener(new DoubleTapListener(this));


    }

    @Override
    public void onDoubleClick(View v) {

        counter++;
        textView.setText(counter+"");
    }
}
