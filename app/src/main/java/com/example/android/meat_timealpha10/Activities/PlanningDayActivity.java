package com.example.android.meat_timealpha10.Activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.example.android.meat_timealpha10.R;

import com.example.android.meat_timealpha10.Fragments.PlanningFragment;

/**
 * this is the activity that will hold the planning in 'day-mode'. One day at a time.
 */

public class PlanningDayActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlanningFragment fragmenttab = new PlanningFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragmenttab).commit();


    }}