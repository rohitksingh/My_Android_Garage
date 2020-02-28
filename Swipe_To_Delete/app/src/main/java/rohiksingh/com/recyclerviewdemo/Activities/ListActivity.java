package rohiksingh.com.recyclerviewdemo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import rohiksingh.com.recyclerviewdemo.Adapters.PeopleListAdapter;
import rohiksingh.com.recyclerviewdemo.Callbacks.ListCallback;
import rohiksingh.com.recyclerviewdemo.Callbacks.SwipeListener;
import rohiksingh.com.recyclerviewdemo.Models.Person;
import rohiksingh.com.recyclerviewdemo.R;
import rohiksingh.com.recyclerviewdemo.Utilities.AppUtility;

public class ListActivity extends AppCompatActivity implements ListCallback{

    private RecyclerView rv;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private SwipeListener swipeListener;
    private ItemTouchHelper itemTouchHelper;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        rv = (RecyclerView)findViewById(R.id.rv);
        adapter = new PeopleListAdapter(this, getDummyList());
        swipeListener = new SwipeListener(this, (PeopleListAdapter) adapter);
        itemTouchHelper = new ItemTouchHelper(swipeListener);
        itemTouchHelper.attachToRecyclerView(rv);
        layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }



    private List<Person> getDummyList(){

        List<Person> people = new ArrayList<Person>();

        for(int i=0;i<20;i++){
            Person person = new Person();
            person.setName("Name "+i);
            people.add(person);
        }

        return people;
    }

    private void openDetail(Person people){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(AppUtility.NAME, people);
        startActivity(intent);
    }

    @Override
    public void click(Person people) {
        openDetail(people);
    }
}
