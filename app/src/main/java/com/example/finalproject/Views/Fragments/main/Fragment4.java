package com.example.finalproject.Views.Fragments.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.finalproject.Application.App;
import com.example.finalproject.R;
import com.example.finalproject.HttpServices.RESTFull_services_user;
import com.example.finalproject.Application.Token;
import com.example.finalproject.Views.Signup;

public class Fragment4 extends Fragment implements View.OnClickListener {
    private Button b;
    private Button deleteBTN;
    private SwipeRefreshLayout swipeRefreshLayout;
    RESTFull_services_user rest = new RESTFull_services_user("user");

    /**
     * On crete for the settings menu fragment, init all UI aspects.
     * @param inflater Inflater Object
     * @param container Container Object
     * @param savedInstanceState Instance of Application
     * @return The view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment4_layout, container, false);
        b = view.findViewById(R.id.buttonSignOut);
        deleteBTN = view.findViewById(R.id.buttonDeleteAccount);
        swipeRefreshLayout = view.findViewById(R.id.swipelayout4);


        deleteBTN.setOnClickListener(this);
        b.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(false);
        });
        return view;
    }

    /**
     *On click of the Buttons in view
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignOut: {
                signOut();
            }
            case R.id.buttonDeleteAccount: {
                Toast.makeText(App.getAppContext(), "Hello", Toast.LENGTH_SHORT).show();

                if (rest.deleteRequest()) {
                    signOut();
                }
            }
        }
    }

    /**
     * SignPut Linked to the Signout Button, Deletes User data and removes the activities  from the stack.
     */
    public void signOut() {
        if (Token.deleteToken()) {
            Intent intent = new Intent(Fragment4.this.getActivity(), Signup.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            Toast.makeText(App.getAppContext(), "Failed to signOut", Toast.LENGTH_SHORT).show();
        }

    }
}
