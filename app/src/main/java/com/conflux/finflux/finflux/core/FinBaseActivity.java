package com.conflux.finflux.finflux.core;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.conflux.finflux.finflux.R;
import com.conflux.finflux.finflux.collectionSheet.activity.CollectionSheetActivity;
import com.conflux.finflux.finflux.core.BaseActivityCallback;
import com.conflux.finflux.finflux.infrastructure.FinfluxApplication;
import com.conflux.finflux.finflux.injection.component.ActivityComponent;
import com.conflux.finflux.finflux.injection.component.DaggerActivityComponent;
import com.conflux.finflux.finflux.injection.module.ActivityModule;
import com.conflux.finflux.finflux.logout.activity.LogoutActivity;
import com.conflux.finflux.finflux.offline.activity.OfflineManagement;
import com.conflux.finflux.finflux.settings.activity.ApplicationSettings;
import com.conflux.finflux.finflux.util.Logger;
import com.conflux.finflux.finflux.util.bluetooth.BluetoothAdmin;

/**
 * Created by jagadeeshakn on 7/2/2016.
 */
public class FinBaseActivity extends AppCompatActivity implements BaseActivityCallback, NavigationView.OnNavigationItemSelectedListener {

    protected Toolbar toolbar;
    private ProgressDialog progress;
    private ActivityComponent mActivityComponent;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(FinfluxApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    public void setActionBarTitle(String title) {
        if (getSupportActionBar() != null && getTitle() != null) {
            setTitle(title);
        }
    }

    protected void showBackButton() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Logger.d(getClass().getSimpleName(),"The item selected is "+item.toString());
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress(String message) {
        if (progress == null) {
            progress = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
            progress.setCancelable(false);
        }
        progress.setMessage(message);
        progress.show();

    }

    @Override
    public void setToolbarTitle(String title) {
        setActionBarTitle(title);
    }

    @Override
    public void hideProgress() {
        if (progress != null && progress.isShowing())
            progress.dismiss();
    }

    @Override
    public void logout() {

    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack, int containerId) {
        invalidateOptionsMenu();
        String backStateName = fragment.getClass().getName();
        boolean fragmentPopped = getSupportFragmentManager().popBackStackImmediate(backStateName,
                0);

        if (!fragmentPopped && getSupportFragmentManager().findFragmentByTag(backStateName) ==
                null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(containerId, fragment, backStateName);
            if (addToBackStack) {
                transaction.addToBackStack(backStateName);
            }
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        // check if the nav mDrawer is open
        if (mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // close the drawer
        Intent intent=new Intent();
        switch (item.getItemId()){
            case R.id.item_collection:
                intent.setClass(getApplicationContext(), CollectionSheetActivity.class);
                startNavigationClickActivity(intent);
        }
        mDrawerLayout.closeDrawer(Gravity.LEFT);
        switch (item.getItemId()){
            case R.id.item_settings : startSettingsActivity();
                break;
            case R.id.item_logout : itinializeLogout();
                mNavigationView.setCheckedItem(R.id.item_logout);
                break;
            case R.id.item_offline : startOffilneActivity();
                break;
        }
        return true;
    }


    private void startOffilneActivity(){
        startActivity(new Intent(this, OfflineManagement.class));
    }

    private void startSettingsActivity(){
        startActivity(new Intent(this, ApplicationSettings.class));
        BluetoothAdmin.setBluetooth(true);
    }

    private void itinializeLogout(){
        startActivity(new Intent(this, LogoutActivity.class));
    }

    protected void setupNavigationBar() {
        // setup navigation view
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        // setup drawer layout and sync to toolbar
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // make an API call to fetch logged in client's details
    }

    public void startNavigationClickActivity(final Intent intent) {
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);

            }
        }, 500);
    }


}
