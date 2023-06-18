package ru.meshgroup.bankApplication.controllerAdvise;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.meshgroup.bankApplication.controllerAdvise.response.ErrorResponse;
import ru.meshgroup.bankApplication.exception.AccountNotFoundException;
import ru.meshgroup.bankApplication.exception.EmailExistsException;
import ru.meshgroup.bankApplication.exception.NotEnoughMoneyException;
import ru.meshgroup.bankApplication.exception.UserNotFoundException;

import java.text.ParseException;

@RestControllerAdvice
public class ExceptionHandlerService extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, AccountNotFoundException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<?> handleEntityNotFoundException(RuntimeException ex, ServletWebRequest request) {
        var message = ex.getMessage() + " for path " + request.getRequest().getRequestURI() ;
        return buildResponse(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({EmailExistsException.class})
    public ResponseEntity<?> handleEntityExistsException(RuntimeException ex, WebRequest request) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotEnoughMoneyException.class, IllegalArgumentException.class, PermissionDeniedDataAccessException.class})
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, ServletWebRequest request) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({javax.validation.ConstraintViolationException.class })
    public ResponseEntity<?> handleValidationException(RuntimeException ex, WebRequest request) {
        return buildResponse("Dto validation exception", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class })
    public ResponseEntity<?> handleConstrainException(RuntimeException ex, WebRequest request) {
        return buildResponse("Entity already exists", HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage(), status), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new ErrorResponse(ex.getMessage(), status), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<?> buildResponse(String message, HttpStatus status){
        return ResponseEntity.status(status).body(new ErrorResponse(message, status));
    }


}
