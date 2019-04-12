package com.example.mwils.april.Exercises.ExerciseWarmup;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mwils.april.Exercises.ExerciseWarmup.ExercisesWarmupSeated;
import com.example.mwils.april.Exercises.ExerciseWarmup.ExercisesWarmupStanding;

import com.example.mwils.april.R;
import com.example.mwils.april.SectionsPageAdapter;


public class ExercisesWarmup extends AppCompatActivity {

    private static final String TAG = "ExerciseProgramme";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_warmup);
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
        adapter.addFragment(new ExercisesWarmupStanding(), "Standing");
        adapter.addFragment(new ExercisesWarmupSeated(), "Seated");

        viewPager.setAdapter(adapter);
    }//end setupViewPager method
}
