package com.sensor.MeteorologicalSensor.util;

import com.sensor.MeteorologicalSensor.dto.SensorDTO;
import com.sensor.MeteorologicalSensor.models.Sensor;
import com.sensor.MeteorologicalSensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if (sensorService.findByName(sensorDTO).isPresent()) {
            errors.rejectValue("name", "", "Sensor's name are already registered");
        }
    }
}
