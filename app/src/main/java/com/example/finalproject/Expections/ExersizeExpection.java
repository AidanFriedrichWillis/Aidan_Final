package com.example.finalproject.Expections;

/**
 * Custom expection class for the creating of exercises, inherits the exception class from Java.
 */
public class ExersizeExpection extends Exception {

    /**
     *
     * @param code Sends in the error code to allow the application to show what the error is.
     */
    public ExersizeExpection(String code) {
        super(code);

    }

    /**
     * Validates the creation of an exercise
     * @param e Takes in the name of the exercise
     * @throws ExersizeExpection Throws new exception if requirements are met.
     */
    public static void validate(String e) throws ExersizeExpection {
        if ((e == null || e.equals(""))) {
            throw new ExersizeExpection("NEEDS TEXT");
        }
        if (e.length()>50) {
            throw new ExersizeExpection("TOO LONG");
        }

    }

}
