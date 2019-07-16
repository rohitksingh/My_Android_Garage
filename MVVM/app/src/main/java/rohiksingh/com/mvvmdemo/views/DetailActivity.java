package rohiksingh.com.mvvmdemo.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import rohiksingh.com.mvvmdemo.R;
import rohiksingh.com.mvvmdemo.models.Player;
import rohiksingh.com.mvvmdemo.viewmodels.MainActivityViewModel;

public class DetailActivity extends AppCompatActivity {

    private EditText name;
    private Button save;

    private MainActivityViewModel mainActivityViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_detail);
        name = (EditText) findViewById(R.id.name);
        save = (Button)findViewById(R.id.save);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addPlayer(new Player(name.getText().toString()));
                finish();
            }
        });



    }

}
