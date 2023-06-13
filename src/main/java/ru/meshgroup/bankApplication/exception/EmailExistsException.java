package ru.meshgroup.bankApplication.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import ru.meshgroup.bankApplication.dto.EmailDataDto;

@Getter
public class EmailExistsException extends RuntimeException{

    private EmailDataDto emailDataDto;

    public EmailExistsException(String message , EmailDataDto emailDataDto) {
        super(message);
        this.emailDataDto = emailDataDto;
    }

    public EmailExistsException(String message) {
        super(message);
    }

    public EmailExistsException() {
        super("Email not exists");
    }
}
