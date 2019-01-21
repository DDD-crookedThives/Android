package com.sloopy.project.ddd.lets.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.sloopy.project.ddd.lets.contract.LoginContract;
import com.sloopy.project.ddd.lets.data.UserInfo;
import com.sloopy.project.ddd.lets.data.source.TasksRepository;
import com.sloopy.project.ddd.lets.data.source.remote.ApiClient;
import com.sloopy.project.ddd.lets.data.source.remote.ApiResult;
import com.sloopy.project.ddd.lets.data.source.remote.ApiService;
import com.sloopy.project.ddd.lets.util.UserPreference;
import com.sloopy.project.ddd.lets.view.LoginActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private TasksRepository mTasksRepository;

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount googleSignInAccount;
    private static final int RC_SIGN_IN_GOOGLE = 3600;

    private ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(LoginContract.View view) {
        this.mView = view;
        mTasksRepository = TasksRepository.getInstance();
        mApiService = ApiClient.getClient().create(ApiService.class);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void createGoogleClient(Context context) {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("654900950413-adi9071mn37oaa7q8lg6b8u893ufhogf.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context);

        handleSignIn(googleSignInAccount);
    }

    @Override
    public void userGoogleLogin(LoginActivity activity) {
        mView.showProgress();
        Intent googleIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(googleIntent, RC_SIGN_IN_GOOGLE);
    }

    @Override
    public void onActivityResult(LoginActivity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode==RC_SIGN_IN_GOOGLE) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(googleSignInResult, activity);
        }
    }

    private void handleSignInResult(GoogleSignInResult googleSignInResult, final LoginActivity activity) {

        if (googleSignInResult.isSuccess()) {

            googleSignInAccount = googleSignInResult.getSignInAccount();
            // 유저정보를 받아와서 모델에 저장
            if (googleSignInAccount != null) {

                String userToken = googleSignInAccount.getIdToken();
                final String userId = googleSignInAccount.getId();
                final String userName = googleSignInAccount.getDisplayName();
                final String userEmail = googleSignInAccount.getEmail();
                final String userPhotoUrl = String.valueOf(googleSignInAccount.getPhotoUrl());

                Log.d("userToken", userToken);
                Log.d("userId", userId);
                Log.d("userName", userName);
                Log.d("userEmail", userEmail);
                Log.d("userPhotoUrl", userPhotoUrl);

                // 토큰이 길어서 토큰 대신 id(고유키)를 넘김
                mCompositeDisposable.add(
                        mApiService
                                .register(userId, userName, userEmail, userPhotoUrl)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeWith(new DisposableSingleObserver<UserInfo>() {
                                    @Override
                                    public void onSuccess(UserInfo userInfo) {
                                        // 서버에서 리턴해줘야 userInfo 사용가능? 할 것 같음.
                                        // 대신 원래의 값으로 일단 사용함.
                                        UserPreference userPreference = new UserPreference();
                                        userPreference.saveUserProfile(activity, userId, userName, userEmail, userPhotoUrl);
                                        mView.hideProgress();
                                        mView.showMainView();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.e("Disposable e", e.getMessage());
                                    }
                                })

                );
            }

        } else {

            mView.showMessage("다시 시도해 주세요.");
            mView.hideProgress();
        }
    }

    private void handleSignIn(GoogleSignInAccount googleSignInAccount) {
        if (googleSignInAccount!=null) {
            mView.showMainView();
        }
    }
}
