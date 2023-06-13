package ru.meshgroup.bankApplication.controllerAdvise;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.meshgroup.bankApplication.controllerAdvise.response.BaseResponse;
import ru.meshgroup.bankApplication.controllerAdvise.response.EmailResponse;
import ru.meshgroup.bankApplication.exception.*;
import ru.meshgroup.bankApplication.controllerAdvise.response.AccountResponse;

@RestControllerAdvice
public class ExceptionHandlerService extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotEnoughMoneyException.class})
    public ResponseEntity<?> constraintViolationException(NotEnoughMoneyException ex, WebRequest request) {
       return ResponseEntity.badRequest().body(new AccountResponse(ex.getMessage(), ex.getAccountDto()));
    }

    @ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<?> constraintViolationException(AccountNotFoundException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new BaseResponse());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> constraintViolationException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new AccountResponse(ex.getMessage()));
    }

    @ExceptionHandler({EmailExistsException.class})
    public ResponseEntity<?> constraintViolationException2(EmailExistsException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new EmailResponse(ex.getMessage(), ex.getEmailDataDto()));
    }

    @ExceptionHandler({UserNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<?> constraintViolationException3(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new BaseResponse(ex.getMessage()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> constraintViolationException4(RuntimeException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(new BaseResponse());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex,
             HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, new BaseResponse(), new HttpHeaders(), HttpStatus.ACCEPTED, request);
    }


}
