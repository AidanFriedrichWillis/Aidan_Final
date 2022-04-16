package com.example.finalproject.Application;

import android.content.Context;
import android.widget.Toast;

import com.example.finalproject.HttpServices.RESTFull_services_user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class Token {

    private static String token;


    public static void saveToken(String t){
        Token.token = t.replaceAll("\"","");
        try{
            File.createTempFile("token",null,App.getAppContext().getCacheDir());
            FileOutputStream fileOutputStream = App.getAppContext().openFileOutput("token", Context.MODE_PRIVATE);
            fileOutputStream.write(token.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();

        }catch(IOException e){
            Toast.makeText(App.getAppContext(), "FAILED TO SAVE TOKEN", Toast.LENGTH_SHORT).show();
        }

    }

    public static boolean isValid(){
                 return (new RESTFull_services_user("user/"+"logged").validToken());


    }

    public static String getToken(){
        String t = null;
        try
        {
            FileInputStream fileInputStream = App.getAppContext().openFileInput("token");
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                Toast.makeText(App.getAppContext(), "Failed to get token", Toast.LENGTH_SHORT).show();

            } finally {
                t = stringBuilder.toString();
            }
        }catch (IOException ignored){

        }
        return t;

    }

    public static boolean deleteToken(){
        try{

            return App.getAppContext().deleteFile("token");
        }
        catch (Exception e){
            return false;
        }


    }

    public static String deCodeToken(){
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        return new String(decoder.decode(chunks[1]));
    }


}
