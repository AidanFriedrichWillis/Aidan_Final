package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.ui.main.SectionsPagerAdapter;
import com.example.finalproject.databinding.ActivityMainPageBinding;

import java.util.Date;

public class MainPage extends AppCompatActivity {

    private ActivityMainPageBinding binding;
    private EditText pastWorkoutsTV;

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
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });






    }

    public void addExerise(View view){

        Intent intent = new Intent(getApplicationContext(),AddExersize.class);
        startActivity(intent);


    }
    public void finishWorkout(View view){
            EditText editText = view.findViewById(R.id.workoutnameET);
            Log.d("lmao",String.valueOf(editText.getText()));
//            Date d= java.util.Calendar.getInstance().getTime();
//                User.addWorkout(User.getCurrentWorkout().getExerises(),d);
//                Workout workout = new Workout(String.valueOf(editText.getText()));
//                User.setCurrentWorkout(workout);
//        Toast.makeText(getApplicationContext(), editText.getText(), Toast.LENGTH_LONG).show();



    }


}