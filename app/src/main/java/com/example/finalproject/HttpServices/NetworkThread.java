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

/**
 * Network thread class, Http requests require A secondary thread, this inherits from the Thread java class.
 */
public class NetworkThread extends Thread{

    private final String route;
    private final String requestType;
    private final JSONObject data;
    private String resultData;
    private int responseCode;
    private String responseMess;
    private boolean isError;

    /**
     * Creates the network object based on various datapoints.
     * @param route This is the string of the route which will be added to the URL to route the request to the correct place in the controller.
     * @param requestType The type of request, GET/POST/PUT/DELETE. These are Restful and correlated with CRUD Operations.
     * @param data The Json object benign sent to the server, allowing for easily readable variables to be read from JS
     */
    public NetworkThread(String route, String requestType, JSONObject data){
        this.route = route;
        this.requestType = requestType;
        this.data = data;
    }

    /**
     * Allows the thread to be watched by the main thread, to see when the data has been received from the server. Works as a Custom ASYNC function.
     * @return True is the Run() function has finished and the response has been received.
     */
    public boolean finish() {
        while (true) {
            if (!this.isAlive()) {
                return true;
            }
        }
    }

    /**
     * The run function being overridden from the Thread class, this is what runs on creation of the thread.
     * This function will generate a HTTP conection and allow us to write data to the URL/Route this uses the Authorization from the token in Token.class.
     * If there is a failed response we save the error code in the IOException catch
     * The data received from the server is saved within this object.
     */
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

    /**
     *
     * @return The resultData from the class
     */
    public String getResultData() {
        return resultData;
    }
    /**
     *
     * @return True if the connection or response was an error.
     */
    public boolean isError() {
        return isError;
    }
}
