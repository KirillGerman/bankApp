package ru.meshgroup.bankApplication.controllerAdvise.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@JsonPropertyOrder({"status", "message", "time" })
public class ErrorResponse {

    private final HttpStatus status;
    private final String message;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private final ZonedDateTime time;

    public ErrorResponse(String message, HttpStatus status) {
        this.time = ZonedDateTime.now();
        this.message = message;
        this.status = status;
    }
}
