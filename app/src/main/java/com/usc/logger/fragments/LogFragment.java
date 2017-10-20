package com.usc.logger.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.usc.logger.R;

import com.usc.logger.controllers.LogActivity;
import com.usc.logger.controllers.LogListActivity;
import com.usc.logger.lab.LogLab;
import com.usc.logger.models.Log;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;


public class LogFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks {

    private Log myActivities;

    private Spinner mType;
    private ImageView mImageView;
    private GoogleApiClient mClient;
    private TextView mLocationField;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button mSaveBtn,mCancelBtn,mCameraBtn;
    private EditText mTitleField,mDateField,mDestinationField,mDurationField,mCommentField;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            this.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID activityID = (UUID) getActivity().getIntent().getSerializableExtra(LogActivity.EXTRA_LOG_ID);
        myActivities = LogLab.get(getActivity()).getActivities(activityID);

        mClient = new GoogleApiClient.Builder(getActivity())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .build();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log,container,false);

        mDateField = (EditText) view.findViewById(R.id.acitivites_date);
        mTitleField = (EditText) view.findViewById(R.id.acitivites_title);
        mCommentField = (EditText) view.findViewById(R.id.acitivites_comment);
        mLocationField = (TextView) view.findViewById(R.id.acitivites_location);
        mDurationField = (EditText) view.findViewById(R.id.acitivites_duration);
        mDestinationField = (EditText) view.findViewById(R.id.acitivites_destination);

        mType = (Spinner) view.findViewById(R.id.acitivites_type);
        mImageView = (ImageView) view.findViewById(R.id.imageView);

        mLocationField.setText("Location Location...");

        mSaveBtn = (Button) view.findViewById(R.id.btn_save);
        mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);
        mCameraBtn = (Button) view.findViewById(R.id.btn_camera);

        mCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                myActivities.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                myActivities.setDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDurationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                myActivities.setDuration(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDestinationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                myActivities.setDestination(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCommentField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                myActivities.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogLab.get(getActivity()).updateLog(myActivities);
                startActivity(LogListActivity.newIntent(getActivity()));
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogLab.get(getActivity()).deleteLog(myActivities);
                startActivity(LogListActivity.newIntent(getActivity()));
            }
        });

        return view;

    }

    @Override
    public void onStart(){
        super.onStart();
        mClient.connect();
    }

    @Override
    public void onPause(){
        super.onPause();
        myActivities.setType(mType.getSelectedItem().toString());
        LogLab.get(getActivity()).updateLog(myActivities);
    }

    @Override
    public void onStop() {
        super.onStop();
        mClient.disconnect();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        LocationRequest request = new LocationRequest().create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setNumUpdates(1);
        request.setInterval(0);

        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            mLocationField.setText("No Permission");
            return;
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, request, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mLocationField.setText(location.getLatitude()+","+location.getLongitude());
                myActivities.setLocation(location.getLatitude()+","+location.getLongitude());
            }
        });


    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
