package com.pewde.authservice.exception;

public class UsernameAlreadyExistsException extends BadRequestException{

    public UsernameAlreadyExistsException() {
        super("Имя пользователя занято");
    }

}
