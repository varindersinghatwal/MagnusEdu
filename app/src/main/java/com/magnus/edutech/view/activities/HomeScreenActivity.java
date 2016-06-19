package com.magnus.edutech.view.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost.OnTabChangeListener;

import com.magnus.edutech.view.fragments.FragmentAboutUs;
import com.magnus.edutech.view.fragments.FragmentContactUs;
import com.magnus.edutech.view.fragments.FragmentHomeCourse;
import com.magnus.edutech.view.fragments.FragmentResources;
import com.magnus.edutech.view.mapper.MainRoutingClass;
import com.magnus.edutech.R;
import com.magnus.edutech.view.adapter.NavDrawerListAdapter;
import com.magnus.edutech.model.NavDrawerItem;
import com.magnus.edutech.model.User;
import com.magnus.edutech.App.GlobalConstants;
import com.magnus.edutech.view.utility.Utilities;

import org.json.JSONException;

import java.util.ArrayList;


public class HomeScreenActivity extends AppCompatActivity {


    //Class
      MainRoutingClass mainRoutingClass;
      LoadDataInBackGround loadDataInBackGround;
      Utilities utilities;
    //XML


    //Variables
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private Context context;


    // slide menu items
    private String[] navMenuTitles;
    private ArrayList<NavDrawerItem> navDrawerItems;
    static final int RC_REQUEST = 10001;
    static final String TAG = "MangusApp";
    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);

        context = this;
        utilities  =new Utilities();
        mainRoutingClass = new MainRoutingClass(this);
        loadDataInBackGround  = new LoadDataInBackGround();
        mTitle = mDrawerTitle = getTitle();
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        TypedArray navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // What's hot, We  will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        //navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        /*navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));*/


        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        NavDrawerListAdapter adapter = new NavDrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        try {
            actionBar = getSupportActionBar();
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
            ex.printStackTrace();
        }


        // enabling action bar app icon and behaving it as toggle button

		/*getActionBar().setHomeButtonEnabled(true);*/
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
               R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                if (actionBar != null) {
                    actionBar.setTitle(mTitle);
                }

                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                if (actionBar != null) {
                    actionBar.setTitle(mDrawerTitle);
                }
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setUpTab();


        boolean isPurchased = Utilities.get_Preferences_boolean(GlobalConstants.PURCHASED, context);
        if(!isPurchased)
        {
            String clientId  =  Utilities.get_Preferences(GlobalConstants.CLIENT_ID, context);
            User user = new User();
            user.setClintId(clientId);
            try {

                loadDataInBackGround.updateStatusToServer(context,user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            displayView(position);
        }
    }

    private FragmentTabHost mTabHost;

    public void setUpTab() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.frame_container);
        Resources res = this.getResources();

        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator("", res.getDrawable(R.drawable.tab_selector_home)),
                FragmentHomeCourse.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("", res.getDrawable(R.drawable.tab_selector_resource)),
                FragmentResources.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator("", res.getDrawable(R.drawable.tab_selector_about_us)),
                FragmentAboutUs.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator("", res.getDrawable(R.drawable.tab_selector_contact_us)),
                FragmentContactUs.class, null);

        changeBackgroundForTabs(0);

        mTabHost.setOnTabChangedListener(new OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                int position = Integer.valueOf(tabId);
                setTitle(navMenuTitles[position]);
                int popStackCount = getSupportFragmentManager().getBackStackEntryCount();
                if (popStackCount > 0) {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }

            }
        });
    }

    private void changeBackgroundForTabs(int value) {

        mTabHost.getTabWidget().getChildAt(0)
                .setBackgroundColor(getResources().getColor(R.color.white));
        mTabHost.getTabWidget().getChildAt(1)
                .setBackgroundColor(getResources().getColor(R.color.white));
        mTabHost.getTabWidget().getChildAt(2)
                .setBackgroundColor(getResources().getColor(R.color.white));
        mTabHost.getTabWidget().getChildAt(3)
                .setBackgroundColor(getResources().getColor(R.color.white));

        //mTabHost.getTabWidget().getChildAt(value) .setBackgroundColor(getResources().getColor(R.color.colorDarkBodyBackground));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        int popStackCount = getSupportFragmentManager().getBackStackEntryCount();

        System.out.println("Fragement Count :" + popStackCount);
        if (popStackCount > 0) {
            getSupportFragmentManager().popBackStack();

        } else {
            utilities.onActivityBackPressedExit(this);

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.logout:
                Log.d("Check this : "," Logout");
                logoutFromApp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    public void displayView(int position) {

        Fragment fragment = null;
        int popStackCount = getSupportFragmentManager().getBackStackEntryCount();
        if (popStackCount > 0) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        switch (position) {
            case 0:
                //fragment = new FragmentHomeCourse();
                mTabHost.setCurrentTab(position);
                break;
            case 1:
                //fragment = new FragementResources();
                mTabHost.setCurrentTab(position);
                break;
            case 2:
                //fragment = new FragementAboutUs();
                mTabHost.setCurrentTab(position);
                break;
            case 3:
                //fragment = new FragementContactUs();
                mTabHost.setCurrentTab(position);
                break;
           default:
                break;
        }

        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        setTitle(navMenuTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer

        }
    }


    private void logoutFromApp() {
        Utilities.save_Preferences(GlobalConstants.CLIENT_ID, "", this);
        Utilities.save_Preferences_boolean(GlobalConstants.LOGGED_IN, false, this);
        Intent signUpIntent = null;
        signUpIntent = new Intent(this, ActivityLoginScreen.class);
        startActivity(signUpIntent);
        finish();

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        if (actionBar != null) {
            actionBar.setTitle(mTitle);
        }

    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


}
