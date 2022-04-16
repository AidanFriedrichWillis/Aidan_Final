package com.example.finalproject.Expections;

public class ExersizeExpection extends Exception {


    public ExersizeExpection(String code) {
        super(code);

    }

    public static void validate(String e) throws ExersizeExpection {
        if ((e == null || e.equals(""))) {
            throw new ExersizeExpection("NEEDS TEXT");
        }
        if (e.length()>50) {
            throw new ExersizeExpection("TOO LONG");
        }

    }

}
