package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Signup extends AppCompatActivity {

    EditText fullnameET;
    EditText usernameET;
    EditText passwordET;
    EditText emailET;

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




    public void signup(View view) {

        String fullname, username, password, email;
        fullname = String.valueOf(fullnameET.getText());
        username = String.valueOf(usernameET.getText());
        password = String.valueOf(passwordET.getText());
        email = String.valueOf(emailET.getText());


        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
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
                PutData putData = new PutData("http://10.65.196.13/LoginRegister/insertUserLogic.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        if(result.equals("Account created Succsessfully")){
                            Toast.makeText(getApplicationContext(), "SignUpSucc", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "SignUp Fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                //End Write and Read data with URL
            }
        });




    }
}