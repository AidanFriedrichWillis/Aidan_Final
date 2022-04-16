package com.example.finalproject.HttpServices;

import com.example.finalproject.Application.Token;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkThread extends Thread{

    private final String route;
    private final String requestType;
    private final JSONObject data;
    private String resultData;
    private int responseCode;
    private String responseMess;
    private boolean isError;

    public NetworkThread(String route, String requestType, JSONObject data){
        this.route = route;
        this.requestType = requestType;
        this.data = data;
    }

    public boolean finish() {
        while (true) {
            if (!this.isAlive()) {
                return true;
            }
        }
    }

    @Override
    public void run(){
        try {
            String UTF8 = "UTF-8", iso = "iso-8859-1";
            String urlSt = "http://10.65.199.137/api/";
            URL url = new URL(urlSt + route);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(this.requestType);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Authorization", Token.getToken());
            if(requestType == "POST"){
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, UTF8));
                bufferedWriter.write("data="+data.toString());
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
            }
            httpURLConnection.connect();
            responseCode = httpURLConnection.getResponseCode();
            responseMess = httpURLConnection.getResponseMessage();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
            StringBuilder result = new StringBuilder();
            String result_line;
            while ((result_line = bufferedReader.readLine()) != null) {
                result.append(result_line);
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            resultData = result.toString();
        } catch (IOException e) {
            isError = true;
            resultData = responseCode + responseMess;
        }
    }

    public String getResultData() {
        return resultData;
    }

    public boolean isError() {
        return isError;
    }
}
