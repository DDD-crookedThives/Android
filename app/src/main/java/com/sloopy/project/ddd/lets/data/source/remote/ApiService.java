package com.sloopy.project.ddd.lets.data.source.remote;

import com.sloopy.project.ddd.lets.data.DogInfo;
import com.sloopy.project.ddd.lets.data.UserInfo;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    //http://13.125.93.83:8080/login?token=test4&email=test4@naver.com&name=test4
    // 유저 로그인
    @GET("login")
    Single<UserInfo> register(
            @Query("token") String userToken,
            @Query("name") String userName,
            @Query("email") String userEmail,
            @Query("photo") String userPhoto
    );

    @POST("dogs/token")
    Single<DogInfo> addDog(
            @Query("token") String userToken,
            @Query("name") String name,
            @Query("photo") String photo,
            @Query("gender") String gender,
            @Query("birth") String birth
    );

}
