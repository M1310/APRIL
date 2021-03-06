package com.example.mwils.april.Exercises.ExerciseProgramme;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mwils.april.R;
import com.example.mwils.april.SectionsPageAdapter;

public class ExerciseProgramme extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_programme);


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Setting up the ViewPager with the sections adapter
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        }//end OnCreate method

    /**
    @params: viewPager - used for the creation of tabs
    This method creates the tabs at the top using the Fragment library, and assigns
    each relevant class, and also gives each tab a title
     **/
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

