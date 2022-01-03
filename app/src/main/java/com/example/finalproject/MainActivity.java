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

import com.vishnusivadas.advanced_httpurlconnection.PutData;

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

    public void signin(View view){

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
            User.setUsername(username);
            goworkoutPage();

        } else {
            Toast.makeText(getApplicationContext(), network.getResult(), Toast.LENGTH_LONG).show();
            Log.d("returnmess", network.getResult());

        }


    }


}