package com.sloopy.project.ddd.lets.contract;

import com.sloopy.project.ddd.lets.view.AddActivity;

public interface AddContract {

    interface View {

        void showProgress();

        void hideProgress();

        void goHomeView();
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void addDogTask(AddActivity activity, String userToken, String photo, String dogName, String dogGender, String dogBirth);
    }

}
