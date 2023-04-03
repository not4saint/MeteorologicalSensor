package com.sensor.MeteorologicalSensor.services;

import com.sensor.MeteorologicalSensor.dto.SensorDTO;
import com.sensor.MeteorologicalSensor.models.Sensor;
import com.sensor.MeteorologicalSensor.repositories.SensorsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorsRepository sensorsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorService(SensorsRepository sensorsRepository, ModelMapper modelMapper) {
        this.sensorsRepository = sensorsRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void save(SensorDTO sensorDTO) {
        sensorsRepository.save(convertToSensor(sensorDTO));
    }


    public Optional<Sensor> findByName(SensorDTO sensorDTO) {
        return sensorsRepository.findByName(sensorDTO.getName());
    }

    Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
