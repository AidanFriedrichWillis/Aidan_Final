package com.example.finalproject.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.Application.App;
import com.example.finalproject.Application.Homescreen;
import com.example.finalproject.R;
import com.example.finalproject.HttpServices.RESTFull_services_user;
import com.example.finalproject.Application.Token;

import org.json.JSONObject;

import java.util.HashMap;

public class Signup extends AppCompatActivity {

   private EditText fullnameET;
    private EditText usernameET;
    private EditText passwordET;
    private EditText emailET;
        private String res;
        Button signupBTN;
    private final HashMap<String,String> postData = new HashMap<String,String>();
    private RESTFull_services_user rest;

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
        if(Token.isValid()){
            goworkoutPage();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Token.isValid()){
            goworkoutPage();
        }
    }



    private void gotologin(){


        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void gologin(View view){

        gotologin();

    }
    public void goworkoutPage(){
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
        rest = new RESTFull_services_user("user");
        try {
            if(rest.postRequest(new JSONObject(postData))){
                Toast.makeText(App.getAppContext(), "Sign Up Succsessfull", Toast.LENGTH_SHORT).show();
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