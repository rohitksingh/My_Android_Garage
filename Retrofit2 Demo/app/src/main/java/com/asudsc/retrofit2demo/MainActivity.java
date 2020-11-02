package com.asudsc.retrofit2demo;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 *     The GsonConvertor dependency is not included with 'com.squareup.retrofit2:retrofit:2.7.1'
 *     Add 'com.squareup.retrofit2:converter-gson:2.7.1' too.
 */
public class MainActivity extends AppCompatActivity implements Callback{

    private PlaceHolderClient restClient;
    private Call<List<Post>> fetchAllPosts;
    private Call<Post> fetchAPost;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if(restClient==null)
            restClient = createRestClient();

        //Request to get all the posts
        //getAllPosts();

        getPost(2);



    }

    private void getAllPosts(){
        fetchAllPosts = restClient.getAllPosts();
        fetchAllPosts.enqueue(this);
    }

    private void getPost(int postNum){
        fetchAPost = restClient.getPost(postNum);
        fetchAPost.enqueue(this);
    }

    //Create Retrofit client = BaseUrl + Convertor + OKhttpClient
    public PlaceHolderClient createRestClient(){

        String API_BASE_URL = "https://jsonplaceholder.typicode.com/";
        OkHttpClient okhttpclient = new OkHttpClient.Builder().build();

        Retrofit.Builder retrofitBuilder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.client(okhttpclient).build();

        PlaceHolderClient restClient = retrofit.create(PlaceHolderClient.class);

        return restClient;

    }


    @Override
    public void onResponse(Call call, Response response) {

        if(call==fetchAllPosts){
            Log.d(TAG, response.code()+"");
            List<Post> posts = (List<Post>)response.body();
            for(Post post:posts){
                Log.d(TAG, post.toString());
            }
        }else if(call==fetchAPost){
            Post post = (Post)response.body();
            Log.d(TAG, post.toString());
        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        Toast.makeText(this, "Unable to fetch", Toast.LENGTH_SHORT).show();
    }
}