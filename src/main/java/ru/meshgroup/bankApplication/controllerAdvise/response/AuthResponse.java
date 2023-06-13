package ru.meshgroup.bankApplication.controllerAdvise.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.meshgroup.bankApplication.dto.UserDto;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse extends BaseResponse {

    @JsonProperty("jwt-token")
    private String token;

    private UserDto user;

    private String message;


}