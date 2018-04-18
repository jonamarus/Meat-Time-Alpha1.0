package com.example.android.meat_timealpha10.Activities;

import android.app.Fragment;
import android.net.Network;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

@Override
    public boolean onOptionsItemSelected (MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void selectItemDrawer (MenuItem menuItem){
        Fragment myFragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()){
            case R.id.home:
                fragmentClass = Home.class;
                break;
            case R.id.planning:
                fragmentClass =
                break;
            case R.id.meal:
                fragmentClass = Log
                break;
            case R.id.recipes:
                fragmentClass = Home.class;
                break;
            case R.id.extra:
                fragmentClass = Home.class;
                break;
            case R.id.options:
                fragmentClass = Home.class;
                break;
            case R.id.logout:
                fragmentClass = Home.class;
                break;
        }
    }
}