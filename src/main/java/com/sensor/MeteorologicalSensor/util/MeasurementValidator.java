package com.sensor.MeteorologicalSensor.util;

import com.sensor.MeteorologicalSensor.dto.MeasurementDTO;
import com.sensor.MeteorologicalSensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if (sensorService.findByName(measurementDTO.getSensor()).isEmpty()) {
            errors.rejectValue("name", "", "Sensor is not registered");
        }
    }
}
