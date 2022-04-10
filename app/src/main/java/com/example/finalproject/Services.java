package com.example.finalproject;

import org.json.JSONObject;

import java.util.HashMap;

public interface Services {

    String getRequest(String url);
    String putRequest();
    String postRequest(HashMap postData);
    String deleteRequest();

}
