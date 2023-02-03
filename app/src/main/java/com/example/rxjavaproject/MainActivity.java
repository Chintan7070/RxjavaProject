package com.example.rxjavaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.rxjavaproject.model.Datum;
import com.example.rxjavaproject.model.ModelClass;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    List<Datum> list = new ArrayList<>();
    private CompositeDisposable disposable = new CompositeDisposable();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apicalling();

    }

    private void apicalling() {


        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        disposable.add(apiInterface.doGetListResources().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnSubscribe(disposable1 -> {
                    Log.e("TAG", "rxJavaApiCalling: " + "do subscribee");
                })
                .doOnTerminate(() -> {
                    Log.e("TAG", "rxJavaApiCalling: " + "do on  terminate");
                })
                .subscribe(getNotes -> {

                    list=getNotes.getData();
                    Log.e("TAG", "onCreate:============================================== " + list.get(0).getYear());
                    Toast.makeText(MainActivity.this, "===============" + list.get(0).getYear(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(MainActivity.this,"++++"+list,Toast.LENGTH_SHORT).show();
//                    Log.e("TAG", "rxJavaApiCalling: " + "subscribee");
                }, Throwable::printStackTrace));
    }


}