package rohitksingh.com.vibrationdemo;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button vibrationButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vibrationButton = (Button)findViewById(R.id.vibrationButton);
        vibrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 normalVibrarte();
            }
        });
    }


    private void normalVibrarte()
    {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if(Build.VERSION.SDK_INT>=26)
        VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE);
        else
            Toast.makeText(this, Build.VERSION.SDK_INT+"", Toast.LENGTH_SHORT).show();
    }
}
