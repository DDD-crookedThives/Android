package com.sloopy.project.ddd.lets.presenter;

import android.util.Log;

import com.sloopy.project.ddd.lets.contract.AddContract;
import com.sloopy.project.ddd.lets.data.DogInfo;
import com.sloopy.project.ddd.lets.data.source.remote.ApiClient;
import com.sloopy.project.ddd.lets.data.source.remote.ApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class AddPresenter implements AddContract.Presenter {

    private AddContract.View mView;
    private ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(AddContract.View view) {
        this.mView = view;
        mApiService = ApiClient.getClient().create(ApiService.class);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void addDogTask(String userToken, String dogName, String photo, String dogGender, String dogBirth) {
        Log.d("dogTask", userToken + dogName + photo + dogGender + dogBirth);
        //mView.showProgress();

        mCompositeDisposable.add(
                mApiService
                        .addDog(userToken, dogName, photo, dogGender, dogBirth)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<DogInfo>() {
                            @Override
                            public void onSuccess(DogInfo dogInfo) {
                                //mView.hideProgress();
                                mView.goHomeView();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("add dog error", e.getMessage());
                            }
                        })
        );
    }
}
