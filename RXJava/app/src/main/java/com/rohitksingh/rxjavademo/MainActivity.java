package com.rohitksingh.rxjavademo;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 *    Step 1:  Create Observable
 *
 *    Step 2: Observe Observable  using Consumer , Observer
 *    Step 3: Dispose Onserver in onDestroy() method;
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView textView;
    private SeekBar seekBar;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        seekBar = findViewById(R.id.seekbar);

        //This line taks List of Task and converts it into Observable;
        Observable<Task> taskObservable = Observable
                .fromIterable(DataSource.getAllTaskList())
                .subscribeOn(Schedulers.io())
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Throwable {
                        Log.d(TAG, "onNext: "+Thread.currentThread().getName());
                        Log.d(TAG, "onNext: "+task.description);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return task.isComplete;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposables.add(d);
                Log.d(TAG, "onSubscribe: called");
            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG, "onNext: "+Thread.currentThread().getName());
                Log.d(TAG, "onNext: "+task.description);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        });

        //Another way to subscribe and Dispose the Observable
//        disposables.add(taskObservable.subscribe(new Consumer<Task>() {
//            @Override
//            public void accept(Task task) throws Throwable {
//
//            }
//        }));



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}