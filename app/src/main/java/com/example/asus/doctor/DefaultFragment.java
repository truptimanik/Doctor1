package com.example.asus.doctor;

/**
 * Created by ASUS on 16-07-2017.
 */

//for creating new default fragment for each menu

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.doctor.R;


public class DefaultFragment extends Fragment {


    public DefaultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_default, container, false);
    }


}
