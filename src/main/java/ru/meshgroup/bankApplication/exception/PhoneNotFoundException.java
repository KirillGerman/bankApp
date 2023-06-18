package ru.meshgroup.bankApplication.exception;

import ru.meshgroup.bankApplication.dto.PhoneDataDto;

public class PhoneNotFoundException extends RuntimeException{

    private PhoneDataDto phoneDataDto;

    public PhoneNotFoundException(){
        super("Phone doesn't exists");
    }

    public PhoneNotFoundException(String message) {
        super(message);
    }

    public PhoneNotFoundException(String message, PhoneDataDto phoneDataDto) {
        super(message);
        this.phoneDataDto = phoneDataDto;
    }
}
