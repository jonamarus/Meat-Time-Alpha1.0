package com.example.android.meat_timealpha10.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Network;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.app.FragmentManager;

import com.example.android.meat_timealpha10.Fragments.extra;
import com.example.android.meat_timealpha10.Fragments.foodOmatic;
import com.example.android.meat_timealpha10.Fragments.home;
import com.example.android.meat_timealpha10.Fragments.logoff;
import com.example.android.meat_timealpha10.Fragments.options;
import com.example.android.meat_timealpha10.Fragments.planning;
import com.example.android.meat_timealpha10.Fragments.recipes;
import com.example.android.meat_timealpha10.R;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nv);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent(nvDrawer);
    }

@Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer (MenuItem menuItem){
        android.support.v4.app.Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.home:
                fragmentClass= home.class;
                break;
            case R.id.planning:
                fragmentClass= planning.class;
                break;
            case R.id.meal:
                fragmentClass= foodOmatic.class;
                break;
            case R.id.recipes:
                fragmentClass= recipes.class;
                break;
            case R.id.extra:
                fragmentClass= extra.class;
                break;
            case R.id.options:
                fragmentClass= options.class;
                break;
            case R.id.logout:
                fragmentClass= logoff.class;
                break;

            default:
                fragmentClass = home.class;

        }
        try {
            myFragment = (android.support.v4.app.Fragment) fragmentClass.newInstance();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent,myFragment).commit();
        menuItem.setChecked(true);
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
}