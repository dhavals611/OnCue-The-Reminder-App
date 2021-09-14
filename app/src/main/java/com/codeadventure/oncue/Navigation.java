package com.codeadventure.oncue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;

public class Navigation extends AppCompatActivity {

    AccessToken accessToken;
    Menu menu;
    CustomPagerAdapter c,c1,c2;
    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        accessToken = AccessToken.getCurrentAccessToken();

        if(accessToken==null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

         bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.action_home);
        //viewPager.setAdapter(new CustomPagerAdapter(getApplicationContext(),R.layout.activity_home));


        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        menu = bottomNavigationView.getMenu();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        c = new CustomPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(c);



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        set_values(R.id.action_home,"Home");
                        break;
                    case 1:
                        set_values(R.id.action_friends,"Friends");
                        break;
                    case 2:
                        set_values(R.id.action_addreminder,"Reminder");
                        break;
                    case 3:
                        set_values(R.id.action_notifications,"Notifications");
                        break;
                    case 4:
                        set_values(R.id.action_setting,"Settings");
                        break;
                }
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_setting:
                                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                                set_values(R.id.action_setting,"Settings");
                                viewPager.setCurrentItem(4);
                                break;

                            case R.id.action_notifications:
                                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                                set_values(R.id.action_notifications,"Notifications");
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.action_home:
                                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                                set_values(R.id.action_home,"Home");
                                viewPager.setCurrentItem(0);
                                break;

                            case R.id.action_friends:
                                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                                set_values(R.id.action_friends,"Friends");
                                viewPager.setCurrentItem(1);
                                break;

                            case R.id.action_addreminder:
                                BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
                                set_values(R.id.action_addreminder,"Reminder");
                                viewPager.setCurrentItem(2);
                                break;


                        }
                        return false;
                    }
                });
    }

    public void set_values(int id,String s)
    {
        menu.findItem(R.id.action_friends).setTitle("");
        menu.findItem(R.id.action_home).setTitle("");
        menu.findItem(R.id.action_addreminder).setTitle("");
        menu.findItem(R.id.action_notifications).setTitle("");
        menu.findItem(R.id.action_setting).setTitle("");

        menu.findItem(id).setTitle(s);
    }

    @Override
    public void onBackPressed() {

        if(viewPager.getCurrentItem()==0){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else{
            viewPager.setCurrentItem(0);
        }
    }

}

