package com.sloopy.project.ddd.lets.util;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.adapter.WalkDogListAdapter;
import com.sloopy.project.ddd.lets.view.AddActivity;

import java.util.Objects;

public class BottomDogsDialog extends BottomSheetDialogFragment {

    public static BottomDogsDialog newInstance() {
        return new BottomDogsDialog();
    }

    private ImageButton addBtn;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_dogs_dialog, container,false);

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
        

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}