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
import android.util.Log;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.android.meat_timealpha10.Models.type.MealType;
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
public class planning extends Fragment implements DatePickerDialog.OnDateSetListener {
  private TabLayout tabLayout;

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

    return fragment;
  }


  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_planning,
      container, false);
    final Button chooseDate = (Button) view.findViewById(R.id.chooseDate);

    // Code for setting the date with calendar
    chooseDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogFragment datePicker = new DatePickerFragment();
        datePicker.show(getChildFragmentManager(), "date picker");
      }
    });

    //code for the tabLayout
    tabLayout = (TabLayout) view.findViewById(R.id.planning_tabs);
    final FragmentManager cfManager = getChildFragmentManager();

    FragmentTransaction myFragmentTransaction = cfManager.beginTransaction();
    myFragmentTransaction.replace(R.id.planning_list_item_frag, new PlanningListItemFragment());
    myFragmentTransaction.addToBackStack(null);
    myFragmentTransaction.commit();

    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        PlanningListItemFragment frag = (PlanningListItemFragment) cfManager.findFragmentById(R.id.planning_list_item_frag);
        frag.setMealType(MealType.LUNCH);
        Log.d("LOGGINNN", "Selected TAB");
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });

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

    PlanningListItemFragment frag = (PlanningListItemFragment) getChildFragmentManager().findFragmentById(R.id.planning_list_item_frag);
    frag.setDate(c.getTime());
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
