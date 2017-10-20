package com.usc.logger.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.usc.logger.R;
import com.usc.logger.controllers.LogActivity;
import com.usc.logger.lab.LogLab;
import com.usc.logger.models.Log;

import java.util.UUID;


public class MapFragment extends Fragment {

    private Log myActivities;
    GoogleMap googleMap;
    LatLng myPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String activityID = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            activityID = bundle.getString(LogActivity.EXTRA_LOG_ID, "");
        }
        myActivities = LogLab.get(getActivity()).getActivities(UUID.fromString(activityID));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map,container,false);

        return view;

    }


}
