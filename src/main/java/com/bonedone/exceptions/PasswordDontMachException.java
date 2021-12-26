package com.bonedone.exceptions;

public class PasswordDontMachException extends Throwable {
    public PasswordDontMachException() {
        super("password didn't match");
    }
}
