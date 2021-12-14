package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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


    }

    public void signin(View view){

        String username,password;
        username = String.valueOf(usernameET.getText());
        password = String.valueOf(passwordET.getText());

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {

                String[] field = new String[2];
                field[0] = "username";
                field[1] = "password";
                //Creating array for data
                String[] data = new String[2];
                data[0] = username;
                data[1] = password;
                //todo: MAke it check for usernames first
//                PutData putData = new PutData("https://homepages.shu.ac.uk/~b7020211/Login/loginlogic.php", "POST", field, data);
                PutData putData = new PutData("http://10.65.197.168/LoginRegister/loginlogic.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();

                        if(result.equals("correct")){
                            Toast.makeText(getApplicationContext(), "login succ", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                            Log.d("returnmess",result);

                        }
                    }
                }
                //End Write and Read data with URL
            }
        });




    }


}