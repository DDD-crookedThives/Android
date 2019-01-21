package com.sloopy.project.ddd.lets.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.sloopy.project.ddd.lets.R;
import com.sloopy.project.ddd.lets.util.BottomDogsDialog;
import com.sloopy.project.ddd.lets.util.BottomStartDialog;

import java.util.Objects;

public class WalkFragment extends Fragment implements OnMapReadyCallback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private CircularImageView selectedImage;
    private Button startBtn;

    public WalkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalkFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalkFragment newInstance(String param1, String param2) {
        WalkFragment fragment = new WalkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private GoogleMap mGoogleMap = null;
    private MapView mMapView = null;

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
        mMapView.getMapAsync(this);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng SEOUL = new LatLng(37.56, 126.97);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("수도");
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
