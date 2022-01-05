package com.example.finalproject;

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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;

public class Fragment3 extends Fragment {
    private Spinner graphSP;
    private GraphView graphView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3_layout,container,false);

        graphSP = view.findViewById(R.id.graphSP);
        graphView = view.findViewById(R.id.graphGV);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,User.allENames());
        graphSP.setAdapter(adapter);

        graphView.addSeries(createGraph());

        graphSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                graphView.removeAllSeries();
                graphView.addSeries(createGraph());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;


    }

    public LineGraphSeries<DataPoint> createGraph(){
        String selected = graphSP.getSelectedItem().toString();
        Date currentDate = java.util.Calendar.getInstance().getTime();
        int currentyear = currentDate.getYear();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        int x = 0;
        int y = 0;
        for(int i =0; i < 12; i++ ){

            x+=1;
            y = User.pb(selected,i,currentyear);
            series.appendData(new DataPoint(x,y),true,12);

        }

        return series;

    }


}
