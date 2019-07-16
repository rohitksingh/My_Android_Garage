package rohiksingh.com.mvvmdemo.views;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import rohiksingh.com.mvvmdemo.ListCallBack;
import rohiksingh.com.mvvmdemo.R;
import rohiksingh.com.mvvmdemo.adapters.PlayerListAdapter;
import rohiksingh.com.mvvmdemo.models.Player;
import rohiksingh.com.mvvmdemo.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity implements ListCallBack{

    private RecyclerView rv;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView;

    private MainActivityViewModel mainActivityViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        mainActivityViewModel.getPlayers().observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                Toast.makeText(MainActivity.this, "Data Set Changed", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });

        initRecyclerView();


    }

    private void initRecyclerView(){
        adapter = new PlayerListAdapter(this, mainActivityViewModel.getPlayers().getValue());
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }


    @Override
    public void click() {
        mainActivityViewModel.updateScore();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mainActivityViewModel.updateScore();
    }
}
