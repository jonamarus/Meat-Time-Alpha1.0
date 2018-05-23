package com.example.android.meat_timealpha10.Fragments;

import android.support.v4.app.Fragment;

public class PlanningItemFragment {

    private String Name;
    private String Keywords;
    private int Photo;


    public PlanningItemFragment(String name, String keywords, int photo) {
        Name = name;
        Keywords = keywords;
        Photo = photo;
    }

    // getter

    public String getName(){
        return Name;
    }

    public String getKeywords(){
        return Keywords;
    }

    public int getPhoto() {
        return Photo;
    }


}
