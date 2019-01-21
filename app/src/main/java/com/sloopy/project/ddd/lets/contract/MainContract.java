package com.sloopy.project.ddd.lets.contract;

import android.content.Context;

import com.sloopy.project.ddd.lets.view.MainActivity;

public interface MainContract {

    interface View {

        void goLoginView();

        void showUserProfile(String name, String email, String photo);
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void onStart();

        void createGoogleClient(MainActivity activity);

        void userCheck(Context context);

        void userLogout();

        void setUserProfile(Context context);
    }
}
