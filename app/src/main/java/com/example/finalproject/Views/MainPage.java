package com.example.finalproject.Views;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.Views.AddExersize;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.widget.EditText;

import com.example.finalproject.Views.Fragments.main.SectionsPagerAdapter;
import com.example.finalproject.databinding.ActivityMainPageBinding;

public class MainPage extends FragmentActivity {

    private ActivityMainPageBinding binding;
    private EditText pastWorkoutsTV;
    /**
     * On Create of the Activity
     * @param savedInstanceState The instance of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
    }

    /**
     * Function for the button add exercise.
     * @param view Takes the View
     */
    public void addExerise(View view){
        Intent intent = new Intent(getApplicationContext(), AddExersize.class);
        startActivity(intent);
    }

    /**
     * Function for the button add exercise.
     * @param v Takes the View
     */
    public void addBtnFunc(View v){
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent());
        overridePendingTransition(0, 0);

    }



}