package com.example.android.meat_timealpha10.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.meat_timealpha10.R;


public class PlanningFragment extends Fragment {
    private FragmentTabHost mTabHost;


    //Mandatory Constructor
    public PlanningFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_planning,container, false);


        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Fragment B"),
                BreakfastFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Fragment C"),
                LunchFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentd").setIndicator("Fragment D"),
                DinnerFragment.class, null);


        return rootView;
    }
}