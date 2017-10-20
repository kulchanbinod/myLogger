package com.usc.logger.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.usc.logger.R;

import com.usc.logger.lab.SettingsLab;
import com.usc.logger.models.Settings;


public class SettingsFragment extends Fragment {

    private Settings mSettings;
    private TextView mName;
    private TextView mId;
    private TextView mEmail;
    private TextView mGender;
    private TextView mCommnet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSettings = SettingsLab.get(getActivity()).getSettings();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);

        mName = (EditText) view.findViewById(R.id.name);
        mEmail = (EditText) view.findViewById(R.id.email);
        mGender = (EditText) view.findViewById(R.id.gender);
        mId = (EditText) view.findViewById(R.id.settings_id);
        mCommnet = (EditText) view.findViewById(R.id.comment);

        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mSettings.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mSettings.setId(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mSettings.setEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mSettings.setGender(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mCommnet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                mSettings.setComment(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mId.setText(mSettings.getId());
        mName.setText(mSettings.getName());
        mEmail.setText(mSettings.getEmail());
        mGender.setText(mSettings.getGender());
        mCommnet.setText(mSettings.getComment());

        return view;

    }

    @Override
    public void onPause(){
        super.onPause();
        SettingsLab.get(getActivity()).updateSettings(mSettings);
    }

}
