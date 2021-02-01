package com.rohitksingh.rxjavademo.flatmap;

import android.os.Bundle;
import android.util.Log;

import com.rohitksingh.rxjavademo.R;
import com.rohitksingh.rxjavademo.flatmap.models.Comment;
import com.rohitksingh.rxjavademo.flatmap.models.Post;
import com.rohitksingh.rxjavademo.flatmap.requests.ServiceGenerator;


import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 *
 *   Fetch all posts
 *   All posts have 100 comments fetch them too
 *
 *   So 1st REST Call gets all the Posts                        /posts
 *   For each Post make another REST call for to get comments   /posts/{id}/comments
 *
 *   Chain these calls together using RXJava
 *         getPostsObservable() -->
 *
 */
public class PostListActivity extends AppCompatActivity {

    private static final String TAG = "PostListActivity";

    private RecyclerView recyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private RVAdapter adapter;
    private LinearLayoutManager llm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        recyclerView = findViewById(R.id.rv);

        initRecyclerView();

        getPostsObservable()
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Post, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(Post post) throws Exception {
                        return getCommentsObservable(post);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(Post post) {
                        updatePost(post);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private Observable<Post> getPostsObservable(){
        return ServiceGenerator.getRequestAPI()
                .getAllPost()                                               //Get all the posys
                .subscribeOn(Schedulers.io())                               //Do it in background thread
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<Post>, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(final List<Post> posts) throws Exception {
                        adapter.setPosts(posts);
                        return Observable.fromIterable(posts)
                                .subscribeOn(Schedulers.io());
                    }
                });
    }

    private void updatePost(Post post){
        adapter.updatePost(post);
    }

    private Observable<Post> getCommentsObservable(final Post post){
        return ServiceGenerator.getRequestAPI()
                .getAllComments(post.getId())
                .map(new Function<List<Comment>, Post>() {
                    @Override
                    public Post apply(List<Comment> comments) throws Exception {

                        int delay = ((new Random()).nextInt(5) + 1) * 1000; // sleep thread for x ms
                        Thread.sleep(delay);
                        Log.d(TAG, "apply: sleeping thread " + Thread.currentThread().getName() + " for " + String.valueOf(delay)+ "ms");

                        post.setComments(comments);
                        return post;
                    }
                })
                .subscribeOn(Schedulers.io());

    }

    private void initRecyclerView(){
        adapter = new RVAdapter();
        llm = new LinearLayoutManager(this);
        Log.d(TAG, "initRecyclerView: "+(recyclerView==null));
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
