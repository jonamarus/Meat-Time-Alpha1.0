package com.example.android.meat_timealpha10.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.meat_timealpha10.R;

import java.util.ArrayList;
import java.util.List;

public class PlanningListItemFragment extends Fragment {

    View v;
    private RecyclerView mrecyclerview;
    private List<PlanningItemFragment> lstPlanningItem;

    public PlanningListItemFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_planning_list, container, false);
        lstPlanningItem = new ArrayList<>();

        mrecyclerview = v.findViewById(R.id.RCview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getActivity(),lstPlanningItem);
        LinearLayoutManager llm= new LinearLayoutManager(getActivity());
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerview.setAdapter(recyclerAdapter);

        lstPlanningItem.add(new PlanningItemFragment("Menu A", "keywords", R.drawable.ic_nav_ic_extra));
        lstPlanningItem.add(new PlanningItemFragment("Menu B", "keywords", R.drawable.ic_nav_ic_meal));
        lstPlanningItem.add(new PlanningItemFragment("Menu C", "keywords", R.drawable.ic_nav_ic_home));
        lstPlanningItem.add(new PlanningItemFragment("Menu D", "keywords", R.drawable.ic_nav_ic_logoff));
        lstPlanningItem.add(new PlanningItemFragment("Menu E", "keywords", R.drawable.ic_nav_ic_planning));
        Log.wtf("size_of_my_list", Integer.toString(lstPlanningItem.size()));
        recyclerAdapter.notifydatasetchanged();
        Log.d("debugMode", "The onCreateView method has been launched");
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 22/05/2018 Implement the call to get the meal picture from the database

       // lstPlanningItem = new ArrayList<>();
        //lstPlanningItem.add(new PlanningItemFragment("Menu A", "keywords", R.drawable.ic_nav_ic_meal));




    }
}
