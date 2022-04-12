package com.example.finalproject;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public final class GoToPage
{

   private static AppCompatActivity appCompatActivity = new AppCompatActivity();

    public static void goToWorkoutPage(){
        Intent intent = new Intent(App.getAppContext(), MainPage.class);
        appCompatActivity.startActivity(intent);
    }
}
