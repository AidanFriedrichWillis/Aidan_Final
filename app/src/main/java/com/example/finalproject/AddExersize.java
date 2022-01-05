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
    private EditText weightET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exersize);
        nameET = findViewById(R.id.exersizeNameET);
        repsET = findViewById(R.id.repsET);
        setsET = findViewById(R.id.setsET);
        addexersieBTN = findViewById(R.id.finishAddBtn);
        weightET = findViewById(R.id.weightET);
    }

    public void add(View view){

        Exerise exerise = new Exerise(String.valueOf(nameET.getText()),Integer.valueOf(String.valueOf(repsET.getText())),Integer.valueOf(String.valueOf(setsET.getText())),Integer.valueOf(String.valueOf(weightET.getText())));

        User.getCurrentWorkout().addExersize(exerise);

        workoutPage();
    }

    public void workoutPage(){
        Intent intent = new Intent(getApplicationContext(),MainPage.class);
        startActivity(intent);

    }




}