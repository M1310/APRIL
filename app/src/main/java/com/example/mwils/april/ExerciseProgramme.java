package com.example.mwils.april;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ExerciseProgramme extends AppCompatActivity {

    private static final String TAG = "ExerciseProgramme";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_information);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Setting up the ViewPager with the sections adapter
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        }//end OnCreate method

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        //Calling the addFragment method to create a tab for each tab in the nutrition information section
        adapter.addFragment(new ExerciseProgramme_Tab1(), "1");
        adapter.addFragment(new ExerciseProgramme_Tab2(), "2");
        adapter.addFragment(new ExerciseProgramme_Tab3(), "3");
        adapter.addFragment(new ExerciseProgramme_Tab4(), "4");
        adapter.addFragment(new ExerciseProgramme_Tab5(), "5");
        adapter.addFragment(new ExerciseProgramme_Tab6(), "6");
        viewPager.setAdapter(adapter);
    }//end setupViewPager method

    }//end class

