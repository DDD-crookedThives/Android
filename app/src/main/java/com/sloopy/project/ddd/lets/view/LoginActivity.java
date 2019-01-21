package com.sloopy.project.ddd.lets.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.contract.LoginContract;
import com.sloopy.project.ddd.lets.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);

        mPresenter.createGoogleClient(this);

        initUI();
    }

    private void initUI() {

        progressBar = findViewById(R.id.progressBar);
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.userGoogleLogin(LoginActivity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(LoginActivity.this, requestCode, resultCode, data);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMainView() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
