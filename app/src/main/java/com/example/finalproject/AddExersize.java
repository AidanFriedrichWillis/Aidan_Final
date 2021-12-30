package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AddExersize extends AppCompatActivity {


    private EditText nameET;
    private EditText repsET;
    private EditText setsET;
    private Button addexersieBTN;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exersize);
        user = getIntent().getParcelableExtra("user");
        nameET = findViewById(R.id.exersizeNameET);
        repsET = findViewById(R.id.repsET);
        setsET = findViewById(R.id.setsET);
        addexersieBTN = findViewById(R.id.finishAddBtn);
    }

    public void add(View view){

        Exerise exerise = new Exerise(String.valueOf(nameET.getText()),Integer.valueOf(String.valueOf(repsET.getText())),Integer.valueOf(String.valueOf(setsET.getText())));
        ArrayList<Exerise> exerises = new ArrayList<Exerise>();
        exerises.add(exerise);
        Workout workout = new Workout(exerises);
        workout.addExersize(exerise);
        user.setCurrentWorkout(workout);
//        tv.setText(exerise.getName()+String.valueOf(exerise.getReps())+ String.valueOf(exerise.getSets()));
        workoutPage(user);
    }

    public void workoutPage(User user){
        Intent intent = new Intent(getApplicationContext(),WorkoutPage.class);
        intent.putExtra("user", user);
        startActivity(intent);

    }




}