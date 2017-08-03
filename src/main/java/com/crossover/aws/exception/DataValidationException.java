package com.crossover.aws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * General data validation exception, but is currently thrown from Data Point
 * complex atmospheric values validation
 * 
 * @author asaeles@gmail.com
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class DataValidationException extends RuntimeException {

    public DataValidationException(String message) {
        super("Data validation error: " + message);
    }
}
