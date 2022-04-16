package com.example.finalproject.Views.Fragments.main;

import static com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.finalproject.R;
import com.example.finalproject.HttpServices.RESTFull_services_workout;
import com.example.finalproject.Models.User;

import org.json.JSONArray;
import org.json.JSONException;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Date;

public class Fragment3 extends Fragment {
    private Spinner graphSP;
    private BarChart barChart;
    private SwipeRefreshLayout swipeRefreshLayout;
    private final String[] xAxis = {"jan","feb","march","april","may","june","july","august","sept","october","november","december"};
    private RESTFull_services_workout rest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3_layout,container,false);
        swipeRefreshLayout = view.findViewById(R.id.swipelayout3);
        graphSP = view.findViewById(R.id.graphSP);
        barChart = view.findViewById(R.id.idBarChart);


        onLoad();


        graphSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                createBarChart();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            onLoad();
            swipeRefreshLayout.setRefreshing(false);

        });



        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        onLoad();
    }

    @Override
    public void onStart() {
        super.onStart();
        onLoad();
    }

    public void createBarChart(){
       barChart.setData(new BarData(new BarDataSet(createGraph(),"set")));
       barChart.getDescription().setEnabled(false);
       barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxis));
        barChart.getXAxis().setPosition(BOTTOM);

        barChart.getXAxis().setGranularity(1);
        barChart.getXAxis().setGranularityEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);
        barChart.getData().setBarWidth(1f);
        barChart.getXAxis().setAxisMinimum(0);
        barChart.animate();
        barChart.invalidate();



    }

    public void onLoad(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item, User.allENames());
        graphSP.setAdapter(adapter);

    }

    public ArrayList<BarEntry> createGraph(){
        String selected = graphSP.getSelectedItem().toString();
        ArrayList<BarEntry> series = new ArrayList<>();
        rest = new RESTFull_services_workout("workout/where/search?name="+selected);
        if(rest.getRequest()){
            try {
                JSONArray dataPoints = new JSONArray(rest.getResult());
                int index = 1;
                for(float i =1f; i < 12f; i += 1f ){
                    series.add(new BarEntry(i, Float.parseFloat(String.valueOf(dataPoints.get(index)))));
                    index++;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return series;

    }


}
