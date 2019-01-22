package com.sloopy.project.ddd.lets.contract;

public interface AddContract {

    interface View {

        void showProgress();

        void hideProgress();

        void goHomeView();
    }

    interface Presenter {

        void attachView(View view);

        void detachView();

        void addDogTask(String userToken, String photo, String dogName, String dogGender, String dogBirth);
    }

}
