package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

   private EditText fullnameET;
    private EditText usernameET;
    private EditText passwordET;
    private EditText emailET;
        private String res;
        Button signupBTN;
    private HashMap<String,String> postData = new HashMap<String,String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
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

        try {
            postData.put("fullname",fullname);
            postData.put("username",username);
            postData.put("email",email);
            postData.put("password",password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        RESTFull_services_user rest = new RESTFull_services_user("user");

        try {
            if(rest.postRequest(new JSONObject(postData))){
                gotologin();
            }
            else{
                Toast.makeText(App.getAppContext(), rest.getResult(), Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();

        }


    }
}