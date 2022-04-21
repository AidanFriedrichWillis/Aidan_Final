package com.example.finalproject.Views;

import static android.Manifest.permission.RECORD_AUDIO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.finalproject.Application.App;
import com.example.finalproject.Application.VoiceAssistant;
import com.example.finalproject.Models.Exerise;
import com.example.finalproject.R;
import com.example.finalproject.Models.User;

public class AddExersize extends AppCompatActivity {


    private EditText nameET;
    private EditText repsET;
    private EditText setsET;
    private Button addexersieBTN;
    private EditText weightET;
    private ImageButton voiceAsBtn;
    private VoiceAssistant voiceAssistant;
    /**
     * On Create of the Activity
     * @param savedInstanceState The instance of the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        ActivityCompat.requestPermissions(this,new String[]{RECORD_AUDIO}, PackageManager.PERMISSION_GRANTED);
        setContentView(R.layout.activity_add_exersize);
        nameET = findViewById(R.id.exersizeNameET);
        repsET = findViewById(R.id.repsET);
        setsET = findViewById(R.id.setsET);
        addexersieBTN = findViewById(R.id.finishAddBtn);
        weightET = findViewById(R.id.weightET);
        voiceAsBtn = findViewById(R.id.voiceBtn);

    }

    /**
     *  Funtion on click for speak btn
     * @param view Takes in app view
     */
    public void SpeakBtn(View view){
        voiceAssistant = new VoiceAssistant();
        voiceAssistant.voiceFunction(nameET,repsET,setsET,weightET);


    }
    /**
     * On click function to add exercise button
     * @param view Passes in a View
     */
    public void add(View view){

        try{
            Exerise exerise = new Exerise(String.valueOf(nameET.getText()),Integer.parseInt(String.valueOf(repsET.getText())),Integer.parseInt(String.valueOf(setsET.getText())),Integer.parseInt(String.valueOf(weightET.getText())));
            User.getCurrentWorkout().addExersize(exerise);
            finish();

        }catch (Exception e){
            Toast.makeText(App.getAppContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    public void plusOne(View view){addBy(1,repsET);}
    public void plusTwo(View view){addBy(1,setsET); }
    public void plusThree(View view){addBy(5,weightET);}
    public void minusOne(View view){addBy(-1,repsET);}
    public void minusTwo(View view){addBy(-1,setsET);}
    public void minusThree(View view){addBy(-5,weightET);}

    /**
     * Adds the value to the Weight Reps and sets
     * @param value To add by.
     * @param editText Edit text to add to.
     */
    private void addBy(int value, EditText editText){
        int currentValue = 0;
            try {
                currentValue =Integer.parseInt(String.valueOf(editText.getText()));
            }catch(Exception e){
                currentValue = 0;
            }
            if(!((currentValue+value) <0)){
                editText.setText(String.valueOf(currentValue+value));
            }
    }





}