package com.sloopy.project.ddd.lets.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sloopy.project.ddd.lets.R;

import java.util.Objects;

public class BottomDogsDialog extends BottomSheetDialogFragment {

    public static BottomDogsDialog newInstance() {
        return new BottomDogsDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_dogs_dialog, container,false);

        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}