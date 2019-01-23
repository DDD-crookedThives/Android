package com.sloopy.project.ddd.lets.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.contract.AddContract;
import com.sloopy.project.ddd.lets.presenter.AddPresenter;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements AddContract.View {

    private AddContract.Presenter mPresenter;

    private ImageButton addBtn;
    private RelativeLayout dogImageLayout;
    private EditText dogName;
    private RadioGroup dogGenderGroup;
    private RadioButton dogFemale, dogMale;
    private TextView dogBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mPresenter = new AddPresenter();
        mPresenter.attachView(this);

        initUI();
    }

    private void initUI() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        addBtn = findViewById(R.id.addBtn);
        dogImageLayout = findViewById(R.id.dogImageLayout);
        dogName = findViewById(R.id.dogName);
        dogGenderGroup = findViewById(R.id.dogGenderGroup);
        dogFemale = findViewById(R.id.dogFemale);
        dogMale = findViewById(R.id.dogMale);
        dogBirth = findViewById(R.id.dogBirth);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 최종 추가 버튼 클릭 시
                String genderValue = ((RadioButton)findViewById(dogGenderGroup.getCheckedRadioButtonId())).getText().toString();
                SharedPreferences pref = getSharedPreferences("userProfile", MODE_PRIVATE);
                String userToken = pref.getString("id", "");
                String photo = "default";

                mPresenter.addDogTask(
                        AddActivity.this,
                        String.valueOf(userToken),
                        String.valueOf(dogName.getText()),
                        String.valueOf(photo),
                        String.valueOf(genderValue),
                        String.valueOf(dogBirth.getText()));

            }
        });

        dogImageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이미지 변경을 위해 클릭

            }
        });

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Context context = new ContextThemeWrapper(this, android.R.style.Theme_Holo_Light_Dialog);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // API 24 이상일 경우 시스템 기본 테마 사용
            context = this;
        }

        final DatePickerDialog datePickerDialog = new DatePickerDialog(context, dateSetListener, year, month, day);

        dogBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            @SuppressLint("DefaultLocale")
            String msg = String.format("%d.%d.%d", year, monthOfYear+1, dayOfMonth);
            dogBirth.setText(msg);

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void goHomeView() {
        startActivity(new Intent(AddActivity.this, MainActivity.class));
        finish();
    }
}
