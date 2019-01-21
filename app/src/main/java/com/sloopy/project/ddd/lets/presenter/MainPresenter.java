package com.sloopy.project.ddd.lets.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.sloopy.project.ddd.lets.contract.MainContract;
import com.sloopy.project.ddd.lets.view.MainActivity;

public class MainPresenter implements MainContract.Presenter, GoogleApiClient.OnConnectionFailedListener {

    private MainContract.View mView;

    private GoogleSignInAccount googleSignInAccount;
    private GoogleApiClient googleApiClient;

    private SharedPreferences pref;

    @Override
    public void attachView(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onStart() {
        googleApiClient.connect();
    }

    @Override
    public void createGoogleClient(MainActivity activity) {
        googleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API)
                .build();
    }

    @Override
    public void userCheck(Context context) {
        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(context);
        if (googleSignInAccount==null) {
            mView.goLoginView();
        } else {
            Log.d("userToken", googleSignInAccount.getIdToken());
            Log.d("userName", googleSignInAccount.getDisplayName());
        }
    }

    @Override
    public void userLogout() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                mView.goLoginView();
            }
        });
    }

    @Override
    public void setUserProfile(Context context) {
        pref = context.getSharedPreferences("userProfile", Context.MODE_PRIVATE);
        String name = pref.getString("name", "userName");
        String email = pref.getString("email", "userEmail");
        String photo = pref.getString("photo", "");
        mView.showUserProfile(name, email, photo);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // 구글 클라이언트 빌드 실패
    }
}
