package ru.meshgroup.bankApplication.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import ru.meshgroup.bankApplication.controllerAdvise.response.ErrorResponse;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static ru.meshgroup.bankApplication.utils.CustomObjectMapper.getMapper;

public class ErrorUtils {

    public static void getResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.getOutputStream().println(getMapper().writeValueAsString(new ErrorResponse(message, status)));
    }
}
