package com.sloopy.project.ddd.lets.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sloopy.project.ddd.lets.data.source.remote.ApiResult;

public class UserInfo extends ApiResult {

    @SerializedName("token")
    @Expose
    private String userToken;
    @SerializedName("id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String userName;
    @SerializedName("email")
    @Expose
    private String userEmail;
    @SerializedName("photo")
    @Expose
    private String userPhoto;

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    @NonNull
    @Override
    public String toString() {
        return "Post{" +
                "userToken='" + userToken + '\'' +
                ", userId='" + userId + '\'' +
                ", userName=" + userName +
                ", userEmail=" + userEmail +
                ", userPhoto=" + userPhoto +
                '}';
    }
}
