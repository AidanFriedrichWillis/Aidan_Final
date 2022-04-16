package com.example.finalproject.HttpServices;

import org.json.JSONObject;

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
