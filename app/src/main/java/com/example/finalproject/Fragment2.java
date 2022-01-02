package com.example.finalproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

        if(!User.getWorkouts().isEmpty()){
            for(int i = 0; i < User.getWorkouts().size(); i++){

                Button button = new Button(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                button.setText(User.getWorkouts().get(i).getName());
                button.setId(i);

                linearLayout.addView(button, layoutParams);


            }



        }


    }

    public void addBTN(View view){



    }



}
