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

public class Signup extends AppCompatActivity {

   private EditText fullnameET;
    private EditText usernameET;
    private EditText passwordET;
    private EditText emailET;

    Button signupBTN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullnameET = findViewById(R.id.fullnameET);
        usernameET= findViewById(R.id.usernameET);
        emailET = findViewById(R.id.emailET);
        passwordET = findViewById(R.id.passwordET);

        signupBTN = findViewById(R.id.signupBTN);

            }


    private void gotologin(){


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void gologin(View view){

        gotologin();

    }
    public void gotoHome(View view){

        Intent intent = new Intent(this, Homescreen.class);
        startActivity(intent);

    }
    public void workout(View view){

        Intent intent = new Intent(this, WorkoutPage.class);
        startActivity(intent);


    }
    public void tab(View view){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);



    }


    public void signup(View view) {

        String fullname, username, password, email;
        fullname = String.valueOf(fullnameET.getText());
        username = String.valueOf(usernameET.getText());
        email = String.valueOf(emailET.getText());
        password = String.valueOf(passwordET.getText());
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                String[] field = new String[4];
                field[0] = "fullname";
                field[1] = "username";
                field[2] = "password";
                field[3] = "email";
                //Creating array for data
                String[] data = new String[4];
                data[0] = fullname;
                data[1] = username;
                data[2] = password;
                data[3] = email;

                PutData putData = new PutData("http://10.65.197.139/LoginRegister/insertUserLogic.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if(result.equals("Account created Succsessfully")){
                            Toast.makeText(getApplicationContext(), "SignUpSucc", Toast.LENGTH_SHORT).show();
                            gotologin();
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