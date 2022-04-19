package com.example.finalproject.Views.Fragments.main;

import android.content.Intent;
import android.graphics.Color;
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

import com.example.finalproject.Application.App;
import com.example.finalproject.R;
import com.example.finalproject.HttpServices.RESTFull_services_workout;
import com.example.finalproject.Models.User;
import com.example.finalproject.Models.Workout;
import com.example.finalproject.Views.ExeriszeInfo;

import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout linearLayout;
    private final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
   private  RESTFull_services_workout rest = new RESTFull_services_workout("workout");
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

        View view =inflater.inflate(R.layout.fragment2_layout,container,false);

        swipeRefreshLayout = view.findViewById(R.id.swipelayout2);
        linearLayout = view.findViewById(R.id.linlay);

        refresh();


        swipeRefreshLayout.setOnRefreshListener(() -> {
            refresh();
            swipeRefreshLayout.setRefreshing(false);

        });

        return view;

    }

    public void refresh(){
        linearLayout.removeAllViewsInLayout();
        TextView textView = new TextView(getActivity());
        textView.setText("EXERSIZE HISTOY:");
        textView.setTextColor(Color.BLACK);
        linearLayout.addView(textView,layoutParams);
        try{
            if(rest.getRequest()){
                ArrayList<Workout> workouts = new ArrayList<>(User.fromJson(rest.getResult()));

                for(int i = 0; i < workouts.size(); i++){
                    Button button = new Button(getActivity());
                    layoutParams.bottomMargin = 0;
                    layoutParams.leftMargin = 50;
                    layoutParams.topMargin = 50;
                    button.setText(workouts.get(i).getName());
                    button.setBackgroundColor(Color.parseColor("#F37851"));
                    button.setId(i+1);
                    button.setOnClickListener(getOnClick(i+1, workouts.get(i).getId()));
                    linearLayout.addView(button, layoutParams);
                }

            }
        }catch(Exception e){
            Toast.makeText(App.getAppContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener getOnClick(final int i, String id){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fragment2.this.getActivity(), ExeriszeInfo.class);
                intent.putExtra("index", i);
                intent.putExtra("id", id);

                Fragment2.this.startActivity(intent);
            }
        };


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
     *On Start of Activity
     */
    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }
}
