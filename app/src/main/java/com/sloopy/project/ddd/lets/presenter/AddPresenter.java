package com.sloopy.project.ddd.lets.presenter;

import android.util.Log;

import com.sloopy.project.ddd.lets.contract.AddContract;

public class AddPresenter implements AddContract.Presenter {

    private AddContract.View mView;

    @Override
    public void attachView(AddContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void addDogTask(String userToken, String dogName, String dogGender, String dogBirth) {
        Log.d("dogTask", userToken + dogName + dogGender + dogBirth);


    }
}
