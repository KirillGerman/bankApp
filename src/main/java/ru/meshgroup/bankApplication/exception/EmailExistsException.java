package ru.meshgroup.bankApplication.exception;

import lombok.Getter;
import ru.meshgroup.bankApplication.dto.EmailDataDto;

@Getter
public class EmailExistsException extends RuntimeException{

    private EmailDataDto emailDataDto;


    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
        super("Email not exists");
    }
}
