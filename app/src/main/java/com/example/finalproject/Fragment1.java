package com.example.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Date;

public class Fragment1 extends Fragment {
    private Button addExersiseBTN;
    private TextView usernameTV;
    private TextView exersizesTV;
    private Button finishBTn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment1_layout,container,false);
        addExersiseBTN = view.findViewById(R.id.addExersise1);
        usernameTV = view.findViewById(R.id.usernameTV1);
        usernameTV.setText(User.getUsername());
        exersizesTV = view.findViewById(R.id.exersizesTV1);
        finishBTn = view.findViewById(R.id.finishAddBtn);
        String s = "";
        exersizesTV.setText(s);
        if(!User.getCurrentWorkout().getExerises().isEmpty()){
            for(int i = 0; i < User.getCurrentWorkout().getExerises().size(); i++){
                s += "Exersize: " +  User.getCurrentWorkout().getExerises().get(i).getName() + " Reps:" + User.getCurrentWorkout().getExerises().get(i).getReps() + " Sets:" + User.getCurrentWorkout().getExerises().get(i).getSets() +"\n";
            }
            exersizesTV.setText(s);
        }


        return  view;


    }
    public void finishWorkout(View view){
        Date d= java.util.Calendar.getInstance().getTime();
        User.addWorkout(User.getCurrentWorkout().getExerises(),d);
        Workout workout = new Workout();
        User.setCurrentWorkout(workout);
        Toast.makeText(getContext(), "SignUpSucc", Toast.LENGTH_SHORT).show();
    }

}
