package rohksin.com.searchviewdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private Toolbar toolbar;
    private Toolbar bottomToolBar;
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private PeopleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // BOTTOM TOOLBAR

        bottomToolBar = (Toolbar)findViewById(R.id.bottomToolbar);
        bottomToolBar.inflateMenu(R.menu.bottom_menu);
        bottomToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.add)
                {
                    Toast.makeText(MainActivity.this, "Add", Toast.LENGTH_SHORT).show();
                }
                else if(item.getItemId()== R.id.filter)
                {
                    Toast.makeText(MainActivity.this, "Filter", Toast.LENGTH_SHORT).show();
                }
                else if(item.getItemId() == R.id.sort)
                {
                    Toast.makeText(MainActivity.this, "Sort", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        // Recyler View Logic

        rv = (RecyclerView)findViewById(R.id.rv);
        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        adapter = new PeopleAdapter(this, getDummyList());
        rv.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.searchBar);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search People");
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.searchBar) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public List<String> getDummyList()
    {
        List<String> list = new ArrayList<String >();
        list.add("rohit");
        list.add("Amanda");
        list.add("Triple H");
        list.add("Barack Obama");
        list.add("Kesha");
        list.add("Ganguly");
        list.add("Tomatino");
        list.add("rohit");
        list.add("Amanda");
        list.add("Triple H");
        list.add("Barack Obama");
        list.add("Kesha");
        list.add("Ganguly");
        list.add("Tomatino");
        list.add("rohit");
        list.add("Amanda");
        list.add("Triple H");
        list.add("Barack Obama");
        list.add("Kesha");
        list.add("Ganguly");
        list.add("Tomatino");

        return list;
    }

    //******************************************************************************
    //               Serach View Query Listener
    //*****************************************************************************

    @Override
    public boolean onQueryTextSubmit(String query) {

        // This method can be used when query is submitted eg. creatting search history using SQLite DB

        Toast.makeText(this, "Query Inserted", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        adapter.filter(newText);
        return true;
    }
}

//***********************************************************************************************************
//        HOW TO IMPLEMENT SEARCHVIEW IN SIMPLE STEPS ? 
//********************************************************************************************************//
//              SearchView can be implemented in 4 steps
//              a) Add searchView in menu Item using app:useActionClass = "appcompat.v7.widget......."
//              b) Setting up SerchView Hint text, listener etc
//              c) implement SearchView.OnQueryTextListener
//              d) Add method filter() in Adapter
//*********************************************************************************************************//
