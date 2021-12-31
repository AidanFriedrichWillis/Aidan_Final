package com.example.finalproject;

public final class Test {

    private static String name;

    private Test(){

        this.name ="";
    }
    public static void setName(String n){
        name = n;
    }

    public static String returnName(){
        return name;
    }



}
