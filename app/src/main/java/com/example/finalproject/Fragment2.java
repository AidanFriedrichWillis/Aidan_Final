package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment2_layout,container,false);

        swipeRefreshLayout = view.findViewById(R.id.swipelayout2);
        linearLayout = view.findViewById(R.id.linlay);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeRefreshLayout.setRefreshing(false);

            }
        });


        return view;

    }

    public void refresh(){
        linearLayout.removeAllViews();
        RESTFull_services_workout rest = new RESTFull_services_workout("workout");
        try{
            if(rest.getRequest()){
                ArrayList<Workout> workouts = User.fromJson(rest.getResult());
                for(int i = 0; i < workouts.size(); i++){
                    Button button = new Button(getActivity());
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    button.setText(workouts.get(i).getName());
                    button.setId(i);
                    button.setOnClickListener(getOnClick(i));
                    linearLayout.addView(button, layoutParams);
                }
            }
        }catch(Exception e){
            Toast.makeText(App.getAppContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }



    }

    private View.OnClickListener getOnClick(final int i){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ExeriszeInfo.class);
                intent.putExtra("index",i);
                startActivity(intent);
            }
        };


    }



}
