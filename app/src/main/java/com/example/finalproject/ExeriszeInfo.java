package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class ExeriszeInfo extends AppCompatActivity {

    private TextView exersizeInfoTV;
    private Workout workout;
    private String info;
    private  RESTFull_services_workout rest;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_exerisze_info);
        Intent intent = getIntent();

        String index = intent.getStringExtra("id");

        rest = new RESTFull_services_workout("workout"+"/"+index);
        try{
            if(rest.getRequest()){
                workout = User.fromJson(rest.getResult()).get(0);
            }
        }catch(Exception e){

        }



        exersizeInfoTV = findViewById(R.id.exersizeInfoTV);

        info = "Workout Name: "+ workout.getName() +"\n"+"\n" + "Date:" + workout.getDate().toString() +"\n" + "\n" + "Exersizes Done: " + "\n";

        for(int i = 0; i < workout.getExerises().size();i++){

            info += workout.getExerises().get(i).getName() +  " " +workout.getExerises().get(i).getReps()+ "x" +workout.getExerises().get(i).getSets() +" "+ String.valueOf(workout.getExerises().get(i).getWeight())+ "KG" + "\n";


        }

        exersizeInfoTV.setText(info);

    }

    public void deleteBtn(View view){
        try {
            if(rest.deleteRequest()){
                Toast.makeText(App.getAppContext(), rest.getResult(), Toast.LENGTH_SHORT).show();
                finish();
            }

        }catch(Exception e){
            Toast.makeText(App.getAppContext(), e.toString(), Toast.LENGTH_SHORT).show();

        }
    }

}