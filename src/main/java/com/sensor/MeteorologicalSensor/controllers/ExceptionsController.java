package com.sensor.MeteorologicalSensor.controllers;

import com.sensor.MeteorologicalSensor.exceptions.MeasurementNotAddingException;
import com.sensor.MeteorologicalSensor.exceptions.SensorErrorResponse;
import com.sensor.MeteorologicalSensor.exceptions.SensorNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionsController {

    public String configureMessage(BindingResult bindingResult) {
        StringBuilder str = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError fieldError : errors) {
            str.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage()).append(";");
        }
        return str.toString();
    }

    @ExceptionHandler({SensorNotCreatedException.class, MeasurementNotAddingException.class})
    public ResponseEntity<SensorErrorResponse> handleCreatedException(RuntimeException e) {
        SensorErrorResponse error = new SensorErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
