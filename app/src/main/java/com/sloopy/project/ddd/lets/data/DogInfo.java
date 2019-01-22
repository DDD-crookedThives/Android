package com.sloopy.project.ddd.lets.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sloopy.project.ddd.lets.data.source.remote.ApiResult;

public class DogInfo extends ApiResult {

    // token(필수값), name, photo, gender, birth
    @SerializedName("token")
    @Expose
    private String userToken;
    @SerializedName("name")
    @Expose
    private String dogName;
    @SerializedName("photo")
    @Expose
    private String dogPhoto;
    @SerializedName("gender")
    @Expose
    private String dogGender;
    @SerializedName("birth")
    @Expose
    private String dogBirth;

    public DogInfo(String userToken, String dogName, String dogPhoto, String dogGender, String dogBirth) {
        this.userToken = userToken;
        this.dogName = dogName;
        this.dogPhoto = dogPhoto;
        this.dogGender = dogGender;
        this.dogBirth = dogBirth;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogPhoto() {
        return dogPhoto;
    }

    public void setDogPhoto(String dogPhoto) {
        this.dogPhoto = dogPhoto;
    }

    public String getDogGender() {
        return dogGender;
    }

    public void setDogGender(String dogGender) {
        this.dogGender = dogGender;
    }

    public String getDogBirth() {
        return dogBirth;
    }

    public void setDogBirth(String dogBirth) {
        this.dogBirth = dogBirth;
    }
}
