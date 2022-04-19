package com.example.finalproject.HttpServices;

import org.json.JSONObject;

/**
 * An abstract class which allows child classes to be created, this class holds the relevant data from the requests, as well as starts them accordingly.
 * Uses RESTFull designs, Sends objects in JSOSN format as a stateless transfer. Allows for the Request type of POST/PUT/DELETE/GET
 */
public abstract class Services {
     protected String route;
     protected String result;
     protected NetworkThread request;
     private JSONObject postData = null;

     abstract boolean putRequest();
     /**
      * Starts a POST request to the server, runs start request as POST.
      * @param postData Takes in a JSON object, which will be used to send various data to the server.
      * @return True if the startrequest() function is true.
      */
      public boolean postRequest(JSONObject postData){
           this.postData = postData;
           return startRequest("POST");
      }
     /**
      * Starts a Delete request, starts a Request
      * @return True if startRequest() is true
      */
     public boolean deleteRequest(){
       return startRequest("DELETE");
     }
     /**
      * Starts a GET request, starts a Request
      * @return True if startRequest() is true
      */
     public boolean getRequest() {
          return startRequest("GET");
     }
     /**
      * Starts a request with a new network thread, this thread is then waited for to finish by this main thread.
      * The data in this object is then saved to be accessed throughout the application.
      * @param method Takes in the request type
      * @return True if the connection was a success by checking against isError(),
      */
     protected boolean startRequest(String method){
          request = new NetworkThread(route,method,postData);
          request.start();
          if(request.finish()){
               result = request.getResultData();
               return !request.isError();
          }
          else return false;
     }
     /**
      *
      * @return Result from request.
      */
     public String getResult() {
          return result;
     }

}
