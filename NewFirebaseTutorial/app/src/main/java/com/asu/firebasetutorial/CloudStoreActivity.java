package com.asu.firebasetutorial;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

public class CloudStoreActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name, address;
    private Button submit;

    private static final String TAG = "CloudStoreActivity";


    private static final String PROFILE_DATABASE_NAME= "profiles";


    private static FirebaseFirestore mainDatabase;
    private static CollectionReference profileDatabase;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloudstore);

        initFirebase();

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);

    }

    private static void initFirebase(){

        mainDatabase = FirebaseFirestore.getInstance();
        profileDatabase = mainDatabase.collection(PROFILE_DATABASE_NAME);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.submit:
                savetoserver();
                break;

            default:
                break;

        }

    }

    private void savetoserver(){

        Toast.makeText(this, "Saved to DB", Toast.LENGTH_SHORT).show();
        addProduct(getPeople());

    }

    private People getPeople(){

        People people = new People();
        people.setName(name.getText().toString());
        people.setAddress(address.getText().toString());
        return people;

    }

    public static void addProduct(People people){

        profileDatabase
                .document(people.getUID())
                .set(people)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "onSuccess: ");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "onFailure: ");
                    }
                });

    }

}
