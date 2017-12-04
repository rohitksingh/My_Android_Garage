package rohksin.com.sqlitedatabasedemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(llm);
        PeopleAdapter adapter = new PeopleAdapter(MainActivity.this,dummyList());
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
            }
        });
    }



    public List<People> dummyList()
    {
        List<People> people = new ArrayList<People>();

        People rohit = new People();
        rohit.setFname("Rohit");
        rohit.setLname("Singh");
        people.add(rohit);

        return people;

    }

    public void add(People people)
    {

    }

    public void delete(People people)
    {

    }

    public void update(People people)
    {

    }

    private void createDialog()
    {
        Dialog dialog = new AddDialog(MainActivity.this);
        dialog.show();
    }


    private class AddDialog extends Dialog{

        private Button addButton;
        private Button cancelButton;

        public AddDialog(@NonNull Context context) {
            super(context);
        }

        @Override
        public void onCreate(Bundle savedInsanceState)
        {
            super.onCreate(savedInsanceState);
            setContentView(R.layout.add_dialog);

            cancelButton = (Button)findViewById(R.id.cancel);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
    }


}
