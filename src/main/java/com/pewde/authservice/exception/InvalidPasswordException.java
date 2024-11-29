package com.pewde.authservice.exception;

public class InvalidPasswordException extends UnauthorizedException{

    public InvalidPasswordException() {
        super("Введен неверный пароль");
    }

}
