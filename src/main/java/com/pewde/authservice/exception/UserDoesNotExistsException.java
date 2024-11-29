package com.pewde.authservice.exception;

public class UserDoesNotExistsException extends BadRequestException{

    public UserDoesNotExistsException() {
        super("Пользователя с таким ником не существует");
    }

}
