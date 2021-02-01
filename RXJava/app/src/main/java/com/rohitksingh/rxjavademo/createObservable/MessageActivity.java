package com.rohitksingh.rxjavademo.createObservable;

import android.os.Bundle;
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
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MessageActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TextView message;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        message = findViewById(R.id.message);
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

    }

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
