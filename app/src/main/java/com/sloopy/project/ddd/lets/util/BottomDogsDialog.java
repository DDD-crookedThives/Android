package com.sloopy.project.ddd.lets.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.adapter.WalkDogListAdapter;
import com.sloopy.project.ddd.lets.data.DogData;
import com.sloopy.project.ddd.lets.data.DogResult;
import com.sloopy.project.ddd.lets.data.source.remote.ApiClient;
import com.sloopy.project.ddd.lets.data.source.remote.ApiService;
import com.sloopy.project.ddd.lets.view.AddActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class BottomDogsDialog extends BottomSheetDialogFragment {

    public static BottomDogsDialog newInstance() {
        return new BottomDogsDialog();
    }

    private ImageButton addBtn;
    private RecyclerView mRecyclerView;

    private ApiService mApiService;
    private CompositeDisposable mCompositeDisposable;
    private List<DogData> dogdata;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_dogs_dialog, container,false);

        mApiService = ApiClient.getClient().create(ApiService.class);
        mCompositeDisposable = new CompositeDisposable();
        dogdata = new ArrayList<>();

        addBtn = view.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddActivity.class));
            }
        });

        mRecyclerView = view.findViewById(R.id.bottomRecycler);
        WalkDogListAdapter mAdapter = new WalkDogListAdapter(new WalkDogListAdapter.DogClickListener() {
            @Override
            public void onDogClicked() {
                // 클릭 시 선택 및 해제

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        getDogServer();

        return view;
    }

    private void getDogServer() {

        SharedPreferences pref = getActivity().getSharedPreferences("userProfile", Context.MODE_PRIVATE);
        String userToken = pref.getString("id", "");

        mCompositeDisposable.add(
                mApiService.getDog(userToken)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<DogResult>() {
                            @Override
                            public void onSuccess(DogResult results) {
                                Log.d("강아지목록", String.valueOf(results));

                                for (int i = 0; i < results.getData().size(); i++) {
                                    Log.d("강아지이름", results.getData().get(i).getName());
                                    Log.d("강아지성별", results.getData().get(i).getGender());
                                    Log.d("강아지생일", results.getData().get(i).getBirth());

                                    getDogData();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("강아지목록 에러", e.getMessage());
                            }
                        })
        );
    }

    private void getDogData() {

        //dogdata.addAll(dogs);
        //Log.d("dogs", String.valueOf(dogs));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}