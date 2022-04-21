package com.example.finalproject.Views.Fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.finalproject.Application.App;
import com.example.finalproject.Expections.ExersizeExpection;
import com.example.finalproject.R;
import com.example.finalproject.HttpServices.RESTFull_services_workout;
import com.example.finalproject.Models.User;
import com.google.gson.Gson;

import org.json.JSONObject;

public class Fragment1 extends Fragment  implements View.OnClickListener {
    private TextView exersizesTV;
    private Button finishBTn;
    private EditText workoutnameET;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RESTFull_services_workout rest = new RESTFull_services_workout("workout");

    /**
     * On crete for the settings menu fragment, init all UI aspects.
     * @param inflater Inflater Object
     * @param container Container Object
     * @param savedInstanceState Instance of Application
     * @return The view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment1_layout,container,false);
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

    /**
     *  The function that runs when the page is refreshed
     */
    public void refresh(){

        String s = "";
        if(!User.getCurrentWorkout().getExerises().isEmpty() ) {
            for (int i = 0; i < User.getCurrentWorkout().getExerises().size(); i++) {
                s += "Exersize: " + User.getCurrentWorkout().getExerises().get(i).getName() + " Reps:" + User.getCurrentWorkout().getExerises().get(i).getReps() + " Sets:" + User.getCurrentWorkout().getExerises().get(i).getSets() + "\n";
            }
            exersizesTV.setText(s);
        }


    }

    /**
     *On Resume of Activity
     */
    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    /**
     * On click of the buttons in the View
     * @param v View
     */
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
