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
import com.example.finalproject.Expections.UserException;
import com.example.finalproject.R;
import com.example.finalproject.HttpServices.RESTFull_services_user;
import com.example.finalproject.Application.Token;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    private EditText fullnameET;
    private EditText usernameET;
    private EditText passwordET;
    private EditText emailET;
    private String res;
    private Button signupBTN;
    private final HashMap<String,String> postData = new HashMap<String,String>();
    private RESTFull_services_user rest;

    /**
     * On Create of the Activity
     * @param savedInstanceState The instance of the activity
     */
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

    /**
     *On Resume of Activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(Token.isValid()){
            goworkoutPage();
        }
    }

    /**
     * Go to login Intent Function
     */
    private void gotologin(){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to login
     * @param view Passes in the View
     */
    public void gologin(View view){
        gotologin();
    }

    /**
     * Starts the Workout Page intent
     */
    public void goworkoutPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);

    }

    /**
     * Function ran when Singup button pressed
     * @param view Psss in the view
     */
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

        try {
            UserException.validate(postData);
            sendData();
        }catch (UserException userException){
            Toast.makeText(App.getAppContext(), userException.toString(), Toast.LENGTH_SHORT).show();

        }




    }
    private void sendData(){
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