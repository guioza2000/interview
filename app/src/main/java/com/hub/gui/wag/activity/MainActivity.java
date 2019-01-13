package com.hub.gui.wag.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.hub.gui.wag.R;
import com.hub.gui.wag.api.StackExchangeClient;
import com.hub.gui.wag.api.StackExchangeInterface;
import com.hub.gui.wag.fragment.AboutFragment;
import com.hub.gui.wag.fragment.UserFragment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int MAIN_CONTAINER = R.id.main_container;

    private Fragment mUserFrament;
    private Fragment mAboutFragment;

    public enum FragmentType{
        user,
        about
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StackExchangeInterface service = StackExchangeClient.getService();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //first creation of the activity
        if(savedInstanceState == null) {
            switchFragment(FragmentType.user);
            navigationView.setCheckedItem(R.id.nav_user);
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



    public void switchFragment(FragmentType type){
        Fragment f = null;
        switch (type){
            case user:
                if(mUserFrament == null)
                    mUserFrament = new UserFragment();

                f = mUserFrament;
                break;
            case about:
                if(mAboutFragment == null)
                    mAboutFragment = new AboutFragment();

                f = mAboutFragment;
                break;

            default:
                return;
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(MAIN_CONTAINER);

        if(f == currentFragment)
            return;

        //TODO not necessary
        //clear the backstack
        /*int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            // Get the back stack fragment id.
            int backStackId = getSupportFragmentManager().getBackStackEntryAt(i).getId();
            fm.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }*/

        getSupportFragmentManager()
                .beginTransaction()
                .replace(MAIN_CONTAINER,f)
                .commit();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_user) {
            switchFragment(FragmentType.user);
        } else if (id == R.id.nav_about) {
            switchFragment(FragmentType.about);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
