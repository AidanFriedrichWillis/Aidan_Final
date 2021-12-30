package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutPage extends AppCompatActivity {

    private Button addExersiseBTN;
    private TextView usernameTV;
    private TextView exersizesTV;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_page);
        user = getIntent().getParcelableExtra("user");

        addExersiseBTN = findViewById(R.id.addExersise);
        usernameTV = findViewById(R.id.usernameTV);
        usernameTV.setText(user.getUsername());
        exersizesTV = findViewById(R.id.exersizesTV);

    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        user = getIntent().getParcelableExtra("user");
//        if(!user.getCurrentWorkout().getExerises().isEmpty()){
//            String s = user.getCurrentWorkout().getExerises().get(0).getName();
//            exersizesTV.setText(s);
//        }
//
//    }

    public void addExerise(View view){

        Intent intent = new Intent(getApplicationContext(),AddExersize.class);
        intent.putExtra("user",user);

        startActivity(intent);


    }

}