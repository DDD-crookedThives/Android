package com.sloopy.project.ddd.lets.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.data.DogInfo;

import java.util.ArrayList;
import java.util.List;

public class WalkDogListAdapter extends RecyclerView.Adapter<WalkDogListAdapter.ViewHolder> {

    private List<DogInfo> dogsList;
    private DogClickListener mDogClickListener;

    public WalkDogListAdapter(DogClickListener dogClickListener) {
        this.dogsList = new ArrayList<>();
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return dogsList.size();
    }

    public interface DogClickListener {
        void onDogClicked();
    }
}
