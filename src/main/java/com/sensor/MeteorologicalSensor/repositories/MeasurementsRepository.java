package com.sensor.MeteorologicalSensor.repositories;

import com.sensor.MeteorologicalSensor.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    int countByRainingIsTrue();
}
