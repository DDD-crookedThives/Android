package com.sloopy.project.ddd.lets.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.util.BottomDogsDialog;
import com.sloopy.project.ddd.lets.util.BottomStartDialog;

import java.util.Objects;

public class WalkFragment extends Fragment implements OnMapReadyCallback {

    private CircularImageView selectedImage;
    private Button startBtn;

    public WalkFragment() {
        // Required empty public constructor
    }

    private GoogleMap mGoogleMap;
    private MapView mMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walk, container, false);

        selectedImage = view.findViewById(R.id.selectedImage);
        selectedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomDogsDialog bottomDogsDialog = BottomDogsDialog.newInstance();
                bottomDogsDialog.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "bottom_dog_fragment");
            }
        });

        startBtn = view.findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomStartDialog bottomStartDialog = BottomStartDialog.newInstance();
                bottomStartDialog.show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), "bottom_start_fragment");
            }
        });

        mMapView = view.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.mGoogleMap = googleMap;

        MapsInitializer.initialize(Objects.requireNonNull(getContext()));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.5542593, 127.0106177), 14);
        googleMap.animateCamera(cameraUpdate);
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
    }
}