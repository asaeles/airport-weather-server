package com.crossover.aws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when having troubles setting a Data Point to
 * an Atmospheric Information
 * 
 * @author asaeles@gmail.com
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class SettingDataPointException extends RuntimeException {

    public SettingDataPointException(String message) {
        super("Error setting data point" + message);
    }
}
