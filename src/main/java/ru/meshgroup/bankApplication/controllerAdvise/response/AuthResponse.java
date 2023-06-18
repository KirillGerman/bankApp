package ru.meshgroup.bankApplication.controllerAdvise.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class AuthResponse {

    @Schema(example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMSIsImV4cCI6MTY5NTY3OTIwMH0.Cx0NK0W_Qn1zilHici8v_90mloN0ZF43GV_N7B7px8LFlsCu_82vpTF5iWTiJFUNehWYP5BHn3D3LLRQLsq9cA" )
    private String token;

    @Schema(example = "11" )
    private String userId;

}