package ru.meshgroup.bankApplication.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.meshgroup.bankApplication.dto.UserDto;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    @JsonProperty("jwt-token")
    private String token;

    @JsonPropertyOrder()
    private UserDto user;

}