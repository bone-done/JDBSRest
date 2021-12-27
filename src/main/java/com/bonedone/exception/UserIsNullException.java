package com.bonedone.exception;

public class UserIsNullException extends Exception {

    public UserIsNullException(String email) {
        super("user with email " + email + " NOT found");
    }

    public UserIsNullException(int id) {
        super("user with Id " + id + " NOT found");
    }
}
