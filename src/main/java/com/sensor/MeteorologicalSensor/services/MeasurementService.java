package com.sensor.MeteorologicalSensor.services;

import com.sensor.MeteorologicalSensor.dto.MeasurementDTO;
import com.sensor.MeteorologicalSensor.models.Measurement;
import com.sensor.MeteorologicalSensor.models.Sensor;
import com.sensor.MeteorologicalSensor.repositories.MeasurementsRepository;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementsRepository measurementsRepository;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementService(MeasurementsRepository measurementsRepository, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementsRepository = measurementsRepository;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    public List<MeasurementDTO> findAll() {
        return measurementsRepository.findAll().stream().map(this::convertToMeasurementDTO).toList();
    }

    @Transactional
    public void save(MeasurementDTO measurementDTO) {
        Measurement measurement = convertToMeasurement(measurementDTO);
        measurement.setSensor(sensorService.findByName(measurementDTO.getSensor()).get());
        measurement.setTimestamp(LocalDateTime.now());
        measurementsRepository.save(measurement);
    }

    public int findCountRainyDays() {
        return measurementsRepository.countByRainingIsTrue();
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
