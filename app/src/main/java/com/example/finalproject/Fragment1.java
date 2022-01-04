package com.example.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;

public class Fragment1 extends Fragment implements View.OnClickListener {
    private Button addExersiseBTN;
    private TextView usernameTV;
    private TextView exersizesTV;
    private Button finishBTn;
    private EditText workoutnameET;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment1_layout,container,false);
        addExersiseBTN = view.findViewById(R.id.addExersise1);
        usernameTV = view.findViewById(R.id.usernameTV1);
        usernameTV.setText(User.getUsername());
        exersizesTV = view.findViewById(R.id.exersizesTV1);
        finishBTn = view.findViewById(R.id.finishworkoutBTN1);
        workoutnameET = view.findViewById(R.id.workoutnameET);
        swipeRefreshLayout = view.findViewById(R.id.swipelayout);
        String s = "";
        exersizesTV.setText(refresh());


        finishBTn.setOnClickListener(this);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "refreshed: " , Toast.LENGTH_SHORT).show();
                exersizesTV.setText(refresh());
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return  view;


    }

    public String refresh(){

        String s = "";
        if(!User.getCurrentWorkout().getExerises().isEmpty()) {
            for (int i = 0; i < User.getCurrentWorkout().getExerises().size(); i++) {
                s += "Exersize: " + User.getCurrentWorkout().getExerises().get(i).getName() + " Reps:" + User.getCurrentWorkout().getExerises().get(i).getReps() + " Sets:" + User.getCurrentWorkout().getExerises().get(i).getSets() + "\n";
            }
        }
        return s;

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishworkoutBTN1:

                String d= java.util.Calendar.getInstance().getTime().toString();
                User.addWorkout(User.getCurrentWorkout().getExerises(),d,workoutnameET.getText().toString());
                Workout workout = new Workout();
                User.setCurrentWorkout(workout);
                Gson gson = new Gson();

                String name = User.getUsername();
                ArrayList<Workout> workouts = User.getWorkouts();

                String s = gson.toJson(workouts);

                String[] field = new String[2];
                field[0] = "workouts";
                field[1] = "username";
                String[] data = new String[2];
                data[0] = s;
                data[1] = User.getUsername();

                Network network = new Network(field, data, "addWorkouts");




                Toast.makeText(getContext(), "Added: " + network.getResult() , Toast.LENGTH_SHORT).show();
                Log.d("lmaoxd", s);
                Log.d("lmaoxd2", network.getResult());




                break;
        }
    }


}
