package com.sloopy.project.ddd.lets.presenter;

import android.content.SharedPreferences;
import android.util.Log;

import com.sloopy.project.ddd.lets.contract.AddContract;
import com.sloopy.project.ddd.lets.data.DogInfo;
import com.sloopy.project.ddd.lets.data.source.remote.ApiClient;
import com.sloopy.project.ddd.lets.data.source.remote.ApiService;
import com.sloopy.project.ddd.lets.util.UserPreference;
import com.sloopy.project.ddd.lets.view.AddActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.MODE_PRIVATE;

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
    public void addDogTask(final AddActivity activity, final String userToken, final String dogName, final String photo, String dogGender, final String dogBirth) {
        Log.d("dogTask", userToken + dogName + photo + dogGender + dogBirth);
        //mView.showProgress();

        String dogGenderEnum = null;

        if (dogGender.equals("암컷")) {
            dogGenderEnum = "W";
        } else if (dogGender.equals("수컷")) {
            dogGenderEnum = "M";
        }

        Log.d("dogGender", dogGenderEnum);

        final String finalDogGenderEnum = dogGenderEnum;

        mCompositeDisposable.add(
                mApiService
                        .addDog(userToken, dogName, photo, dogGenderEnum, dogBirth)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<DogInfo>() {
                            @Override
                            public void onSuccess(DogInfo dogInfo) {
                                UserPreference userPreference = new UserPreference();
                                userPreference.saveDogProfile(activity, userToken, dogName, finalDogGenderEnum, dogBirth);
                                SharedPreferences pref = activity.getSharedPreferences("dogProfile", MODE_PRIVATE);
                                String dogNamee = pref.getString("name", "");
                                Log.d("저장된 강아지", dogNamee);
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
