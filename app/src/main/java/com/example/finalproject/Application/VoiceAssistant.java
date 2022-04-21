package com.example.finalproject.Application;

import static android.Manifest.permission.RECORD_AUDIO;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to hold the information for the Speach data gathered from the user.
 */
public class VoiceAssistant {
    private SpeechRecognizer speechRecognizer;
    private String name;
    private String reps;
    private String sets;
    private String weight;

    /**
     * Gets the weight
     * @return Weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * Get the speech object
     * @return Speech recogniser object
     */
    public SpeechRecognizer getSpeechRecognizer(){
        return this.speechRecognizer;
    }

    /**
     * Get the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get the reps
     * @return Reps
     */
    public String getReps() {
        return reps;
    }

    /**
     * Get sets
     * @return Sets
     */
    public String getSets() {
        return sets;
    }

    /**
     * Function to run to start the voice input, sets the values in this object to the re levant information, so it can be set to the relevant textViews
     */
    public void voiceFunction(EditText nameET,EditText repsET,EditText setsET,EditText weightET) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(App.getAppContext());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                String string = "";
                if(matches != null) {
                    String[] words = matches.get(0).split(" ");
                    for(int i = 0; i< words.length;i++){
                        try{
                            if(words[i].toLowerCase().equals("named") ||words[i].toLowerCase().equals("add") || words[i].toLowerCase().equals("called")) name = words[i + 1];
                            if(words[i].toLowerCase().equals("reps")) reps = words[i - 1];
                            if(words[i].toLowerCase().equals("sets")) sets = words[i - 1];
                            if(words[i].toLowerCase().equals("kg") || words[i].toLowerCase().equals("kilograms")) weight = words[i-1];
                        }catch (Exception e){
                            Toast.makeText(App.getAppContext(), "Failed to understand, please try again or enter manually", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                try{
                    nameET.setText(name);
                    setsET.setText(sets);
                    weightET.setText(weight);
                    repsET.setText(reps);
                }catch (Exception e){
                    Toast.makeText(App.getAppContext(), "Failed to set attributes", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
        speechRecognizer.startListening(intent);

    }


}
