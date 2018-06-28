package com.example.android.meat_timealpha10.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.android.meat_timealpha10.Models.type.MealType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

  private final List<Fragment> lstFragment = new ArrayList<>();
  private  final List<String> lstTitles = new ArrayList<>();
  private final List<String> lstMealType = new ArrayList<>();

  public ViewPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override
  public Fragment getItem(int position) {
    return lstFragment.get (position);
  }


  @Override
  public int getCount() {
    return lstTitles.size();
  }


  public String getMealType() {return lstMealType.toString();}
  @Override
  public CharSequence getPageTitle(int position) {
    return lstTitles.get(position);
  }

  public void  addFragment (Fragment fragment,String title, String mealtype) {

    lstFragment.add(fragment);
    lstTitles.add(title);
    lstMealType.add(getMealType());

  }
}
