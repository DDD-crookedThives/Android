package com.sloopy.project.ddd.lets.util;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.sloopy.project.ddd.lets.R;


public class BottomStartDialog extends BottomSheetDialogFragment {

    public static BottomStartDialog newInstance() {
        return new BottomStartDialog();
    }

    private CircularImageView userImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_start_dialog, container,false);

        userImage = view.findViewById(R.id.userImage);
        SharedPreferences pref = getContext().getSharedPreferences("userProfile", Context.MODE_PRIVATE);
        String userPhoto = pref.getString("photo", "");
        Glide.with(this).load(userPhoto).into(userImage);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}