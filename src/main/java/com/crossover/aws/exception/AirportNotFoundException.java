package com.crossover.aws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to be thrown after Airport validation in controllers if
 * the IATA code is not found
 * 
 * @author asaeles@gmail.com
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class AirportNotFoundException extends RuntimeException {

    public AirportNotFoundException(String iata) {
        super("Could't find an airport with the IATA code: " + iata);
    }
}
