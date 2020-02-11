package com.rohitksingh.dialogdemo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements YesNoClickListner{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FireMissilesDialog fireMissilesDialog = new FireMissilesDialog(this);
        fireMissilesDialog.show(getSupportFragmentManager(),"Dialog");

    }

    @Override
    public void yesClicked() {
        Toast.makeText(this, "Yes Clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void noClicked() {
        Toast.makeText(this, "No Clicked", Toast.LENGTH_SHORT).show();
    }
}
