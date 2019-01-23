package com.sloopy.project.ddd.lets.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DogData {

    @SerializedName("dogid")
    @Expose
    private Integer dogid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("reg_ymd")
    @Expose
    private String regYmd;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("birth")
    @Expose
    private String birth;

    public Integer getDogid() {
        return dogid;
    }

    public void setDogid(Integer dogid) {
        this.dogid = dogid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegYmd() {
        return regYmd;
    }

    public void setRegYmd(String regYmd) {
        this.regYmd = regYmd;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
}
