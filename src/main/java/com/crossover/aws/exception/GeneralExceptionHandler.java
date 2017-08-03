package com.crossover.aws.exception;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * A very neat Spring solution to act as a fallback for all MVC exceptions
 * and to be able to make use of them in the response status and body
 * 
 * @author asaeles@gmail.com
 */
@ControllerAdvice
public class GeneralExceptionHandler {

    HttpStatus resolveAnnotatedResponseStatus(Exception exception) {
        ResponseStatus annotation = findMergedAnnotation(exception.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @ExceptionHandler
    @ResponseBody
    ResponseEntity handle(Exception exception) {
        HttpStatus responseStatus = resolveAnnotatedResponseStatus(exception);
        return new ResponseEntity<>(exception.getLocalizedMessage(), responseStatus);
    }
}
