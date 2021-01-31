package com.rohitksingh.rxjavademo.flatmap;

import android.os.Bundle;

import com.rohitksingh.rxjavademo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class PostListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CompositeDisposable compositeDisposable;
    private RVAdapter adapter;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        recyclerView = findViewById(R.id.rv);
        initRV();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void initRV(){
        adapter = new RVAdapter();
        llm = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(llm);
    }
}
