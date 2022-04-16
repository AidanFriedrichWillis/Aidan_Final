package com.example.finalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Fragment1 extends Fragment  implements View.OnClickListener {
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
        exersizesTV = view.findViewById(R.id.exersizesTV1);
        finishBTn = view.findViewById(R.id.finishworkoutBTN1);
        workoutnameET = view.findViewById(R.id.workoutnameET);
        swipeRefreshLayout = view.findViewById(R.id.swipelayout);
        String s = "";
        refresh();

        finishBTn.setOnClickListener(this);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        return  view;


    }

    public void refresh(){

        String s = "";
        if(!User.getCurrentWorkout().getExerises().isEmpty()) {
            for (int i = 0; i < User.getCurrentWorkout().getExerises().size(); i++) {
                s += "Exersize: " + User.getCurrentWorkout().getExerises().get(i).getName() + " Reps:" + User.getCurrentWorkout().getExerises().get(i).getReps() + " Sets:" + User.getCurrentWorkout().getExerises().get(i).getSets() + "\n";
            }
            exersizesTV.setText(s);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishworkoutBTN1:
                Gson gson = new Gson();
                try{
                    ExersizeExpection.validate(workoutnameET.getText().toString());
                    User.getCurrentWorkout().setName(String.valueOf(workoutnameET.getText()));
                    User.getCurrentWorkout().setDate(String.valueOf(System.currentTimeMillis()));
                    String s = gson.toJson(User.getCurrentWorkout());
                    RESTFull_services_workout rest = new RESTFull_services_workout("workout");
                    try{
                        if(rest.postRequest(new JSONObject(s))){
                            Toast.makeText(App.getAppContext(), rest.getResult(), Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(App.getAppContext(), rest.getResult(), Toast.LENGTH_SHORT).show();

                        }
                    }
                    catch (Exception e){
                        Toast.makeText(App.getAppContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }catch(ExersizeExpection e){
                    Toast.makeText(App.getAppContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }



                break;
        }
    }


}
