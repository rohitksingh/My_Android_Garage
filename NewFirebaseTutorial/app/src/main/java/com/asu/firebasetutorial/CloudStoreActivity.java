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


/*************************************************************************************************
 *
 *       Adding an entry in Firebase CloudStorage
 *       1) FirebaseCloudStorage is different from FirebaseRealTime Database
 *       2) Here I am using Firebase CloudStorage
 *
 *          How to get Started?
 *          Connect to Firebase Console -> Use Android Studio for this, Its easy
 *          Add dependency for FireStorage  -> eg: implementation 'com.google.firebase:firebase-firestore:21.1.1'
 *          It has 2 main classes
 *              FirebaseFirestore   - Which is the main Repository
 *              CollectionReference - Which is child repository
 *
 *              Adding data to Firebase CloudStorage -> addProfile()
 *              Reading list of Data
 *
 *
 *             https://github.com/rohitksingh/FBN-Admin/blob/master/app/src/main/java/admin/fbn/com/fbnadmin/AppUtility.java to see add and update
 *
 *
 ************************************************************************************************/


public class CloudStoreActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name, address;
    private Button submit, read;

    private static final String TAG = "CloudStoreActivity";


    private static final String PROFILE_DATABASE_NAME= "profiles";


    private static FirebaseFirestore mainDatabase;
    private static CollectionReference profileDatabase;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloudstore);
        initFirebaseCloudStorage();
        setUpUI();

    }

    /***********************************************************************************************
     *                                  Interface methods
     ***********************************************************************************************/
    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.submit:
                addToServer();
                break;

            default:
                break;

        }

    }


    /***********************************************************************************************
     *                              Init, Write, Read methods
     ***********************************************************************************************/

    private static void initFirebaseCloudStorage(){

        mainDatabase = FirebaseFirestore.getInstance();
        profileDatabase = mainDatabase.collection(PROFILE_DATABASE_NAME);

    }

    private void addPeople(People people){

        profileDatabase
                .document(people.getUID())
                .set(people)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CloudStoreActivity.this, "Added to Server", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "onFailure: ");
                        Toast.makeText(CloudStoreActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void getAllPeople(){

    }



    /***********************************************************************************************
     *                                       Private methods
     ***********************************************************************************************/

    private void setUpUI(){
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        read = findViewById(R.id.read);
        read.setOnClickListener(this);

    }

    private void addToServer(){
        addPeople(getPeople());
    }

    private People getPeople(){

        People people = new People();
        people.setName(name.getText().toString());
        people.setAddress(address.getText().toString());
        return people;

    }

    // Adding a entry to CloudStorage


}
