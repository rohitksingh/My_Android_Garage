package com.rohitksingh.recyclerviewpagination;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private LinearLayoutManager llm;
    private PlayerAdapter adapter;
    private List<Player> playerList = new ArrayList<>();

    private Server server = new Server();
    private int pageNum =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        adapter = new PlayerAdapter(this, playerList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(llm);
        getPlayersFromServer(pageNum);

    }

    private void getPlayersFromServer(int pageNum){
        List<Player> newResult = server.getPlayersFromPage(1);
        Log.d("LIST", newResult.size()+"");
        playerList.addAll(newResult);
        adapter.updateData(playerList);
    }


}