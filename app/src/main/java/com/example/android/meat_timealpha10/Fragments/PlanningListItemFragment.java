package com.example.android.meat_timealpha10.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.meat_timealpha10.Models.Meal;
import com.example.android.meat_timealpha10.Models.User;
import com.example.android.meat_timealpha10.Models.type.MealType;
import com.example.android.meat_timealpha10.R;
import com.example.android.meat_timealpha10.RestService.RestClient;
import com.example.android.meat_timealpha10.RestService.RestService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlanningListItemFragment extends Fragment {

  View v;
  private RecyclerView mrecyclerview;
  private List<PlanningItemFragment> lstPlanningItem;

  private Date date;
  private MealType mealType;

  public RestService restService;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    v = inflater.inflate(R.layout.fragment_planning_list, container, false);
    restService = RestClient.createService(RestService.class);
    this.date = new Date();

    lstPlanningItem = new ArrayList<>();
    LinearLayoutManager llm = new LinearLayoutManager(getActivity());

    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mrecyclerview.getContext(),
      llm.getOrientation());

    mrecyclerview.addItemDecoration(dividerItemDecoration);

    mrecyclerview = v.findViewById(R.id.RCview);
    RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getActivity(), lstPlanningItem);
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

  public void setDate(Date date) {
    this.date = date;
    getMeals();
  }

  public void setMealType(MealType mealType) {
    this.mealType = mealType;
    getMeals();
  }

  public void getMeals() {
    Call<Meal> call = restService.getMealForDay(this.date, this.mealType.name());
    call.enqueue(new Callback<Meal>() {

      @Override
      public void onResponse(Call<Meal> call, Response<Meal> response) {
        if (response.isSuccessful()) {

        } else {
          Log.d("Get meal", response.message());
        }

        Log.d("CallBack", " response is " + response);
      }

      @Override
      public void onFailure(Call<Meal> call, Throwable t) {
        Log.d("CallBack", " Throwable is " + t);
      }
    });
  }
}
