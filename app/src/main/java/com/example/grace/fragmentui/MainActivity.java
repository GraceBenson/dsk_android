package com.example.grace.fragmentui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout simpleFrameLayout;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// get the reference of FrameLayout and TabLayout
        simpleFrameLayout = (FrameLayout) findViewById(R.id.simpleFrameLayout);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);

// Create a new Tab named "First"
        TabLayout.Tab dskTab = tabLayout.newTab();
        dskTab.setText("DSK"); // set the Text for the first Tab
        dskTab.setIcon(R.drawable.ic_launcher_background); // set an icon for the
// first tab
        tabLayout.addTab(dskTab); // add  the tab at in the TabLayout

// Create a new Tab named "Second"
        TabLayout.Tab variTab = tabLayout.newTab();
        variTab.setText("VARI"); // set the Text for the second Tab
        variTab.setIcon(R.drawable.ic_launcher_background); // set an icon for the second tab
        tabLayout.addTab(variTab); // add  the tab  in the TabLayout

// Create a new Tab named "Third"
        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("Kraken"); // set the Text for the first Tab
        thirdTab.setIcon(R.drawable.ic_launcher_background); // set an icon for the first tab
        tabLayout.addTab(thirdTab); // add  the tab at in the TabLayout

        // Settings Tab
        TabLayout.Tab settingsTab = tabLayout.newTab();
        settingsTab.setText("Settings"); // set the Text for the first Tab
        settingsTab.setIcon(R.drawable.ic_action_name); // set an icon for the first tab
        tabLayout.addTab(settingsTab); // add  the tab at in the TabLayout

// perform setOnTabSelectedListener event on TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
// get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new dskTab();
                        break;
                    case 1:
                        fragment = new variTab();
                        break;
                    case 2:
                        fragment = new thirdTab();
                        break;
                    case 3:
                        fragment = new settingsTab();
                        break;
                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
