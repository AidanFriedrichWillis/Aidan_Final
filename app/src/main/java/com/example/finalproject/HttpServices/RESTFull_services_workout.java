package com.example.finalproject.HttpServices;

import org.json.JSONObject;

/**
 * Another child class of services, allows for the workout route to be called to.
 */
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
