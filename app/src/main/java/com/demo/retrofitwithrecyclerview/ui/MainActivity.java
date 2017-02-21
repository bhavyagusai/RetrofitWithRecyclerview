package com.demo.retrofitwithrecyclerview.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.retrofitwithrecyclerview.R;
import com.demo.retrofitwithrecyclerview.fragment.DashboardFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.frame)
    FrameLayout frame;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private int page = 0;
    private boolean isRefresh = false;
    Context mContext;
    Handler mHandler;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    private static final String TAG_HOME = "home";
    private static final String TAG_SEARCH = "search";
    private static final String TAG_USER = "user";
    private static final String TAG_PROVIDER = "provider";
    private static final String TAG_PROFILE = "profile";
    public static String CURRENT_TAG = TAG_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        mHandler = new Handler();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Das");
        //getSupportActionBar().setIcon(R.drawable.ic_actionbar_archive);
        setSupportActionBar(toolbar);


        View header = navView.getHeaderView(0);
        TextView txtUserName = (TextView) header.findViewById(R.id.txtUserName);
        txtUserName.setText("Bhavya Gusai");
        TextView txtUserEmail = (TextView) header.findViewById(R.id.txtUserEmail);
        txtUserEmail.setText("bhavyagusai024@gmail.com");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setUpNavigationView();
        loadHomeFragment();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    private void setUpNavigationView() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_dashboard:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;

                    case R.id.nav_search:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_SEARCH;
                        break;

                    case R.id.nav_users:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_USER;
                        break;

                    case R.id.nav_providers:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_PROVIDER;
                        break;

                    case R.id.nav_profile:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_PROFILE;
                        break;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

    }

    private void loadHomeFragment() {


        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        drawerLayout.closeDrawers();

    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // home
                DashboardFragment homeFragment = new DashboardFragment();
                return homeFragment;

            case 1:
                //search
                DashboardFragment homeFragment1 = new DashboardFragment();
                return homeFragment1;

            case 2:
                //users
                DashboardFragment homeFragment2 = new DashboardFragment();
                return homeFragment2;

            case 3:
                //providers
                DashboardFragment homeFragment3 = new DashboardFragment();
                return homeFragment3;

            case 4:
                DashboardFragment homeFragment4 = new DashboardFragment();
                return homeFragment4;

            default:
                return new DashboardFragment();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_users) {

        } else if (id == R.id.nav_providers) {

        } else if (id == R.id.nav_profile) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
