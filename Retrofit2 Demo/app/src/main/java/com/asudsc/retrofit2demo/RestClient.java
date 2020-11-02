package com.asudsc.retrofit2demo;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static final String API_BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final OkHttpClient okhttpclient = new OkHttpClient.Builder().build();
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                                            .baseUrl(API_BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.client(okhttpclient).build();

    public static <S> S createClient(Class<S> serviceclass){
        return retrofit.create(serviceclass);
    }




}
