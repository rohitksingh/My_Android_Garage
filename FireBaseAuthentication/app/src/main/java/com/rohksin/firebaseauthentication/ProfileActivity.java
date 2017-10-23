package com.rohksin.firebaseauthentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * Created by Illuminati on 10/22/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView userName;
    private TextView emailId;

    private FirebaseUser user;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        user = FirebaseAuth.getInstance().getCurrentUser();

        profileImage = (ImageView)findViewById(R.id.profileImage);
        userName = (TextView)findViewById(R.id.username);
        emailId = (TextView)findViewById(R.id.emailId);


       // userName.setText("somr tandom text");

        userName.setText(user.getDisplayName());
        emailId.setText(user.getEmail());

        Picasso.with(this)
                .load(user.getPhotoUrl())
                .into(profileImage);


        Log.d("Auth provider Id",user.getProviderId());



    }


}
