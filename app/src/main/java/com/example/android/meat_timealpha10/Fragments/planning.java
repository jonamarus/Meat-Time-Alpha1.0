package com.example.android.meat_timealpha10.Fragments;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.android.meat_timealpha10.R;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link planning.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link planning#newInstance} factory method to
 * create an instance of this fragment.
 */
public class planning extends Fragment implements DatePickerDialog.OnDateSetListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public planning() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment planning.
     */
    // TODO: Rename and change types and number of parameters
    public static planning newInstance(String param1, String param2) {
        planning fragment = new planning();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    private TabLayout tabLayout ;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_planning,
                container, false);
        final Button chooseDate = (Button)view.findViewById(R.id.chooseDate);

        // Code for setting the date with calendar
        chooseDate.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

                                        DialogFragment datePicker = new DatePickerFragment();
                                        datePicker.show(getChildFragmentManager(), "date picker");

                                      }
                                      });

        //code for the tabLayout

        tabLayout = (TabLayout)view.findViewById(R.id.planning_tabs);
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        FragmentManager cfManager = getChildFragmentManager();
        adapter = new ViewPagerAdapter(cfManager);

        // add Fragment Here

        adapter.addFragment(new PlanningListItemFragment(),"Breakfast", "Breakfast");
        adapter.addFragment(new PlanningListItemFragment(),"Lunch", "Lunch");
        adapter.addFragment(new PlanningListItemFragment(),"Dinner", "Dinner");
        // set Icons to tabs

        // tablayout.getTabAt(0).setIcon(R.drawable.X1):
        // tablayout.getTabAt(1).setIcon(R.drawable.X2):
        // tablayout.getTabAt(2).setIcon(R.drawable.X3):

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // remove shadow from the action bar
        // ActionBar actionBar = getSupportActionbar();
        // actionBar.setElevation(0);




        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView date = getView().findViewById(R.id.dateGiven);
        date.setText(currentDateString);

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
