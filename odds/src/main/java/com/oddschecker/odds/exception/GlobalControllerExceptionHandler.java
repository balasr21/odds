package com.oddschecker.odds.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.oddschecker.odds.models.ErrorResponse;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({InvalidBetException.class,
                       InvalidOddsException.class,
                       InvalidUserException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public ErrorResponse handleBadRequest(HttpServletRequest req, Throwable exception) {
        return new ErrorResponse("Invalid request " + exception.getMessage());
    }

}
