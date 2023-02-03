package com.example.rxjavaproject;

import com.example.rxjavaproject.model.ModelClass;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

        @GET("/api/unknown")
        Observable<ModelClass> doGetListResources();

}
