package com.example.uas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.uas.fragment.AccountFragment;
import com.example.uas.fragment.NotificationFragment;
import com.example.uas.fragment.TimelineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById(R.id.bn_main);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_main,
                    new TimelineFragment()).commit();
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_t:
                            selectedFragment = new TimelineFragment();
                            break;
                        case R.id.nav_a:
                            selectedFragment = new AccountFragment();
                            break;
                        case R.id.nav_r:
                            selectedFragment = new NotificationFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_main,
                            selectedFragment).commit();
                    return true;
                }
            };
}