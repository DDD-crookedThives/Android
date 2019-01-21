package com.sloopy.project.ddd.lets.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.contract.MainContract;
import com.sloopy.project.ddd.lets.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity
        implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    private MainContract.Presenter mPresenter;

    private Toolbar toolbar;

    private Fragment walkFragment;
    private Fragment historyFragment;
    private Fragment rewardFragment;
    private FragmentManager mFragmentManager;
    private Fragment active;

    private CircularImageView userPhoto;
    private TextView userName, userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter();
        mPresenter.attachView(this);

        mPresenter.createGoogleClient(this);
        mPresenter.userCheck(this);

        initUI();
    }

    private void initUI() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("산책하기");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        walkFragment = new WalkFragment();
        historyFragment = new HistoryFragment();
        rewardFragment = new RewardFragment();
        mFragmentManager = getSupportFragmentManager();

        active = walkFragment;

        mFragmentManager.beginTransaction().add(R.id.mainFrame, rewardFragment).hide(rewardFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.mainFrame, historyFragment).hide(historyFragment).commit();
        mFragmentManager.beginTransaction().add(R.id.mainFrame, walkFragment).commit();

        userPhoto = navigationView.getHeaderView(0).findViewById(R.id.userPhoto);
        userName = navigationView.getHeaderView(0).findViewById(R.id.userName);
        userEmail = navigationView.getHeaderView(0).findViewById(R.id.userEmail);

        mPresenter.setUserProfile(this);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_walk:
                    toolbar.setTitle("산책하기");
                    mFragmentManager.beginTransaction().hide(active).show(walkFragment).commit();
                    active = walkFragment;
                    return true;
                case R.id.navigation_history:
                    toolbar.setTitle("기록");
                    mFragmentManager.beginTransaction().hide(active).show(historyFragment).commit();
                    active = historyFragment;
                    return true;
                case R.id.navigation_reward:
                    toolbar.setTitle("리워드");
                    mFragmentManager.beginTransaction().hide(active).show(rewardFragment).commit();
                    active = rewardFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_non_1) {

        } else if (id == R.id.nav_non_2) {

        } else if (id == R.id.nav_non_3) {

        } else if (id == R.id.nav_logout) {
            // 유저 로그아웃
            mPresenter.userLogout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void goLoginView() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void showUserProfile(String name, String email, String photo) {
        userName.setText(name);
        userEmail.setText(email);
        Glide.with(this).load(photo).into(userPhoto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
