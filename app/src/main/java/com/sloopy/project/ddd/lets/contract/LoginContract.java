package com.sloopy.project.ddd.lets.contract;

import android.content.Context;
import android.content.Intent;

import com.sloopy.project.ddd.lets.view.LoginActivity;

public interface LoginContract {

    interface View {

        void showProgress();

        void hideProgress();

        void showMainView();

        void showMessage(String message);
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void onStart();

        void onStop();

        void createGoogleClient(Context context);

        void userGoogleLogin(LoginActivity activity);

        void onActivityResult(LoginActivity activity, int requestCode, int resultCode, Intent data);
    }
}
