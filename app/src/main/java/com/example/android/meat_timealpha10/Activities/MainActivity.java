package com.example.android.meat_timealpha10.Activities;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonToken;
import android.util.Log;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.meat_timealpha10.Fragments.extra;
import com.example.android.meat_timealpha10.Fragments.foodOmatic;

import com.example.android.meat_timealpha10.Fragments.home;
import com.example.android.meat_timealpha10.Fragments.logoff;
import com.example.android.meat_timealpha10.Fragments.options;
import com.example.android.meat_timealpha10.Fragments.planning;
import com.example.android.meat_timealpha10.Fragments.recipes;
import com.example.android.meat_timealpha10.Models.User;
import com.example.android.meat_timealpha10.R;
import com.example.android.meat_timealpha10.helpers.HelperMethods;
import com.example.android.meat_timealpha10.helpers.TokenHelper;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.google.gson.Gson;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity  {

  private DrawerLayout mDrawerLayout;
  private ActionBarDrawerToggle mToggle;

  //@BindView(R.id.profile_picture)
  public ImageView profilePicture;

  @BindView(R.id.image)
  public ProfilePictureView profilePic;

  @BindView(R.id.naam_header)
  public TextView fullName;

  @BindView(R.id.emailaddress)
  public TextView email;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
    mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
    mDrawerLayout.addDrawerListener(mToggle);
    NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
    View header = nvDrawer.getHeaderView(0);
    mToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setupDrawerContent(nvDrawer);

    ButterKnife.bind(this, header);

    setHeaderContent();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (mToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  public void selectItemDrawer(MenuItem menuItem) {
    android.support.v4.app.Fragment myFragment = null;

    switch (menuItem.getItemId()) {
      case R.id.home:
        myFragment = new home();
        break;
      case R.id.planning:
        myFragment = new planning();
        break;
      case R.id.meal:
        myFragment = new foodOmatic();
        break;
      case R.id.recipes:
        myFragment = new recipes();
        break;
      case R.id.extra:
        myFragment = new extra();
        break;
      case R.id.options:
        myFragment = new options();
        break;
      case R.id.logout:
        TokenHelper.logout(this.getBaseContext());
        LoginManager.getInstance().logOut();
        toLoginActivity();
        break;

      default:

    }
    if (myFragment != null) {

      android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
      FragmentTransaction myFragmentTransaction = fragmentManager.beginTransaction();
      myFragmentTransaction.replace(R.id.flcontent, myFragment);
      myFragmentTransaction.addToBackStack(null);
      myFragmentTransaction.commit();

    }
    setTitle(menuItem.getTitle());
    mDrawerLayout.closeDrawers();
  }

  private void setupDrawerContent(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        selectItemDrawer(item);
        return true;
      }
    });
  }

  private void toLoginActivity() {
    Intent intent = new Intent(this.getBaseContext(), LoginActivity.class);
    startActivity(intent);
  }

  private void setHeaderContent() {
    String jsonBody = "";
    try {
      jsonBody = HelperMethods.decoded(TokenHelper.getToken(this.getBaseContext()));
      Gson gson = new Gson();

      User user = gson.fromJson(jsonBody, User.class);

      profilePic.setProfileId(user.getFacebookId());
      fullName.setText(user.getFirstName() + " " + user.getLastName());
      email.setText(user.getEmail());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}