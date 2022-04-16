package com.example.finalproject.HttpServices;

public class RESTFull_services_user extends Services{


    public RESTFull_services_user(String route){
        this.route = route;
    }

    public boolean getRequest() {
        return false;
    }

    public boolean putRequest() {
        return false;
    }

    public boolean validToken(){
        return startRequest("GET");
    }

}
