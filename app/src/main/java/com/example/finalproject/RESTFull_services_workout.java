package com.example.finalproject;

import org.json.JSONObject;

import java.util.HashMap;

public class RESTFull_services_workout extends Services {

    public RESTFull_services_workout(String route){
        this.route = route;
    }

    public boolean getRequest(JSONObject data) {
        return startRequest("GET");
    }

    public boolean putRequest() {
        return false;
    }


}
