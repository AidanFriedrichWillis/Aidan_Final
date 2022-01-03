package com.example.finalproject;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

 public class Network {

    private  String[] field;
    private  String[] data;
    private  String type;
    private String result;

     public String getResult() {
         return result;
     }

     public Network(String [] field, String[] data, String type){
        this.field = field;
        this.data = data;
        this.type = type;
        PutData putData = new PutData("http://10.65.196.30/LoginRegister/"+type+".php", "POST", field, data);
        if (putData.startPut()) {
            if (putData.onComplete()) {
                result=  putData.getResult();
            }
        }


    }



}
