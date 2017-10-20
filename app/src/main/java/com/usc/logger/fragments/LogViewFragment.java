package com.usc.logger.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.usc.logger.R;
import com.usc.logger.controllers.LogActivity;
import com.usc.logger.controllers.LogListActivity;
import com.usc.logger.lab.LogLab;
import com.usc.logger.models.Log;

import java.util.UUID;


public class LogViewFragment extends Fragment {

    private Log myActivities;

    private TextView mTitleField,mDateField,mDestinationField,mDurationField,mCommentField,mLocationField,mTypeField;
    private ImageView mImageView;
    private Button mCancelBtn,mMapBtn;

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
        View view = inflater.inflate(R.layout.fragment_log_view,container,false);

        mDateField = (TextView) view.findViewById(R.id.date);
        mTypeField = (TextView) view.findViewById(R.id.type);
        mTitleField = (TextView) view.findViewById(R.id.title);
        mCommentField = (TextView) view.findViewById(R.id.comment);
        mDurationField = (TextView) view.findViewById(R.id.duration);
        mLocationField = (TextView) view.findViewById(R.id.location);
        mDestinationField = (TextView) view.findViewById(R.id.destination);

        mDateField.setText(myActivities.getDate());
        mTypeField.setText(myActivities.getType());
        mTitleField.setText(myActivities.getTitle());
        mCommentField.setText(myActivities.getComment());
        mDurationField.setText(myActivities.getDuration());
        mLocationField.setText(myActivities.getLocation());
        mDestinationField.setText(myActivities.getDestination());

        mMapBtn = (Button) view.findViewById(R.id.btn_map);
        mCancelBtn = (Button) view.findViewById(R.id.btn_cancel);
        mImageView = (ImageView) view.findViewById(R.id.imageView);

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogLab.get(getActivity()).deleteLog(myActivities);
                startActivity(LogListActivity.newIntent(getActivity()));
            }
        });

        mMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString(LogActivity.EXTRA_LOG_ID, myActivities.getId().toString());

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                LogViewFragment myFragment = new LogViewFragment();

                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, myFragment)
                        .addToBackStack(null).commit();

            }
        });

        return view;

    }

}
