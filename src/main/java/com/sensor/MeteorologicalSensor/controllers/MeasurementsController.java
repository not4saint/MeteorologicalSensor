package com.sensor.MeteorologicalSensor.controllers;

import com.sensor.MeteorologicalSensor.dto.MeasurementDTO;
import com.sensor.MeteorologicalSensor.services.MeasurementService;
import com.sensor.MeteorologicalSensor.exceptions.MeasurementNotAddingException;
import com.sensor.MeteorologicalSensor.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ExceptionsController exceptionsController;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, MeasurementValidator measurementValidator, ExceptionsController exceptionsController) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.exceptionsController = exceptionsController;
    }

    @GetMapping
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new MeasurementNotAddingException(exceptionsController.configureMessage(bindingResult));
        }
        measurementService.save(measurementDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public int countRainyDays() {
        return measurementService.findCountRainyDays();
    }
}
