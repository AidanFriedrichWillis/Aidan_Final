package com.example.finalproject.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.finalproject.R;
import com.example.finalproject.HttpServices.RESTFull_services_user;
import com.example.finalproject.Application.Token;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private EditText usernameET;
    private EditText passwordET;
    private Button signupBTN;
    private final HashMap<String,String> putdata = new HashMap<String,String>();
    private String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        usernameET = findViewById(R.id.usernamesET);
        passwordET = findViewById(R.id.passwordET);
        signupBTN = findViewById(R.id.signupBTN);
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        Log.d("ipxd" ,ipAddress);

    }
    public void goworkoutPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);

    }
    public void signUpPage(){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public void goSignUp(View v){
        signUpPage();
    }

    public void signin(View view) {

        try {
            putdata.put("username",String.valueOf(usernameET.getText()));
            putdata.put("password",String.valueOf(passwordET.getText()));

        }catch(Exception e){

        }
        RESTFull_services_user rest = new RESTFull_services_user("user/signin");

        try{
            if(rest.postRequest(new JSONObject(putdata))){
                Token.saveToken(rest.getResult());
                goworkoutPage();
            }
            else{
                Toast.makeText(this, rest.getResult(), Toast.LENGTH_SHORT).show();

            }

        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }



    }


}