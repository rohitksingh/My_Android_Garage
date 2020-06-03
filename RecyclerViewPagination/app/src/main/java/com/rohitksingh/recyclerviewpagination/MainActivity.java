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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PaginationCallback{

    private RecyclerView rv;
    private LinearLayoutManager llm;
    private PlayerAdapter adapter;
    private List<Player> playerList = new ArrayList<>();

    private Server server = new Server();
    private int pageNum =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        adapter = new PlayerAdapter(this, playerList);
        rv.setAdapter(adapter);
        rv.setLayoutManager(llm);
        playerList = getPlayersFromServer(pageNum);
        adapter.updateData(playerList);
    }

    private List<Player> getPlayersFromServer(int pageNum){
        List<Player> newResult = server.getPlayersFromPage(pageNum);
        return newResult;
    }

    @Override
    public void callNextPage() {

        pageNum = pageNum+1;
        Toast.makeText(this, "Page "+pageNum+" Called", Toast.LENGTH_SHORT).show();
        playerList.addAll(getPlayersFromServer(pageNum));

        rv.post(new Runnable() {
            @Override
            public void run() {
                adapter.updateData(playerList);
            }
        });

    }
}