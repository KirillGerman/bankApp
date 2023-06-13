package ru.meshgroup.bankApplication.controllerAdvise.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import ru.meshgroup.bankApplication.dto.EmailDataDto;

@Getter
@Setter
public class EmailResponse extends BaseResponse {

    private String email;

    public EmailResponse(String message) {
        super(message);
    }

    public EmailResponse(String message, EmailDataDto emailDataDto) {
        super(message);
        this.email = emailDataDto.getEmail();
    }
}
