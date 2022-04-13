package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import org.json.JSONObject;

public class Fragment4 extends Fragment implements View.OnClickListener {
    private Button b;
    private Button deleteBTN;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.fragment4_layout,container,false);
        b = view.findViewById(R.id.buttonSignOut);
        deleteBTN = view.findViewById(R.id.buttonDeleteAccount);
        deleteBTN.setOnClickListener(this);
        b.setOnClickListener(this);
    return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignOut:
            {
                signOut();
            }
            case R.id.buttonDeleteAccount:
            {                Toast.makeText(App.getAppContext(), "Hello", Toast.LENGTH_SHORT).show();

                RESTFull_services_user rest = new RESTFull_services_user("user");
                if(rest.deleteRequest()){
                    signOut();
                }
            }
        }
    }

    public void signOut(){
        if(Token.deleteToken()){
            Intent intent = new Intent(Fragment4.this.getActivity(),Signup.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        else {
            Toast.makeText(App.getAppContext(), "Failed to signOut", Toast.LENGTH_SHORT).show();
        }

    }
}
