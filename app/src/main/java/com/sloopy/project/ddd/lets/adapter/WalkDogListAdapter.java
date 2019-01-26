package com.sloopy.project.ddd.lets.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.data.DogResult;

import java.util.ArrayList;

public class WalkDogListAdapter extends RecyclerView.Adapter<WalkDogListAdapter.ViewHolder> {

    private static final String TAG = "WalkDogListAdapter";

    private ArrayList<DogResult> dogsList;
    private DogClickListener mDogClickListener;

    public WalkDogListAdapter(ArrayList<DogResult> dogsList, DogClickListener dogClickListener) {
        this.dogsList = dogsList;
        mDogClickListener = dogClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView dogName;
        private View picker;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dogName = itemView.findViewById(R.id.dogName);
            picker = itemView.findViewById(R.id.picker);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bottom_dialog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "onBindViewHolder");
        DogResult mDogResult = dogsList.get(position);

        Log.d("어뎁터 값", mDogResult.getData().get(position).getName());
        holder.dogName.setText(mDogResult.getData().get(position).getName());

        holder.picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDogClickListener.onDogClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    public interface DogClickListener {
        void onDogClicked(int position);
    }
}
