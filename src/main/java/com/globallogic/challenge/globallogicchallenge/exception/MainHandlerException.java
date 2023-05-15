package com.globallogic.challenge.globallogicchallenge.exception;

import com.globallogic.challenge.globallogicchallenge.exception.errorModels.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class MainHandlerException {

    @ExceptionHandler({ NotFoundEx.class }) // Clase de errorUserNotFoundException
    public ResponseEntity<ErrorMessage> notFoundRequestEx(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(List.of(exception.getMessage()), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ BadRequestEx.class }) // Clase de errorUserNotFoundException
    public ResponseEntity<ErrorMessage> badRequestEx(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(List.of(exception.getMessage()), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ UnauthorizedEx.class }) // Clase de errorUserNotFoundException
    public ResponseEntity<ErrorMessage> unauthorizedEx(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(List.of(exception.getMessage()), HttpStatus.UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ ForbiddenEx.class }) // Clase de errorUserNotFoundException
    public ResponseEntity<ErrorMessage> ForbiddenEx(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(List.of(exception.getMessage()), HttpStatus.FORBIDDEN), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ InternalServerErrorEx.class }) // Clase de errorUserNotFoundException
    public ResponseEntity<ErrorMessage> internalServerErrorEx(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(List.of(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> validationException(MethodArgumentNotValidException ex) {

        List<String> errs = ex.getBindingResult().getAllErrors()
                                                 .stream()
                                                    .map(err -> err.getDefaultMessage())
                                                    .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorMessage(errs, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorMessage> unknownException(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage(List.of(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
