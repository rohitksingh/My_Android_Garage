package com.rohksin.firebaserealtimedb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

/**
 * Created by Illuminati on 10/21/2017.
 */

public class FireBaseStorageActivity extends AppCompatActivity{

    private ImageView imageToUpload;
    private Button uploadButton;
    private Button authButton;

    private FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_activity);
        imageToUpload = (ImageView)findViewById(R.id.imageToUpload);
        uploadButton = (Button)findViewById(R.id.UplaodButton);
        authButton = (Button)findViewById(R.id.authButton);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FireBaseStorageActivity.this, FireBaseAuthActivity.class));
            }
        });


    }

    public byte[] getImage()
    {

        imageToUpload.setDrawingCacheEnabled(true);
        imageToUpload.buildDrawingCache();

        Bitmap bitmap = imageToUpload.getDrawingCache();
        ByteArrayOutputStream boas = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG,100, boas);

        imageToUpload.setDrawingCacheEnabled(false);




        byte[] data = boas.toByteArray();

        return data;
    }

    public void uploadImage()
    {
        byte[] data = getImage();
        String path = "myimage/"+ UUID.randomUUID()+".png";

        StorageReference imageDir = storage.getReference(path);

        StorageMetadata metadata = new StorageMetadata.Builder()
                                            .setCustomMetadata("Caption","This is fisr image on Firebase")
                                            .build();
        uploadButton.setVisibility(View.GONE);

        final UploadTask uploadTask = imageDir.putBytes(data,metadata);

        uploadTask.addOnSuccessListener(FireBaseStorageActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploadButton.setVisibility(View.VISIBLE);
            }
        });

    }

}
