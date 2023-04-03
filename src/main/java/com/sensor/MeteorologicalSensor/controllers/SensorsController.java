package com.sensor.MeteorologicalSensor.controllers;

import com.sensor.MeteorologicalSensor.dto.SensorDTO;
import com.sensor.MeteorologicalSensor.services.SensorService;
import com.sensor.MeteorologicalSensor.exceptions.SensorNotCreatedException;
import com.sensor.MeteorologicalSensor.util.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ExceptionsController exceptionsController;

    @Autowired
    public SensorsController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator, ExceptionsController exceptionsController) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.exceptionsController = exceptionsController;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> regSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new SensorNotCreatedException(exceptionsController.configureMessage(bindingResult));
        }
        sensorService.save(sensorDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
