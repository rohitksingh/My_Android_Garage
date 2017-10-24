package com.rohksin.firebaseauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private SignInButton googleSignInButton;

    private LoginButton facebookLoginButton;

    /*
       Req codes for diffrent providers
     */

    private static final int GOOGLE_SIGN_IN_REQ_CODE = 9001;


    /*
    Facebook related
     */

    private CallbackManager callbackManager;


    /*
        Twitter related
     */

    private TwitterLoginButton twitterLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());


        TwitterAuthConfig twitterAuthConfig = new TwitterAuthConfig(
                getString(R.string.twitter_consumer_key),
                getString(R.string.twitter_consumer_secret)
        );

        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .twitterAuthConfig(twitterAuthConfig)
                .build();

        Twitter.initialize(twitterConfig);



        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        googleSignInButton = (SignInButton)findViewById(R.id.googleSignIn);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSignIn();
            }
        });



        /*
         Facebook Button
         */

        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton = (LoginButton)findViewById(R.id.facebookLoginButton);
        facebookLoginButton.setReadPermissions("email","public_profile");
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("FacebookAuth","Success");
                fireBaseAuthWithFacebook(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FacebookAuth","failed");
            }
        });


        /*
        Twitter related
         */

        twitterLoginButton = (TwitterLoginButton)findViewById(R.id.twitterLoginButton);
        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d("Twitter auth", "success");
                fireBaseAuthWithTwitter(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("Twitter auth", "failure");
            }
        });




    }


    @Override
    public void onStart()
    {

        super.onStart();

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null)
        {
            Log.d("Auth", "user is not null");
            openProfileActivity();
        }
        else
        {
            Log.d("Auth", "user is null");
        }
    }

    private void googleSignIn()
    {

        GoogleSignInOptions gso  = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        GoogleApiClient apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQ_CODE);
    }

    @Override
    public void onActivityResult(int reqCode, int resCode, Intent data)
    {

        callbackManager.onActivityResult(reqCode, resCode, data);

        twitterLoginButton.onActivityResult(reqCode,resCode,data);

        if(reqCode == GOOGLE_SIGN_IN_REQ_CODE)
        {

            GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if(signInResult.isSuccess())
            {

                GoogleSignInAccount googleSignInAccount = signInResult.getSignInAccount();
                fireBaseAuthWithProviderAccount(googleSignInAccount);

            }
            else
            {
                Log.d("Auth", "in resultActivity fail");
            }

        }
    }

    private void openProfileActivity()
    {
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }

    private void fireBaseAuthWithProviderAccount(GoogleSignInAccount googleSignInAccount)
    {

        AuthCredential credential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(),null);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful())
                        {
                            openProfileActivity();
                        }
                        else
                        {
                            Log.d("Auth","Firebase Auth not success "+task.getException() );
                        }

                    }
                });

    }


    private void fireBaseAuthWithFacebook(AccessToken accessToken)
    {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        currentUser = firebaseAuth.getCurrentUser();
                        Log.d("facebook user null ",(currentUser==null)+"");

                        openProfileActivity();
                    }
                });


    }

    private void fireBaseAuthWithTwitter(TwitterSession session)
    {
         AuthCredential credential = TwitterAuthProvider.getCredential(session.getAuthToken().token,session.getAuthToken().secret);

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Twitter","Success");
                        openProfileActivity();
                    }
                });

    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
