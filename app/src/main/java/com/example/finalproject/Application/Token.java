package com.example.finalproject.Application;

import android.content.Context;
import android.widget.Toast;

import com.example.finalproject.HttpServices.RESTFull_services_user;
import com.example.finalproject.Models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A class which uses the file system of the device to allow for saving and retrieving of the token sent from the server.
 * Uses temp cache files which are safe.
 */
public final class Token {

    private static String token;

    /**
     * Saves the token generated from the server, saved to file and very small in size.
     *
     * @param t The param being a string which is the token data.
     */
    public static void saveToken(String t) {
        Token.token = t.replaceAll("\"", "");
        try {
            File.createTempFile("token", null, App.getAppContext().getCacheDir());
            FileOutputStream fileOutputStream = App.getAppContext().openFileOutput("token", Context.MODE_PRIVATE);
            fileOutputStream.write(token.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();

        } catch (IOException e) {
            Toast.makeText(App.getAppContext(), "FAILED TO SAVE TOKEN", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Sends a request to the server asking if this current token is valid
     *
     * @return True if the token is valid.
     */
    public static boolean isValid() {
        return (new RESTFull_services_user("user/" + "logged").validToken());

    }

    /**
     * Get the token from the saved file Token.
     * @return A string which is the token of the user.
     */
    public static String getToken() {
        String t = null;
        try {
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
        } catch (IOException ignored) {

        }
        return t;

    }

    /**
     * Deletes the token from the application data, allowing for safe logging off/deleting of account.
     * @return True is the token has been deleted and the user data cleared from the scope of the project
     */
    public static boolean deleteToken() {
        try {
            return (App.getAppContext().deleteFile("token") && User.resetUser());
        } catch (Exception e) {
            return false;
        }


    }

    /**
     * Allows the token to be decoded if needed
     * @return The data from the token, if ever needing to be used.
     */
    public static String deCodeToken() {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        return new String(decoder.decode(chunks[1]));
    }


}
