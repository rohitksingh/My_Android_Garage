package com.rohksin.firebaserealtimedb;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by Illuminati on 10/21/2017.
 */

public class FireBaseAuthActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signInButton;
    private Button signOut;

    private FirebaseAuth fireBaseAuth;
    private GoogleApiClient apiClient;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);


        fireBaseAuth = FirebaseAuth.getInstance();

        signInButton =(SignInButton)findViewById(R.id.signIn);
        signOut = (Button)findViewById(R.id.signOut);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }

    @Override
    public void onStart()
    {

        super.onStart();

        FirebaseUser currentUser = fireBaseAuth.getCurrentUser();
        if(currentUser!=null)
        {
            Log.d("Auth","User is not null");
            openProfileActivity();
        }
        else
        {
            Log.d("Auth","User is null");
        }
    }

    public void signIn()
    {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                          .requestIdToken(getString(R.string.default_web_client_id))
                                          .requestEmail()
                                          .build();

        apiClient = new GoogleApiClient.Builder(this)
                      .enableAutoManage(this,this)
                      .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                      .build();

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(signInIntent, 9001);
    }

    public void signOut()
    {

    }


    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data)
    {
        if(reqCode==9001)
        {
            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(signInResult.isSuccess())
            {

                GoogleSignInAccount signInAccount = signInResult.getSignInAccount();

                fireBaseAuthWithGoogle(signInAccount);


            }
            else
            {
                Log.d("Auth","Authentication failed");
            }
        }
    }



    public void fireBaseAuthWithGoogle(GoogleSignInAccount account){

        Log.d("Auth:ID" , account.getId() +"");
        Log.d("Auth:Token" , account.getIdToken() +"");


        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);

        fireBaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful())
                        {
                            Log.d("Auth","Firebase Auth success");

                            FirebaseUser currentUser = fireBaseAuth.getCurrentUser();
                        }
                        else
                        {
                            Log.d("Auth","Firebase Auth not success");
                        }

                    }
                });

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }



    public void openProfileActivity()
    {


        Intent i = new Intent(FireBaseAuthActivity.this, ProfileActivity.class);
        //i.putExtra("CurrentUser",currentUser);

        startActivity(i);

    }
}
