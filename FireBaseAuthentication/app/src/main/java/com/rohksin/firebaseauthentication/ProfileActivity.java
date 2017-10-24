package com.rohksin.firebaseauthentication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
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



        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("facebook","share");
                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
                        .build();

                shareLink(shareLinkContent);



            }
        });



        Log.d("Auth provider Id",user.getProviderId());



    }


    public void shareLink(ShareLinkContent shareLinkContent)
    {
        ShareDialog shareDialog = new ShareDialog(this);
        shareDialog.show(shareLinkContent);
    }


}
