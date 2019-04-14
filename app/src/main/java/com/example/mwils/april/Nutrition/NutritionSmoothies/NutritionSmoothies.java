package com.example.mwils.april.Nutrition.NutritionSmoothies;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mwils.april.R;
import com.example.mwils.april.SectionsPageAdapter;

public class NutritionSmoothies extends AppCompatActivity {


    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_smoothies);


        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Setting up the ViewPager with the sections adapter
        mViewPager =  findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        }//end OnCreate method

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        //Calling the addFragment method to create a tab for each tab in the nutrition information section
        adapter.addFragment(new NutritionSmoothies_Tab1(), "Recipe 1");
        adapter.addFragment(new NutritionSmoothies_Tab2(), "Recipe 2");
        adapter.addFragment(new NutritionSmoothies_Tab3(), "Recipe 3");
        adapter.addFragment(new NutritionSmoothies_Tab4(), "Recipe 4");
        viewPager.setAdapter(adapter);
    }//end setupViewPager method

    }//end class

