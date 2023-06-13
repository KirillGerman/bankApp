package ru.meshgroup.bankApplication.controllerAdvise.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class BaseResponse {


    private LocalDateTime currentTime = LocalDateTime.now();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public BaseResponse(){

    }

    public BaseResponse(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" + "message:'" + message + '\'' + '}';
    }
}
