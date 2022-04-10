package com.example.finalproject;

import java.util.HashMap;

public class RESTFull_services_user implements Services{

    private String route ;
    private NetworkThread request;

    public RESTFull_services_user(String route){
        this.route = route;
    }

    @Override
    public String getRequest(String Data) {
        return null;
    }

    @Override
    public String putRequest() {
        return null;
    }

    @Override
    public String postRequest(HashMap postData) {
        request = new NetworkThread(route,"POST",postData);
        request.start();
        if(request.finish()){
            return request.getResultData();
        }
        else return "Failed to start request";
    }

    @Override
    public String deleteRequest() {
        return null;
    }



}
