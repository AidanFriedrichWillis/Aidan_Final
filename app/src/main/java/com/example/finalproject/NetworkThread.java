package com.example.finalproject;

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
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class NetworkThread extends Thread{

    private String route;
    private String requestType;
    private HashMap data;
    private String resultData;
    private String jsondtring = "";
    private int responseCode;
    private String responseMess;
    public NetworkThread(String route, String requestType, HashMap data){
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
            URL url = new URL("http://10.65.199.35/api/" + route);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(this.requestType);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Authorization", "222");
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, UTF8));
            data.forEach((key,value) -> {
                jsondtring = jsondtring + key + "=" + value + "&";
            });
            bufferedWriter.write(jsondtring);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
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
            resultData = String.valueOf(responseCode) + responseMess;
        }
    }

    public String getResultData() {
        return resultData;
    }

    public int getResponseCode() {
        return responseCode;
    }
}