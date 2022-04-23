package com.example.finalproject.Expections;

import java.util.HashMap;

/**
 * Custom UserException class for the creating of exercises, inherits the exception class from Java.
 */
public class UserException extends Exception {

    /**
     *
     * @param code Sends in the error code to allow the application to show what the error is.
     */
    public UserException(String code) {
        super(code);

    }

    /**
     * Validates the creation of a user
     * @param e Takes in the name of the exercise
     * @throws UserException Throws new exception if requirements are met.
     */
    public static void validate(HashMap<String,String> e) throws UserException {
        if ((e == null || e.containsValue(""))) {
            throw new UserException("NEEDS TEXT");
        }

    }

}
