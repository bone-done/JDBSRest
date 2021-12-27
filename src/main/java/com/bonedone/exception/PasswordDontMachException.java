package com.bonedone.exception;

public class PasswordDontMachException extends Throwable {
    public PasswordDontMachException() {
        super("password didn't match");
    }
}
