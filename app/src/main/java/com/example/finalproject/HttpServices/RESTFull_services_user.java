package com.example.finalproject.HttpServices;

/**
 * A child class of services, if there are any functions that need to be overridden from the base class.
 */
public class RESTFull_services_user extends Services{

    /**
     * Creates the service object, allowing functions to be called from within this class.
     * @param route Takes in the Route which is used to make the connection, we can also put URL Params within the route itself.
     */
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
