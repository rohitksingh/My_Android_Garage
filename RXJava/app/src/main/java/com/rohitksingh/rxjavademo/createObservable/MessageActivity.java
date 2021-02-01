package com.rohitksingh.rxjavademo.createObservable;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rohitksingh.rxjavademo.R;

import java.util.Arrays;
import java.util.List;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MessageActivity extends AppCompatActivity {

    private static final String TAG = "MessageActivity";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TextView message, apicall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        message = findViewById(R.id.message);
        apicall = findViewById(R.id.apicall);

        startInstructions()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        message.setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        getAPICallObservable()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Throwable {
                        apicall.setText("API call"+integer);
                    }
                });

    }

    /*************************************************************************************
     *     This method uses create() operator to create Observable
     *     map() operator should be called before scheduleOn and observeOn()
     *     This is the flow
     *          - Observable.create()               ->Takes ObservableOnSubscribe
     *                      .map()                  ->Takes Function<String, String>
     *                      .subscribeOn()          -> Schedulers.io
     *                      .observeOn()            -> AndroidSchedulers.mainThread
     */
    public Observable<String> startInstructions(){

        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                for(String instruction : getInstructionList()){
                    if(!emitter.isDisposed()){
                        emitter.onNext(instruction);
                    }
                }

                if(!emitter.isDisposed()){
                    emitter.onComplete();
                }
            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Throwable {
                Thread.sleep(2000);
                return s;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * This function uses range funtion to create Observable
     * You can use it in conjunction with map oprator to do some useful task
     * For eg> Checking an API every 1 sec
     * range(1,10) -> It means it will have a stream of 10 events
     * @return
     */

    public @NonNull Observable<Integer> getAPICallObservable(){
        return Observable.range(1,10)
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Throwable {
                        Thread.sleep(500);
                        Log.d(TAG, "apply: making API call again");
                        return integer;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private List<String> getInstructionList(){
        return Arrays.asList("Brethe in", "Breathe out",
                "Focus", "Stare at the screen",
                "Stop", "Relax");
    }
}
