package com.example.finalproject;

import org.json.JSONObject;

public abstract class Services {
     protected String route;
     protected String result;
     protected NetworkThread request;
     private JSONObject postData = null;

     abstract boolean putRequest();


      boolean postRequest(JSONObject postData){
           this.postData = postData;
           return startRequest("POST");
      };


     public boolean deleteRequest(){
       return startRequest("DELETE");
     }

     public boolean getRequest() {
          return startRequest("GET");
     }

     protected boolean startRequest(String method){
          request = new NetworkThread(route,method,postData);
          request.start();
          if(request.finish()){
               result = request.getResultData();
               if(!request.isError()){
                    return true;
               }
               else{
                    return false;
               }
          }
          else return false;
     }

     public String getResult() {
          return result;
     }




}
