package com.example.android.meat_timealpha10.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.meat_timealpha10.R;

public class PlanningListItemFragment extends Fragment {

    View v;

    public PlanningListItemFragment() {

    }

    @Nullable
    //@Override
    public View OncreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_planning_list, container, false);
        return v;
    }

}
