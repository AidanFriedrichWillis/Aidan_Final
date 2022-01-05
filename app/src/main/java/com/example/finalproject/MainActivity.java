package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class MainActivity extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private Button signupBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.usernamesET);
        passwordET = findViewById(R.id.passowrdsET);
        signupBTN = findViewById(R.id.signupBTN);
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        Log.d("ipxd" ,ipAddress);

    }
    public void goworkoutPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);

    }

    public void signin(View view) throws JsonProcessingException, JSONException {

        String username,password;
        username = String.valueOf(usernameET.getText());
        password = String.valueOf(passwordET.getText());
        String[] field = new String[2];
        field[0] = "username";
        field[1] = "password";
        //Creating array for data
        String[] data = new String[2];
        data[0] = username;
        data[1] = password;

        Network network = new Network(field,data,"loginLogic");


        if (network.getResult().startsWith("c")) {
            Toast.makeText(getApplicationContext(), "login succ", Toast.LENGTH_SHORT).show();
            Log.d("succmess", network.getResult());

            String jsonWork = "";
            jsonWork = network.getResult().replace("correct","");
            ArrayList<Workout> workouts = new ArrayList<>();

            if(jsonWork != "") {


                JSONArray jsonArray = new JSONArray(jsonWork);

                for(int i = 0;i<jsonArray.length();i++){

                    JSONObject tempo = new JSONObject(jsonArray.get(i).toString());

                    Workout w = new Workout(tempo.get("name").toString(),tempo.get("date").toString());

                    JSONArray tempa = new JSONArray(tempo.getJSONArray("exerises").toString());

                    for(int j = 0; j < tempa.length();j++){

                        String name;
                        int reps;
                        int sets;
                        int weight;
                        JSONObject tempo2 = new JSONObject(tempa.get(j).toString());

                        name = (String) tempo2.get("name");
                        reps = (int) tempo2.get("reps");
                        sets = (int) tempo2.get("sets");
                        weight = (int) tempo2.get("weight");
                        Exerise e = new Exerise(name,reps,sets,weight);
                        w.addExersize(e);

                    }

                    workouts.add(w);


                }

            }



            User.setUsername(username);
            User.setWorkouts(workouts);
            goworkoutPage();

        } else {
            Toast.makeText(getApplicationContext(), network.getResult(), Toast.LENGTH_LONG).show();
            Log.d("returnmess", network.getResult());

        }


    }


}